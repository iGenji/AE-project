package be.vinci.pae.domain;


import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import views.Views;

/*
 * ignore all null fields in order to avoid sending props not linked to a JSON view
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class FurnitureImpl implements Furniture {

  @JsonView(Views.Public.class)
  private int idFurniture;
  @JsonView(Views.Public.class)
  private String stateFurniture;
  @JsonView(Views.Public.class)
  private int typeFurniture; // FK, id lié à table types_meubles
  @JsonView(Views.Public.class)
  private String description;
  @JsonView(Views.Public.class)
  private double purchasePrice;
  @JsonView(Views.Public.class)
  private double sellingPrice;
  @JsonView(Views.Public.class)
  private double specialSalePrice;
  @JsonView(Views.Public.class)
  private int favouritePhoto; // FK, id lié à table photos_meubles
  @JsonView(Views.Public.class)
  private int visit; // FK, id lié à table visites
  @JsonView(Views.Public.class)
  private Timestamp furnitureCollectionDateBoss;
  @JsonView(Views.Public.class)
  private Timestamp depositDate;

  /**
   * Constructor of the class
   * 
   * @param idFurniture - int
   * @param stateFurniture - String
   * @param typeFurniture - int
   * @param description - String
   * @param purchasePrice - double
   * @param sellingPrice - double
   * @param specialSalePrice - double
   * @param favouritePhoto - int
   * @param visit - int
   * @param furnitureCollectionDateBoss - Timestamp
   * @param depositDate - Timestamp
   */
  public FurnitureImpl(int idFurniture, String stateFurniture, int typeFurniture,
      String description, double purchasePrice, double sellingPrice, double specialSalePrice,
      int favouritePhoto, int visit, Timestamp furnitureCollectionDateBoss, Timestamp depositDate) {
    super();
    this.idFurniture = idFurniture;
    this.stateFurniture = stateFurniture;
    this.typeFurniture = typeFurniture;
    this.description = description;
    this.purchasePrice = purchasePrice;
    this.sellingPrice = sellingPrice;
    this.specialSalePrice = specialSalePrice;
    this.favouritePhoto = favouritePhoto;
    this.visit = visit;
    this.furnitureCollectionDateBoss = furnitureCollectionDateBoss;
    this.depositDate = depositDate;
  }

  /**
   * Another constructor of the class
   */
  public FurnitureImpl() {
    super();
    this.idFurniture = -1;
    this.stateFurniture = null;
    this.typeFurniture = -1;
    this.description = null;
    this.purchasePrice = -1;
    this.sellingPrice = -1;
    this.specialSalePrice = -1;
    this.favouritePhoto = -1;
    this.visit = -1;
    this.furnitureCollectionDateBoss = null;
    this.depositDate = null;
  }

  @Override
  public int getIdFurniture() {
    return idFurniture;
  }

  @Override
  public void setIdFurniture(int idFurniture) {
    this.idFurniture = idFurniture;
  }

  @Override
  public String getStateFurniture() {
    return stateFurniture;
  }

  @Override
  public void setStateFurniture(String stateFurniture) {
    this.stateFurniture = stateFurniture;
  }

  @Override
  public int getTypeFurniture() {
    return typeFurniture;
  }

  @Override
  public void setTypeFurniture(int typeFurniture) {
    this.typeFurniture = typeFurniture;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public double getPurchasePrice() {
    return purchasePrice;
  }

  @Override
  public void setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  @Override
  public double getSellingPrice() {
    return sellingPrice;
  }

  @Override
  public void setSellingPrice(double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  @Override
  public double getSpecialSalePrice() {
    return specialSalePrice;
  }

  @Override
  public void setSpecialSalePrice(double specialSalePrice) {
    this.specialSalePrice = specialSalePrice;
  }

  @Override
  public int getFavouritePhoto() {
    return favouritePhoto;
  }

  public void setFavouritePhoto(int favouritePhoto) {
    this.favouritePhoto = favouritePhoto;
  }

  public int getVisit() {
    return visit;
  }

  @Override
  public void setVisit(int visit) {
    this.visit = visit;
  }

  @Override
  public Timestamp getFurnitureCollectionDateBoss() {
    return furnitureCollectionDateBoss;
  }

  @Override
  public void setFurnitureCollectionDateBoss(Timestamp funitureCollectionDateBoss) {
    this.furnitureCollectionDateBoss = funitureCollectionDateBoss;
  }

  @Override
  public Timestamp getDepositDate() {
    return depositDate;
  }

  @Override
  public void setDepositDate(Timestamp depositDate) {
    this.depositDate = depositDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idFurniture;
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
    FurnitureImpl other = (FurnitureImpl) obj;
    if (idFurniture != other.idFurniture) {
      return false;
    }
    return true;
  }



}
