package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AthleteTest {
  private Athlete athlete;
  private Athlete athleteNoLeagueInfo;
  private Name name;
  private Double height;
  private Double weight;
  private String league;

  @BeforeEach
  void setUp() {
    name = new Name("Diego", "", "Maradona");
    height = 165.0;
    weight = 150.0;
    league = "Serie A";
    athlete = new Athlete(name, height, weight, league);
    athleteNoLeagueInfo = new Athlete(name, height, weight);
  }

  @Test
  void getAthletesName() {
    assertEquals(name, athlete.getAthletesName());
  }

  @Test
  void getHeight() {
    assertEquals(height, athlete.getHeight());
  }

  @Test
  void getWeight() {
    assertEquals(weight, athlete.getWeight());
  }

  @Test
  void getLeague() {
    assertEquals(league, athlete.getLeague());
  }


  @Test
  public void testEquals_SameObject() {
    assertTrue(athlete.equals(athlete));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(athlete.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not an Athlete Object";
    assertFalse(athlete.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffName() {
    Name nameMessi = new Name("Lionel", "Andres", "Messi");
    Athlete testAthlete = new Athlete(nameMessi, 165.0, 150.0, "Serie A");
    assertFalse(athlete.equals(testAthlete));
  }

  @Test
  public void testEquals_DiffHeight() {
    Athlete testAthlete = new Athlete(name, 170.0, 150.0, "Serie A");
    assertFalse(athlete.equals(testAthlete));
  }

  @Test
  public void testEquals_DiffWeight() {
    Athlete testAthlete = new Athlete(name, 165.0, 155.0, "Serie A");
    assertFalse(athlete.equals(testAthlete));
  }

  @Test
  public void testEquals_DiffLeague() {
    Athlete testAthlete = new Athlete(name, 165.0, 150.0, "La Liga");
    assertFalse(athlete.equals(testAthlete));
  }

  @Test
  public void testEquals_AllEqual() {
    Athlete testAthlete = new Athlete(name, 165.0, 150.0, "Serie A");
    assertTrue(athlete.equals(testAthlete));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(name,
        athlete.getHeight(),athlete.getWeight(), athlete.getLeague());
    assertEquals(expectedHash, athlete.hashCode());
  }

  @Test
  void testToString() {
    String expectedStr = "Athlete{name='Diego Maradona', height=165.0, weight=150.0, "
        + "league='Serie A'}";
    assertEquals(expectedStr, athlete.toString());
  }
}