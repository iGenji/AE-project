package be.vinci.pae.usecases;

import java.util.List;
import be.vinci.pae.domain.AddressDTO;
import be.vinci.pae.domain.UserDTO;

public interface UserUCC {

  /**
   * This method is the use case method to register a user.
   * 
   * @param userDTO - UserDTO, user that has most of it fields
   *    full, fulfilled by the front-end.
   * @param addressDTO - AddressDTO, address that has most
   *    of it fields full, fulfilled by the front end.
   * @return return false if failed, true if succeed.
   */
  boolean register(UserDTO userDTO, AddressDTO addressDTO);

  /**
   * This method is the use case method to log in into the system with a password and a login.
   * 
   * @param username - String , username of the user.
   * @param password - String , password of the user.
   * @return UserDTO with full fields or null if the user is not found.
   */
  UserDTO login(String username, String password);
  
  /**
   * This method is the use case method to see all the customers in the system.
   * @return List of all customers
   */
  List<UserDTO> allCustomers();

}
