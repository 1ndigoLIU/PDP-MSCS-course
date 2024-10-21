package problem1;

/**
 * The PaperTowel class represents a type of Household product that is a paper towel. It extends the
 * {@link Household} class.
 *
 * @author Xihao Liu
 */
public class PaperTowel extends Household {

  /**
   * Constructs a new PaperTowel object with the specified manufacturer, name, price, minimum age
   * restriction, and units.
   *
   * @param manufacturer the name of the manufacturer of the paper towel.
   * @param name         the name of the paper towel.
   * @param price        the price of the paper towel.
   * @param minAge       the minimum age required to purchase the paper towel.
   * @param units        the number of individual units in a package.
   */
  public PaperTowel(String manufacturer, String name, double price, int minAge, int units) {
    super(manufacturer, name, price, minAge, units);
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
    return "Paper Towel{" + "manufacturer=" + getManufacturer() + ", name=" + getName() + ", price="
        + getPrice() + ", minimum age=" + getMinAge() + ", units=" + getUnits() + '}';
  }
}
