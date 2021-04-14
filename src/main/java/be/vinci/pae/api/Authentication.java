package be.vinci.pae.api;


import java.time.LocalDateTime;
import java.util.Date;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import be.vinci.pae.api.utils.Json;
import be.vinci.pae.domain.AddressDTO;
import be.vinci.pae.domain.AddressFactory;
import be.vinci.pae.domain.User;
import be.vinci.pae.domain.UserDTO;
import be.vinci.pae.domain.UserFactory;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.usecases.UserUCC;
import be.vinci.pae.utils.Config;
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
  public final static long EXPIRATION_TIME = 1800 * 1000; // 30min (1 800 000 ms)
  @Inject
  private UserUCC uccService;

  @Inject
  private UserFactory userFactory;

  @Inject
  private AddressFactory addressFactory;



  /**
   * {@inheritDoc} This method log in the user
   * 
   * @param json - JsonNode which contains the pseudo and password
   *    entered by the user via the form
   * @return a response.ok saying that the login method worked.
   *    This response gives access to the user's id and token, gives an exception.
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
      token = JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
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
   * @param json - Json file non empty
   * @return response
   */
  @POST
  @Path("register")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response register(JsonNode json) {
    // Get and check credentials
    checkJson("username", json);
    checkJson("firstname", json);
    checkJson("lastname", json);
    checkJson("email", json);
    checkJson("password", json);
    checkJson("street", json);
    checkJson("building_number", json);
    checkJson("unit_number", json);
    checkJson("postcode", json);
    checkJson("commune", json);
    checkJson("country", json);
    UserDTO userDTO = userFactory.getInstance();
    AddressDTO addressDTO = addressFactory.getInstance();
    String username = json.get("username").asText();
    userDTO.setUsername(username);
    String firstname = json.get("firstname").asText();
    userDTO.setFirstName(firstname);
    String lastname = json.get("lastname").asText();
    userDTO.setLastName(lastname);
    String email = json.get("email").asText();
    userDTO.setEmail(email);
    String password = json.get("password").asText();
    userDTO.setPassword(password);
    String street = json.get("street").asText();
    addressDTO.setStreet(street);
    int buildingNumber = json.get("building_number").asInt();
    addressDTO.setBuildingNumber(buildingNumber);
    String unitNumber = json.get("unit_number").asText();
    addressDTO.setUnitNumber(unitNumber);
    int postcode = json.get("postcode").asInt();
    addressDTO.setPostcode(postcode);
    String commune = json.get("commune").asText();
    addressDTO.setCommune(commune);
    String country = json.get("country").asText();
    addressDTO.setCountry(country);

    // puting role
    userDTO.setRole("inactif");
    LocalDateTime now = LocalDateTime.now();
    userDTO.setRegistrationDate(now);

    // check if user exists
    boolean userDTORegister = this.uccService.register(userDTO, addressDTO);
    if (userDTORegister == false) {
      return Response.status(Status.CONFLICT).entity("This username is already in use")
          .type(MediaType.TEXT_PLAIN).build();
    }

    // Build response
    return Response.ok(MediaType.APPLICATION_JSON).build();

  }

  /**
   * {@inheritDoc} This method checks if this field contained in the Json object is empty.
   * 
   * @param field - String , field's name of a user.
   * 
   * @return Response Status.ACCEPTED if the field is not empty, if not,run an Response Status.UNAUTHORIZED.
   */
  private Response checkJson(String field, JsonNode json) {
    if (!json.hasNonNull(field)) {
      return Response.status(Status.UNAUTHORIZED).entity(field + " needed")
          .type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Status.ACCEPTED).build();
  }


}
