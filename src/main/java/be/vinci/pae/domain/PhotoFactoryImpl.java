package be.vinci.pae.domain;

public class PhotoFactoryImpl implements PhotoFactory {

  @Override
  public PhotoDTO getInstance() {
    // TODO Auto-generated method stub
    return new PhotoImpl();
  }
}
