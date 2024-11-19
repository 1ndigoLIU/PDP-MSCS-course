package problem2;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {
  private String id;
  private Integer mfgYear;
  private MakeModel makeModel;
  private double msrp;
  private Location location;
  private Vehicle vehicle;

  @BeforeEach
  void setUp() {
    id = "aptx4869";
    mfgYear = 2024;
    makeModel = new MakeModel("BMW", "BMW 3 Series");
    msrp = 45000;
    location = new Location(47.62, -122.33);
    vehicle = new NewCar(id, mfgYear, makeModel, msrp, location);
  }

  @Test
  void getId() {
    assertEquals(id, vehicle.getId());
  }

  @Test
  void getMfgYear() {
    assertEquals(mfgYear, vehicle.getMfgYear());
  }

  @Test
  void getMakeModel() {
    assertEquals(makeModel, vehicle.getMakeModel());
  }

  @Test
  void getMsrp() {
    assertEquals(msrp, vehicle.getMsrp());
  }

  @Test
  void getLocation() {
    assertEquals(location, vehicle.getLocation());
  }

  @Test
  void testEquals() {
    assertTrue(vehicle.equals(vehicle));
    Object obj = new Object();
    assertFalse(vehicle.equals(obj));
    Vehicle copyVehicle = new NewCar(id, mfgYear, makeModel, msrp, location);
    assertTrue(vehicle.equals(copyVehicle));
  }

  @Test
  void testHashCode() {
    Vehicle copyVehicle = new NewCar(id, mfgYear, makeModel, msrp, location);
    assertEquals(vehicle.hashCode(), copyVehicle.hashCode());
  }
}