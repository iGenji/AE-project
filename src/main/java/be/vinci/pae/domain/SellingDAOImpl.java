package be.vinci.pae.domain;

import be.vinci.pae.services.DalServices;
import jakarta.inject.Inject;
import java.util.List;

public class SellingDAOImpl implements SellingDAO{

  @Inject
  private DalServices dalServices;

  @Inject
  private SellingFactory factory;

  @Override
  public List<SellingDTO> findAll() {
    return null;
  }

  @Override
  public SellingDTO findByID(int sellingID) {
    return null;
  }

  @Override
  public int findByAllAttributes(SellingDTO selling) {
    return 0;
  }

  @Override
  public SellingDTO findByFurnitureId(int furniture) {
    return null;
  }

  @Override
  public List<SellingDTO> findByUser(int user) {
    return null;
  }

  @Override
  public List<SellingDTO> findByState(String state) {
    return null;
  }

  @Override
  public int insert(SellingDTO selling) {
    return 0;
  }

  @Override
  public boolean delete(SellingDTO selling) {
    return false;
  }

  @Override
  public Selling update(SellingDTO selling) {
    return null;
  }
}
