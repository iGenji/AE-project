package be.vinci.pae.exceptions;




public class DataBaseException extends RuntimeException {

  private static final long serialVersionUID = -5926196101906096391L;

  public DataBaseException(Throwable cause) {
    super(cause);
  }



  public DataBaseException(String message) {
    super(message);
  }

  public DataBaseException(String message, Throwable cause) {
    super(message, cause);
  }

}
