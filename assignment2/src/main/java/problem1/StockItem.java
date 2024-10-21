package problem1;

import java.util.Objects;

/**
 * Represents a stock item in the inventory along with its quantity. Each stock item consists of a
 * {@link Product} and the number of items in stock.
 *
 * @author Xihao Liu
 */
public class StockItem {

  private Product product;
  private int quantity;

  /**
   * Constructs a StockItem with the specified product and quantity.
   *
   * @param product  the {@link Product} associated with this stock item.
   * @param quantity the number of items in stock for the specified product.
   */
  public StockItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * Gets the product associated with this stock item.
   *
   * @return the {@link Product} associated with this stock item.
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Gets the quantity of the product available in stock.
   *
   * @return the quantity of the product in stock.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the product available in stock.
   *
   * @param quantity the new quantity of the product in stock.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Checks if there are enough items in stock to fulfill a purchase request.
   *
   * @param requestNum the number of items requested in the purchase.
   * @return {@code true} if there are enough items in stock to fulfill the request; {@code false}
   * otherwise.
   */
  public boolean isAvailable(int requestNum) {
    return quantity >= requestNum;
  }

  /**
   * Reduces the quantity of the stock item after a purchase.
   *
   * @param num the number of items to reduce.
   */
  public void reduceQuantity(int num) {
    quantity -= num;
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
    StockItem stockItem = (StockItem) o;
    return Objects.equals(product, stockItem.product) && quantity == stockItem.quantity;
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(product.hashCode(), quantity);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "StockItem{" + "product=" + product.getName() + ", quantity=" + quantity + '}';
  }
}
