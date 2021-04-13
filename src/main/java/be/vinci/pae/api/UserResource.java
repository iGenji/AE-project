package be.vinci.pae.api;

import org.glassfish.jersey.server.ContainerRequest;

import be.vinci.pae.api.utils.Json;
import be.vinci.pae.domain.User;
import be.vinci.pae.domain.UserDAO;
import be.vinci.pae.domain.UserFactory;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Singleton
@Path("/users")
public class UserResource {

  @Inject
  private UserDAO dataService;

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
  public User getUser(@Context ContainerRequest request) {
    User currentUser = (User) request.getProperty("user");
    return Json.filterPublicJsonView(currentUser, User.class);
  }

}
