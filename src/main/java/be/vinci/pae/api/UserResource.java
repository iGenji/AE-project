package be.vinci.pae.api;

import org.glassfish.jersey.server.ContainerRequest;
import java.util.List;
import be.vinci.pae.api.filters.Authorize;
import be.vinci.pae.api.utils.Json;
import be.vinci.pae.domain.UserDTO;
import be.vinci.pae.domain.User;
import be.vinci.pae.domain.UserDAO;
import be.vinci.pae.domain.UserFactory;
import be.vinci.pae.usecases.UserUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/users")
public class UserResource {

  @Inject
  private UserDAO dataService;

  @Inject
  private UserUCC uccService;

  @Inject
  private UserFactory userFactory;

  /**
   * {@inheritDoc} This method initiates a user.
   * 
   * 
   * @return a User, an exception
   */
  @POST
  @Path("init")
  @Produces(MediaType.APPLICATION_JSON)
  public User init() {
    User user = (User) this.userFactory.getInstance();
    user.setIdUser(1);
    user.setUsername("james");
    user.setPassword(user.passwordEncryption("password"));
    this.dataService.insert(user);
    // load the user data from a public JSON view to filter out the private info not
    // to be returned by the API (such as password)
    return Json.filterPublicJsonView(user, User.class);
  }

  /**
   * {@inheritDoc} This method gives the accessible attributes of a user
   * 
   * @param request - ContainerRequest that contains the request.
   * @return a User with these attributes accessible, an exception
   */
  @GET
  @Path("me")
  @Produces(MediaType.APPLICATION_JSON)
  @Authorize
  public User getUser(@Context ContainerRequest request) {
    User currentUser = (User) request.getProperty("user");
    return Json.filterPublicJsonView(currentUser, User.class);
  }


  /**
   * {@inheritDoc} Show all users in list
   * 
   * @return a list of UserDTO
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  // @Authorize
  public List<UserDTO> getAllCustomers() {
    return uccService.allCustomers();

  }

  /**
   * {@inheritDoc} Show all users in list
   * 
   * @return a list of UserDTO
   */
  @GET
  @Path("/{pseudo}")
  @Produces(MediaType.APPLICATION_JSON)
  // @Authorize
  public UserDTO getCustomer(@PathParam("pseudo") String pseudo) {
    UserDTO toReturn = null;
    toReturn = uccService.getCustomer(pseudo);
    if (toReturn == null) {
      throw new WebApplicationException("User not found", null, Status.NOT_FOUND);
    }
    toReturn = Json.filterPublicJsonView(toReturn, UserDTO.class);
    
    return toReturn;

  }


}
