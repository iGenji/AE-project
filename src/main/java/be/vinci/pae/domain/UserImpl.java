package be.vinci.pae.domain;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import views.Views;

/*
 * ignore all null fields in order to avoid sending props not linked to a JSON view
 */
@JsonInclude(JsonInclude.Include.NON_NULL)


class UserImpl implements User {

  @JsonView(Views.Public.class)
  private int idUser;
  @JsonView(Views.Public.class)
  private String username;
  @JsonView(Views.Public.class)
  private String lastName;
  @JsonView(Views.Public.class)
  private String firstName;
  @JsonView(Views.Public.class)
  private String email;
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonView(Views.Public.class)
  private LocalDateTime registrationDate;
  @JsonView(Views.Public.class)
  private String role;
  @JsonView(Views.Public.class)
  private int address;

  @JsonView(Views.Internal.class)
  private String password;


  public UserImpl(int idUser, String username, String lastName, String password, String firstName,
      String email, LocalDateTime registrationDate, String role, int address) {
    super();
    this.idUser = idUser;
    this.username = username;
    this.lastName = lastName;
    this.password = password;
    this.firstName = firstName;
    this.email = email;
    this.registrationDate = registrationDate;
    this.role = role;
    this.address = address;
  }

  public UserImpl() {
    super();
    this.idUser = -1;
    this.username = null;
    this.lastName = null;
    this.password = null;
    this.firstName = null;
    this.email = null;
    this.registrationDate = null;
    this.role = null;
    this.address = -1;
  }

  @Override
  public int getIdUser() {
    return this.idUser;
  }

  @Override
  public void setIdUser(int id_user) {
    this.idUser = id_user;

  }

  @Override
  public String getLastName() {
    return this.lastName;
  }

  @Override
  public void setLastName(String last_name) {
    this.lastName = last_name;

  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;

  }

  @Override
  public String getFirstName() {
    return this.firstName;
  }

  @Override
  public void setFirstName(String first_name) {
    this.firstName = first_name;

  }


  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;

  }

  @Override
  public LocalDateTime getRegistrationDate() {
    return this.registrationDate;
  }

  @Override
  public void setRegistrationDate(LocalDateTime registration_date) {
    this.registrationDate = registration_date;

  }

  @Override
  public String getRole() {
    return this.role;
  }

  @Override
  public void setRole(String role) {
    this.role = role;

  }

  @Override
  public int getAddress() {
    return this.address;
  }

  @Override
  public void setAddress(int address) {
    this.address = address;

  }

  @Override
  public boolean passwordCheck(String password) {
    return BCrypt.checkpw(password, this.password);
  }

  @Override
  public String passwordEncryption(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  @Override
  public boolean checkCanBeAdmin(UserDTO user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UserDTO changeToAdmin(UserDTO user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idUser;
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
    UserImpl other = (UserImpl) obj;
    if (idUser != other.idUser) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "";
  }



}
