package be.vinci.pae.usecases;

import be.vinci.pae.domain.SellingDTO;
import java.util.List;

public interface SellingUCC {

  /**
   * {@inheritDoc}This method is part of the use case method to indicate the selling of a
   * furniture.
   *
   * @param sellingDTO - that has most of it fields full, fulfilled by the front-end.
   * @return return false if failed, true if succeed.
   */
  boolean confirmSelling(SellingDTO sellingDTO);

  /**
   * {@inheritDoc} This method returns all the sales in a list
   *
   * @return List of the sales
   */
  List<SellingDTO> seeAll();
}
