package be.vinci.pae.services;



import java.sql.PreparedStatement;


public interface DalServices {

  /**
   * {@inheritDoc} This method takes a query in parameter and return it PreparedStatement.
   * 
   * @param sqlQuery - String, query.
   * @return PreparedStatement.
   */
  PreparedStatement getPreparedStatement(String sqlQuery);

  /**
   * {@inheritDoc} This method takes a query in parameter and return it PreparedStatement.
   * 
   * @param sqlQuery - String, query.
   * @return PreparedStatement.
   */
  PreparedStatement getPreparedStatementReturningId(String sqlQuery);



}
