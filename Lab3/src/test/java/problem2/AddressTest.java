package problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {
  private Address address;
  private String streetAndNumber;
  private String city;
  private String zipCode;
  private String state;
  private String country;

  @BeforeEach
  void setUp() {
    streetAndNumber = "225 Terry Ave N";
    city = "Seattle";
    zipCode = "98109";
    state = "WA";
    country = "USA";
    address = new Address(streetAndNumber, city, zipCode, state, country);
  }

  @Test
  void getStreetAndNumber() {
    assertEquals("225 Terry Ave N", address.getStreetAndNumber());
  }

  @Test
  void setStreetAndNumber() {
    String newStreetAndNumber = "226 Terry Ave N";
    address.setStreetAndNumber(newStreetAndNumber);
    assertEquals(newStreetAndNumber, address.getStreetAndNumber());
  }

  @Test
  void getCity() {
    assertEquals("Seattle", address.getCity());
  }

  @Test
  void setCity() {
    String newCity = "Shoreline";
    address.setCity(newCity);
    assertEquals(newCity, address.getCity());
  }

  @Test
  void getZipCode() {
    assertEquals("98109", address.getZipCode());
  }

  @Test
  void setZipCode() {
    String newZipCode = "98108";
    address.setZipCode(newZipCode);
    assertEquals(newZipCode, address.getZipCode());
  }

  @Test
  void getState() {
    assertEquals("WA", address.getState());
  }

  @Test
  void setState() {
    String newState = "CA";
    address.setState(newState);
    assertEquals(newState, address.getState());
  }

  @Test
  void getCountry() {
    assertEquals("USA", address.getCountry());
  }

  @Test
  void setCountry() {
    String newCountry = "UK";
    address.setCountry(newCountry);
    assertEquals(newCountry, address.getCountry());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(address.equals(address));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(address.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not an Address Object";
    assertFalse(address.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffStreetAndNumber() {
    Address testAddress = new Address("226 Terry Ave N", "Seattle", "98109", "WA", "USA");
    assertFalse(address.equals(testAddress));
  }

  @Test
  public void testEquals_DiffCity() {
    Address testAddress = new Address("225 Terry Ave N", "Shoreline", "98109", "WA", "USA");
    assertFalse(address.equals(testAddress));
  }

  @Test
  public void testEquals_DiffZipCode() {
    Address testAddress = new Address("225 Terry Ave N", "Seattle", "98108", "WA", "USA");
    assertFalse(address.equals(testAddress));
  }

  @Test
  public void testEquals_DiffState() {
    Address testAddress = new Address("225 Terry Ave N", "Seattle", "98109", "CA", "USA");
    assertFalse(address.equals(testAddress));
  }

  @Test
  public void testEquals_DiffCountry() {
    Address testAddress = new Address("225 Terry Ave N", "Seattle", "98109", "WA", "UK");
    assertFalse(address.equals(testAddress));
  }

  @Test
  public void testEquals_AllEqual() {
    Address testAddress = new Address("225 Terry Ave N", "Seattle", "98109", "WA", "USA");
    assertTrue(address.equals(testAddress));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(streetAndNumber, city, zipCode, state, country);
    assertEquals(expectedHash, address.hashCode());
  }

  @Test
  void testToString() {
    String expectedStr = "address: {street and number: 225 Terry Ave N, "
        + "city: Seattle, zipCode: 98109, state: WA, country: USA}";
    assertEquals(expectedStr, address.toString());
  }
}