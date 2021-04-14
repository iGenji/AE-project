package be.vinci.pae.domain;

public class UserFactoryImpl implements UserFactory {

  

  @Override
  public UserDTO getInstance() {
    return new UserImpl();
  }

}
