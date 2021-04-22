package be.vinci.pae.domain;

import java.time.LocalDateTime;

public interface SellingDTO {
  /**
   * {@inheritDoc} This method returns the selling ID of the selling DTO.
   *
   * @return int ID of the selling.
   */
  int getIdSelling();

  /**
   * {@inheritDoc} This method set the selling ID of the selling DTO.
   *
   * @param idSelling - int ID of the selling.
   */
  void setIdSelling(int idSelling);

  /**
   * {@inheritDoc} This method returns the furniture ID of the selling DTO.
   *
   * @return int furniture ID of the selling.
   */
  int getIdFurniture();

  /**
   * {@inheritDoc} This method set the furniture ID of the selling DTO.
   *
   * @param idFurniture - int furniture ID of the selling.
   */
  void setIdFurniture(int idFurniture);

  /**
   * {@inheritDoc} This method returns the user ID of the selling DTO.
   *
   * @return int user ID of the selling.
   */
  int getIdUser();

  /**
   * {@inheritDoc} This method set the user ID of the selling.
   *
   * @param idUser - int user ID of the selling.
   */
  void setIdUser(int idUser);

  /**
   * {@inheritDoc} This method returns the state of the selling DTO.
   *
   * @return String state of the selling.
   */
  String getStateSelling();

  /**
   * {@inheritDoc} This method set the state of the selling DTO.
   *
   * @param stateSelling - String state of the selling.
   */
  void setStateSelling(String stateSelling);

  /**
   * {@inheritDoc} This method returns the sold date of the selling DTO.
   *
   * @return LocalDateTime sold date of the address.
   */
  LocalDateTime getSoldDate();

  /**
   * {@inheritDoc} This method set the sold date of the selling DTO.
   *
   * @param soldDate - LocalDateTime sold date of the selling.
   */
  void setSoldDate(LocalDateTime soldDate);

  /**
   * {@inheritDoc} This method returns the user's takeaway date of the selling DTO.
   *
   * @return LocalDateTime user's takeaway date of the selling.
   */
  LocalDateTime getUserTakeawayDate();

  /**
   * {@inheritDoc} This method set the user's takeaway date of the selling DTO.
   *
   * @param userTakeawayDate - LocalDateTime user's takeaway date of the selling.
   */
  void setUserTakeawayDate(LocalDateTime userTakeawayDate);

  /**
   * {@inheritDoc} This method returns the delivery date of the selling DTO.
   *
   * @return LocalDateTime delivery date of the selling.
   */
  LocalDateTime getDeliveryDate();

  /**
   * {@inheritDoc} This method set the delivery date of the selling DTO.
   *
   * @param deliveryDate - LocalDateTime delivery date of the selling.
   */
  void setDeliveryDate(LocalDateTime deliveryDate);
}
