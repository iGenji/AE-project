package be.vinci.pae.domain;


import java.sql.Timestamp;

public interface FurnitureDTO {

  /**
   * {@inheritDoc} This method returns the furniture ID of the furniture DTO.
   * 
   * @return int ID of the furniture.
   */
  public int getIdFurniture();

  /**
   * {@inheritDoc} This method set the furniture ID of the furniture DTO.
   * 
   * @param id_furniture - int ID of the furniture.
   */
  public void setIdFurniture(int id_furniture);

  /**
   * {@inheritDoc} This method returns the state of the furniture DTO.
   * 
   * @return String state of the furniture.
   */
  public String getStateFurniture();

  /**
   * {@inheritDoc} This method set the furniture state of the furniture DTO.
   * 
   * @param state_furniture - String state of the furniture.
   */
  public void setStateFurniture(String state_furniture);


  /**
   * {@inheritDoc} This method returns the type of furniture's id of the furniture DTO.
   * 
   * @return int type of furniture's id.
   */
  public int getTypeFurniture();

  /**
   * {@inheritDoc} This method set the type of furniture's id of the furniture DTO.
   * 
   * @param type_furniture - int the type of furniture's id.
   */
  public void setTypeFurniture(int type_furniture);


  /**
   * {@inheritDoc} This method returns the description of the furniture DTO.
   * 
   * @return String the description of the furniture DTO.
   */
  public String getDescription();

  /**
   * {@inheritDoc} This method set the description of the furniture DTO.
   * 
   * @param description - String the description of the furniture DTO.
   */
  public void setDescription(String description);


  /**
   * {@inheritDoc} This method returns the purchase price of the furniture DTO.
   * 
   * @return double the purchase price of the furniture DTO.
   */
  public double getPurchasePrice();

  /**
   * {@inheritDoc} This method set the purchase price of the furniture DTO.
   * 
   * @param purchase_price - double the purchase price of the furniture DTO.
   */
  public void setPurchasePrice(double purchase_price);


  /**
   * {@inheritDoc} This method returns the selling price of the furniture DTO.
   * 
   * @return double the selling price of the furniture DTO.
   */
  public double getSellingPrice();

  /**
   * {@inheritDoc} This method set the selling price of the furniture DTO.
   * 
   * @param selling_price - double the selling price of the furniture DTO.
   */
  public void setSellingPrice(double selling_price);


  /**
   * {@inheritDoc} This method returns the special sale price of the furniture DTO.
   * 
   * @return double the special sale price of the furniture DTO.
   */
  public double getSpecialSalePrice();

  /**
   * {@inheritDoc} This method set the special sale price of the furniture DTO.
   * 
   * @param special_sale_price - double the special sale price of the furniture DTO.
   */
  public void setSpecialSalePrice(double special_sale_price);


  /**
   * {@inheritDoc} This method returns the favourite photo id of the furniture DTO.
   * 
   * @return int the favourite photo id of the furniture DTO.
   */
  public int getFavouritePhoto();

  /**
   * {@inheritDoc} This method set the favourite photo id of the furniture DTO.
   * 
   * @param favourite_photo - int the favourite photo id of the furniture DTO.
   */
  public void setFavouritePhoto(int favourite_photo);


  /**
   * {@inheritDoc} This method returns the visit id of the furniture DTO.
   * 
   * @return int the visit id of the furniture DTO.
   */
  public int getVisit();

  /**
   * {@inheritDoc} This method set the visit id of the furniture DTO.
   * 
   * @param visit - int the visit id of the furniture DTO.
   */
  public void setVisit(int visit);


  /**
   * {@inheritDoc} This method returns the boss's furniture collection date of the furniture DTO.
   * 
   * @return Timestamp the boss's furniture collection date of the furniture DTO.
   */
  public Timestamp getFurnitureCollectionDateBoss();

  /**
   * {@inheritDoc} This method set the boss's furniture collection date of the furniture DTO.
   * 
   * @param furniture_collection_date_boss - Timestamp the boss's furniture collection date of the furniture DTO.
   */
  public void setFurnitureCollectionDateBoss(Timestamp furniture_collection_date_boss);


  /**
   * {@inheritDoc} This method returns the deposit date of the furniture DTO.
   * 
   * @return Timestamp the deposit date of the furniture DTO.
   */
  public Timestamp getDepositDate();

  /**
   * {@inheritDoc} This method set the deposit date of the furniture DTO.
   * 
   * @param deposit_date - Timestamp the deposit date of the furniture DTO.
   */
  public void setDepositDate(Timestamp deposit_date);
}
