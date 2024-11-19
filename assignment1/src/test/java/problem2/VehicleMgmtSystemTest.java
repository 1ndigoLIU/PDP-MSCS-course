package problem2;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.FlySystem;
import problem1.FrequentFlyer;

class VehicleMgmtSystemTest {
  private Map<String, Vehicle> vehicleMap;

  private String id;
  private Integer mfgYear;
  private MakeModel makeModel;
  private double msrp;
  private Location location;
  private NewCar newCar;

  @BeforeEach
  void setUp() {
    vehicleMap = VehicleMgmtSystem.getVehiclesMap();
    id = "aptx4869";
    mfgYear = 2024;
    makeModel = new MakeModel("BMW", "BMW 3 Series");
    msrp = 45000;
    location = new Location(47.62, -122.33);
    newCar = new NewCar(id, mfgYear, makeModel, msrp, location);
  }

  @AfterEach
  void tearDown() {
    vehicleMap.clear();
  }

  @Test
  void getVehiclesMap() {
    Map<String, Vehicle> map = VehicleMgmtSystem.getVehiclesMap();
    assertNotNull(map);
  }

  @Test
  void addVehicle() {
    VehicleMgmtSystem.addVehicle(newCar);
    assertTrue(vehicleMap.containsKey(newCar.getId()));
    Exception exception = assertThrows(IllegalStateException.class, () ->{
      VehicleMgmtSystem.addVehicle(newCar);
    });
    String expectedMessage = "Add Vehicle failed, ID: " + newCar.getId() + " already exists!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void removeVehicle() {
    VehicleMgmtSystem.addVehicle(newCar);
    VehicleMgmtSystem.removeVehicle(newCar.getId());
    assertFalse(vehicleMap.containsKey(newCar.getId()));

    Exception exception = assertThrows(IllegalArgumentException.class, () ->{
      VehicleMgmtSystem.removeVehicle(newCar.getId());
    });
    String expectedMessage = "Remove Vehicle failed, ID: " + newCar.getId() + " does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void isVehicleExists() {
    assertFalse(VehicleMgmtSystem.isVehicleExists(newCar.getId()));
    VehicleMgmtSystem.addVehicle(newCar);
    assertTrue(VehicleMgmtSystem.isVehicleExists(newCar.getId()));
  }
}