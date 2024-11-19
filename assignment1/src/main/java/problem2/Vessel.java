package problem2;

/**
 * An abstract class inheriting Vehicle represent Vessel
 *
 * @author Xihao Liu
 */
public abstract class Vessel extends Vehicle {

  /**
   * constructor of class
   *
   * @param id        a unique identifier of a vehicle
   * @param mfgYear   a year vehicle was manufactured
   * @param makeModel vehicle make and model
   * @param msrp      manufacturer suggested retail price
   * @param location  vehicle's storage location, including latitude ang longitude
   */
  public Vessel(String id, Integer mfgYear, MakeModel makeModel, double msrp, Location location) {
    super(id, mfgYear, makeModel, msrp, location);
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
}
