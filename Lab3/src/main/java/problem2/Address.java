package problem2;

import java.util.Objects;

/**
 * Represent restaurant's address containing
 * address's street and number, city, zip code, state, and country
 * @author Xihao Liu
 */
public class Address {
  private String streetAndNumber;
  private String city;
  private String zipCode;
  private String state;
  private String country;

  /**
   * Constructs an address, based upon all the provided input parameters
   * Return Address object
   * @param streetAndNumber - address's street and number, expressed as a String
   * @param city - address's city, expressed as a String
   * @param zipCode - address's zip code, expressed as a String
   * @param state - address's state, expressed as a String
   * @param country - address's country, expressed as a String
   */
  public Address(String streetAndNumber, String city, String zipCode, String state, String country) {
    this.streetAndNumber = streetAndNumber;
    this.city = city;
    this.zipCode = zipCode;
    this.state = state;
    this.country = country;
  }

  /**
   * Getter method to get the street and number
   * @return the street and number
   */
  public String getStreetAndNumber() {
    return streetAndNumber;
  }

  /**
   * Setter method to set the street and number
   * @param streetAndNumber - new street and number
   */
  public void setStreetAndNumber(String streetAndNumber) {
    this.streetAndNumber = streetAndNumber;
  }

  /**
   * Getter method to get the city
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Setter method to set the city
   * @param city - new city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Getter method to get the zip code
   * @return the zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Setter method to set the zip code
   * @param zipCode - new zip code
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Getter method to get the state
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * Setter method to set the state
   * @param state - new state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Getter method to get the country
   * @return the country
   */
  public String getCountry() {
    return country;
  }
  /**
   * Setter method to set the country
   * @param country - new country
   */
  public void setCountry(String country) {
    this.country = country;
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
    Address address = (Address) o;
    return Objects.equals(streetAndNumber, address.getStreetAndNumber()) &&
        Objects.equals(city, address.getCity()) &&
        Objects.equals(zipCode, address.getZipCode()) &&
        Objects.equals(state, address.getState()) &&
        Objects.equals(country, address.getCountry());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(streetAndNumber, city, zipCode, state, country);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "address: {" +
        "street and number: " + streetAndNumber +
        ", city: " + city +
        ", zipCode: " + zipCode +
        ", state: " + state +
        ", country: " + country +
        '}';
  }
}
