package be.vinci.pae.domain;

import java.util.List;

public interface AddressDAO {

  /**
   * {@inheritDoc} This method returns a list of all the addresses that the database has.
   * 
   * @return List of addresses.
   */
  List<AddressDTO> findAll();

  /**
   * {@inheritDoc} This method returns a address DTO fulfilled from the database with the ID that was given.
   * 
   * @param address - AddressDTO.
   * @return The id address or null if not found.
   */
  int findByAllAttributes(AddressDTO address);

  /**
   * {@inheritDoc} This method returns a address DTO fulfilled from the database with the ID that was given.
   * 
   * @param addressID - int.
   * @return The address or null if not found.
   */
  AddressDTO findByID(int addressID);

  /**
   * {@inheritDoc} This method returns a list of all the addresses that the database has with the postcode that was given.
   * 
   * @param postcode - int.
   * @return List of addresses.
   */
  List<AddressDTO> findByPostcode(int postcode);


  /**
   * {@inheritDoc} This method returns a list of all the addresses that the database has with the commune that was given.
   * 
   * @param commune - String.
   * @return List of addresses.
   */
  List<AddressDTO> findByCommune(String commune);

  /**
   * {@inheritDoc} This method insert into the database the AddressDTO.
   * 
   * @param address - AddressDTO.
   * @return the id of the address if it succeed, -1 if it failed.
   */
  int insert(AddressDTO address);

  /**
   * {@inheritDoc} This method delete the address DTO in argument from the database.
   * 
   * @param address - AddressDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean delete(AddressDTO address);

  /**
   * {@inheritDoc} This method update the address DTO in argument from the database.
   * 
   * @param address - AddressDTO.
   * @return the new address DTO or null if the address was not found.
   */
  AddressDTO update(AddressDTO address);


}
