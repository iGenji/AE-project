package be.vinci.pae.services;

import java.sql.SQLException;

public interface DalTransactions {

  /**
   * {@inheritDoc} This method is set to start the transaction.
   * 
   */
  void startTransaction();

  /**
   * {@inheritDoc} This method is set to commit a transaction.
   * 
   */
  void commitTransaction();

  /**
   * {@inheritDoc} This method is set to roll back a transaction.
   * 
   */
  void rollBackTransaction();

}
