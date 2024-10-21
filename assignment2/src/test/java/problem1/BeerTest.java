package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BeerTest {

  private Beer baseBeer;

  @BeforeEach
  void setUp() {
    baseBeer = new Beer("Manufacturer A", "Beer A", 8.99, 21, 12.0);
  }

  @Test
  void testEquals() {
    assertEquals(baseBeer, baseBeer);
  }

  @Test
  void testHashCode() {
    assertEquals(baseBeer.hashCode(), baseBeer.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Bear{manufacturer=Manufacturer A, "
        + "name=Beer A, price=8.99, minimum age=21, weight=12.0}";
    assertEquals(expectedString, baseBeer.toString());
  }
}