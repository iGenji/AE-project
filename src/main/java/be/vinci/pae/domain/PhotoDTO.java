package be.vinci.pae.domain;

public interface PhotoDTO {

  /**
   * {@inheritDoc} This method returns the photo ID of the photo DTO.
   * 
   * @return int ID of the photo.
   */
  public int getIdPhoto();

  /**
   * {@inheritDoc} This method set the photo ID of the photo DTO.
   * 
   * @param id_photo - int ID of the photo.
   */
  public void setIdphoto(int id_photo);

  /**
   * {@inheritDoc} This method returns the furniture ID of the photo DTO.
   * 
   * @return int the furniture ID of the photo DTO.
   */
  public int getFurniture();

  /**
   * {@inheritDoc} This method set the furniture ID of the photo DTO.
   * 
   * @param furniture - int the furniture ID of the photo DTO.
   */
  public void setFurniture(int furniture);

  /**
   * {@inheritDoc} This method returns the image's link of the photo DTO.
   * 
   * @return String the image's link of the photo DTO.
   */
  public String getPhoto();

  /**
   * {@inheritDoc} This method set the image's link of the photo DTO.
   * 
   * @param photo - String the image's link of the photo DTO.
   */
  public void setPhoto(String photo);

  /**
   * {@inheritDoc} This method returns 0 or 1 to indicate whether the image is visible or not of the photo DTO.
   * 
   * @return int 0 or 1, 0 for false and 1 for true, of the photo DTO.
   */
  public int getIsVisible();

  /**
   * {@inheritDoc} This method set the attribute "est_visible" to indicate whether the image is visible or not of the photo DTO.
   * 
   * @param is_visible - int 0 or 1, 0 for false and 1 for true, of the photo DTO.
   */
  public void setIsVisible(int is_visible);
}
