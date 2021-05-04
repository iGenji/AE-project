package be.vinci.pae.utils;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public interface MethodDuplicated {

  /**
   * {@inheritDoc} this method return Response.status(Status.CONFLICT) build with the text
   *
   * @param text - String.
   * @return Response.status(Status.CONFLICT) build with the text.
   */
  public static Response statusConflict(String text) {
    return Response.status(Status.CONFLICT).entity(text).type(MediaType.TEXT_PLAIN).build();
  }

}
