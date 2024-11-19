package problem1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepositTest {

  private Deposit deposit;
  private FlyerName expectedFlyerName;

  @BeforeEach
  void setUp() {
    deposit = new Deposit(6666, "12longstring", new FlyerName("Xihao", "Indigo", "Liu"));
    expectedFlyerName = new FlyerName("Xihao", "Indigo", "Liu");
  }

  @Test
  void testConstructorException() {
    int milesUp = 10000;
    int milesDown = 1000;
    Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
      new Deposit(999, "black666", expectedFlyerName);
    });
    Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
      new Deposit(10001, "black666", expectedFlyerName);
    });
    String expectedMessage =
        "Deposit milesAmount must be within range (" + milesDown + " ~ " + milesUp + ")!";
    String actualMessage1 = exception1.getMessage();
    assertEquals(actualMessage1, expectedMessage);
    String actualMessage2 = exception2.getMessage();
    assertEquals(actualMessage2, expectedMessage);
  }

  @Test
  void getMilesAmount() {
    assertEquals(6666, deposit.getMilesAmount());
  }

  @Test
  void getRecipientId() {
    assertEquals("12longstring", deposit.getRecipientId());
  }

  @Test
  void getRecipientName() {
    assertEquals(expectedFlyerName, deposit.getRecipientName());
  }

  @Test
  void testEquals() {
    assertTrue(deposit.equals(deposit));
    Object obj1 = null;
    assertFalse(deposit.equals(obj1));
    Object obj2 = new Object();
    assertFalse(deposit.equals(obj2));
    Deposit sameDeposit = new Deposit(6666, "12longstring", expectedFlyerName);
    assertTrue(deposit.equals(sameDeposit));
    Deposit diffDeposit1 = new Deposit(6667, "12longstring", expectedFlyerName);
    assertFalse(deposit.equals(diffDeposit1));
    Deposit diffDeposit2 = new Deposit(6666, "diffreciptid", expectedFlyerName);
    assertFalse(deposit.equals(diffDeposit2));
    Deposit diffDeposit3 = new Deposit(6666, "12longstring",
        new FlyerName("Cong", "Heather", "Ming"));
    assertFalse(deposit.equals(diffDeposit3));
  }

  @Test
  void testHashCode() {
    Deposit deposit = new Deposit(3000, "testID123456", expectedFlyerName);
    Deposit sameDeposit = new Deposit(3000, "testID123456", expectedFlyerName);
    assertEquals(deposit.hashCode(), sameDeposit.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "Deposit{milesAmount=6666, recipientId='12longstring', "
        + "recipientName=FlyerName{firstName='Xihao', middleName='Indigo', lastName='Liu'}}";
    assertEquals(exceptedString, deposit.toString());
  }
}