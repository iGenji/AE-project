package be.vinci.pae.services;

import java.sql.SQLException;

public interface DalTransactions {

  /**
   * {@inheritDoc} This method is set to start the transaction.
   * 
   * @throws SQLException
   */
  void startTransaction() throws SQLException;

  /**
   * {@inheritDoc} This method is set to commit a transaction.
   * 
   * @throws SQLException
   */
  void commitTransaction() throws SQLException;

  /**
   * {@inheritDoc} This method is set to roll back a transaction.
   * 
   * @throws SQLException
   */
  void rollBackTransaction() throws SQLException;

}
