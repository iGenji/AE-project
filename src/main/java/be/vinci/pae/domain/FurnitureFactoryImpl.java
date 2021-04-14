package be.vinci.pae.domain;

public class FurnitureFactoryImpl implements FurnitureFactory {

  @Override
  public FurnitureDTO getInstance() {
    // TODO Auto-generated method stub
    return new FurnitureImpl();
  }

}
