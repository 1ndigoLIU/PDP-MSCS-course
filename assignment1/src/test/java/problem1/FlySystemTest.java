package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlySystemTest {

  private FrequentFlyer firstFrequentFlyer;
  private FrequentFlyer testFlyer;

  @BeforeEach
  void setUp() {
    Map<String, FrequentFlyer> flyersMap = FlySystem.getFlySystemInstance().getFlyersMap();
    firstFrequentFlyer = new FrequentFlyer("12longstring", new FlyerName("Xihao", "Indigo", "Liu"),
        "liu.xiha@northeastern.edu");
    testFlyer = new FrequentFlyer("testflyer123", new FlyerName("Cong", "Heather", "Ming"),
        "congming@northeastern.edu");
    FlySystem.getFlySystemInstance().addFlyer(firstFrequentFlyer);
  }

  @AfterEach
  void tearDown() {
    Map<String, FrequentFlyer> flyersMap = FlySystem.getFlySystemInstance().getFlyersMap();
    flyersMap.clear();
  }

  @Test
  void getFlySystemInstance() {
    FlySystem flySystem = FlySystem.getFlySystemInstance();
    assertNotNull(flySystem);
  }

  @Test
  void getFlyersMap() {
    Map<String, FrequentFlyer> flyersMap = FlySystem.getFlySystemInstance().getFlyersMap();
    assertNotNull(flyersMap);
    assertEquals(1, flyersMap.size());
  }

  @Test
  void getFlyer() {
    FrequentFlyer gettedFlyer = FlySystem.getFlySystemInstance().getFlyer("12longstring");
    assertTrue(gettedFlyer.equals(firstFrequentFlyer));
  }

  @Test
  void getNonexistentFlyer() {
    String wrongID = "unexpectedID";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      FrequentFlyer gettedUnexpectedFlyer = FlySystem.getFlySystemInstance().getFlyer(wrongID);
    });
    String expectedMessage = "GET FLYER FAILED, account ID: " + wrongID + " does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void addFlyer() {
    FlySystem.getFlySystemInstance().addFlyer(testFlyer);
    assertTrue(FlySystem.getFlySystemInstance().isFlyerExists(testFlyer.getAccountId()));
  }

  @Test
  void addFlyerException() {
    Exception exception = assertThrows(IllegalStateException.class, () -> {
      FlySystem.getFlySystemInstance().addFlyer(firstFrequentFlyer);
    });
    String expectedMessage =
        "ADD FLYER FAILED, account ID: " + firstFrequentFlyer.getAccountId() + " already exists!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void removeFlyer() {
    FlySystem.getFlySystemInstance().addFlyer(testFlyer);
    assertTrue(FlySystem.getFlySystemInstance().isFlyerExists(testFlyer.getAccountId()));
    FlySystem.getFlySystemInstance().removeFlyer(testFlyer.getAccountId());
    assertFalse(FlySystem.getFlySystemInstance().isFlyerExists(testFlyer.getAccountId()));
  }

  @Test
  void removeFlyerException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      FlySystem.getFlySystemInstance().removeFlyer(testFlyer.getAccountId());
    });
    String expectedMessage =
        "REMOVE FLYER FAILED, account ID: " + testFlyer.getAccountId() + " does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void isFlyerExists() {
    assertTrue(FlySystem.getFlySystemInstance().isFlyerExists(firstFrequentFlyer.getAccountId()));
  }

  @Test
  void testEquals() {
    String diffObj = "this is different object";
    assertEquals(FlySystem.getFlySystemInstance(), FlySystem.getFlySystemInstance());
    assertFalse(FlySystem.getFlySystemInstance().equals(null));
    assertFalse(FlySystem.getFlySystemInstance().equals(diffObj));
  }

  @Test
  void testHashCode() {
    assertEquals(FlySystem.getFlySystemInstance().hashCode(),
        FlySystem.getFlySystemInstance().hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "FlySystem{flyersMap={12longstring=FrequentFlyer{"
        + "accountId='12longstring', name=FlyerName{"
        + "firstName='Xihao', middleName='Indigo', lastName='Liu'}, "
        + "email='liu.xiha@northeastern.edu', milesBalance=MilesBalance{"
        + "milesAvailable=0, milesEarnedThisYear=0, milesExpiringThisYear=0}}}}";
    assertEquals(exceptedString, FlySystem.getFlySystemInstance().toString());
  }
}