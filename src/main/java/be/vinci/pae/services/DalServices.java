package be.vinci.pae.services;



import java.sql.PreparedStatement;
import be.vinci.pae.exceptions.DataBaseException;

public interface DalServices {

  /**
   * {@inheritDoc} This method takes a query in parameter and return it PreparedStatement.
   * 
   * @param sqlQuery - String, query.
   * @return PreparedStatement.
   * @throws DataBaseException
   */
  PreparedStatement getPreparedStatement(String sqlQuery) throws DataBaseException;

  PreparedStatement getPreparedStatementReturningId(String sqlQuery) throws DataBaseException;



}
