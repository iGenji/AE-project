package be.vinci.pae.domain;

public class SellingFactoryImpl implements SellingFactory {

  @Override
  public SellingDTO getInstance() {
    return new SellingImpl();
  }
}
