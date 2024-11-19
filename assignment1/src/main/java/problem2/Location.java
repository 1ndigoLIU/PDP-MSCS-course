package problem2;

import java.util.Objects;

/**
 * Represent a precise location on a map, including latitude and longitude
 *
 * @author Xihao Liu
 */
public class Location {

  private double latitude;
  private double longitude;

  /**
   * constructor of class
   *
   * @param latitude  the latitude
   * @param longitude the longitude
   */
  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * @return latitude
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * @return longitude
   */
  public double getLongitude() {
    return longitude;
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
    Location location = (Location) o;
    return Double.compare(latitude, location.latitude) == 0
        && Double.compare(longitude, location.longitude) == 0;
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Location{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
  }
}