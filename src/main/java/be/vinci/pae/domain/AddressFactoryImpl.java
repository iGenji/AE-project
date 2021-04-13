package be.vinci.pae.domain;


public class AddressFactoryImpl implements AddressFactory {

  @Override
  public AddressDTO getInstance() {
    return new AddressImpl();
  }
}
