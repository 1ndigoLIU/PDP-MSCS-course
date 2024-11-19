package problem2;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent a vehicle management system with a static Map storing managed vehicles
 *
 * @author Xihao Liu
 */
public class VehicleMgmtSystem {

  private static Map<String, Vehicle> vehiclesMap = new HashMap<String, Vehicle>();
  private static final double EARTH_RADIUS = 3963;


  /**
   * get hashmap which stores all vehicles
   *
   * @return vehiclesMap
   */
  public static Map<String, Vehicle> getVehiclesMap() {
    return vehiclesMap;
  }

  /**
   * register new vehicle to vehicle management system
   *
   * @param vehicle new vehicle waiting for register
   * @throws IllegalStateException throw when new vehicle's ID has already been registered
   */
  public static void addVehicle(Vehicle vehicle) {
    if (isVehicleExists(vehicle.getId())) {
      throw new IllegalStateException(
          "Add Vehicle failed, ID: " + vehicle.getId() + " already exists!");
    } else {
      updateNearbyVehicleNum(vehicle, true);
      vehiclesMap.put(vehicle.getId(), vehicle);
    }
  }

  /**
   * unregister a vehicle from vehicle management system by its ID
   *
   * @param id ID of the vehicle waiting for unregister
   * @throws IllegalArgumentException throw when vehicle's ID doesn't exist in system
   */
  public static void removeVehicle(String id) {
    if (isVehicleExists(id)) {
      Vehicle vehicle = vehiclesMap.get(id);
      vehiclesMap.remove(id);
      updateNearbyVehicleNum(vehicle, false);
    } else {
      throw new IllegalArgumentException("Remove Vehicle failed, ID: " + id + " does not exist!");
    }
  }

  /**
   * check if the vehicle exists in system by ID
   *
   * @param id the vehicle's ID
   * @return result of vehicle's existence, it's true if exists
   */
  public static boolean isVehicleExists(String id) {
    return vehiclesMap.containsKey(id);
  }

  /**
   * update all new cars' the number of available vehicles within 50 miles when a vehicle added or
   * removed from system
   *
   * @param updatedVehicle the vehicle added or removed from system
   * @param isAdd          whether operation is adding, operation is removing if value is false
   */
  private static void updateNearbyVehicleNum(Vehicle updatedVehicle, boolean isAdd) {
    boolean isNewCar = updatedVehicle instanceof NewCar;
    int updateDiff = isAdd ? 1 : -1;
    for (Vehicle vehicle : vehiclesMap.values()) {
      boolean nearBy = ifVehiclesNearby(vehicle, updatedVehicle);
      if (nearBy) {
        if (vehicle instanceof NewCar newCar) {
          newCar.setVehicleNumWithin50Miles(newCar.getVehicleNumWithin50Miles() + updateDiff);
        }
        if (isNewCar) {
          NewCar newUpdateCar = (NewCar) updatedVehicle;
          newUpdateCar.setVehicleNumWithin50Miles(
              newUpdateCar.getVehicleNumWithin50Miles() + updateDiff);
        }
      }
    }
  }

  /**
   * calculate radian with degree
   *
   * @param d degree
   * @return radian
   */
  private static double rad(double d) {
    return d * Math.PI / 180.0;
  }

  /**
   * check whether distance between two vehicles is within 50 miles
   *
   * @param vehicle1 the first vehicle
   * @param vehicle2 the second vehicle
   * @return boolean result of whether distance between two vehicles is within 50 miles
   */
  private static boolean ifVehiclesNearby(Vehicle vehicle1, Vehicle vehicle2) {
    Location location1 = vehicle1.getLocation();
    Location location2 = vehicle2.getLocation();
    double latRadDiff = rad(location2.getLatitude() - location1.getLatitude());
    double lonRadDiff = rad(location2.getLongitude() - location1.getLongitude());
    double a = Math.sin(latRadDiff / 2) * Math.sin(latRadDiff / 2)
        + Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(
        Math.toRadians(location2.getLatitude())) * Math.sin(lonRadDiff / 2) * Math.sin(
        lonRadDiff / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = EARTH_RADIUS * c;
    return distance <= 50;
  }
}
