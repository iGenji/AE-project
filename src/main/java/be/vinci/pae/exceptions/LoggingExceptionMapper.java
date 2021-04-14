package be.vinci.pae.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import be.vinci.pae.utils.Config;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoggingExceptionMapper implements ExceptionMapper<Throwable> {

  private static final Logger logger = LogManager.getLogger(LoggingExceptionMapper.class.getName());

  @Override
  public Response toResponse(Throwable exception) {
    System.out.println("toResponse()");
    exception.printStackTrace();
    if (Config.getBoolProperty("SendStackTraceToClient")) {
      return Response.status(getStatusCode(exception)).entity(getEntity(exception)).build();
    }
    return Response.status(getStatusCode(exception)).entity(exception.getMessage()).build();
  }

  /*
   * Get appropriate HTTP status code for an exception.
   */
  private int getStatusCode(Throwable exception) {
    System.out.println("getStatusCode()");
    if (exception instanceof WebApplicationException) {
      logger.error("Error: WebApplication");
      logger.error(exception.getMessage());
      logger.error(exception.getStackTrace());
      System.out.println(exception.getClass().getName());
      return ((WebApplicationException) exception).getResponse().getStatus();
    } else {
      logger.error("Error: Database level");
      System.out.println("DataBaseException instanceOf");
      logger.error(exception.getMessage());
      logger.error(exception.getStackTrace());
    }
    logger.error("Erreur ! getStatusCode");

    return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
  }

  /*
   * Get response body for an exception.
   */
  private Object getEntity(Throwable exception) {
    StringWriter errorMsg = new StringWriter();
    exception.printStackTrace(new PrintWriter(errorMsg));
    return errorMsg.toString();
  }

}
