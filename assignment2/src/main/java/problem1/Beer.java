package problem1;

/**
 * The Beer class represents a type of Grocery product that is a beer. It extends the
 * {@link Grocery} class.
 *
 * @author Xihao Liu
 */
public class Beer extends Grocery {

  /**
   * Constructs a new Beer object with the specified manufacturer, name, price, minimum age
   * restriction, and weight.
   *
   * @param manufacturer the name of the manufacturer of the beer.
   * @param name         the name of the beer.
   * @param price        the price of the beer.
   * @param minAge       the minimum age required to purchase the beer.
   * @param weight       the weight of the beer in ounces.
   */
  public Beer(String manufacturer, String name, double price, int minAge, double weight) {
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
    return "Bear{" + "manufacturer=" + getManufacturer() + ", name=" + getName() + ", price="
        + getPrice() + ", minimum age=" + getMinAge() + ", weight=" + getWeight() + '}';
  }
}
