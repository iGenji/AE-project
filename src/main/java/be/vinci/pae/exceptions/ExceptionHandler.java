package be.vinci.pae.exceptions;

import java.sql.SQLException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ExceptionHandler extends SQLException {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ExceptionHandler(Throwable cause) {
    super();
  }


  public ExceptionHandler(String message) {
    super();
  }

}
