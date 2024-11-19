package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoatTest {

  private float length;
  private int passengersNum;
  private PropulsionType propulsionType;
  private Boat boat;

  @BeforeEach
  void setUp() {
    length = 5.18f;
    passengersNum = 7;
    propulsionType = PropulsionType.OUTBOARD_ENGINE;
    boat = new Boat("aptx4869", 2020, new MakeModel("Boston Whaler", "170 Montauk"), 39000,
        new Location(47.62, -122.33), length, passengersNum, propulsionType);
  }

  @Test
  void getLength() {
    assertEquals(length, boat.getLength());
  }

  @Test
  void getPassengersNum() {
    assertEquals(passengersNum, boat.getPassengersNum());
  }

  @Test
  void getPropulsionType() {
    assertEquals(propulsionType, boat.getPropulsionType());
  }

  @Test
  void testEquals() {
    assertEquals(boat, boat);
  }

  @Test
  void testHashCode() {
    assertEquals(boat.hashCode(), boat.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "Boat{length=5.18, passengersNum=7, propulsionType=OUTBOARD_ENGINE, "
        + "id='aptx4869', mfgYear=2020, makeModel=MakeModel{make='Boston Whaler', "
        + "model='170 Montauk'}, msrp=39000.0, location=Location{latitude=47.62, "
        + "longitude=-122.33}}";
    assertEquals(exceptedString, boat.toString());
  }
}