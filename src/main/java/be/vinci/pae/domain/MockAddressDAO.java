package be.vinci.pae.domain;

import java.util.List;

class MockAddressDAO implements AddressDAO {

  @Override
  public List<AddressDTO> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AddressDTO findByID(int addressID) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AddressDTO> findByPostcode(int postalCode) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AddressDTO> findByCommune(String municipality) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int insert(AddressDTO address) {
    return 0;
    // TODO Auto-generated method stub
  }

  @Override
  public boolean delete(AddressDTO address) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public AddressDTO update(AddressDTO address) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int findByAllAttributes(AddressDTO address) {
    // TODO Auto-generated method stub
    return 0;
  }

}
