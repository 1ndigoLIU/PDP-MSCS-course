package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationTest {

  private double latitude;
  private double longitude;
  private Location location;

  @BeforeEach
  void setUp() {
    latitude = 47.62;
    longitude = -122.33;
    location = new Location(latitude, longitude);
  }

  @Test
  void getLatitude() {
    assertEquals(latitude, location.getLatitude());
  }

  @Test
  void getLongitude() {
    assertEquals(longitude, location.getLongitude());
  }

  @Test
  void testEquals() {
    assertTrue(location.equals(location));
    Object obj1 = null;
    assertFalse(location.equals(obj1));
    Object obj2 = new Object();
    assertFalse(location.equals(obj2));
    Location sameLocation = new Location(latitude, longitude);
    assertTrue(location.equals(sameLocation));
    Location diffLocation1 = new Location(latitude + 1, longitude);
    assertFalse(location.equals(diffLocation1));
    Location diffLocation2 = new Location(latitude, longitude + 1);
    assertFalse(location.equals(diffLocation2));
  }

  @Test
  void testHashCode() {
    Location sameLocation = new Location(latitude, longitude);
    assertEquals(location.hashCode(), sameLocation.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "Location{latitude=47.62, longitude=-122.33}";
    assertEquals(exceptedString, location.toString());
  }
}