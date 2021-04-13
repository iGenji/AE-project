package be.vinci.pae.domain;

public interface AddressDTO {

  /**
   * {@inheritDoc} This method returns the address ID of the address DTO.
   * 
   * @return int ID of the address.
   */
  int getIdAddress();

  /**
   * {@inheritDoc} This method set the address ID of the address DTO.
   * 
   * @param id_address - int ID of the address.
   */
  void setIdAddress(int idAddress);

  /**
   * {@inheritDoc} This method returns the street of the address DTO.
   * 
   * @return String street of the address.
   */
  String getStreet();

  /**
   * {@inheritDoc} This method set the street of the address DTO.
   * 
   * @param street - String street of the address.
   */
  void setStreet(String street);

  /**
   * {@inheritDoc} This method returns the building's number of the address DTO.
   * 
   * @return int building number of the address.
   */
  int getBuildingNumber();

  /**
   * {@inheritDoc} This method set the building's number of the address DTO.
   * 
   * @param buildingNumber - int building number of the address.
   */
  void setBuildingNumber(int buildingNumber);

  /**
   * {@inheritDoc} This method returns the unit number of the address DTO.
   * 
   * @return String unit number of the address.
   */
  String getUnitNumber();

  /**
   * {@inheritDoc} This method set the unit number of the address DTO.
   * 
   * @param unitNumber - String unit number of the address.
   */
  void setUnitNumber(String unitNumber);

  /**
   * {@inheritDoc} This method returns the postcode of the address DTO.
   * 
   * @return int postcode of the address.
   */
  int getPostcode();

  /**
   * {@inheritDoc} This method set the postcode of the address DTO.
   * 
   * @param postcode - int postcode of the address.
   */
  void setPostcode(int postcode);

  /**
   * {@inheritDoc} This method returns the commune of the address DTO.
   * 
   * @return String commune of the address.
   */
  String getCommune();

  /**
   * {@inheritDoc} This method set the commune of the address DTO.
   * 
   * @param commune - String commune of the address.
   */
  void setCommune(String commune);

  /**
   * {@inheritDoc} This method returns the country of the address DTO.
   * 
   * @return String country of the address.
   */
  String getCountry();

  /**
   * {@inheritDoc} This method set the country of the address DTO.
   * 
   * @param country - String country of the address.
   */
  void setCountry(String country);

}
