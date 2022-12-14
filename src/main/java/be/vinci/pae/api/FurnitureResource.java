package be.vinci.pae.api;

import be.vinci.pae.domain.FurnitureDTO;
import be.vinci.pae.usecases.FurnitureUCC;
import be.vinci.pae.usecases.PhotoUCC;
import be.vinci.pae.utils.MethodDuplicated;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

  private final ObjectMapper jsonMapper = new ObjectMapper();

  @Inject
  private FurnitureUCC uccService;
  @Inject
  private PhotoUCC photoUcc;

  private String pathImage = "D:\\Utilisateurs\\pboyc\\Desktop\\imagesPAE\\ImageDemoFinale\\";
  private int idMeuble;
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

    int idMeuble = json.get("id_meuble").asInt();
    double prixVente = json.get("prix_vente").asDouble();

    return uccService.proposedToSell(idMeuble, prixVente);
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
   * {@inheritDoc} This method update the description
   *
   * @param furnitureDTO - FurnitureDTO fulfilled by the frontend
   * @return furnitureDTO Object
   */
  @POST
  @Path("description")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateDescription(FurnitureDTO furnitureDTO) {

    // check if furniture exists
    boolean furnitureDTOconfirm = this.uccService.updateDescription(furnitureDTO);
    if (!furnitureDTOconfirm) {
      return Response.status(Status.CONFLICT).entity("This furniture does not exist")
          .type(MediaType.TEXT_PLAIN).build();
    }
    ObjectNode node = jsonMapper.createObjectNode().put("success", true);
    idMeuble = furnitureDTO.getIdFurniture();
    // Build response
    return Response.ok(node, MediaType.APPLICATION_JSON).build();
  }

  /**
   * {@inheritDoc} This method checks if this field contained in the Json object is empty.
   *
   * @param field - String , field's name of a user.
   * @param json - Json , json that contains every fields.
   * @return Response Status.ACCEPTED if the field is not empty, if not,
   * run a Response Status.UNAUTHORIZED.
   */
  private Response checkJson(String field, JsonNode json) {
    if (!json.hasNonNull(field)) {
      return Response.status(Status.UNAUTHORIZED).entity(field + " needed")
          .type(MediaType.TEXT_PLAIN).build();
    }
    return Response.status(Status.ACCEPTED).build();
  }
  
  /**
   * {@inheritDoc} Upload the file into a path
   * 
   * @param stream - InputStream
   * @param fileInfo - FormDataContentDisposition
   */
  @POST
  @Path("/uploadImage")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  // @Authorize
  public void uploadImage(@FormDataParam("image") InputStream stream,
      @FormDataParam("image") FormDataContentDisposition fileInfo) {

    String fileName = fileInfo.getFileName();
    System.out.println(fileName);
    // String extension = fileInfo.getFileName();

    System.out.println("uploadImage Called");
    try {
      int read = 0;
      FileOutputStream f = new FileOutputStream(pathImage + fileName);
      CountingOutputStream out = new CountingOutputStream(f);
      byte[] bytes = new byte[2048];
      while ((read = stream.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }
      photoUcc.addPhoto(fileName,idMeuble);
      out.flush();
      out.close();
      

    } catch (FileNotFoundException e) {

      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }
}


