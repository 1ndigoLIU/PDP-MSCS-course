package problem1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlyerNameTest {

  private FlyerName flyerName;
  private FlyerName expectedFlyerName;

  @BeforeEach
  void setUp() {
    flyerName = new FlyerName("Xihao", "Indigo", "Liu");
    expectedFlyerName = new FlyerName("Xihao", "Indigo", "Liu");
  }

  @Test
  void getFirstName() {
    assertEquals("Xihao", flyerName.getFirstName());
  }

  @Test
  void getMiddleName() {
    assertEquals("Indigo", flyerName.getMiddleName());
  }

  @Test
  void getLastName() {
    assertEquals("Liu", flyerName.getLastName());
  }

  @Test
  void testEquals() {
    assertTrue(flyerName.equals(flyerName));
    Object obj1 = null;
    assertFalse(flyerName.equals(obj1));
    Object obj2 = new Object();
    assertFalse(flyerName.equals(obj2));
    assertTrue(flyerName.equals(expectedFlyerName));
    FlyerName diffName1 = new FlyerName("diffFirstName", "Indigo", "Liu");
    assertFalse(flyerName.equals(diffName1));
    FlyerName diffName2 = new FlyerName("Xihao", "diffMiddleName", "Liu");
    assertFalse(flyerName.equals(diffName2));
    FlyerName diffName3 = new FlyerName("Xihao", "Indigo", "diffLastName");
    assertFalse(flyerName.equals(diffName3));
  }

  @Test
  void testHashCode() {
    assertEquals(flyerName.hashCode(), expectedFlyerName.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "FlyerName{firstName='Xihao', middleName='Indigo', lastName='Liu'}";
    assertEquals(exceptedString, flyerName.toString());
  }
}