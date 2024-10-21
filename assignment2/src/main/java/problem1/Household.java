package problem1;

import java.util.Objects;

/**
 * An abstract class represents a type of Product that is a Household. It extends the
 * {@link Product} class.
 *
 * @author Xihao Liu
 */
public abstract class Household extends Product {

  private int units;

  /**
   * Constructs a new Household object with the specified manufacturer, name, price, minimum age
   * restriction, and units.
   *
   * @param manufacturer the name of the manufacturer of the household product.
   * @param name         the name of the household product.
   * @param price        the price of the household product.
   * @param minAge       the minimum age required to purchase the household product.
   * @param units        the number of individual units in a package.
   */
  public Household(String manufacturer, String name, double price, int minAge, int units) {
    super(manufacturer, name, price, minAge);
    this.type = ProductType.HOUSEHOLD;
    this.units = units;
  }

  /**
   * Gets the number of individual units in a package.
   *
   * @return the number of individual units in a package.
   */
  public int getUnits() {
    return units;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
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
    if (!super.equals(o)) {
      return false;
    }
    Household household = (Household) o;
    return units == household.getUnits();
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), units);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Household{" + "manufacturer=" + getManufacturer() + ", name=" + getName() + ", price="
        + getPrice() + ", minimum age=" + getMinAge() + ", units=" + getUnits() + '}';
  }
}
