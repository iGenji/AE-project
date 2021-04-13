package be.vinci.pae.useCases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import be.vinci.pae.domain.FurnitureDAO;
import be.vinci.pae.domain.FurnitureDTO;
import be.vinci.pae.exceptions.DataBaseException;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalTransactions;
import jakarta.inject.Inject;


// this class never create a furniture.
public class FurnitureUCCImpl implements FurnitureUCC {

  @Inject
  private FurnitureDAO furnitureDAO;

  @Inject
  private DalTransactions dal;

  @Override
  public FurnitureDTO proposedToSell(int id_meuble, double prix_vente, double prix_special) {
    FurnitureDTO furnitureDTO = null;
    try {
      dal.startTransaction();
      furnitureDTO = this.furnitureDAO.findByID(id_meuble);
      if (furnitureDTO == null) {
        dal.rollBackTransaction();
      } else {
        furnitureDTO.setSellingPrice(prix_vente);
        furnitureDTO.setSpecialSalePrice(prix_special);
        furnitureDTO.setStateFurniture("vente");
        furnitureDAO.update(furnitureDTO);
        dal.commitTransaction();
      }
    } catch (SQLException e) {
      throw new FatalException(e.getMessage(), e);
    }finally {
      rollBackError();
    }
    return furnitureDTO;
  }

  @Override
  public List<FurnitureDTO> seeAll() {
    List<FurnitureDTO> list = new ArrayList<FurnitureDTO>();

    try {
      dal.startTransaction();
      list = furnitureDAO.findAll();
      dal.commitTransaction();
    } catch (Exception e) {
      rollBackError();
      throw new DataBaseException(e.getMessage(), e);
    }


    return list;
  }

  /**
   * {@inheritDoc} This method is used to roll back the database if an exception was caught. It also frees the connection and release the thread
   */
  private void rollBackError() {
    try {
      dal.rollBackTransaction();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new FatalException(e.getMessage(), e);
    }


  }

}
