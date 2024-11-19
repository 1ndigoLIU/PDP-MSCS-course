package problem1;

import java.util.Objects;

/**
 * A class to store flyer's mile information including total miles available, miles earned this year
 * and miles expiring by the end of this calendar year
 *
 * @author Xihao Liu
 */
public class MilesBalance {

  private int milesAvailable = 0;
  private int milesEarnedThisYear = 0;
  private int milesExpiringThisYear = 0;

  /**
   * set the total miles available
   *
   * @param milesAvailable new value of total miles available
   */
  public void setMilesAvailable(int milesAvailable) {
    this.milesAvailable = milesAvailable;
  }

  /**
   * set the miles earned this year
   *
   * @param milesEarnedThisYear new value of miles earned this year
   */
  public void setMilesEarnedThisYear(int milesEarnedThisYear) {
    this.milesEarnedThisYear = milesEarnedThisYear;
  }

  /**
   * set the miles expiring by the end of this calendar year
   *
   * @param milesExpiringThisYear new value of miles expiring by the end of this calendar year
   */
  public void setMilesExpiringThisYear(int milesExpiringThisYear) {
    this.milesExpiringThisYear = milesExpiringThisYear;
  }

  /**
   * get total miles available
   *
   * @return total miles available
   */
  public int getMilesAvailable() {
    return milesAvailable;
  }

  /**
   * get miles earned this year
   *
   * @return miles earned this year
   */
  public int getMilesEarnedThisYear() {
    return milesEarnedThisYear;
  }

  /**
   * get miles expiring by the end of this calendar year
   *
   * @return miles expiring by the end of this calendar year
   */
  public int getMilesExpiringThisYear() {
    return milesExpiringThisYear;
  }

  /**
   * override equals method
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
    MilesBalance that = (MilesBalance) o;
    return milesAvailable == that.milesAvailable && milesEarnedThisYear == that.milesEarnedThisYear
        && milesExpiringThisYear == that.milesExpiringThisYear;
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(milesAvailable, milesEarnedThisYear, milesExpiringThisYear);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "MilesBalance{" + "milesAvailable=" + milesAvailable + ", milesEarnedThisYear="
        + milesEarnedThisYear + ", milesExpiringThisYear=" + milesExpiringThisYear + '}';
  }
}
