package be.vinci.pae.api;

import be.vinci.pae.domain.SellingDTO;
import be.vinci.pae.usecases.SellingUCC;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Singleton
@Path("/sales")
public class SellingResource {

  private final ObjectMapper jsonMapper = new ObjectMapper();

  @Inject
  private SellingUCC uccService;

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

    // check if furniture and user exist if necessary
    boolean sellingDTOconfirm = this.uccService.confirmSelling(sellingDTO);
    if (!sellingDTOconfirm) {
      return Response.status(Status.CONFLICT)
          .entity(
              "This furniture or user does not exist, or the furniture is not in the sale state,"
                  + "or a special price was specified for a non-antique dealer.")
          .type(MediaType.TEXT_PLAIN).build();
    }
    ObjectNode node = jsonMapper.createObjectNode().put("success", true);
    // Build response
    return Response.ok(node, MediaType.APPLICATION_JSON).build();
  }

}
