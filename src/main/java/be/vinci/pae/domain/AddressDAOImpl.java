package be.vinci.pae.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalServices;
import jakarta.inject.Inject;

public class AddressDAOImpl implements AddressDAO {

  private final String insertAddress = "INSERT INTO pae_project.adresses (" + " rue," + " numero,"
      + " boite," + " code_postal," + " commune," + " pays ) VALUES (" + " ?, ?, ?, ?, ?, ?)";
  private final String SelectByAllAttributes = "SELECT ad.id_adresse, ad.rue, ad.numero, ad.boite, "
      + " ad.code_postal, ad.commune, ad.pays" + " FROM pae_project.adresses ad"
      + " WHERE ad.rue =?" + " AND ad.numero =?" + " AND ad.boite =?" + " AND ad.code_postal =?"
      + " AND ad.commune =?" + " AND ad.pays =?";

  @Inject
  private DalServices dalServices;

  @Inject
  private AddressFactory factory;

  @Override
  public List<AddressDTO> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AddressDTO findByID(int addressID) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AddressDTO> findByPostcode(int postalCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AddressDTO> findByCommune(String municipality) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int insert(AddressDTO address) {
    PreparedStatement ps;
    try {

      ps = dalServices.getPreparedStatementReturningId(insertAddress);
      ps.setString(1, address.getStreet());
      ps.setInt(2, address.getBuildingNumber());
      ps.setString(3, address.getUnitNumber());
      ps.setInt(4, address.getPostcode());
      ps.setString(5, address.getCommune());
      ps.setString(6, address.getCountry());
      int affectedRows = ps.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating address failed, no rows affected.");
      }

      try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return (int) generatedKeys.getLong(1);
        } else {
          throw new SQLException("Creating address failed, no ID obtained.");
        }
      }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }
  }

  @Override
  public boolean delete(AddressDTO address) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public AddressDTO update(AddressDTO address) {
    // TODO Auto-generated method stub
    return null;
  }

  private AddressDTO setAddress(ResultSet rs) {

    AddressDTO address = factory.getInstance();
    try {
      address.setIdAddress(rs.getInt("id_adresse"));
      address.setStreet(rs.getString("rue"));
      address.setBuildingNumber(rs.getInt("numero"));
      address.setUnitNumber(rs.getString("boite"));
      address.setPostcode(rs.getInt("code_postal"));
      address.setCommune(rs.getString("commune"));
      address.setCountry(rs.getString("pays"));

    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new FatalException(e.getMessage(), e);
    }

    return address;
  }

  @Override
  public int findByAllAttributes(AddressDTO address) {
    AddressDTO addressDTO = null;
    ResultSet rs = null;
    PreparedStatement ps;
    try {

      ps = dalServices.getPreparedStatement(SelectByAllAttributes);
      ps.setString(1, address.getStreet());
      ps.setInt(2, address.getBuildingNumber());
      ps.setString(3, address.getUnitNumber());
      ps.setInt(4, address.getPostcode());
      ps.setString(5, address.getCommune());
      ps.setString(6, address.getCountry());
      rs = ps.executeQuery();
      while (rs.next()) {
        addressDTO = setAddress(rs);
      }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }
    return addressDTO.getIdAddress();
  }

}
