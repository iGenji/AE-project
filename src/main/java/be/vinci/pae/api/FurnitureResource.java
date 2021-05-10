package be.vinci.pae.api;

import be.vinci.pae.api.filters.Authorize;
import be.vinci.pae.api.utils.Json;
import be.vinci.pae.domain.FurnitureDAO;
import be.vinci.pae.domain.FurnitureDTO;
import be.vinci.pae.usecases.FurnitureUCC;
import be.vinci.pae.utils.MethodDuplicated;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/furnitures")
public class FurnitureResource {

  private final ObjectMapper jsonMapper = new ObjectMapper();

  @Inject
  private FurnitureUCC uccService;
  
  @Inject
  private FurnitureDAO dataService;

  /*
   * @Inject private FurnitureFactory furnitureFactory;
   */

  /**
   * {@inheritDoc} This method indicates the purchase of a furniture
   *
   * @param furnitureDTO - FurnitureDTO fulfilled by the frontend
   * @return furnitureDTO Object
   */
  @POST
  @Path("purchasedSubmitted")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response purchaseSubmitted(FurnitureDTO furnitureDTO) {

    // check if furniture exists
    boolean furnitureDTOconfirm = this.uccService.confirmPurchase(furnitureDTO);
    if (!furnitureDTOconfirm) {
      return MethodDuplicated.statusConflict("This furniture does not exist");
    }
    ObjectNode node = jsonMapper.createObjectNode().put("success", true);
    // Build response
    return Response.ok(node, MediaType.APPLICATION_JSON).build();
  }

  /**
   * {@inheritDoc} This method update the prices of a furniture
   *
   * @param json - Json file non empty
   * @return FurnitureDTO Object
   */
  @POST
  @Path("priceSubmitted")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public FurnitureDTO priceSubmitted(JsonNode json) {
    // Get and check credentials
    checkJson("id_meuble", json);
    checkJson("prix_vente", json);
    checkJson("prix_special", json);

    int idMeuble = json.get("id_meuble").asInt();
    double prixVente = json.get("prix_vente").asDouble();
    double prixSpecial = json.get("prix_special").asDouble();

    return uccService.proposedToSell(idMeuble, prixVente, prixSpecial);
  }

  /**
   * {@inheritDoc} This method indicates the sale of a furniture
   *
   * @param json - Json file non empty
   * @return FurnitureDTO Object
   */
  @POST
  @Path("soldSubmitted")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public FurnitureDTO soldSubmitted(JsonNode json) {
    // Get and check credentials
    checkJson("idFurniture", json);
    int idMeuble = json.get("idFurniture").asInt();

    return uccService.confirmSelling(idMeuble);
  }

  /**
   * {@inheritDoc} This method indicate that a furniture is sent to the workshop
   *
   * @param json - Json file non empty
   * @return FurnitureDTO Object
   */
  @POST
  @Path("toWorkshop")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public FurnitureDTO toWorkshop(JsonNode json) {
    // Get and check credentials
    checkJson("idFurniture", json);
    int idMeuble = json.get("idFurniture").asInt();

    return uccService.toWorkshop(idMeuble);
  }

  /**
   * {@inheritDoc} This method is to complete
   *
   * @param furnitureDTO - FurnitureDTO fulfilled by the frontend
   * @return furnitureDTO Object
   */
  @POST
  @Path("deposit")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deposit(FurnitureDTO furnitureDTO) {

    // check if furniture exists
    boolean furnitureDTOconfirm = this.uccService.confirmDeposit(furnitureDTO);
    if (!furnitureDTOconfirm) {
      return Response.status(Status.CONFLICT).entity("This furniture does not exist")
          .type(MediaType.TEXT_PLAIN).build();
    }
    ObjectNode node = jsonMapper.createObjectNode().put("success", true);
    // Build response
    return Response.ok(node, MediaType.APPLICATION_JSON).build();
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
    if (id == 0)
      throw new WebApplicationException("Lacks of mandatory info", null, Status.BAD_REQUEST);
   
      FurnitureDTO currentFurniture = dataService.findByID(id);
      if (currentFurniture == null) {
        throw new WebApplicationException("Ressource with id = " + id + " could not be found", null,
                Status.NOT_FOUND);
      }     
    return Json.filterPublicJsonView(currentFurniture, FurnitureDTO.class);
  }
  

  /**
   * {@inheritDoc} This method checks if this field contained in the Json object is empty.
   *
   * @param field - String , field's name of a user.
   * @param json - Json , json that contains every fields.
   * @return Response Status.ACCEPTED if the field is not empty, if not, run an Response Status.UNAUTHORIZED.
   */
  private Response checkJson(String field, JsonNode json) {
    if (!json.hasNonNull(field)) {
      return Response.status(Status.UNAUTHORIZED).entity(field + " needed")
          .type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Status.ACCEPTED).build();
  }
}


