package be.vinci.pae.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalServices;
import jakarta.inject.Inject;

public class PhotoDAOImpl implements PhotoDAO {

  private final String findPhotoById = "SELECT p.id_photo, p.meuble, " + " p.photo, p.peut_defiler"
      + " FROM pae_project.photos_meubles p" + " WHERE p.id_photo=?";

  private final String findPhotoByIdFurniture =
      "SELECT p.id_photo, " + " p.meuble, p.photo, p.peut_defiler"
          + " FROM pae_project.photos_meubles p" + " WHERE p.meuble=?";
  
  private final String insertPhoto = "INSERT INTO pae_project.photos_meubles(meuble,photo,peut_defiler)"
      + " VALUES"
      + "(?,?,false)";

  @Inject
  private DalServices dalServices;

  @Inject
  PhotoFactory factory;

  @Override
  public PhotoDTO findByID(int photoID) {
    PhotoDTO photo = null;
    ResultSet rs;
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(findPhotoById);
      ps.setInt(1, photoID);
      rs = ps.executeQuery();
      while (rs.next()) {
        photo = setPhoto(rs);
      }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return photo;

  }

  @Override
  public List<PhotoDTO> findByIDFurniture(int idFurniture) {
    ArrayList<PhotoDTO> toReturn = new ArrayList<PhotoDTO>();
    PhotoDTO photo = null;
    ResultSet rs = null;
    try {
      PreparedStatement ps = dalServices.getPreparedStatement(findPhotoByIdFurniture);
      ps.setInt(1, idFurniture);
      rs = ps.executeQuery();
      while (rs.next()) {
        photo = setPhoto(rs);
        toReturn.add(photo);
      }
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }
    return toReturn;
  }


  @Override
  public boolean insert(String photo, int idMeuble) {
    PreparedStatement ps;
    try {

      ps = dalServices.getPreparedStatement(insertPhoto);
      ps.setInt(1, idMeuble);
      ps.setString(2, photo);
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return true;
  }

  @Override
  public boolean delete(PhotoDTO photo) {
    // TODO Auto-generated method stub
    return false;
  }

  

  /**
   * {@inheritDoc} this method retrieves the data of a user present in a ResultSet.
   *
   * @param rs - ResultSet.
   * @return the new user DTO.
   */
  private PhotoDTO setPhoto(ResultSet rs) {

    PhotoDTO photo = factory.getInstance();
    try {
      photo.setIdphoto(rs.getInt("id_photo"));
      photo.setFurniture(rs.getInt("meuble"));
      photo.setPhoto(rs.getString("photo"));
      photo.setIsVisible(rs.getBoolean("peut_defiler"));

    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new FatalException(e.getMessage(), e);
    }

    return photo;
  }
}
