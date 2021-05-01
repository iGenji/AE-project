package be.vinci.pae.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider.App;
import be.vinci.pae.exceptions.DataBaseException;


public class DalServicesImpl implements DalServices, DalTransactions {


  private Properties properties;
  private String url;
  private String user;
  private String password;
  private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
  private BasicDataSource bds;
  private String driverJdbc = "org.postgresql.Driver";
  private String fileConfigName = "config_db.properties";
  // private String driverClassName = "org.h2.Driver";


  /**
   * Constructor of the class.
   */
  public DalServicesImpl() {


    properties = new Properties();

    try {
      properties.load(App.class.getClassLoader().getResourceAsStream(fileConfigName));
      url = properties.getProperty("url");
      user = properties.getProperty("user");
      password = properties.getProperty("password");
    } catch (IOException e) {
      e.getMessage();
      throw new DataBaseException(e.getMessage(), e);
    }

    // String fullUrl = url + port;

    bds = new BasicDataSource();
    bds.setDriverClassName(driverJdbc);
    bds.setUrl(url);
    bds.setUsername(user);
    bds.setPassword(password);
    bds.setMinIdle(10);
    bds.setMaxIdle(20);

  }


  @Override
  public PreparedStatement getPreparedStatement(String sqlQuery) {
    PreparedStatement ps = null;


    Connection connection = threadLocal.get();
    if (connection == null) {
      System.out.println("Pool connection is empty");
      throw new DataBaseException("Error database");
    }
    try {
      ps = connection.prepareStatement(sqlQuery);
    } catch (SQLException e) {
      closeConnections(connection);
      throw new DataBaseException(e.getMessage(), e);
    }

    return ps;
  }

  @Override
  public PreparedStatement getPreparedStatementReturningId(String sqlQuery) {
    PreparedStatement ps = null;


    Connection connection = threadLocal.get();
    if (connection == null) {
      System.out.println("Pool connection is empty");
      throw new DataBaseException("Error database");
    }
    try {
      ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
    } catch (SQLException e) {
      closeConnections(connection);
      throw new DataBaseException(e.getMessage(), e);
    }

    return ps;
  }



  @Override
  public void startTransaction() {
    Connection connection = threadLocal.get();
    try {
      if (connection == null) {
        connection = bds.getConnection();
        if (connection == null) {
          throw new DataBaseException("Connection empty,startTransaction");
        }
        connection.setAutoCommit(false);
        threadLocal.set(connection);
        System.out.println("New connection created");
      }

      connection.beginRequest();
    } catch (SQLException e) {
      closeConnections(connection);
      throw new DataBaseException(e.getMessage(), e);
    }

  }

  @Override
  public void commitTransaction() {
    Connection connection = threadLocal.get();
    try {
      connection.commit();
      threadLocal.remove();
      connection.endRequest();
      connection.close();
      System.out.println("Commit done, connection closed");
    } catch (SQLException e) {
      closeConnections(connection);
      throw new DataBaseException(e.getMessage(), e);
    }

  }

  @Override
  public void rollBackTransaction() {
    Connection connection = threadLocal.get();

    try {
      connection.rollback();
      threadLocal.remove();
      connection.endRequest();
      connection.close();
      System.out.println("RollBack done, connection closed");
    } catch (SQLException e) {
      closeConnections(connection);
      throw new DataBaseException(e.getMessage(), e);
    }

  }

  /**
   * {@inheritDoc} This method close the connection and free the thread
   * 
   * @param connection Connection of the database
   */
  private void closeConnections(Connection connection) {
    try {
      connection.rollback();
      threadLocal.remove();
      connection.endRequest();
      connection.close();
    } catch (SQLException e) {
      closeConnections(connection);
      throw new DataBaseException(e.getMessage(), e);
    }
  }



}
