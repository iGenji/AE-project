package be.vinci.pae.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import views.Views;

/*
 * ignore all null fields in order to avoid sending props not linked to a JSON view
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoImpl implements Photo {

  @JsonView(Views.Public.class)
  private int idPhoto;
  @JsonView(Views.Public.class)
  private int furniture; // id lié à table meubles
  @JsonView(Views.Public.class)
  private String photo;
  @JsonView(Views.Public.class)
  private int isVisible;

  /**
   * Constructor of the class.
   * 
   * @param idPhoto - int
   * @param furniture - int
   * @param photo - String
   * @param isVisible - int
   */
  public PhotoImpl(int idPhoto, int furniture, String photo, int isVisible) {
    super();
    this.idPhoto = idPhoto;
    this.furniture = furniture;
    this.photo = photo;
    this.isVisible = isVisible;
  }

  /**
   * Another constructor of the class.
   */
  public PhotoImpl() {
    super();
    this.idPhoto = -1;
    this.furniture = -1;
    this.photo = null;
    this.isVisible = -1;
  }

  @Override
  public int getIdPhoto() {
    return idPhoto;
  }

  @Override
  public void setIdphoto(int idPhoto) {
    this.idPhoto = idPhoto;
  }

  @Override
  public int getFurniture() {
    return furniture;
  }

  @Override
  public void setFurniture(int furniture) {
    this.furniture = furniture;
  }

  @Override
  public String getPhoto() {
    return photo;
  }

  @Override
  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Override
  public int getIsVisible() {
    return isVisible;
  }

  @Override
  public void setIsVisible(int isVisible) {
    this.isVisible = isVisible;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idPhoto;
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
    PhotoImpl other = (PhotoImpl) obj;
    if (idPhoto != other.idPhoto) {
      return false;
    }
    return true;
  }



}
