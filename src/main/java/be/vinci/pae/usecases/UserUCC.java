package be.vinci.pae.usecases;

<<<<<<< HEAD
=======
import java.util.List;
import be.vinci.pae.domain.AddressDTO;
>>>>>>> f8c1964071870e6dce22e9f0b0647a8061bdb896
import be.vinci.pae.domain.UserDTO;

public interface UserUCC {

  /**
   * {@inheritDoc}This method is the use case method to register a user.
   * 
   * @param userDTO - UserDTO, user that has most of it fields full, fulfilled by the front-end.
   * @return return false if failed, true if succeed.
   */
  boolean register(UserDTO userDTO);

  /**
   * {@inheritDoc}This method is the use case method.
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
  
  /**
   * {@inheritDoc} Returns the userDTO with the pseudo
   * @param pseudo - String
   * @return UserDTO that match the pseudo or null
   */
  UserDTO getCustomer(String pseudo);
  
  /**
   * {@inheritDoc} Returns the address with the id
   * @param idAdress - integer
   * @return AddressDTO
   */
  AddressDTO getAdress(int idAdress);

}
