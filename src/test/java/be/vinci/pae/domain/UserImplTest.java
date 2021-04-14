package be.vinci.pae.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Classe de tests de la classe UserImpl")
public class UserImplTest {

  LocalDateTime date = LocalDateTime.now();
  UserImpl userTest = new UserImpl();


  @DisplayName("Test de la méthode getNumUtilisateur")
  @Test
  void testGetNumUtilisateur() {
    userTest.setIdUser(0);
    assertEquals(0, userTest.getIdUser(), "Le numero d'utilisateur renvoye est incorrect");
  }

  @DisplayName("Test de la méthode setNumUtilisateur")
  @Test
  void testSetNumUtilisateur() {
    userTest.setIdUser(1);
    assertEquals(1, userTest.getIdUser());
    userTest.setIdUser(0);
  }

  @DisplayName("Test de la méthode getNom")
  @Test
  void testGetNom() {
    userTest.setLastName("Albert");
    assertEquals("Albert", userTest.getLastName());
  }

  @DisplayName("Test de la méthode setNom")
  @Test
  void testSetNom() {
    userTest.setLastName("Francis");
    assertEquals("Francis", userTest.getLastName());
    userTest.setLastName("Albert");
  }

  @DisplayName("Test de la méthode getMotDePasse")
  @Test
  void testGetMotDePasse() {
    userTest.setPassword("test123");
    assertEquals("test123", userTest.getPassword());
  }

  @DisplayName("Test de la méthode setMotDePasse")
  @Test
  void testSetMotDePasse() {
    userTest.setPassword("test456");
    assertEquals("test456", userTest.getPassword());
    userTest.setPassword("test123");
  }

  @DisplayName("Test de la méthode getPrenom")
  @Test
  void testGetPrenom() {
    userTest.setFirstName("Roger");
    assertEquals("Roger", userTest.getFirstName());
  }

  @DisplayName("Test de la méthode setPrenom")
  @Test
  void testSetPrenom() {
    userTest.setFirstName("Henry");
    assertEquals("Henry", userTest.getFirstName());
    userTest.setFirstName("Roger");
  }

  @DisplayName("Test de la méthode getEmail")
  @Test
  void testGetEmail() {
    userTest.setEmail("testmethod@vinci.be");
    assertEquals("testmethod@vinci.be", userTest.getEmail());
  }

  @DisplayName("Test de la méthode setEmail")
  @Test
  void testSetEmail() {
    userTest.setEmail("set@vinci.be");
    assertEquals("set@vinci.be", userTest.getEmail());
    userTest.setEmail("testmethod@vinci.be");
  }

  @DisplayName("Test de la méthode getDateInscription")
  @Test
  void testGetDateInscription() {
    userTest.setRegistrationDate(date);
    assertEquals(date, userTest.getRegistrationDate());
  }

  @DisplayName("Test de la méthode setDateInscription")
  @Test
  void testSetDateInscription() {
    LocalDateTime dateTest = LocalDateTime.now();
    userTest.setRegistrationDate(dateTest);
    assertEquals(dateTest, userTest.getRegistrationDate());
    userTest.setRegistrationDate(date);
  }

  @DisplayName("Test de la méthode getRole")
  @Test
  void testGetRole() {
    userTest.setRole("tester");
    assertEquals("tester", userTest.getRole());
  }

  @DisplayName("Test de la méthode setRole")
  @Test
  void testSetRole() {
    userTest.setRole("hacker");
    assertEquals("hacker", userTest.getRole());
    userTest.setRole("tester");
  }

  @DisplayName("Test de la méthode getAdresse")
  @Test
  void testGetAdresse() {
    userTest.setAddress(0);
    assertEquals(0, userTest.getAddress());
  }

  @DisplayName("Test de la méthode setAdresse")
  @Test
  void testSetAdresse() {
    userTest.setAddress(1);
    assertEquals(1, userTest.getAddress());
    userTest.setAddress(0);
  }

  @DisplayName("Test de la méthode getPseudo")
  @Test
  void testGetPseudo() {
    userTest.setUsername("Testy");
    assertEquals("Testy", userTest.getUsername());
  }

  @DisplayName("Test de la méthode setPseudo")
  @Test
  void testSetPseudo() {
    userTest.setUsername("Testo");
    assertEquals("Testo", userTest.getUsername());
    userTest.setUsername("Testy");
  }

  @DisplayName("Test de la méthode password_encryption")
  @Test
  void testPasswordEncryption() {
    userTest.setPassword(userTest.passwordEncryption("test123"));
    assertNotEquals(userTest.getPassword(), "test123");
  }

  @DisplayName("Test de la méthode passwordCheck")
  @Test
  void testPasswordCheck() {
    userTest.setPassword(userTest.passwordEncryption("test123"));
    assertTrue(userTest.passwordCheck("test123"));
  }
}
