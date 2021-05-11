package be.vinci.pae.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
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
  private String typeString;
  @JsonView(Views.Public.class)
  private int favouritePhoto; // FK, id lié à table photos_meubles
  @JsonView(Views.Public.class)
  private String favouritePhotoString;
  @JsonView(Views.Public.class)
  private int visit; // FK, id lié à table visites
  @JsonView(Views.Public.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date furnitureCollectionDateBoss;
  @JsonView(Views.Public.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date depositDate;


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
  public Date getFurnitureCollectionDateBoss() {
    return furnitureCollectionDateBoss;
  }

  @Override
  public void setFurnitureCollectionDateBoss(Timestamp funitureCollectionDateBoss) {
    this.furnitureCollectionDateBoss = funitureCollectionDateBoss;
  }

  @Override
  public Date getDepositDate() {
    return depositDate;
  }

  @Override
  public void setDepositDate(Timestamp depositDate) {
    this.depositDate = depositDate;
  }


  @Override
  public String getTypeString() {
    return typeString;
  }

  @Override
  public void setTypeString(String typeString) {
    this.typeString = typeString;
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

  public String getFavouritePhotoString() {
    return favouritePhotoString;
  }

  public void setFavouritePhotoString(String favouritePhotoString) {
    this.favouritePhotoString = favouritePhotoString;
  }


}
