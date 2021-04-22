package be.vinci.pae.usecases;

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
  public FurnitureDTO proposedToSell(int idMeuble, double prixVente, double prixSpecial) {
    FurnitureDTO furnitureDTO = null;
    try {
      dal.startTransaction();
      furnitureDTO = this.furnitureDAO.findByID(idMeuble);
      if (furnitureDTO == null) {
        dal.rollBackTransaction();
      } else {
        furnitureDTO.setSellingPrice(prixVente);
        furnitureDTO.setSpecialSalePrice(prixSpecial);
        furnitureDTO.setStateFurniture("vente");
        furnitureDAO.update(furnitureDTO);
        dal.commitTransaction();
      }
    } catch (Exception e) {
      rollBackError();
      throw new FatalException(e.getMessage(), e);
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
  
  @Override
  public FurnitureDTO getFurnitureById(int id) {
    FurnitureDTO toReturn = null;
    try {
      dal.startTransaction();
      toReturn = furnitureDAO.findByID(id);
      dal.commitTransaction();
    }catch (Exception e) {
      rollBackError();
      throw new DataBaseException(e.getMessage());
    }
    return null;
  }

  /**
   * {@inheritDoc}This method
   * is used to roll back the
   * database if an exception was caught.
   * It also frees the connection and release
   * the thread.
   */
  private void rollBackError() {
    try {
      dal.rollBackTransaction();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FatalException(e.getMessage(), e);
    }


  }

  

}
