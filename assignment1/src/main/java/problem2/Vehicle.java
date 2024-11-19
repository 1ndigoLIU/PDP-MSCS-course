package problem2;

import java.util.Objects;

/**
 * Represents a vehicle with its information including ID, manufacturing year, make and model,
 * manufacturer suggested retail price and location
 *
 * @author Xihao Liu
 */
public abstract class Vehicle {

  /**
   * a unique identifier of a vehicle
   */
  protected String id;
  /**
   * a year vehicle was manufactured
   */
  protected Integer mfgYear;
  /**
   * vehicle make and model
   */
  protected MakeModel makeModel;
  /**
   * manufacturer suggested retail price
   */
  protected double msrp;
  /**
   * vehicle's storage location, including latitude ang longitude
   */
  protected Location location;

  /**
   * constructor of class
   *
   * @param id        a unique identifier of a vehicle
   * @param mfgYear   a year vehicle was manufactured
   * @param makeModel vehicle make and model
   * @param msrp      manufacturer suggested retail price
   * @param location  vehicle's storage location, including latitude ang longitude
   */
  public Vehicle(String id, Integer mfgYear, MakeModel makeModel, double msrp, Location location) {
    this.id = id;
    this.mfgYear = mfgYear;
    this.makeModel = makeModel;
    this.msrp = msrp;
    this.location = location;
  }

  /**
   * get vehicle unique ID
   *
   * @return vehicle ID
   */
  public String getId() {
    return id;
  }

  /**
   * get the year vehicle was manufactured
   *
   * @return manufactured year
   */
  public Integer getMfgYear() {
    return mfgYear;
  }

  /**
   * get vehicle's make and model
   *
   * @return makeModel
   */
  public MakeModel getMakeModel() {
    return makeModel;
  }

  /**
   * get the manufacturer suggested retail price of the vehicle
   *
   * @return manufacturer suggested retail price
   */
  public double getMsrp() {
    return msrp;
  }

  /**
   * get vehicle's storage location
   *
   * @return location (including latitude ang longitude)
   */
  public Location getLocation() {
    return location;
  }

  /**
   * override equals method, the same vehicle ID is treated as equal Vehicle objects
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(id, vehicle.id);
  }

  /**
   * override hashCode method, hash code is generated using vehicle ID
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
