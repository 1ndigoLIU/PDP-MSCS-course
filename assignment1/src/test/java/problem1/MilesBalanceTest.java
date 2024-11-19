package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MilesBalanceTest {
  private MilesBalance mb;
  private MilesBalance sameMb;

  @BeforeEach
  void setUp() {
    mb = new MilesBalance();
    sameMb = new MilesBalance();

    mb.setMilesAvailable(10000);
    mb.setMilesEarnedThisYear(3000);
    mb.setMilesExpiringThisYear(2000);

    sameMb.setMilesAvailable(10000);
    sameMb.setMilesEarnedThisYear(3000);
    sameMb.setMilesExpiringThisYear(2000);
  }

  @Test
  void setMilesAvailable() {
    mb.setMilesAvailable(20000);
    assertEquals(20000, mb.getMilesAvailable());
  }

  @Test
  void setMilesEarnedThisYear() {
    mb.setMilesEarnedThisYear(5000);
    assertEquals(5000, mb.getMilesEarnedThisYear());
  }

  @Test
  void setMilesExpiringThisYear() {
    mb.setMilesExpiringThisYear(3000);
    assertEquals(3000, mb.getMilesExpiringThisYear());
  }

  @Test
  void getMilesAvailable() {
    assertEquals(10000, mb.getMilesAvailable());
  }

  @Test
  void getMilesEarnedThisYear() {
    assertEquals(3000, mb.getMilesEarnedThisYear());
  }

  @Test
  void getMilesExpiringThisYear() {
    assertEquals(2000, mb.getMilesExpiringThisYear());
  }

  @Test
  void testEquals() {
    assertTrue(mb.equals(mb));
    Object obj1 = null;
    assertFalse(mb.equals(obj1));
    Object obj2 = new Object();
    assertFalse(mb.equals(obj2));
    assertTrue(mb.equals(sameMb));
    sameMb.setMilesAvailable(10001);
    assertFalse(mb.equals(sameMb));
    sameMb.setMilesAvailable(10000);
    sameMb.setMilesEarnedThisYear(3001);
    assertFalse(mb.equals(sameMb));
    sameMb.setMilesEarnedThisYear(3000);
    sameMb.setMilesExpiringThisYear(2001);
    assertFalse(mb.equals(sameMb));
  }

  @Test
  void testHashCode() {
    assertEquals(mb.hashCode(), sameMb.hashCode());
  }
}