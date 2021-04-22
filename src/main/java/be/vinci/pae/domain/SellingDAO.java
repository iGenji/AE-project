package be.vinci.pae.domain;

import java.util.List;

public interface SellingDAO {

  /**
   * {@inheritDoc} This method returns a list of all the sellings that the database has.
   *
   * @return List of sellings.
   */
  List<SellingDTO> findAll();

  /**
   * {@inheritDoc} This method returns a selling DTO fulfilled
   * from the database with the ID that was given.
   *
   * @param sellingID - int.
   * @return The selling or null if not found.
   */
  SellingDTO findByID(int sellingID);

  /**
   * {@inheritDoc} This method returns a selling DTO fulfilled
   * from the database with the ID that was given.
   *
   * @param selling - sellingDTO.
   * @return The selling id or null if not found.
   */
  int findByAllAttributes(SellingDTO selling);

  /**
   * {@inheritDoc} This method returns a selling DTO fulfilled from
   * the database with the furniture ID that was given.
   *
   * @param furniture - int.
   * @return The selling or null if not found.
   */
  SellingDTO findByFurnitureId(int furniture);

  /**
   * {@inheritDoc} This method returns a list of all the sellings
   * that the database has with the user ID that was given.
   *
   * @param user - int.
   * @return List of sellings.
   */
  List<SellingDTO> findByUser(int user);

  /**
   * {@inheritDoc} This method returns a list of all the sellings
   * that the database has with the state that was given.
   *
   * @param state - String.
   * @return List of sellings.
   */
  List<SellingDTO> findByState(String state);

  /**
   * {@inheritDoc} This method insert into the database the SellingDTO.
   *
   * @param selling - SellingDTO.
   * @return the id of the selling if it succeed, -1 if it failed.
   */
  int insert(SellingDTO selling);

  /**
   * {@inheritDoc} This method delete the selling DTO in argument from the database.
   *
   * @param selling - SellingDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean delete(SellingDTO selling);

  /**
   * {@inheritDoc} This method update the selling DTO in argument from the database.
   *
   * @param selling - SellingDTO.
   * @return the new selling DTO or null if the selling was not found.
   */
  Selling update(SellingDTO selling);

}
