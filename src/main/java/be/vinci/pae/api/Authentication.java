package be.vinci.pae.api;


import java.time.LocalDateTime;
import java.util.Date;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import be.vinci.pae.api.utils.Json;
import be.vinci.pae.domain.User;
import be.vinci.pae.domain.UserDTO;
import be.vinci.pae.usecases.UserUCC;
import be.vinci.pae.utils.Config;
import be.vinci.pae.utils.MethodDuplicated;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/auths")
public class Authentication {

  private final Algorithm jwtAlgorithm = Algorithm.HMAC256(Config.getProperty("JWTSecret"));
  private final ObjectMapper jsonMapper = new ObjectMapper();
  public final long expirationTime = (long) Config.getIntProperty("expirationTime");
  @Inject
  private UserUCC uccService;



  /**
   * {@inheritDoc} This method log in the user
   * 
   * @param json - JsonNode which contains the pseudo
   * @return a response.ok saying that the login method worked
   */
  @POST
  @Path("login")
  @Consumes(MediaType.APPLICATION_JSON)

  public Response login(JsonNode json) {
    // Get and check credentials
    checkJson("username", json);
    checkJson("password", json);
    checkJson("rememberMe", json);

    String username = json.get("username").asText();
    String password = json.get("password").asText();
    String checked = json.get("rememberMe").asText();

    // Try to login
    UserDTO userDTO = uccService.login(username, password);
    if (userDTO == null) {
      return Response.status(Status.UNAUTHORIZED).entity("username or password incorrect")
          .type(MediaType.TEXT_PLAIN).build();
    }
    User user = (User) userDTO;
    // Create token
    String token;
    try {
      token = JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
          .withIssuer("auth0").withClaim("user", userDTO.getIdUser()).sign(this.jwtAlgorithm);
    } catch (Exception e) {
      throw new WebApplicationException("Unable to create token", e, Status.INTERNAL_SERVER_ERROR);
    }
    // Build response

    // load the user data from a public JSON view to filter out the private info not
    // to be returned by the API (such as password)
    User publicUser = Json.filterPublicJsonView(user, User.class);
    ObjectNode node = jsonMapper.createObjectNode().put("token", token).putPOJO("user", publicUser)
        .put("checked", checked);
    return Response.ok(node, MediaType.APPLICATION_JSON).build();

  }

  /**
   * This method register a user to the database.
   * 
   * @param userDTO - UserDTO file non empty
   * @return response
   */
  @POST
  @Path("register")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response register(UserDTO userDTO) {

    // peut check dans le frontend si buildingNumber = 0 pour opti
    // check if required fields is null or empty
    if (userDTO == null || userDTO.getUsername() == null || userDTO.getUsername().isEmpty()
        || userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()
        || userDTO.getLastName() == null || userDTO.getLastName().isEmpty()
        || userDTO.getEmail() == null || userDTO.getEmail().isEmpty()
        || userDTO.getPassword() == null || userDTO.getPassword().isEmpty()
        || userDTO.getAddressObject().getStreet() == null
        || userDTO.getAddressObject().getStreet().isEmpty()
        || userDTO.getAddressObject().getBuildingNumber() == 0
        || userDTO.getAddressObject().getPostcode() == 0
        || userDTO.getAddressObject().getCommune() == null
        || userDTO.getAddressObject().getCommune().isEmpty()
        || userDTO.getAddressObject().getCountry() == null
        || userDTO.getAddressObject().getCountry().isEmpty()) {
      return Response.status(Status.UNAUTHORIZED).entity("Please fill in the required fields")
          .type(MediaType.TEXT_PLAIN).build();
    }

    // puting role
    userDTO.setRole("inactif");
    LocalDateTime now = LocalDateTime.now();
    userDTO.setRegistrationDate(now);

    // check if user exists
    boolean result = this.uccService.register(userDTO);
    if (!result) {
      return MethodDuplicated.statusConflict("This username is already in use");
    }
    ObjectNode node = jsonMapper.createObjectNode().put("success", true);
    // Build response
    return Response.ok(node, MediaType.APPLICATION_JSON).build();
  }


  /**
   * {@inheritDoc}checks if this field contained in the Json object is empty.
   * 
   * @param field - String , field's name of a user.
   * 
   * @return Response Status.ACCEPTED
   */
  private Response checkJson(String field, JsonNode json) {
    if (!json.hasNonNull(field)) {
      return Response.status(Status.UNAUTHORIZED).entity(field + " needed")
          .type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Status.ACCEPTED).build();
  }


}
