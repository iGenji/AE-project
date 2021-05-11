package be.vinci.pae.api;

import java.util.List;
import be.vinci.pae.domain.PhotoDTO;
import be.vinci.pae.usecases.PhotoUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Singleton
@Path("/photo")
public class PhotoRessource {

  @Inject
  private PhotoUCC uccService;

  /**
   * {@inheritDoc} Show all furniture's photos in a list
   * 
   * @return a list of photoDTO
   */
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  // @Authorize
  public List<PhotoDTO> getAllPhoto(@PathParam("id") int id) {
    return uccService.getAllPhotosOfAFurniture(id);

  }
}
