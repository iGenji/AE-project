
package be.vinci.pae.api;

import be.vinci.pae.domain.FurnitureDAO;
import be.vinci.pae.domain.FurnitureDTO;
import be.vinci.pae.usecases.FurnitureUCC;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/furnitures")
public class FurnitureResource {

  @Inject
  private FurnitureDAO dataService;

  @Inject
  private FurnitureUCC uccService;

  /*
   * @Inject private FurnitureFactory furnitureFactory;
   */

  /**
   * {@inheritDoc} This method is to complete
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

    FurnitureDTO toReturn = uccService.proposedToSell(idMeuble, prixVente, prixSpecial);
    return toReturn;
  }



  /**
   * {@inheritDoc} This method checks if this field contained in the Json object is empty.
   *
   * @param field - String , field's name of a user.
   *
   * @return Response Status.ACCEPTED if the field is not empty,
   * if not, run an Response Status.UNAUTHORIZED.
   */
  private Response checkJson(String field, JsonNode json) {
    if (!json.hasNonNull(field)) {
      return Response.status(Status.UNAUTHORIZED).entity(field + " needed")
          .type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Status.ACCEPTED).build();
  }
}


