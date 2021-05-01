package be.vinci.pae.domain;

import java.util.List;

class MockUserDAO implements UserDAO {

  @Override
  public List<UserDTO> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean insert(UserDTO user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UserDTO findByID(int userID) {
    // TODO Auto-generated method stub
    return null;
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

  @Override
  public UserDTO findByUsername(String pseudo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserDTO findByLastName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AddressDTO getAdress(int id) {
    // TODO Auto-generated method stub
    return null;
  }

}
