package be.vinci.pae.usecases;

import be.vinci.pae.domain.FurnitureDAO;
import be.vinci.pae.domain.FurnitureDTO;
import be.vinci.pae.domain.SellingDAO;
import be.vinci.pae.domain.SellingDTO;
import be.vinci.pae.domain.UserDAO;
import be.vinci.pae.domain.UserDTO;
import be.vinci.pae.exceptions.DataBaseException;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalTransactions;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SellingUCCImpl implements SellingUCC {

  @Inject
  private SellingDAO sellingDAO;

  @Inject
  private FurnitureDAO furnitureDAO;

  @Inject
  private UserDAO userDao;

  @Inject
  private FurnitureUCC furnitureUCC;

  @Inject
  private DalTransactions dal;

  @Override
  public boolean confirmSelling(SellingDTO sellingDTO) {
    try {
      dal.startTransaction();
      FurnitureDTO furnitureDTO = furnitureDAO.findByID(sellingDTO.getIdFurniture());
      if (furnitureDTO == null) {
        dal.commitTransaction();
        return false;
      }
      UserDTO userDTO = userDao.findByID(sellingDTO.getIdUser());
      if (sellingDTO.getIdUser() >= 0 && userDTO == null) {
        dal.commitTransaction();
        return false;
      }
      if (sellingDTO.getSpecialSalePrice() > 0 && !userDTO.getRole().equals("antiquaire")) {
        dal.commitTransaction();
        return false;
      }
      if (sellingDTO.getSpecialSalePrice() < 0 && !furnitureDTO.getStateFurniture()
          .equals("vente")) {
        dal.commitTransaction();
        return false;
      }
      if (sellingDAO.insert(sellingDTO)) {
        dal.commitTransaction();
        System.out.println("sale confirmed");
        furnitureDTO.setSpecialSalePrice(sellingDTO.getSpecialSalePrice());
        return furnitureUCC.confirmSelling(furnitureDTO);
      }
      dal.rollBackTransaction();
      System.out.println("sale failed");
      return false;

    } catch (Exception e) {
      rollBackError();
      throw new FatalException(e.getMessage(), e);
    }

  }

  @Override

  public List<SellingDTO> seeAll() {
    List<SellingDTO> list = new ArrayList<SellingDTO>();

    try {
      dal.startTransaction();
      list = sellingDAO.findAll();
      dal.commitTransaction();
    } catch (Exception e) {
      rollBackError();
      throw new DataBaseException(e.getMessage(), e);
    }

    return list;
  }

  /**
   * {@inheritDoc}This method is used to roll back the database if an exception was caught. It also
   * frees the connection and release the thread.
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
