package be.vinci.pae.domain;

import java.util.List;

public interface PhotoDAO {

  /**
   * {@inheritDoc} This method returns a photo DTO fulfilled from the database with the ID that was given.
   * 
   * @param photoID - int.
   * @return The photo or null if not found.
   */
  PhotoDTO findByID(int photoID);

  /**
   * {@inheritDoc} This method returns a list of all the photos that the database has with the ID of furniture that was given.
   * 
   * @param IDFurniture - int.
   * @return List of photos.
   */
  List<PhotoDTO> findByIDFurniture(int IDFurniture);


  /**
   * {@inheritDoc} This method insert into the database the PhotoDTO.
   * 
   * @param photo - PhotoDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean insert(PhotoDTO photo);

  /**
   * {@inheritDoc} This method delete the photo DTO in argument from the database.
   * 
   * @param photo - PhotoDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean delete(PhotoDTO photo);

  /**
   * {@inheritDoc} This method update the photo DTO in argument from the database.
   * 
   * @param photo - PhotoDTO.
   * @return the new photo DTO or null if the photo was not found.
   */
  AddressDTO update(PhotoDTO photo);

}
