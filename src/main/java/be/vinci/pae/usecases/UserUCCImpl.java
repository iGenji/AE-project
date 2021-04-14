package be.vinci.pae.usecases;



import be.vinci.pae.domain.AddressDAO;
import be.vinci.pae.domain.AddressDTO;
import be.vinci.pae.domain.User;
import be.vinci.pae.domain.UserDAO;
import be.vinci.pae.domain.UserDTO;
import be.vinci.pae.domain.UserFactory;
import be.vinci.pae.exceptions.FatalException;
import be.vinci.pae.services.DalTransactions;
import jakarta.inject.Inject;


// this class never create a user.
public class UserUCCImpl implements UserUCC {

  @Inject
  private UserDAO userDao;

  @Inject
  private AddressDAO addressDao;

  @Inject
  private DalTransactions dal;

  @Inject
  private UserFactory factory;

  @Override
  public boolean register(UserDTO userDTO, AddressDTO addressDTO) {
    try {
      dal.startTransaction();

      UserDTO userDto = userDTO;

      // check if username already in use
      if (userDao.findByUsername(userDto.getUsername()) != null) {
        return false;
      }
      // insert the address and get her autoIncremented Id
      int idAdresse = addressDao.insert(addressDTO);

      // put the id_address into the user
      userDto.setAddress(idAdresse);

      // get the password of the user
      String password = userDto.getPassword();
      // create a UserImpl to have access to the method passwordEncryption()
      User userImplNull = (User) factory.getInstance();
      // encrypt the password
      String passwordBCrypt = userImplNull.passwordEncryption(password);
      userDto.setPassword(passwordBCrypt);

      if (userDao.insert(userDto)) {
        dal.commitTransaction();
        System.out.println("register fait");
        return true;
      }
      dal.rollBackTransaction();
      System.out.println("register failed");
      return false;

    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    } finally {
      rollBackError();
    }
  }


  @Override
  public UserDTO login(String username, String password) {

    UserDTO userDTO = null;

    try {
      dal.startTransaction();
      userDTO = this.userDao.findByUsername(username);
      if (userDTO == null || userDTO.getRole().equals("inactif")) {
        dal.commitTransaction();
        return null;
      }

      User user = (User) userDTO;
      if (user == null || !user.passwordCheck(password)) {
        return null;
      }
      dal.commitTransaction();
      userDTO = (UserDTO) user;
      return userDTO;
    } catch (Exception e) {
      rollBackError();
      throw new FatalException(e.getMessage(), e);
    }



  }

  /**
   * {@inheritDoc} This method is used to roll back the database
   * if an exception was caught.
   * It also frees the connection and release the thread
   */
  private void rollBackError() {
    try {
      dal.rollBackTransaction();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FatalException(e.getMessage(), e);
    }
  }


}

