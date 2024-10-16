package problem1;

import java.util.Objects;

/**
 * Represent name containing athlete's first, middle and last name
 * @author Xihao Liu
 */
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;

  /**
   * Constructs a name, based upon all the provided input parameters
   * Return Name object
   * @param firstName - first name, expressed as a String
   * @param middleName - middle name, expressed as a String
   * @param lastName - last name, expressed as a String
   */
  public Name(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * Getter method to get the first name
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Getter method to get the middle name
   * @return the middle name
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Getter method to get the last name
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
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
    return Objects.equals(firstName, name.getFirstName())
        && Objects.equals(middleName, name.getMiddleName())
        && Objects.equals(lastName, name.getLastName());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    if (middleName == null || middleName.isEmpty()) {
      return firstName + " " + lastName;
    }
    return firstName + " " + middleName + " " + lastName;
  }
}