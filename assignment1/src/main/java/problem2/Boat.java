package problem2;

import java.util.Objects;

/**
 * Class represent a boat (inheriting Vessel) with its specification including length, passenger
 * number and propulsion type
 *
 * @author Xihao Liu
 */
public class Boat extends Vessel {

  private float length;
  private int passengersNum;
  private PropulsionType propulsionType;

  /**
   * Constructor of class
   *
   * @param id             a unique identifier of a vehicle
   * @param mfgYear        a year vehicle was manufactured
   * @param makeModel      vehicle make and model
   * @param msrp           manufacturer suggested retail price
   * @param location       vehicle's storage location, including latitude ang longitude
   * @param length         boat's length
   * @param passengersNum  number of passengers
   * @param propulsionType boat's propulsion type
   */
  public Boat(String id, Integer mfgYear, MakeModel makeModel, double msrp, Location location,
      float length, int passengersNum, PropulsionType propulsionType) {
    super(id, mfgYear, makeModel, msrp, location);
    this.length = length;
    this.passengersNum = passengersNum;
    this.propulsionType = propulsionType;
  }

  /**
   * get boat's length
   *
   * @return boat's length
   */
  public float getLength() {
    return this.length;
  }

  /**
   * get number of boat's passengers
   *
   * @return number of passengers
   */
  public int getPassengersNum() {
    return this.passengersNum;
  }

  /**
   * get boat's propulsion type
   *
   * @return propulsion type
   */
  public PropulsionType getPropulsionType() {
    return this.propulsionType;
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
    return "Boat{" + "length=" + length + ", passengersNum=" + passengersNum + ", propulsionType="
        + propulsionType + ", id='" + id + '\'' + ", mfgYear=" + mfgYear + ", makeModel="
        + makeModel + ", msrp=" + msrp + ", location=" + location + '}';
  }
}
