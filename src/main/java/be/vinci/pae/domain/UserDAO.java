package be.vinci.pae.domain;

import java.util.List;

public interface UserDAO {

  /**
   * {@inheritDoc} This method returns a list of all the customers that the database has.
   * 
   * @return List of users.
   */
  List<UserDTO> findAll();

  /**
   * {@inheritDoc} This method returns a user DTO fulfilled from the database with the ID that was given.
   * 
   * @param userID - int.
   * @return The user or null if not found.
   */
  UserDTO findByID(int userID);

  /**
   * {@inheritDoc} This method returns a user DTO fulfilled from the
   *     database with the username that was given.
   * 
   * @param username - String.
   * @return The user or null if not found. {
   */
  UserDTO findByUsername(String username);

  /**
   * {@inheritDoc} This method returns a user DTO fulfilled
   *     from the database with the last name that was given.
   * 
   * @param lastName - String.
   * @return The user or null if not found.
   */
  UserDTO findByLastName(String lastName);

  /**
   * {@inheritDoc} This method insert into the database the UserDTO.
   * 
   * @param user - UserDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean insert(UserDTO user);

  /**
   * {@inheritDoc} This method delete the user DTO in
   *     argument from the database.
   * 
   * @param user - UserDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean delete(UserDTO user);

  /**
   * {@inheritDoc} This method update the user DTO in argument from the database.
   * 
   * @param user - UserDTO.
   * @return the new user DTO or null if the user was not found.
   */
  UserDTO update(UserDTO user);

  /**
   * {@inheritDoc}This method returns the address
   * 
   * @param id - int id of the user
   * @return AddressDTO
   */
  AddressDTO getAdress(int id);

}
