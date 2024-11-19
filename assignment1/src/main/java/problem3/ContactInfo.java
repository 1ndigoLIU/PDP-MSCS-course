package problem3;

import java.util.Objects;

/**
 * Represent contact information of tax filer including his/her name, address, phone number and
 * e-mail
 *
 * @author Xihao Liu
 */
public class ContactInfo {

  private Name name;
  private String address;
  private String phoneNumber;
  private String email;

  /**
   * constructor of class ContactInfo
   *
   * @param name        tax filer's name
   * @param address     tax filer's address
   * @param phoneNumber tax filer's phone number
   * @param email       tax filer's e-mail address
   */
  public ContactInfo(Name name, String address, String phoneNumber, String email) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  /**
   * get tax filer's name
   *
   * @return tax filer's name
   */
  public Name getName() {
    return name;
  }

  /**
   * get tax filer's address
   *
   * @return tax filer's address
   */
  public String getAddress() {
    return address;
  }

  /**
   * get tax filer's phone number
   *
   * @return tax filer's phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * get tax filer's e-mail address
   *
   * @return tax filer's e-mail address
   */
  public String getEmail() {
    return email;
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
    ContactInfo that = (ContactInfo) o;
    return Objects.equals(name, that.name) && Objects.equals(address, that.address)
        && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email);
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, address, phoneNumber, email);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "ContactInfo{" + "name=" + name + ", address='" + address + '\'' + ", phoneNumber='"
        + phoneNumber + '\'' + ", email='" + email + '\'' + '}';
  }
}
