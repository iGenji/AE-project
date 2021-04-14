package be.vinci.pae.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import views.Views;

/*
 * ignore all null fields in order to avoid sending props not linked to a JSON view
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AddressImpl implements Address {

  @JsonView(Views.Public.class)
  private int idAddress;
  @JsonView(Views.Public.class)
  private String street;
  @JsonView(Views.Public.class)
  private int buildingNumber;
  @JsonView(Views.Public.class)
  private String unitNumber;
  @JsonView(Views.Public.class)
  private int postCode;
  @JsonView(Views.Public.class)
  private String commune;
  @JsonView(Views.Public.class)
  private String country;

  /**
   * Constructor for AdresseImpl.
   * @param idAddress - int that represents the id
   * @param street - String that represents the street
   * @param buildingNumber - int that represents the number
   * @param unitNumber - String 
   * @param postCode - int post code
   * @param commune - String area
   * @param country - String country
   */
  public AddressImpl(int idAddress, String street, int buildingNumber, String unitNumber,
      int postCode, String commune, String country) {
    super();
    this.idAddress = idAddress;
    this.street = street;
    this.buildingNumber = buildingNumber;
    this.unitNumber = unitNumber;
    this.postCode = postCode;
    this.commune = commune;
    this.country = country;
  }
  
  /**
   * constructor of this class with 0 parameters.
   */
  public AddressImpl() {
    super();
    this.idAddress = -1;
    this.street = null;
    this.buildingNumber = -1;
    this.unitNumber = null;
    this.commune = null;
    this.country = null;
  }

  @Override
  public int getIdAddress() {
    // TODO Auto-generated method stub
    return this.idAddress;
  }

  @Override
  public void setIdAddress(int idAddress) {
    // TODO Auto-generated method stub
    this.idAddress = idAddress;

  }

  @Override
  public String getStreet() {
    // TODO Auto-generated method stub
    return this.street;
  }

  @Override
  public void setStreet(String street) {
    // TODO Auto-generated method stub
    this.street = street;

  }

  @Override
  public int getBuildingNumber() {
    // TODO Auto-generated method stub
    return this.buildingNumber;
  }

  @Override
  public void setBuildingNumber(int buildingNumber) {
    // TODO Auto-generated method stub
    this.buildingNumber = buildingNumber;

  }

  @Override
  public String getUnitNumber() {
    // TODO Auto-generated method stub
    return this.unitNumber;
  }

  @Override
  public void setUnitNumber(String unitNumber) {
    // TODO Auto-generated method stub
    this.unitNumber = unitNumber;

  }

  @Override
  public int getPostcode() {
    // TODO Auto-generated method stub
    return this.postCode;
  }

  @Override
  public void setPostcode(int postcode) {
    // TODO Auto-generated method stub
    this.postCode = postcode;

  }

  @Override
  public String getCommune() {
    // TODO Auto-generated method stub
    return this.commune;
  }

  @Override
  public void setCommune(String commune) {
    // TODO Auto-generated method stub
    this.commune = commune;

  }

  @Override
  public String getCountry() {
    // TODO Auto-generated method stub
    return this.country;
  }

  @Override
  public void setCountry(String country) {
    // TODO Auto-generated method stub
    this.country = country;

  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idAddress;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AddressImpl other = (AddressImpl) obj;
    if (idAddress != other.idAddress) {
      return false;
    }
    return true;
  }


}
