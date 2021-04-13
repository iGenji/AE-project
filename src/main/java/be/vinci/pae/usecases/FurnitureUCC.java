package be.vinci.pae.usecases;

import java.util.List;
import be.vinci.pae.domain.FurnitureDTO;

public interface FurnitureUCC {
  /**
   * {@inheritDoc}This method is the use case method to propose
   * a furniture to sell.
   *
   * @param id_meuble - int, id of the furniture.
   * @param prix_vente - double , selling price of the furniture.
   * @param prix_special - double , special price of the furniture.
   * @return furnitureDTO with full fields. {@inheritDoc}
   */
  FurnitureDTO proposedToSell(int idMeuble, double prixVente, double prixSpecial);

  /**
   * {@inheritDoc} This method returns all the furniture in a list
   * 
   * @return List of the furniture
   */
  List<FurnitureDTO> seeAll();
}
