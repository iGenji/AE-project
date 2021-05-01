package be.vinci.pae.domain;

import java.util.List;

public interface FurnitureDAO {

  /**
   * {@inheritDoc} This method returns a furniture DTO fulfilled from the database with the ID that
   * was given.
   *
   * @param furnitureID - int.
   * @return The furniture or null if not found.
   */
  FurnitureDTO findByID(int furnitureID);

  /**
   * {@inheritDoc} This method insert into the database the FurnitureDTO.
   *
   * @param furniture - FurnitureDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean insert(FurnitureDTO furniture);

  /**
   * {@inheritDoc} This method delete the furniture DTO in argument from the database.
   *
   * @param furniture - FurnitureDTO.
   * @return true if it succeed, false if it failed.
   */
  boolean delete(FurnitureDTO furniture);

  /**
   * {@inheritDoc} This method update the furniture DTO in argument from the database.
   *
   * @param furniture - FurnitureDTO.
   * @return the new furniture DTO or null if the furniture was not found.
   */
  FurnitureDTO update(FurnitureDTO furniture);

  /**
   * {@inheritDoc} This method update the state of the furniture DTO in argument from the database.
   *
   * @param furniture - FurnitureDTO.
   * @return the new furniture DTO or null if the furniture was not found.
   */
  FurnitureDTO updateState(FurnitureDTO furniture);

  /**
   * {@inheritDoc} This method returns all furniture from the database
   *
   * @return List of all furniture from the database
   */
  List<FurnitureDTO> findAll();
}
