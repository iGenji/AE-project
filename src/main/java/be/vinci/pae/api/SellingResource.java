package be.vinci.pae.api;

import be.vinci.pae.domain.SellingDTO;
import be.vinci.pae.domain.SellingFactory;
import be.vinci.pae.usecases.SellingUCC;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.fasterxml.jackson.databind.JsonNode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Singleton
@Path("/sales")
public class SellingResource {

  private final ObjectMapper jsonMapper = new ObjectMapper();

  @Inject
  private SellingUCC uccService;

  @Inject
  private SellingFactory sellingFactory;

  /**
   * {@inheritDoc} This method is to complete
   *
   * @param sellingDTO - SellingDTO fulfilled by the frontend
   * @return SellingDTO Object
   */
  @POST
  @Path("soldSubmitted")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response soldSubmitted(SellingDTO sellingDTO) {
    /*
    checkJson("id_meuble", json);
    checkJson("id_client", json);
    checkJson("date_vente", json);

    int idMeuble = json.get("id_meuble").asInt();
    int idClient = json.get("id_client").asInt();
    String dateVente = json.get("date_vente").asText();
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateVente);
    SellingDTO sellingDTO = sellingFactory.getInstance();
    sellingDTO.setIdFurniture(idMeuble);
    sellingDTO.setIdUser(idClient);
    sellingDTO.setSoldDate(date);
     */

    // check if furniture and user exist if necessary
    boolean sellingDTOconfirm = this.uccService.confirmSelling(sellingDTO);
    if (!sellingDTOconfirm) {
      return Response.status(Status.CONFLICT).entity("This furniture or user does not exist")
          .type(MediaType.TEXT_PLAIN).build();
    }
    ObjectNode node = jsonMapper.createObjectNode().put("success", true);
    // Build response
    return Response.ok(node, MediaType.APPLICATION_JSON).build();
  }

  /**
   * {@inheritDoc} This method checks if this field contained in the Json object is empty.
   *
   * @param field - String , field's name of a user.
   * @return Response Status.ACCEPTED if the field is not empty, if not, run an Response
   * Status.UNAUTHORIZED.
   */
  private Response checkJson(String field, JsonNode json) {
    if (!json.hasNonNull(field)) {
      return Response.status(Status.UNAUTHORIZED).entity(field + " needed")
          .type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Status.ACCEPTED).build();
  }

}
