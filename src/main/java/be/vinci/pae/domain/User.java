package be.vinci.pae.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserImpl.class)
public interface User extends UserDTO {

  /**
   * {@inheritDoc} This method checks if the password is the same as the user's password.
   * 
   * @param password - String that contains the password with Bcrypt.
   * @return true if it succeed, false if it failed. {@inheritDoc}
   */
  boolean passwordCheck(String password);

  /**
   * {@inheritDoc} This method encrypts the password
   * 
   * @param password - String that contains the password without Bcrypt.
   * @return true if it succeed, false if it failed. {@inheritDoc}
   */
  String passwordEncryption(String password);

  /**
   * {@inheritDoc} This method check if the user DTO's role is high enough for the admin role.
   * 
   * @param user - UserDTO the checked user.
   * @return UserDTO with the new password hash or null if it failed. {@inheritDoc}
   */
  boolean checkCanBeAdmin(UserDTO user);

  /**
   * {@inheritDoc} This method changes user DTO's role to admin.
   * 
   * @param user - UserDTO user that has his role upgraded.
   * @return UserDTO if it succeed, null if it failed. {@inheritDoc}
   */
  UserDTO changeToAdmin(UserDTO user);

}
