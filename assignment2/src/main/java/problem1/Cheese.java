package problem1;

/**
 * The Cheese class represents a type of Grocery product that is a cheese. It extends the
 * {@link Grocery} class.
 *
 * @author Xihao Liu
 */
public class Cheese extends Grocery {

  /**
   * Constructs a new Beer object with the specified manufacturer, name, price, minimum age
   * restriction, and weight.
   *
   * @param manufacturer the name of the manufacturer of the cheese.
   * @param name         the name of the cheese.
   * @param price        the price of the cheese.
   * @param minAge       the minimum age required to purchase the cheese.
   * @param weight       the weight of the cheese in ounces.
   */
  public Cheese(String manufacturer, String name, double price, int minAge, double weight) {
    super(manufacturer, name, price, minAge, weight);
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
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
    return "Cheese{" + "manufacturer=" + getManufacturer() + ", name=" + getName() + ", price="
        + getPrice() + ", minimum age=" + getMinAge() + ", weight=" + getWeight() + '}';
  }
}
