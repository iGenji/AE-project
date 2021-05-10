package be.vinci.pae.main;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import be.vinci.pae.exceptions.LoggingExceptionMapper;
import be.vinci.pae.utils.ApplicationBinder;
import be.vinci.pae.utils.Config;

/**
 * Main class.
 *
 */
public class Main {

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
   * 
   * @return Grizzly HTTP server.
   */
  public static HttpServer startServer() {
    // Create a resource config that scans for JAX-RS resources and providers
    final ResourceConfig rc = new ResourceConfig().packages("be.vinci.pae.api")
        // .packages("org.glassfish.jersey.examples.jackson")
        .register(JacksonFeature.class).register(ApplicationBinder.class)
        .register(LoggingExceptionMapper.class).register(MultiPartFeature.class) // upload
        .property("jersey.config.server.wadl.disableWadl", true);

    // Create and start a new instance of grizzly http server
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(Config.getProperty("BaseUri")), rc);
  }

  /**
   * Main method.
   *
   * @param args - Strings[] that contains a list of arguments
   * @throws IOException for security
   */
  public static void main(String[] args) throws IOException {
    // Load properties file
    Config.load("dev.properties");
    // Start the server
    final HttpServer server = startServer();
    System.out.println("Jersey app started at " + Config.getProperty("BaseUri"));
    // Listen to key press and shutdown server
    System.out.println("Hit enter to stop it...");
    System.in.read();
    server.shutdownNow();
  }

}
