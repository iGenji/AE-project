package be.vinci.pae.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDateTime;
import java.util.Date;
import views.Views;

/*
 * ignore all null fields in order to avoid sending props not linked to a JSON view
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SellingImpl implements Selling {

  @JsonView(Views.Public.class)
  private int idSelling;
  @JsonView(Views.Public.class)
  private int idFurniture;
  @JsonView(Views.Public.class)
  private int idUser;
  @JsonView(Views.Public.class)
  private String stateSelling;
  @JsonView(Views.Public.class)
  @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date soldDate;
  @JsonView(Views.Public.class)
  private LocalDateTime userTakeawayDate;
  @JsonView(Views.Public.class)
  private LocalDateTime deliveryDate;

  @Override
  public int getIdSelling() {
    return idSelling;
  }

  @Override
  public void setIdSelling(int idSelling) {
    this.idSelling = idSelling;
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
  public int getIdUser() {
    return idUser;
  }

  @Override
  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  @Override
  public String getStateSelling() {
    return stateSelling;
  }

  @Override
  public void setStateSelling(String stateSelling) {
    this.stateSelling = stateSelling;
  }

  @Override
  public Date getSoldDate() {
    return soldDate;
  }

  @Override
  public void setSoldDate(Date soldDate) {
    this.soldDate = soldDate;
  }

  @Override
  public LocalDateTime getUserTakeawayDate() {
    return userTakeawayDate;
  }

  @Override
  public void setUserTakeawayDate(LocalDateTime userTakeawayDate) {
    this.userTakeawayDate = userTakeawayDate;
  }

  @Override
  public LocalDateTime getDeliveryDate() {
    return deliveryDate;
  }

  @Override
  public void setDeliveryDate(LocalDateTime deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SellingImpl selling = (SellingImpl) o;
    return idSelling == selling.idSelling;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idSelling;
    return result;
  }
}
