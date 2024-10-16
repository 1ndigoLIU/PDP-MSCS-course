package problem3;

/**
 * Represents a Cartesian coordinate.
 */
public class Posn {
  private Integer x;
  private Integer y;

  /**
   * Constructs a Cartesian coordinate, based upon all the provided input parameters
   * Return Name object
   * @param x - x position, expressed as a Integer
   * @param y - y position, expressed as a Integer
   */
  public Posn(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter for property 'x'.
   * @return Value for property 'x'.
   */
  public Integer getX() {
    return this.x;
  }

  /**
   * Getter for property 'y'.
   * @return Value for property 'y'.
   */
  public Integer getY() {
    return this.y;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
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
    Posn posn = (Posn) o;
    if (this.x != null ? !this.x.equals(posn.x) : posn.x != null) {
      return false;
    }
    return this.y != null ? this.y.equals(posn.y) : posn.y == null;
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    int result = this.x != null ? this.x.hashCode() : 0;
    result = 31 * result + (this.y != null ? this.y.hashCode() : 0);
    return result;
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Posn{" + "x=" + x + ", y=" + y + '}';
  }
}
