package be.vinci.pae.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import be.vinci.pae.exceptions.FatalException;

public class Config {

  private static Properties props;

  /**
   * {@inheritDoc} This method loads a file. {@inheritDoc}
   * 
   * @param file - String that contains the file name.{@inheritDoc}
   */
  public static void load(String file) {
    props = new Properties();
    try (InputStream input = new FileInputStream(file)) {
      props.load(input);
    } catch (IOException e) {
      System.out.println("Config fail");
      throw new FatalException();
    }
  }

  /**
   * {@inheritDoc} This method returns property of the file by his key.
   * 
   * @param key - String that contains the key.
   * @return String - property of the file.
   */
  public static String getProperty(String key) {
    return props.getProperty(key);
  }

  /**
   * {@inheritDoc} This method returns Integer property of the file by his key.{@inheritDoc}
   * 
   * @param key - String that contains the key.
   * @return Integer - property of the file.
   */
  public static Integer getIntProperty(String key) {
    return Integer.parseInt(props.getProperty(key));
  }

  /**
   * {@inheritDoc} This method returns Boolean property of the file by his key.{@inheritDoc}
   * 
   * @param key - String that contains the key.
   * @return Integer - property of the file.
   */
  public static boolean getBoolProperty(String key) {
    return Boolean.parseBoolean(props.getProperty(key));
  }

}
