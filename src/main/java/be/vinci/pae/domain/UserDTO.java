package be.vinci.pae.domain;

import java.time.LocalDateTime;

public interface UserDTO {

  /**
   * {@inheritDoc} This method returns the user ID of the user DTO.
   * 
   * @return int - ID of the user.
   */
  int getIdUser();

  /**
   * {@inheritDoc} This method set the user ID of the user DTO.
   * 
   * @param idUser - int number that is given to the user DTO.
   */
  void setIdUser(int idUser);

  /**
   * {@inheritDoc} This method returns the last name of the user DTO.
   * 
   * @return String - last name of the user DTO.
   */
  String getLastName();

  /**
   * {@inheritDoc} This method sets the last name of the user DTO.
   * 
   * @param lastName - String last name of the user DTO.
   */
  void setLastName(String lastName);

  /**
   * {@inheritDoc} This method returns the password of the user DTO.
   * 
   * @return String - password of the user DTO.
   */
  String getPassword();

  /**
   * {@inheritDoc} This method sets the password of the user DTO.
   * 
   * @param password - String password of the user DTO.
   */
  void setPassword(String password);

  /**
   * {@inheritDoc} This method returns the first name of the user DTO.
   * 
   * @return String - fist name of the user DTO.
   */
  String getFirstName();

  /**
   * {@inheritDoc} this method sets the first name of the user DTO.
   * 
   * @param firstName - String first name of the user DTO.
   */
  void setFirstName(String firstName);

  /**
   * {@inheritDoc} This method returns the email of the user DTO.
   * 
   * @return String - email of the user DTO.
   */
  String getEmail();

  /**
   * {@inheritDoc} This method sets the email of the user DTO.
   * 
   * @param email - String email of the user DTO.
   */
  void setEmail(String email);

  /**
   * {@inheritDoc} This method returns the user's DTO date of registration.
   * 
   * @return LocalDateTime - user's DTO date of registration.
   */
  LocalDateTime getRegistrationDate();

  /**
   * {@inheritDoc} this method sets the user's DTO date of registration.
   * 
   * @param now - LocalDateTime user's DTO date of registration.
   */
  void setRegistrationDate(LocalDateTime now);

  /**
   * {@inheritDoc} this method returns user's DTO role.
   * 
   * @return String - user's DTO role.
   */
  String getRole();

  /**
   * {@inheritDoc} This method sets user's DTO role.
   * 
   * @param role - String user's DTO role.
   */
  void setRole(String role);

  /**
   * {@inheritDoc} This method returns user's DTO ID address.
   * 
   * @return int - user's DTO ID address.
   */
  int getAddress();

  /**
   * {@inheritDoc} This method sets user's DTO ID address.
   * 
   * @param address - int user's DTO ID address.
   */
  void setAddress(int address);

  /**
   * {@inheritDoc} This method returns user's DTO username.
   * 
   * @return String - user's DTO username.
   */
  String getUsername();

  /**
   * {@inheritDoc} This method sets user's DTO username.
   * 
   * @param username - String user's DTO username.
   */
  void setUsername(String username);

}
