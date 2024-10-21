package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HouseholdTest {

  private Household baseHousehold;

  @BeforeEach
  void setUp() {
    baseHousehold = new Shampoo("Manufacturer A", "Household A", 19.99, 18, 5);
  }

  @Test
  void getUnits() {
    assertEquals(5, baseHousehold.getUnits());
  }

  @Test
  void testEquals_DiffParent() {
    Household diffParent = new Shampoo("Manufacturer A", "Household B", 19.99, 18, 5);
    assertNotEquals(baseHousehold, diffParent);
  }

  @Test
  void testEquals_DiffUnits() {
    Household diffUnits = new Shampoo("Manufacturer A", "Household A", 19.99, 18, 10);
    assertNotEquals(baseHousehold, diffUnits);
  }

  @Test
  void testEquals_SameValues() {
    Household sameHousehold = new Shampoo("Manufacturer A", "Household A", 19.99, 18, 5);
    assertEquals(baseHousehold, sameHousehold);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(baseHousehold, null);
  }

  @Test
  void testEquals_DiffClass() {
    String notAHousehold = "I am not a household";
    assertNotEquals(baseHousehold, notAHousehold);
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(baseHousehold, baseHousehold);
  }

  @Test
  void testHashCode() {
    Household sameHousehold = new Shampoo("Manufacturer A", "Household A", 19.99, 18, 5);
    assertEquals(baseHousehold.hashCode(), sameHousehold.hashCode());
  }

  @Test
  void testToString() {
    Household household = new Household("Manufacturer A", "Household A", 19.99, 18, 5) {
    };
    String expectedString = "Household{manufacturer=Manufacturer A, "
        + "name=Household A, price=19.99, minimum age=18, units=5}";
    assertEquals(expectedString, household.toString());
  }
}
