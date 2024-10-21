package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheeseTest {

  private Cheese baseCheese;

  @BeforeEach
  void setUp() {
    baseCheese = new Cheese("Manufacturer A", "Cheese A", 12.99, 18, 5.0);
  }

  @Test
  void testEquals() {
    assertEquals(baseCheese, baseCheese);
  }

  @Test
  void testHashCode() {
    assertEquals(baseCheese.hashCode(), baseCheese.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Cheese{manufacturer=Manufacturer A, name=Cheese A, "
        + "price=12.99, minimum age=18, weight=5.0}";
    assertEquals(expectedString, baseCheese.toString());
  }
}
