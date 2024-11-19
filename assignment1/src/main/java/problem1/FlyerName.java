package problem1;

import java.util.Objects;

/**
 * Represents	flyer's name information including first, middle and last name
 *
 * @author Xihao Liu
 */
public class FlyerName {

  private String firstName;
  private String middleName;
  private String lastName;

  /**
   * @param firstName  flyer's first name
   * @param middleName flyer's middle name
   * @param lastName   flyer's last name
   */
  public FlyerName(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * get flyer's first name
   *
   * @return firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * get flyer's middle name
   *
   * @return middleName
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * get flyer's last name
   *
   * @return lastName
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
    FlyerName flyerName = (FlyerName) o;
    return Objects.equals(firstName, flyerName.firstName) && Objects.equals(middleName,
        flyerName.middleName) && Objects.equals(lastName, flyerName.lastName);
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "FlyerName{" + "firstName='" + firstName + '\'' + ", middleName='" + middleName + '\''
        + ", lastName='" + lastName + '\'' + '}';
  }
}
