package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseballPlayerTest {
  private BaseballPlayer baseballPlayer;
  private BaseballPlayer playerNoLeagueInfo;
  private Name name;
  private Double height;
  private Double weight;
  private String league;
  private String team;
  private Double avgBatting;
  private Integer seasonHomeRuns;

  @BeforeEach
  void setUp() {
    this.name = new Name("George", "Herman", "Ruth Jr.");
    this.height = 188.0;
    this.weight = 215.0;
    this.league = "MLB";
    this.team = "New York Yankees";
    this.avgBatting = 0.342;
    this.seasonHomeRuns = 60;
    baseballPlayer = new BaseballPlayer(name, height, weight, league,
        team, avgBatting, seasonHomeRuns);
    playerNoLeagueInfo = new BaseballPlayer(name, height, weight,
        team, avgBatting, seasonHomeRuns);
  }

  @Test
  void getTeam() {
    assertEquals(team, baseballPlayer.getTeam());
  }

  @Test
  void getAvgBatting() {
    assertEquals(avgBatting, baseballPlayer.getAvgBatting());
  }

  @Test
  void getSeasonHomeRuns() {
    assertEquals(seasonHomeRuns, baseballPlayer.getSeasonHomeRuns());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(baseballPlayer.equals(baseballPlayer));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(baseballPlayer.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not a BaseballPlayer Object";
    assertFalse(baseballPlayer.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffBase() {
    assertFalse(baseballPlayer.equals(playerNoLeagueInfo));
  }

  @Test
  public void testEquals_DiffTeam() {
    BaseballPlayer testPlayer = new BaseballPlayer(name, 188.0, 215.0, "MLB", "Boston Red Sox", 0.342, 60);
    assertFalse(baseballPlayer.equals(testPlayer));
  }

  @Test
  public void testEquals_DiffAvgBatting() {
    BaseballPlayer testPlayer = new BaseballPlayer(name, 188.0, 215.0, "MLB", "New York Yankees", 0.350, 60);
    assertFalse(baseballPlayer.equals(testPlayer));
  }

  @Test
  public void testEquals_DiffSeasonHomeRuns() {
    BaseballPlayer testPlayer = new BaseballPlayer(name, 188.0, 215.0, "MLB", "New York Yankees", 0.342, 55);
    assertFalse(baseballPlayer.equals(testPlayer));
  }

  @Test
  public void testEquals_AllEqual() {
    BaseballPlayer testPlayer = new BaseballPlayer(name, 188.0, 215.0, "MLB", "New York Yankees", 0.342, 60);
    assertTrue(baseballPlayer.equals(testPlayer));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(Objects.hash(baseballPlayer.getAthletesName(),
        baseballPlayer.getHeight(),baseballPlayer.getWeight(),baseballPlayer.getLeague()),
        baseballPlayer.getTeam(), baseballPlayer.getAvgBatting(),
        baseballPlayer.getSeasonHomeRuns());
    assertEquals(expectedHash, baseballPlayer.hashCode());
  }

  @Test
  void testToString() {
    String expectedStr = "BaseballPlayer{name='George Herman Ruth Jr.', height=188.0, "
        + "weight=215.0, league='MLB', "
        + "team='New York Yankees', avgBatting=0.342, seasonHomeRuns=60}";
    assertEquals(expectedStr, baseballPlayer.toString());
  }
}