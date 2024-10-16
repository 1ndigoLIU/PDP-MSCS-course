package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunnerTest {
  private Runner runner;
  private Runner runnerNoLeagueInfo;
  private Name name;
  private Double height;
  private Double weight;
  private String league;
  private Double best5kTime;
  private Double bestHalfMaraTime;
  private String favRunEvent;

  @BeforeEach
  void setUp() {
    name = new Name("Usain", "St.", "Leo Bolt");
    height = 195.0;
    weight = 207.0;
    league = "Olympics";
    best5kTime = 13.1;
    bestHalfMaraTime = 58.1;
    favRunEvent = "100m dash";
    runner = new Runner(name, height, weight, league, best5kTime, bestHalfMaraTime, favRunEvent);
    runnerNoLeagueInfo = new Runner(name, height, weight, best5kTime, bestHalfMaraTime, favRunEvent);
  }

  @Test
  void getBest5kTime() {
    assertEquals(best5kTime, runner.getBest5kTime());
  }

  @Test
  void getBestHalfMaraTime() {
    assertEquals(bestHalfMaraTime, runner.getBestHalfMaraTime());
  }

  @Test
  void getFavRunEvent() {
    assertEquals(favRunEvent, runner.getFavRunEvent());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(runner.equals(runner));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(runner.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not a Runner Object";
    assertFalse(runner.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffBase() {
    assertFalse(runner.equals(runnerNoLeagueInfo));
  }

  @Test
  public void testEquals_DiffBest5KTime() {
    Runner testRunner = new Runner(name, 195.0, 207.0, "Olympics", 14.1, 58.1, "100m dash");
    assertFalse(runner.equals(testRunner));
  }

  @Test
  public void testEquals_DiffBestHalfMaraTime() {
    Runner testRunner = new Runner(name, 195.0, 207.0, "Olympics", 13.1, 60.0, "100m dash");
    assertFalse(runner.equals(testRunner));
  }

  @Test
  public void testEquals_DiffFavRunEvent() {
    Runner testRunner = new Runner(name, 195.0, 207.0, "Olympics", 13.1, 58.1, "200m dash");
    assertFalse(runner.equals(testRunner));
  }

  @Test
  public void testEquals_AllEqual() {
    Runner testRunner = new Runner(name, 195.0, 207.0, "Olympics", 13.1, 58.1, "100m dash");
    assertTrue(runner.equals(testRunner));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(Objects.hash(runner.getAthletesName(),
        runner.getHeight(),runner.getWeight(),runner.getLeague()),
        runner.getBest5kTime(), runner.getBestHalfMaraTime(), runner.getFavRunEvent());
    assertEquals(expectedHash, runner.hashCode());
  }

  @Test
  void testToString() {
      String expectedStr = "Runner{name='Usain St. Leo Bolt', height=195.0, weight=207.0, "
          + "league='Olympics', best5kTime=13.1, bestHalfMaraTime=58.1, favRunEvent='100m dash'}";
      assertEquals(expectedStr, runner.toString());
  }
}