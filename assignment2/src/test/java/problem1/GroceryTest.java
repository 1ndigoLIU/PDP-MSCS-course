package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GroceryTest {

  private Grocery baseGrocery;

  @BeforeEach
  void setUp() {
    baseGrocery = new Beer("Manufacturer A", "Grocery A", 5.99, 0, 16.0);
  }

  @Test
  void getWeight() {
    assertEquals(16.0, baseGrocery.getWeight());
  }

  @Test
  void testEquals_DiffParent() {
    Grocery diffParent = new Beer("Manufacturer B", "Grocery A", 5.99, 0, 16.0);
    assertNotEquals(baseGrocery, diffParent);
  }

  @Test
  void testEquals_DiffWeight() {
    Grocery diffWeight = new Beer("Manufacturer A", "Grocery A", 5.99, 0, 20.0);
    assertNotEquals(baseGrocery, diffWeight);
  }

  @Test
  void testEquals_SameValues() {
    Grocery sameGrocery = new Beer("Manufacturer A", "Grocery A", 5.99, 0, 16.0);
    assertEquals(baseGrocery, sameGrocery);
  }

  @Test
  void testEquals_DiffClass() {
    String notAGrocery = "I am not a grocery";
    assertNotEquals(baseGrocery, notAGrocery);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(baseGrocery, null);
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(baseGrocery, baseGrocery);
  }

  @Test
  void testHashCode() {
    Grocery sameGrocery = new Beer("Manufacturer A", "Grocery A", 5.99, 0, 16.0);
    assertEquals(baseGrocery.hashCode(), sameGrocery.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Grocery{manufacturer=Manufacturer A, name=Grocery A, price=5.99, minimum age=0, weight=16.0}";
    Grocery grocery = new Grocery("Manufacturer A", "Grocery A", 5.99, 0, 16.0) {
    };
    assertEquals(expectedString, grocery.toString());
  }
}
