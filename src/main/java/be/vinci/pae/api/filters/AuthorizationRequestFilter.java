package be.vinci.pae.api.filters;

import java.io.IOException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import be.vinci.pae.domain.UserDAO;
import be.vinci.pae.utils.Config;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Singleton
@Provider
@Authorize
public class AuthorizationRequestFilter implements ContainerRequestFilter {

  private final Algorithm jwtAlgorithm = Algorithm.HMAC256(Config.getProperty("JWTSecret"));
  private final JWTVerifier jwtVerifier =
      JWT.require(this.jwtAlgorithm).withIssuer("auth0").build();

  @Inject
  private UserDAO dataService;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    String token = requestContext.getHeaderString("Authorization");
    if (token == null) {
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
          .entity("A token is needed to access this resource").build());
    } else {
      DecodedJWT decodedToken = null;
      try {
        decodedToken = this.jwtVerifier.verify(token);
      } catch (TokenExpiredException e) {
        throw new WebApplicationException("Expired token", e, Status.UNAUTHORIZED);
      } catch (Exception e) {
        throw new WebApplicationException("Malformed token", e, Status.UNAUTHORIZED);
      }
      requestContext.setProperty("user",
          this.dataService.findByID(decodedToken.getClaim("user").asInt()));
    }
  }

}
