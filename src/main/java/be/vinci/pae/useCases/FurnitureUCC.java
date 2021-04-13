package be.vinci.pae.useCases;

import java.sql.SQLException;
import java.util.List;
import be.vinci.pae.domain.FurnitureDTO;

public interface FurnitureUCC {
  /**
   * This method is the use case method to propose a furniture to sell.
   *
   * @param id_meuble - int, id of the furniture.
   * @param prix_vente - double , selling price of the furniture.
   * @param prix_special - double , special price of the furniture.
   * @return furnitureDTO with full fields. {@inheritDoc}
   * @throws SQLException
   */
  FurnitureDTO proposedToSell(int id_meuble, double prix_vente, double prix_special);

  /**
   * {@inheritDoc} This method returns all the furnitures in a list
   * 
   * @return List of the furnitures
   */
  List<FurnitureDTO> seeAll();
}
