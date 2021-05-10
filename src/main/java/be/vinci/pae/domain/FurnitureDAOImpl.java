package be.vinci.pae.domain;

import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalServices;
import jakarta.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FurnitureDAOImpl implements FurnitureDAO {

  private final String findFurnitureById =
      "SELECT " + "m.id_meuble,m.etat_meuble,m.type_meuble,m.description,m.prix_achat,m.prix_vente,"
          + "m.prix_special,m.photo_preferee,"
          + "m.visite,m.date_emporte_patron,m.date_depot,"
          + "t.nom FROM pae_project.meubles m,pae_project.types_meubles t"
          + " WHERE  m.id_meuble=?";

  private final String updateFurnitureState =
      "Update pae_project.meubles SET etat_meuble=? WHERE id_meuble=?";

  private final String updateFurnitureCollectionDate =
      "Update pae_project.meubles SET date_emporte_patron=? WHERE id_meuble=?";

  private final String updateFurniturePurchasePrice =
      "UPDATE pae_project.meubles SET prix_achat=? WHERE id_meuble=?";

  private final String updateFurnitureDepositDate =
      "Update pae_project.meubles SET date_depot=? WHERE id_meuble=?";

  private final String updateFurnitureSellingPrice =
      "UPDATE pae_project.meubles SET prix_vente=? WHERE id_meuble=?";

  private final String updateFurnitureSpecialPrice =
      "UPDATE pae_project.meubles SET prix_special=? WHERE id_meuble=?";

  private final String findFurnitures =
      "SELECT " + "m.id_meuble,m.etat_meuble,m.type_meuble,m.description,m.prix_achat,m.prix_vente,"
          + "m.prix_special,m.photo_preferee,"
          + "m.visite,m.date_emporte_patron,m.date_depot,"
          + "t.nom FROM pae_project.meubles m,pae_project.types_meubles t"
          + " WHERE t.id_type=m.type_meuble";

  @Inject
  private DalServices dalServices;

  @Inject
  FurnitureFactory factory;

  @Override
  public FurnitureDTO findByID(int furnitureID) {
    FurnitureDTO furniture = null;
    ResultSet rs;
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(findFurnitureById);
      ps.setInt(1, furnitureID);
      rs = ps.executeQuery();
      while (rs.next()) {
        furniture = setFurniture(rs);
      }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return furniture;
  }

  @Override
  public boolean insert(FurnitureDTO furniture) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delete(FurnitureDTO furniture) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public FurnitureDTO update(FurnitureDTO furniture) {
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(updateFurnitureSellingPrice);
      double sellingPrice = furniture.getSellingPrice();
      ps.setDouble(1, sellingPrice);
      ps.setInt(2, furniture.getIdFurniture());
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    try {
      ps = dalServices.getPreparedStatement(updateFurnitureSpecialPrice);
      double specialPrice = furniture.getSpecialSalePrice();
      ps.setDouble(1, specialPrice);
      ps.setInt(2, furniture.getIdFurniture());
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return updateState(furniture);
  }

  @Override
  public FurnitureDTO updateState(FurnitureDTO furniture) {
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(updateFurnitureState);
      ps.setString(1, furniture.getStateFurniture());
      ps.setInt(2, furniture.getIdFurniture());
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return furniture;
  }

  @Override
  public FurnitureDTO updatePurchasePrice(FurnitureDTO furniture) {
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(updateFurniturePurchasePrice);
      ps.setDouble(1, furniture.getPurchasePrice());
      ps.setInt(2, furniture.getIdFurniture());
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return furniture;
  }

  @Override
  public FurnitureDTO updateCollectionDate(FurnitureDTO furniture) {
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(updateFurnitureCollectionDate);
      ps.setTimestamp(1, Timestamp.from(furniture.getFurnitureCollectionDateBoss().toInstant()));
      ps.setInt(2, furniture.getIdFurniture());
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return furniture;
  }

  @Override
  public FurnitureDTO updateDepositDate(FurnitureDTO furniture) {
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(updateFurnitureDepositDate);
      ps.setTimestamp(1, Timestamp.from(furniture.getDepositDate().toInstant()));
      ps.setInt(2, furniture.getIdFurniture());
      ps.executeUpdate();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return furniture;
  }

  @Override
  public List<FurnitureDTO> findAll() {
    ArrayList<FurnitureDTO> toReturn = new ArrayList<FurnitureDTO>();
    FurnitureDTO furniture = null;
    ResultSet rs = null;

    try {
      PreparedStatement ps = dalServices.getPreparedStatement(findFurnitures);
      rs = ps.executeQuery();

      while (rs.next()) {
        furniture = setFurniture(rs);
        toReturn.add(furniture);
      }
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return toReturn;
  }

  /**
   * {@inheritDoc} this method retrieves the data of a user present in a ResultSet.
   *
   * @param rs - ResultSet.
   * @return the new user DTO. {@inheritDoc}
   */
  private FurnitureDTO setFurniture(ResultSet rs) {

    FurnitureDTO furniture = factory.getInstance();
    try {
      furniture.setIdFurniture(rs.getInt("id_meuble"));
      furniture.setStateFurniture(rs.getString("etat_meuble"));
      furniture.setTypeFurniture(rs.getInt("type_meuble"));
      furniture.setDescription(rs.getString("description"));
      furniture.setPurchasePrice(rs.getDouble("prix_achat"));
      furniture.setSellingPrice(rs.getDouble("prix_vente"));
      furniture.setSpecialSalePrice(rs.getDouble("prix_special"));
      furniture.setFavouritePhoto(rs.getInt("photo_preferee"));
      furniture.setVisit(rs.getInt("visite"));
      furniture.setFurnitureCollectionDateBoss(rs.getTimestamp("date_emporte_patron"));
      furniture.setDepositDate(rs.getTimestamp("date_depot"));
      furniture.setTypeString(rs.getString("nom"));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new FatalException(e.getMessage(), e);
    }

    return furniture;
  }

}
