package be.vinci.pae.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Timestamp;
import java.util.Date;

@JsonDeserialize(as = FurnitureImpl.class)
public interface FurnitureDTO {

  /**
   * {@inheritDoc} This method returns the furniture ID of the furniture DTO.
   *
   * @return int ID of the furniture.
   */
  int getIdFurniture();

  /**
   * {@inheritDoc} This method set the furniture ID of the furniture DTO.
   *
   * @param idFurniture - int ID of the furniture.
   */
  void setIdFurniture(int idFurniture);

  /**
   * {@inheritDoc} This method returns the state of the furniture DTO.
   *
   * @return String state of the furniture.
   */
  String getStateFurniture();

  /**
   * {@inheritDoc} This method set the furniture state of the furniture DTO.
   *
   * @param stateFurniture - String state of the furniture.
   */
  void setStateFurniture(String stateFurniture);


  /**
   * {@inheritDoc} This method returns the type of furniture's id of the furniture DTO.
   *
   * @return int type of furniture's id.
   */
  int getTypeFurniture();

  /**
   * {@inheritDoc} This method set the type of furniture's id of the furniture DTO.
   *
   * @param typeFurniture - int the type of furniture's id.
   */
  void setTypeFurniture(int typeFurniture);


  /**
   * {@inheritDoc} This method returns the description of the furniture DTO.
   *
   * @return String the description of the furniture DTO.
   */
  String getDescription();

  /**
   * {@inheritDoc} This method set the description of the furniture DTO.
   *
   * @param description - String the description of the furniture DTO.
   */
  void setDescription(String description);


  /**
   * {@inheritDoc} This method returns the purchase price of the furniture DTO.
   *
   * @return double the purchase price of the furniture DTO.
   */
  double getPurchasePrice();

  /**
   * {@inheritDoc} This method set the purchase price of the furniture DTO.
   *
   * @param purchasePrice - double the purchase price of the furniture DTO.
   */
  void setPurchasePrice(double purchasePrice);


  /**
   * {@inheritDoc} This method returns the selling price of the furniture DTO.
   *
   * @return double the selling price of the furniture DTO.
   */
  double getSellingPrice();

  /**
   * {@inheritDoc} This method set the selling price of the furniture DTO.
   *
   * @param sellingPrice - double the selling price of the furniture DTO.
   */
  void setSellingPrice(double sellingPrice);


  /**
   * {@inheritDoc} This method returns the special sale price of the furniture DTO.
   *
   * @return double the special sale price of the furniture DTO.
   */
  double getSpecialSalePrice();

  /**
   * {@inheritDoc} This method set the special sale price of the furniture DTO.
   *
   * @param specialSalePrice - double the special sale price of the furniture DTO.
   */
  void setSpecialSalePrice(double specialSalePrice);


  /**
   * {@inheritDoc} This method returns the favorite photo id of the furniture DTO.
   *
   * @return int the favorite photo id of the furniture DTO.
   */
  int getFavouritePhoto();

  /**
   * {@inheritDoc} This method set the favorite photo id of the furniture DTO.
   *
   * @param favouritePhoto - int the favorite photo id of the furniture DTO.
   */
  void setFavouritePhoto(int favouritePhoto);


  /**
   * {@inheritDoc} This method returns the visit id of the furniture DTO.
   *
   * @return int the visit id of the furniture DTO.
   */
  int getVisit();

  /**
   * {@inheritDoc} This method set the visit id of the furniture DTO.
   *
   * @param visit - int the visit id of the furniture DTO.
   */
  void setVisit(int visit);


  /**
   * {@inheritDoc} This method returns the boss's furniture collection date of the furniture DTO.
   *
   * @return Timestamp the boss's furniture collection date of the furniture DTO.
   */
  Date getFurnitureCollectionDateBoss();

  /**
   * {@inheritDoc} This method set the boss's furniture collection date of the furniture DTO.
   *
   * @param furnitureCollectionDateBoss - Timestamp
   */
  void setFurnitureCollectionDateBoss(Timestamp furnitureCollectionDateBoss);


  /**
   * {@inheritDoc} This method returns the deposit date of the furniture DTO.
   *
   * @return Timestamp the deposit date of the furniture DTO.
   */
  Date getDepositDate();

  /**
   * {@inheritDoc} This method set the deposit date of the furniture DTO.
   *
   * @param depositDate - Timestamp the deposit date of the furniture DTO.
   */
  void setDepositDate(Timestamp depositDate);
  
  /**
   * {@inheritDoc} This method returns the string of the type.
   *
   * @return String of the type
   */
  String getTypeString();

  /**
   * {@inheritDoc} This method sets the string type
   *
   * @param typeString - String
   */
  void setTypeString(String typeString);
}
