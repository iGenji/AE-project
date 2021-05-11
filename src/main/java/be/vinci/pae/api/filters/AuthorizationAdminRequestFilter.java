package be.vinci.pae.api.filters;

import java.io.IOException;
import be.vinci.pae.domain.UserDTO;
import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Singleton
@Provider
@AuthorizeAdmin
public class AuthorizationAdminRequestFilter extends AuthorizationRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    super.filter(requestContext);
    UserDTO user = (UserDTO) requestContext.getProperty("user");
    if(!user.getRole().equals("admin")) {
      throw new WebApplicationException("Not an admin", Status.UNAUTHORIZED);
    }
    
  }

}
