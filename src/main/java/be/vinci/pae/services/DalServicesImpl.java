package be.vinci.pae.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;
import be.vinci.pae.exceptions.FatalException;

import be.vinci.pae.utils.Config;


public class DalServicesImpl implements DalServices, DalTransactions {

  private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
  private BasicDataSource bds;
  // private String driverJdbc = "org.postgresql.Driver"; // utile ?
  private static final String DB_STRING_CONNECTION = Config.getProperty("url");
  private static final String DB_USERNAME = Config.getProperty("user");
  private static final String DB_PASSWORD = Config.getProperty("password");
  private static final int DB_MINCONNECTION = Config.getIntProperty("minIdle");
  private static final int DB_MAXCONNECTION = Config.getIntProperty("maxIdle");
  // private String driverClassName = "org.h2.Driver";


  /**
   * Constructor of the class
   */
  public DalServicesImpl() {
    bds = new BasicDataSource();
    // bds.setDriverClassName(driverJdbc); //utile ?
    bds.setUrl(DB_STRING_CONNECTION);
    bds.setUsername(DB_USERNAME);
    bds.setPassword(DB_PASSWORD);
    bds.setMinIdle(DB_MINCONNECTION);
    bds.setMaxIdle(DB_MAXCONNECTION);
  }


  @Override
  public PreparedStatement getPreparedStatement(String sqlQuery) throws FatalException {
    PreparedStatement ps = null;

    Connection connection = threadLocal.get();
    if (connection == null) {
      try {
        connection = bds.getConnection();
      } catch (SQLException e) {
        throw new FatalException(e.getMessage(), e);
      }
      threadLocal.set(connection);
    }
    try {
      ps = connection.prepareStatement(sqlQuery);
    } catch (SQLException e) {
      throw new FatalException(e.getMessage(), e);
    }

    return ps;
  }

  @Override
  public PreparedStatement getPreparedStatementReturningId(String sqlQuery) {
    PreparedStatement ps = null;

    Connection connection = threadLocal.get();
    if (connection == null) {
      try {
        connection = bds.getConnection();
      } catch (SQLException e) {
        throw new FatalException(e.getMessage(), e);
      }
      threadLocal.set(connection);
    }
    try {
      ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
    } catch (SQLException e) {
      throw new FatalException(e.getMessage(), e);

    }

    return ps;
  }



  @Override
  public void startTransaction() {
    Connection connection = threadLocal.get();
    try {
      if (connection == null) {
        connection = bds.getConnection();
        threadLocal.set(connection);
        System.out.println("Nouvelle connexion cr��e");
      }
      connection.setAutoCommit(false);
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

  }

  @Override
  public void commitTransaction() {
    Connection connection = threadLocal.get();
    try {
      connection.commit();
      threadLocal.remove();
      connection.close();
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

  }

  @Override
  public void rollBackTransaction() throws FatalException {
    Connection connection = threadLocal.get();

    try {
      connection.rollback();
      threadLocal.remove();
      connection.close();
      System.out.println("a RollBack has been done");
    } catch (Exception e) {
      throw new FatalException(e.getMessage(), e);
    }

  }

}
