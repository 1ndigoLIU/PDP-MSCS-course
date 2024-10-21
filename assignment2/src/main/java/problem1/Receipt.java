package problem1;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a receipt for a customer order. The receipt contains information about the total price
 * of the order, the map of products that the customer received and their quantity, the map of any
 * products that were out of stock and could not be substituted and their quantity, and map of any
 * products that were removed because the customer did not meet minimum age requirements and their
 * quantity.
 *
 * @author Xihao Liu
 */
public class Receipt {

  private double totalPrice;
  private Map<Product, Integer> purchasedProducts;
  private Map<Product, Integer> noStockProducts;
  private Map<Product, Integer> ageRestrictProducts;

  /**
   * Constructs a Receipt object.
   *
   * @param totalPrice          The total price paid.
   * @param purchasedProducts   the map of products that the customer received and their quantity.
   * @param noStockProducts     the map of any products that were out of stock and could not be
   *                            substituted and their quantity.
   * @param ageRestrictProducts the map of any products that were removed from the order because the
   *                            customer did not meet minimum age requirements and their quantity.
   */
  public Receipt(double totalPrice, Map<Product, Integer> purchasedProducts,
      Map<Product, Integer> noStockProducts, Map<Product, Integer> ageRestrictProducts) {
    this.totalPrice = totalPrice;
    this.purchasedProducts = purchasedProducts;
    this.noStockProducts = noStockProducts;
    this.ageRestrictProducts = ageRestrictProducts;
  }

  /**
   * Gets the total price paid.
   *
   * @return the total price paid.
   */
  public double getTotalPrice() {
    return totalPrice;
  }

  /**
   * Gets the map of products that the customer received and their quantity.
   *
   * @return the map of products that the customer received and their quantity.
   */
  public Map<Product, Integer> getReceivedProducts() {
    return purchasedProducts;
  }

  /**
   * Gets the map of any products that were out of stock and could not be substituted and their
   * quantity.
   *
   * @return the map of any products that were out of stock and could not be substituted and their
   * quantity.
   */
  public Map<Product, Integer> getNoStockProducts() {
    return noStockProducts;
  }

  /**
   * Gets the map of any products that were removed from the order because the customer did not meet
   * minimum age requirements and their quantity.
   *
   * @return the map of any products that were removed from the order because the customer did not
   * meet minimum age requirements and their quantity.
   */
  public Map<Product, Integer> getAgeRestrictProducts() {
    return ageRestrictProducts;
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
    Receipt receipt = (Receipt) o;
    return Double.compare(totalPrice, receipt.totalPrice) == 0 && Objects.equals(purchasedProducts,
        receipt.purchasedProducts) && Objects.equals(noStockProducts, receipt.noStockProducts)
        && Objects.equals(ageRestrictProducts, receipt.ageRestrictProducts);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(totalPrice, purchasedProducts, noStockProducts, ageRestrictProducts);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Receipt{" + "totalPrice=" + totalPrice + ", purchasedProducts="
        + purchasedProducts.toString() + ", noStockProducts=" + noStockProducts.toString()
        + ", ageRestrictProducts=" + ageRestrictProducts.toString() + '}';
  }
}
