package problem1;

import java.util.Objects;

/**
 * An abstract class represents a type of Product that is a Grocery. It extends the {@link Grocery}
 * class.
 *
 * @author Xihao Liu
 */
public abstract class Grocery extends Product {

  private double weight;

  /**
   * Constructs a new Grocery object with the specified manufacturer, name, price, minimum age
   * restriction, and weight.
   *
   * @param manufacturer the name of the manufacturer of the grocery.
   * @param name         the name of the grocery.
   * @param price        the price of the grocery.
   * @param minAge       the minimum age required to purchase the grocery.
   * @param weight       the weight of the grocery in ounces.
   */
  public Grocery(String manufacturer, String name, double price, int minAge, double weight) {
    super(manufacturer, name, price, minAge);
    this.type = ProductType.GROCERY;
    this.weight = weight;
  }

  /**
   * Gets the weight of the product in ounces.
   *
   * @return the weight of the product in ounces.
   */
  public double getWeight() {
    return weight;
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
    Grocery grocery = (Grocery) o;
    return Double.compare(weight, grocery.getWeight()) == 0;
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), weight);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Grocery{" + "manufacturer=" + getManufacturer() + ", name=" + getName() + ", price="
        + getPrice() + ", minimum age=" + getMinAge() + ", weight=" + getWeight() + '}';
  }
}
