package be.vinci.pae.domain;

public interface PhotoFactory {

  /**
   * This method create an empty PhotoImpl and returns it.
   * 
   * @return empty PhotoDTO.
   */
  PhotoDTO getInstance();
}
