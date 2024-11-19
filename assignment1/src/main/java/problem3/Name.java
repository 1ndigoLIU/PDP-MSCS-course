package problem3;

import java.util.Objects;

/**
 * Represent name of a tax filer including first name and last name
 *
 * @author Xihao Liu
 */
public class Name {

  private String firstName;
  private String lastName;

  /**
   * constructor of class Name
   *
   * @param firstName first name
   * @param lastName  last name
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * override equals method
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Name name = (Name) o;
    return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Name{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
  }
}