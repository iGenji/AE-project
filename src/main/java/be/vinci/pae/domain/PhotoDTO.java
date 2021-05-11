package be.vinci.pae.domain;

public interface PhotoDTO {

  /**
   * {@inheritDoc} This method returns the photo ID of the photo DTO.
   * 
   * @return int ID of the photo.
   */
  int getIdPhoto();

  /**
   * {@inheritDoc} This method set the photo ID of the photo DTO.
   * 
   * @param idPhoto - int ID of the photo.
   */
  void setIdphoto(int idPhoto);

  /**
   * {@inheritDoc} This method returns the furniture ID of the photo DTO.
   * 
   * @return int the furniture ID of the photo DTO.
   */
  int getFurniture();

  /**
   * {@inheritDoc} This method set the furniture ID of the photo DTO.
   * 
   * @param furniture - int the furniture ID of the photo DTO.
   */
  void setFurniture(int furniture);

  /**
   * {@inheritDoc} This method returns the image's link of the photo DTO.
   * 
   * @return String the image's link of the photo DTO.
   */
  String getPhoto();

  /**
   * {@inheritDoc} This method set the image's link of the photo DTO.
   * 
   * @param photo - String the image's link of the photo DTO.
   */
  void setPhoto(String photo);

  /**
   * {@inheritDoc} This method returns 0 or 1 to indicate whether the image is visible or not of the photo DTO.
   * 
   * @return boolean false or true, of the photo DTO.
   */
  boolean getIsVisible();

  /**
   * {@inheritDoc} This method set the attribute "est_visible" to indicate whether the image is visible or not of the photo DTO.
   * 
   * @param b - boolean false or true, of the photo DTO.
   */
  void setIsVisible(boolean b);
}
