package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShampooTest {

  private Shampoo baseShampoo;

  @BeforeEach
  void setUp() {
    baseShampoo = new Shampoo("Manufacturer A", "Shampoo A", 19.99, 0, 3);
  }

  @Test
  void testEquals() {
    assertEquals(baseShampoo, baseShampoo);
  }

  @Test
  void testHashCode() {
    assertEquals(baseShampoo.hashCode(), baseShampoo.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Shampoo{manufacturer=Manufacturer A, name=Shampoo A, "
        + "price=19.99, minimum age=0, units=3}";
    assertEquals(expectedString, baseShampoo.toString());
  }
}