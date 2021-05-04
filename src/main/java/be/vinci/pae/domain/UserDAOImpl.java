package be.vinci.pae.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalServices;
import jakarta.inject.Inject;

public class UserDAOImpl implements UserDAO {

  private final String findUserById =
      "SELECT u.id_utilisateur, u.pseudo, u.nom, u.mot_de_passe, u.prenom"
          + ", u.email, u.date_inscription, u.role, u.adresse "
          + "FROM pae_project.utilisateurs u WHERE u.id_utilisateur=?";

  private final String findAllUsers =
      "SELECT u.id_utilisateur, u.pseudo, u.nom, u.mot_de_passe, u.prenom"
          + ", u.email, u.date_inscription, u.role, u.adresse FROM pae_project.utilisateurs u"
          + " WHERE u.role!='admin' ";

  private final String findUserByUsername =
      "SELECT u.id_utilisateur, u.pseudo, u.nom, u.mot_de_passe, u.prenom"
          + ", u.email, u.date_inscription, u.role,"
          + " u.adresse FROM pae_project.utilisateurs u WHERE u.pseudo=?";

  private final String insertUser = "INSERT INTO pae_project.utilisateurs (" + " pseudo,"
      + " prenom," + " nom," + " email," + " mot_de_passe," + " date_inscription," + " role,"
      + " adresse ) VALUES (" + " ?, ?, ?, ?, ?, ?, ?, ?)";
  
  //private final String getAdress = "SELECT rue,numero,boite,code_postal,commune,pays"
      //+ " FROM pae_project.adresses WHERE id_adresse=?";


  @Inject
  private DalServices dalServices;

  @Inject
  private UserFactory factory;
  
 

  @Override
  public List<UserDTO> findAll() {
    ArrayList<UserDTO> list = new ArrayList<UserDTO>();
    UserDTO user = null;
    ResultSet rs = null;
    try {

      PreparedStatement ps = dalServices.getPreparedStatement(findAllUsers);
      // il faut injecter dalServices.
      // à cette classe.
      rs = ps.executeQuery();

      while (rs.next()) {
        user = setUser(rs);
        list.add(user);
      }
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return list;

  }

  @Override
  public UserDTO findByID(int userID) {
    UserDTO user = null;
    ResultSet rs;
    PreparedStatement ps;
    try {
      ps = dalServices.getPreparedStatement(findUserById);
      ps.setInt(1, userID);
      rs = ps.executeQuery();
      while (rs.next()) {
        user = setUser(rs);
      }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return user;
  }

  @Override
  public UserDTO findByUsername(String username) {

    UserDTO user = null;
    ResultSet rs = null;
    PreparedStatement ps;
    try {

      ps = dalServices.getPreparedStatement(findUserByUsername);
      ps.setString(1, username);
      rs = ps.executeQuery();
      while (rs.next()) {
        user = setUser(rs);
      }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return user;
  }

  @Override
  public UserDTO findByLastName(String lastName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean insert(UserDTO user) {

    // UserDTO userDTO = null;
    // ResultSet rs = null;
    PreparedStatement ps;
    try {

      ps = dalServices.getPreparedStatement(insertUser);
      ps.setString(1, user.getUsername());
      ps.setString(2, user.getFirstName());
      ps.setString(3, user.getLastName());
      ps.setString(4, user.getEmail());
      ps.setString(5, user.getPassword());
      LocalDateTime time = LocalDateTime.now();
      ps.setTimestamp(6, Timestamp.valueOf(time));
      ps.setString(7, user.getRole());
      ps.setInt(8, user.getAddress());
      ps.executeUpdate();
      // while (rs.next()) {
      // userDTO = setUser(rs);
      // }

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

    return true;
  }

  @Override
  public boolean delete(UserDTO user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UserDTO update(UserDTO user) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc} this method retrieves the data of a user present in a ResultSet.
   *
   * @param rs - ResultSet.
   * @return the new user DTO.
   */
  private UserDTO setUser(ResultSet rs) {

    UserDTO user = factory.getInstance();
    try {
      user.setIdUser(rs.getInt("id_utilisateur"));
      user.setUsername(rs.getString("pseudo"));
      user.setLastName(rs.getString("nom"));
      user.setPassword(rs.getString("mot_de_passe"));
      user.setFirstName(rs.getString("prenom"));
      user.setEmail(rs.getString("email"));
      Timestamp time = rs.getTimestamp("date_inscription");
      LocalDateTime timeLoc = null;
      if (time != null) {
        timeLoc = time.toLocalDateTime();
      }
      user.setRegistrationDate(timeLoc);
      user.setRole(rs.getString("role"));
      user.setAddress(rs.getInt("adresse"));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new FatalException(e.getMessage(), e);
    }

    return user;
  }

 

  @Override
  public AddressDTO getAdress(int id) {
    AddressDTO toReturn = null;
    //ResultSet rs = null;
    //PreparedStatement ps;
    System.out.println("id=" + id);
//    try {
//
//      ps = dalServices.getPreparedStatement(getAdress);
//      ps.setInt(1, id);
//      rs = ps.executeQuery();
//      //while (rs.next()) {
//        //toReturn = setAddress(rs);
//      //}
//    } catch (Exception e) {
//      throw new FatalException(e.getMessage(), e);
//    }
    return toReturn;
  }



}
