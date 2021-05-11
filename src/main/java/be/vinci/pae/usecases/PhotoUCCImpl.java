package be.vinci.pae.usecases;

import java.util.ArrayList;
import java.util.List;
import be.vinci.pae.domain.PhotoDAO;
import be.vinci.pae.domain.PhotoDTO;
import be.vinci.pae.exceptions.DataBaseException;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalTransactions;
import jakarta.inject.Inject;

public class PhotoUCCImpl implements PhotoUCC {

  @Inject
  private PhotoDAO photoDAO;

  @Inject
  private DalTransactions dal;

  @Override
  public List<PhotoDTO> getAllPhotosOfAFurniture(int idFurniture) {
    List<PhotoDTO> list = new ArrayList<PhotoDTO>();

    try {
      dal.startTransaction();
      list = photoDAO.findByIDFurniture(idFurniture);
      dal.commitTransaction();
    } catch (Exception e) {
      rollBackError();
      throw new DataBaseException(e.getMessage(), e);
    }

    return list;
  }

  /**
   * {@inheritDoc}This method is used to roll back the database if an exception was caught.
   *  It also frees the connection and release the thread.
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
