package problem2;

/**
 * Class represent a NewCar (inheriting Car) with its specification including the number of
 * available vehicles within 50 miles
 *
 * @author Xihao Liu
 */
public class NewCar extends Car {

  /**
   * the number of available vehicles within 50 miles
   */
  private int vehicleNumWithin50Miles;

  /**
   * constructor of class
   *
   * @param id        a unique identifier of a vehicle
   * @param mfgYear   a year vehicle was manufactured
   * @param makeModel vehicle make and model
   * @param msrp      manufacturer suggested retail price
   * @param location  vehicle's storage location, including latitude ang longitude
   */
  public NewCar(String id, Integer mfgYear, MakeModel makeModel, double msrp, Location location) {
    super(id, mfgYear, makeModel, msrp, location);
    // this statistic number tracked by VehicleMgmtSystem, don't update value here
    vehicleNumWithin50Miles = 0;
  }

  /**
   * get the number of available vehicles within 50 miles
   *
   * @return the number of available vehicles within 50 miles
   */
  public int getVehicleNumWithin50Miles() {
    return vehicleNumWithin50Miles;
  }

  /**
   * set the number of available vehicles within 50 miles
   *
   * @param vehicleNumWithin50Miles the new number of available vehicles within 50 miles
   */
  public void setVehicleNumWithin50Miles(int vehicleNumWithin50Miles) {
    this.vehicleNumWithin50Miles = vehicleNumWithin50Miles;
  }

  /**
   * override equals method the same vehicle ID is treated as equal Vehicle objects
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  /**
   * override hashCode method hash code is generated using vehicle ID
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "NewCar{" + "vehicleNumWithin50Miles=" + vehicleNumWithin50Miles + ", id='" + id + '\''
        + ", mfgYear=" + mfgYear + ", msrp=" + msrp + ", makeModel=" + makeModel + ", location="
        + location + '}';
  }
}
