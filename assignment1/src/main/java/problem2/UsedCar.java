package problem2;

/**
 * Class represent a UsedCar (inheriting Car) with its specification including mileage, number of
 * previous owners and number of minor traffic accidents
 *
 * @author Xihao Liu
 */
public class UsedCar extends Car {

  private int mileage;
  private int preOwnerNum;
  private int minorAccNum;

  /**
   * constructor of class
   *
   * @param id          a unique identifier of a vehicle
   * @param mfgYear     a year vehicle was manufactured
   * @param makeModel   vehicle make and model
   * @param msrp        manufacturer suggested retail price
   * @param location    vehicle's storage location, including latitude ang longitude
   * @param mileage     used car's mileage
   * @param preOwnerNum number of used car's previous owners
   * @param minorAccNum number of used car's minor traffic accidents
   */
  public UsedCar(String id, Integer mfgYear, MakeModel makeModel, double msrp, Location location,
      int mileage, int preOwnerNum, int minorAccNum) {
    super(id, mfgYear, makeModel, msrp, location);
    this.mileage = mileage;
    this.preOwnerNum = preOwnerNum;
    this.minorAccNum = minorAccNum;
  }

  /**
   * get used car's mileage
   *
   * @return used car's mileage
   */
  public int getMileage() {
    return mileage;
  }

  /**
   * get number of used car's previous owners
   *
   * @return number of previous owners
   */
  public int getPreOwnerNum() {
    return preOwnerNum;
  }

  /**
   * get number of minor traffic accidents
   *
   * @return number of minor traffic accidents
   */
  public int getMinorAccNum() {
    return minorAccNum;
  }

  /**
   * set new mileage
   *
   * @param mileage new mileage
   */
  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  /**
   * set new number of previous owners
   *
   * @param preOwnerNum new number of previous owners
   */
  public void setPreOwnerNum(int preOwnerNum) {
    this.preOwnerNum = preOwnerNum;
  }

  /**
   * set new number of minor traffic accidents
   *
   * @param minorAccNum new number of minor traffic accidents
   */
  public void setMinorAccNum(int minorAccNum) {
    this.minorAccNum = minorAccNum;
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
    return "UsedCar{" + "mileage=" + mileage + ", preOwnerNum=" + preOwnerNum + ", minorAccNum="
        + minorAccNum + ", location=" + location + ", msrp=" + msrp + ", makeModel=" + makeModel
        + ", mfgYear=" + mfgYear + ", id='" + id + '\'' + '}';
  }
}
