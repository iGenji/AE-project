package be.vinci.pae.domain;

import java.util.List;

public class MockPhotoDAO implements PhotoDAO {

  @Override
  public PhotoDTO findByID(int photoID) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<PhotoDTO> findByIDFurniture(int idFurniture) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean insert(String photo, int idMeuble) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delete(PhotoDTO photo) {
    // TODO Auto-generated method stub
    return false;
  }

  

}
