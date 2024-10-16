package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PosnTest {
  private Posn posn;
  private Integer x;
  private Integer y;

  @BeforeEach
  void setUp() {
    x = 0;
    y = 1;
    posn = new Posn(x, y);
  }

  @Test
  void getX() {
    assertEquals(x, posn.getX());
  }

  @Test
  void getY() {
    assertEquals(y, posn.getY());
  }

  @Test
  void testEquals_Reflexive() {
    assertTrue(posn.equals(posn));
  }

  @Test
  void testEquals_Symmetric() {
    Posn testPosn1 = new Posn(0, 1);
    assertTrue(posn.equals(testPosn1));
    assertTrue(testPosn1.equals(posn));

    Posn testPosn2 = new Posn(0, 0);
    assertFalse(posn.equals(testPosn2));
    assertFalse(testPosn2.equals(posn));
  }

  @Test
  void testEquals_Transitive() {
    Posn testPosn1 = new Posn(0, 1);
    Posn testPosn2 = new Posn(0, 1);
    assertTrue(posn.equals(testPosn1));
    assertTrue(testPosn1.equals(testPosn2));
    assertTrue(posn.equals(testPosn2));
  }

  @Test
  void testEquals_Consistent() {
    Posn testPosn = new Posn(1, 1);
    assertEquals(posn.equals(testPosn), posn.equals(testPosn));
  }

  @Test
  void testEquals_NullObj() {
    assertFalse(posn.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not a Posn Object";
    assertFalse(posn.equals(otherClassObject));
  }

  @Test
  public void testEquals_NullX() {
    Posn testPosn1 = new Posn(null, 1);
    Posn testPosn2 = new Posn(null, 1);
    assertFalse(posn.equals(testPosn1));
    assertFalse(testPosn1.equals(posn));
    assertTrue(testPosn1.equals(testPosn2));
  }

  @Test
  public void testEquals_NullY() {
    Posn testPosn1 = new Posn(0, null);
    Posn testPosn2 = new Posn(0, null);
    assertFalse(posn.equals(testPosn1));
    assertFalse(testPosn1.equals(posn));
    assertTrue(testPosn1.equals(testPosn2));
  }

  @Test
  void testHashCode_MultiInvoke() {
    assertEquals(posn.hashCode(), posn.hashCode());
  }

  @Test
  void testHashCode_SameValue() {
    Posn testPosn = new Posn(0, 1);
    assertTrue(posn.equals(testPosn));
    assertEquals(posn.hashCode(), testPosn.hashCode());
  }

  @Test
  void testHashCode_DiffValue() {
    Posn testPosn = new Posn(0, 0);
    assertFalse(posn.equals(testPosn));
    assertNotEquals(posn.hashCode(), testPosn.hashCode());
  }

  @Test
  void testHashCode_NullValue() {
    Posn testPosn = new Posn(null, null);
    assertEquals(0, testPosn.hashCode());
  }

  @Test
  void testToString() {
    String expectedStr = "Posn{x=0, y=1}";
    assertEquals(expectedStr, posn.toString());
  }
}