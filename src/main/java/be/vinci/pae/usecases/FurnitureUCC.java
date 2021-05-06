package be.vinci.pae.usecases;

import java.util.List;
import be.vinci.pae.domain.FurnitureDTO;

public interface FurnitureUCC {

  /**
   * {@inheritDoc}This method is part of the use case method to confirm a selling.
   *
   * @param idMeuble - int, id of the furniture.
   * @return furnitureDTO with full fields.
   */
  FurnitureDTO confirmSelling(int idMeuble);

  /**
   * {@inheritDoc}This method is the use case method to propose a furniture to sell.
   *
   * @param idMeuble    - int, id of the furniture.
   * @param prixVente   - double , selling price of the furniture.
   * @param prixSpecial - double , special price of the furniture.
   * @return furnitureDTO with full fields.
   */
  FurnitureDTO proposedToSell(int idMeuble, double prixVente, double prixSpecial);

  /**
   * {@inheritDoc} This method returns all the furniture in a list
   *
   * @return List of the furniture
   */
  List<FurnitureDTO> seeAll();

  /**
   * {@inheritDoc}This method is used to get a furniture dto object by its id
   *
   * @param id - integer
   * @return Furniture DTO object if found or null
   */
  FurnitureDTO getFurnitureById(int id);

  /**
   * {@inheritDoc}This method is the use case method to indicate the purchase of a furniture.
   *
   * @param furnitureDTO - that has most of it fields full, fulfilled by the front-end.
   * @return return false if failed, true if succeed.
   */
  boolean confirmPurchase(FurnitureDTO furnitureDTO);

  /**
   * {@inheritDoc}This method is the use case method to send a furniture to the workshop.
   *
   * @param idMeuble - int, id of the furniture.
   * @return furnitureDTO with full fields.
   */
  FurnitureDTO toWorkshop(int idMeuble);

  /**
   * {@inheritDoc}This method is the use case method to indicate the deposit of a furniture.
   *
   * @param furnitureDTO - that has most of it fields full, fulfilled by the front-end.
   * @return return false if failed, true if succeed.
   */
  boolean confirmDeposit(FurnitureDTO furnitureDTO);
  
  
}
