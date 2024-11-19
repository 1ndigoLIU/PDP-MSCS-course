package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsedCarTest {

  private String id;
  private Integer mfgYear;
  private MakeModel makeModel;
  private double msrp;
  private Location location;

  private int mileage;
  private int preOwnerNum;
  private int minorAccNum;
  private UsedCar usedCar;

  @BeforeEach
  void setUp() {
    id = "aptx4869";
    mfgYear = 2024;
    makeModel = new MakeModel("BMW", "BMW 3 Series");
    msrp = 45000;
    location = new Location(47.62, -122.33);

    mileage = 200;
    preOwnerNum = 2;
    minorAccNum = 0;
    usedCar = new UsedCar(id, mfgYear, makeModel, msrp, location,
        mileage, preOwnerNum, minorAccNum);
  }

  @Test
  void getMileage() {
    assertEquals(mileage, usedCar.getMileage());
  }

  @Test
  void getPreOwnerNum() {
    assertEquals(preOwnerNum, usedCar.getPreOwnerNum());
  }

  @Test
  void getMinorAccNum() {
    assertEquals(minorAccNum, usedCar.getMinorAccNum());
  }

  @Test
  void setMileage() {
    usedCar.setMileage(mileage + 100);
    assertEquals(mileage + 100, usedCar.getMileage());
  }

  @Test
  void setPreOwnerNum() {
    usedCar.setPreOwnerNum(preOwnerNum + 1);
    assertEquals(preOwnerNum + 1, usedCar.getPreOwnerNum());
  }

  @Test
  void setMinorAccNum() {
    usedCar.setMinorAccNum(minorAccNum + 1);
    assertEquals(minorAccNum + 1, usedCar.getMinorAccNum());
  }

  @Test
  void testEquals() {
    assertEquals(usedCar, usedCar);
  }

  @Test
  void testHashCode() {
    assertEquals(usedCar.hashCode(), usedCar.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "UsedCar{mileage=200, preOwnerNum=2, minorAccNum=0, "
        + "location=Location{latitude=47.62, longitude=-122.33}, msrp=45000.0, "
        + "makeModel=MakeModel{make='BMW', model='BMW 3 Series'}, mfgYear=2024, id='aptx4869'}";
    assertEquals(exceptedString, usedCar.toString());
  }
}