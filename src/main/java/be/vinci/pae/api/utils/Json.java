package be.vinci.pae.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import views.Views;

public class Json {
  private static final ObjectMapper jsonMapper = new ObjectMapper();


  /**
   * 
   * {@inheritDoc} This method loads data from a file.
   * 
   * @param dbFilePath - String that contains the data base file path.
   * @param collectionName - String that contains the collection name.
   * @param targetClass - Class T that contains the target class.
   * @return a list that contains objects of the targetClass type.
   */
  public static <T> List<T> loadDataFromFile(String dbFilePath, String collectionName,
      Class<T> targetClass) {
    try {
      JsonNode node = jsonMapper.readTree(Paths.get(dbFilePath).toFile());
      JsonNode collection = node.get(collectionName);
      if (collection == null) {
        return new ArrayList<T>();
      }
      return jsonMapper.readerFor(targetClass).readValue(node.get(collectionName));

    } catch (FileNotFoundException e) {
      return new ArrayList<T>();
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<T>();
    }
  }

  /**
   * {@inheritDoc} This method saves data to a file.
   * 
   * @param list - List T that contains a list that we have to save in our database.
   * @param dbFilePath - String that contains the data base file path.
   * @param collectionName - String that contains the collection name.
   * @return a list that contains objects of the targetClass type.
   */
  public static <T> void saveDataToFile(List<T> list, String dbFilePath, String collectionName) {
    try {

      // get all collections
      Path pathToDb = Paths.get(dbFilePath);
      if (!Files.exists(pathToDb)) {
        // write a new collection to the db file
        ObjectNode newCollection = jsonMapper.createObjectNode().putPOJO(collectionName, list);
        jsonMapper.writeValue(pathToDb.toFile(), newCollection);
        return;

      }

      // get all collections
      JsonNode allCollections = jsonMapper.readTree(pathToDb.toFile());

      if (allCollections.has(collectionName)) {
        // remove current collection
        ((ObjectNode) allCollections).remove(collectionName);
      }

      // create a new JsonNode and add it to allCollections
      String currentCollectionAsString = jsonMapper.writeValueAsString(list);
      JsonNode updatedCollection = jsonMapper.readTree(currentCollectionAsString);
      ((ObjectNode) allCollections).putPOJO(collectionName, updatedCollection);

      // write to the db file
      jsonMapper.writeValue(pathToDb.toFile(), allCollections);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * {@inheritDoc} This method load data from a file based on a view.
   * 
   * @param <T> - generic. {@inheritDoc}
   * @param dbFilePath - path of the file.
   * @param jsonViewClass - class of the json view.
   * @param collectionName - name of the Collection.
   * @param targetClass - target class.
   * @return List T - list of datas.
   */
  public static <T> List<T> loadDataFromFileBasedOnView(String dbFilePath, Class<?> jsonViewClass,
      String collectionName, Class<T> targetClass) {
    try {
      JsonNode node = jsonMapper.readTree(Paths.get(dbFilePath).toFile());
      // Get the type at execution because new TypeReference<List<T>>() is not allowed
      JavaType type = jsonMapper.getTypeFactory().constructCollectionType(List.class, targetClass);
      // deserialize using JSON Views : Internal View
      return jsonMapper.readerWithView(jsonViewClass).forType(type)
          .readValue(node.get(collectionName));

    } catch (FileNotFoundException e) {
      return new ArrayList<T>();
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<T>();
    }
  }

  /**
   * {@inheritDoc} This method saves the data to a file based on a view.
   * 
   * @param <T> - Generics.
   * @param list - list of generics.
   * @param jsonViewClass - the json view class.
   * @param dbFilePath - the path of the file.
   * @param collectionName - the collection name.
   */
  public static <T> void saveDataToFileBasedOnView(List<T> list, Class<?> jsonViewClass,
      String dbFilePath, String collectionName) {
    try {
      // get all collections
      Path pathToDb = Paths.get(dbFilePath);
      if (!Files.exists(pathToDb)) {
        // write a new collection to the db file
        ObjectNode newCollection = jsonMapper.createObjectNode().putPOJO(collectionName, list);
        jsonMapper.writeValue(pathToDb.toFile(), newCollection);
        return;

      }

      JsonNode allCollections = jsonMapper.readTree(pathToDb.toFile());

      if (allCollections.has(collectionName)) {
        // remove current collection
        ((ObjectNode) allCollections).remove(collectionName);
      }

      // create a new JsonNode and add it to allCollections
      String currentCollectionAsString =
          jsonMapper.writerWithView(jsonViewClass).writeValueAsString(list);
      // String currentCollectionAsString = jsonMapper.writeValueAsString(list);
      JsonNode updatedCollection = jsonMapper.readTree(currentCollectionAsString);
      ((ObjectNode) allCollections).putPOJO(collectionName, updatedCollection);

      // write to the db file
      jsonMapper.writeValue(pathToDb.toFile(), allCollections);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * {@inheritDoc} This method serialize a public json view.
   * 
   * @param <T> generics.
   * @param item - generics item.
   * @return String serialization of the json.
   */
  public static <T> String serializePublicJsonView(T item) {
    // serialize using JSON Views : Public View
    try {
      return jsonMapper.writerWithView(Views.Public.class).writeValueAsString(item);
    } catch (JsonProcessingException e) {

      e.printStackTrace();
      return null;
    }

  }

  /**
   * {@inheritDoc} This method filter the public json view as a list.
   * 
   * @param <T> - generics.
   * @param list - list of a generic.
   * @param targetClass - the target class.
   * @return List T - list of a generic.
   */
  public static <T> List<T> filterPublicJsonViewAsList(List<T> list, Class<T> targetClass) {

    try {
      System.out.println("List prior to serialization : " + list);
      JavaType type = jsonMapper.getTypeFactory().constructCollectionType(List.class, targetClass);
      // serialize using JSON Views : public view (all fields not required in the
      // views are set to null)
      String publicItemListAsString =
          jsonMapper.writerWithView(Views.Public.class).writeValueAsString(list);
      System.out.println("serializing public Json view: " + publicItemListAsString);
      // deserialize using JSON Views : Public View
      try {
        return jsonMapper.readerWithView(Views.Public.class).forType(type)
            .readValue(publicItemListAsString);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
      }

    } catch (JsonProcessingException e) {

      e.printStackTrace();
      return null;
    }

  }

  /**
   * {@inheritDoc} This method filters the public json view.
   * 
   * @param <T> - generic.
   * @param item - item generic.
   * @param targetClass - the target class.
   * @return T - generic.
   */
  public static <T> T filterPublicJsonView(T item, Class<T> targetClass) {

    try {
      // serialize using JSON Views : public view (all fields not required in the
      // views are set to null)
      String publicItemAsString =
          jsonMapper.writerWithView(Views.Public.class).writeValueAsString(item);
      // deserialize using JSON Views : Public View
      try {
        return jsonMapper.readerWithView(Views.Public.class).forType(targetClass)
            .readValue(publicItemAsString);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
      }

    } catch (JsonProcessingException e) {

      e.printStackTrace();
      return null;
    }

  }

}
