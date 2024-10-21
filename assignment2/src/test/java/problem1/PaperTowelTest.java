package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaperTowelTest {

  private PaperTowel basePaperTowel;

  @BeforeEach
  void setUp() {
    basePaperTowel = new PaperTowel("Manufacturer A", "PaperTowel A", 15.99, 18, 6);
  }

  @Test
  void testEquals() {
    assertEquals(basePaperTowel, basePaperTowel);
  }

  @Test
  void testHashCode() {
    assertEquals(basePaperTowel.hashCode(), basePaperTowel.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Paper Towel{manufacturer=Manufacturer A, name=PaperTowel A, "
        + "price=15.99, minimum age=18, units=6}";
    assertEquals(expectedString, basePaperTowel.toString());
  }
}
