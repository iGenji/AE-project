package be.vinci.pae.usecases;

import java.util.List;
import be.vinci.pae.domain.PhotoDTO;

public interface PhotoUCC {
  /**
   * {@inheritDoc} This method returns furniture's photo in a list of a furniture
   *
   * @param idFurniture - Integer , the furniture's id.
   * @return List of the furniture's photo of a furniture
   */
  List<PhotoDTO> getAllPhotosOfAFurniture(int idFurniture);
  
  
  /**
   * {@inheritDoc} This method add photo to the database
   * @param name - name of the picture
   * @param idMeuble - id of the furniture
   * @return boolen - true if succeed
   */
  boolean addPhoto(String name,int idMeuble);
}
