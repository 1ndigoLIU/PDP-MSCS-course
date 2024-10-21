package problem1;

import java.util.Objects;

/**
 * An abstract class that represents a general product in a supermarket.
 *
 * @author Xihao Liu
 */
public abstract class Product {

  /**
   * The type of the product (e.g., Grocery, Household).
   */
  protected ProductType type;
  private String manufacturer;
  private String name;
  private double price;
  private int minAge;

  /**
   * Constructs a new Product with the specified manufacturer, name, price, and minimum age
   * requirement.
   *
   * @param manufacturer the name of the manufacturer of the product.
   * @param name         the name of the product.
   * @param price        the price of the product.
   * @param minAge       the minimum age required to purchase the product.
   */
  public Product(String manufacturer, String name, double price, int minAge) {
    this.type = ProductType.DEFAULT;
    this.manufacturer = manufacturer;
    this.name = name;
    this.price = price;
    this.minAge = minAge;
  }

  /**
   * Returns the type of the product.
   *
   * @return the product type.
   */
  public ProductType getType() {
    return type;
  }

  /**
   * Returns the manufacturer of the product.
   *
   * @return the manufacturer name.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Returns the name of the product.
   *
   * @return the product name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the price of the product.
   *
   * @return the product price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Returns the minimum age required to purchase the product.
   *
   * @return the minimum age.
   */
  public int getMinAge() {
    return minAge;
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
    if (!(o instanceof Product product)) {
      return false;
    }
    return Objects.equals(manufacturer, product.manufacturer) && Objects.equals(name, product.name)
        && Double.compare(price, product.price) == 0 && minAge == product.minAge;
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(manufacturer, name, price, minAge);
  }

  @Override
  public String toString() {
    return "Product{" + "type=" + type + ", manufacturer='" + manufacturer + '\'' + ", name='"
        + name + '\'' + ", price=" + price + ", minAge=" + minAge + '}';
  }
}
