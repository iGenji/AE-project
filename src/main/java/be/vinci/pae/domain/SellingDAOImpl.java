package be.vinci.pae.domain;

import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalServices;
import jakarta.inject.Inject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class SellingDAOImpl implements SellingDAO {

  private final String insertSelling = "INSERT INTO pae_project.ventes (meuble, client,"
      + "etat_vente, date_vente,date_emporte,date_livraison ) VALUES ( ?, ?, ?, ?, null, null)";

  @Inject
  private DalServices dalServices;

  @Override
  public List<SellingDTO> findAll() {
    return null;
  }

  @Override
  public SellingDTO findByID(int sellingID) {
    return null;
  }

  @Override
  public int findByAllAttributes(SellingDTO selling) {
    return 0;
  }

  @Override
  public SellingDTO findByFurnitureId(int furniture) {
    return null;
  }

  @Override
  public List<SellingDTO> findByUser(int user) {
    return null;
  }

  @Override
  public List<SellingDTO> findByState(String state) {
    return null;
  }

  @Override
  public boolean insert(SellingDTO selling) {
    PreparedStatement ps;
    try {

      ps = dalServices.getPreparedStatement(insertSelling);
      ps.setInt(1, selling.getIdFurniture());
      if (selling.getIdUser() != -1) {
        ps.setInt(2, selling.getIdUser());
      } else {
        ps.setString(2, null);
      }
      ps.setString(3, "cloturee");
      ps.setTimestamp(4, Timestamp.from(selling.getSoldDate().toInstant()));
      ps.executeUpdate();

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }
    return true;
  }

  @Override
  public boolean delete(SellingDTO selling) {
    return false;
  }

  @Override
  public Selling update(SellingDTO selling) {
    return null;
  }
}
