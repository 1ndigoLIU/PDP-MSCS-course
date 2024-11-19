package problem2;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewCarTest {
  private String id;
  private Integer mfgYear;
  private MakeModel makeModel;
  private double msrp;
  private Location location;
  private NewCar newCar;

  @BeforeEach
  void setUp() {
    id = "aptx4869";
    mfgYear = 2024;
    makeModel = new MakeModel("BMW", "BMW 3 Series");
    msrp = 45000;
    location = new Location(47.62, -122.33);
    newCar = new NewCar(id, mfgYear, makeModel, msrp, location);
    VehicleMgmtSystem.addVehicle(newCar);
  }

  @AfterEach
  void tearDown() {
    Map<String, Vehicle> vehicleMap = VehicleMgmtSystem.getVehiclesMap();
    vehicleMap.clear();
  }

  @Test
  void getVehicleNumWithin50Miles() {
    assertEquals(0, newCar.getVehicleNumWithin50Miles());
    String nextCarId = id+"plus";
    NewCar nextCar = new NewCar(nextCarId, mfgYear, makeModel, msrp, location);
    VehicleMgmtSystem.addVehicle(nextCar);
    assertEquals(1, newCar.getVehicleNumWithin50Miles());

    String nextCarId2 = id+"plus2";
    Location nextCarLocation = new Location(47.62, 122.33);
    NewCar nextCar2 = new NewCar(nextCarId2, mfgYear, makeModel, msrp, nextCarLocation);
    VehicleMgmtSystem.addVehicle(nextCar2);
    assertEquals(1, newCar.getVehicleNumWithin50Miles());
  }

  @Test
  void setVehicleNumWithin50Miles() {
    String nextCarId = id+"plus";
    NewCar nextCar = new NewCar(nextCarId, mfgYear, makeModel, msrp, location);
    VehicleMgmtSystem.addVehicle(nextCar);
    assertEquals(1, newCar.getVehicleNumWithin50Miles());
    VehicleMgmtSystem.removeVehicle(nextCarId);
    assertEquals(0, newCar.getVehicleNumWithin50Miles());
  }

  @Test
  void testToString() {
    String exceptedString = "NewCar{vehicleNumWithin50Miles=0, id='aptx4869', mfgYear=2024, "
        + "msrp=45000.0, makeModel=MakeModel{make='BMW', model='BMW 3 Series'}, "
        + "location=Location{latitude=47.62, longitude=-122.33}}";
    assertEquals(exceptedString, newCar.toString());
  }
}