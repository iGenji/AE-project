package be.vinci.pae.api;



import java.util.List;
import be.vinci.pae.api.filters.Authorize;
import be.vinci.pae.api.utils.Json;
import be.vinci.pae.domain.FurnitureDAO;
import be.vinci.pae.domain.FurnitureDTO;
import be.vinci.pae.usecases.FurnitureUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/")
public class HomeRessource {

  @Inject
  private FurnitureDAO dataService;

  @Inject
  private FurnitureUCC uccService;


  /**
   * {@inheritDoc} Show all furniture in a list
   * 
   * @return a list of FurnitureDTO
   */
  @POST
  @Path("furnitureList")
  @Produces(MediaType.APPLICATION_JSON)
  // @Authorize
  public List<FurnitureDTO> getAllFurniture() {
    return uccService.seeAll();

  }

  /**
   * {@inheritDoc} This method gives the accessible attributes of a furniture
   *
   * @param id - Integer
   * @return FurnitureDTO Object
   */
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Authorize
  public FurnitureDTO getFurniture(@PathParam("id") int id) {
    if (id == 0) {
      throw new WebApplicationException("Lacks of mandatory info", null, Status.BAD_REQUEST);
    }
    // get a FurnitureDTO object by a furniture's id
    FurnitureDTO currentFurniture = dataService.findByID(id);
    if (currentFurniture == null) {
      throw new WebApplicationException("Ressource with id = " + id + " could not be found", null,
          Status.NOT_FOUND);
    }

    return Json.filterPublicJsonView(currentFurniture, FurnitureDTO.class);
  }

  

}
