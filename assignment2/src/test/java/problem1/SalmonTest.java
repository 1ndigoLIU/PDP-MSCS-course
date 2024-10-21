package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SalmonTest {

  private Salmon baseSalmon;

  @BeforeEach
  void setUp() {
    baseSalmon = new Salmon("Manufacturer A", "Salmon A", 29.99, 0, 10.5);
  }

  @Test
  void testEquals() {
    assertEquals(baseSalmon, baseSalmon);
  }

  @Test
  void testHashCode() {
    assertEquals(baseSalmon.hashCode(), baseSalmon.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Salmon{manufacturer=Manufacturer A, name=Salmon A, "
        + "price=29.99, minimum age=0, weight=10.5}";
    assertEquals(expectedString, baseSalmon.toString());
  }
}
