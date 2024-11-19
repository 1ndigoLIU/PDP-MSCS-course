package problem1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrequentFlyerTest {
  private FrequentFlyer flyer1;
  private FrequentFlyer flyer2;

  @BeforeEach
  void setUp() {
    flyer1 = new FrequentFlyer("12longstring"
        , new FlyerName("Xihao", "Indigo", "Liu")
        , "liu.xiha@northeastern.edu");
    flyer2 = new FrequentFlyer("testflyer123"
        , new FlyerName("Cong", "Heather", "Ming")
        , "congming@northeastern.edu");
  }

  @AfterEach
  void tearDown() {
    Map<String, FrequentFlyer> flyersMap = FlySystem.getFlySystemInstance().getFlyersMap();
    flyersMap.clear();
  }

  @Test
  void testConstructorException() {
    int idLength = 12;
    FlyerName testName = new FlyerName("Test", "Test", "Test");
    String illegalID = "IllegalID";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new FrequentFlyer(illegalID, testName, "testemail@neu.edu");
    });
    String expectedMessage = "Invalid FrequentFlyer.accountId, accountId must be " +
        idLength + " characters long!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void getAccountId() {
    assertEquals(flyer1.getAccountId(), "12longstring");
  }

  @Test
  void getName() {
    FlyerName Xihao = new FlyerName("Xihao", "Indigo", "Liu");
    assertEquals(flyer1.getName(), Xihao);
  }

  @Test
  void getEmail() {
    assertEquals(flyer1.getEmail(), "liu.xiha@northeastern.edu");
  }

  @Test
  void transferMiles() {
    FlySystem.getFlySystemInstance().addFlyer(flyer1);
    FlySystem.getFlySystemInstance().addFlyer(flyer2);

    flyer1.getMilesBalance().setMilesAvailable(10000);
    flyer1.getMilesBalance().setMilesEarnedThisYear(3000);
    flyer1.getMilesBalance().setMilesExpiringThisYear(2000);
    flyer2.getMilesBalance().setMilesAvailable(5000);
    flyer2.getMilesBalance().setMilesEarnedThisYear(500);
    flyer2.getMilesBalance().setMilesExpiringThisYear(300);

    Deposit newDeposit = new Deposit(2500, flyer2.getAccountId(), flyer2.getName());
    flyer1.transferMiles(newDeposit);
    assertEquals(flyer1.getMilesBalance().getMilesAvailable(), 7500);
    assertEquals(flyer1.getMilesBalance().getMilesEarnedThisYear(), 3000);
    assertEquals(flyer1.getMilesBalance().getMilesExpiringThisYear(), 0);
    assertEquals(flyer2.getMilesBalance().getMilesAvailable(), 7500);
    assertEquals(flyer2.getMilesBalance().getMilesEarnedThisYear(), 3000);
    assertEquals(flyer2.getMilesBalance().getMilesExpiringThisYear(), 2800);
  }

  @Test
  void transferException() {
    FlySystem.getFlySystemInstance().addFlyer(flyer1);
    Deposit newDeposit = new Deposit(2000, flyer2.getAccountId(), flyer2.getName());

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      flyer1.transferMiles(newDeposit);
    });
    String expectedMessage = "Recipient is not valid!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void testEquals() {
    Object obj = new Object();
    assertFalse(flyer1.equals(obj));
    FrequentFlyer sameFlyer1 = new FrequentFlyer("12longstring"
        , new FlyerName("Xihao", "Indigo", "Liu")
        , "new.email@northeastern.edu");
    assertTrue(flyer1.equals(sameFlyer1));
  }

  @Test
  void testHashCode() {
    FrequentFlyer sameFlyer1 = new FrequentFlyer("12longstring"
        , new FlyerName("Xihao", "Indigo", "Liu")
        , "new.email@northeastern.edu");
    assertEquals(flyer1.hashCode(), sameFlyer1.hashCode());
  }
}