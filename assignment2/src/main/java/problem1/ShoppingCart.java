package problem1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a customer's shopping cart that holds products and their quantities. It provides
 * methods to add, remove, and clear products, as well as calculating the total cost. The shopping
 * cart uses a {@link Map} to store products with their corresponding quantities.
 *
 * @author Xihao Liu
 */
public class ShoppingCart {

  /**
   * The map storing products and their respective quantities in the cart. The key is a
   * {@link Product}, and the value is an {@link Integer} representing the quantity of the product.
   */
  private Map<Product, Integer> products;

  /**
   * Constructs an empty shopping cart. Initializes the products map to store products and their
   * quantities.
   */
  public ShoppingCart() {
    this.products = new HashMap<>();
  }

  /**
   * Returns the map of products and their quantities in the shopping cart.
   *
   * @return a map where the keys are {@link Product} and the values are {@link Integer} quantities.
   */
  public Map<Product, Integer> getProducts() {
    return products;
  }

  /**
   * Returns the quantity of a specific product in the cart.
   *
   * @param product the product whose quantity is to be returned.
   * @return the quantity of the product in the cart, or 0 if the product is not in the cart.
   */
  public int getQuantity(Product product) {
    if (products.containsKey(product)) {
      return products.get(product);
    }
    return 0;
  }

  /**
   * Calculates the total cost of all products in the shopping cart.
   *
   * @return the total cost of all products.
   */
  public double totalCost() {
    double total = 0.0;
    for (Map.Entry<Product, Integer> entry : products.entrySet()) {
      total += entry.getKey().getPrice() * entry.getValue();
    }
    return total;
  }

  /**
   * Adds a product to the shopping cart with a specified quantity. If the product already exists in
   * the cart, its quantity is increased by the specified number.
   *
   * @param product the product to be added to the cart.
   * @param num     the quantity of the product to add.
   */
  public void addProduct(Product product, int num) {
    products.put(product, getQuantity(product) + num);
  }

  /**
   * Deletes a specific quantity of a product from the shopping cart. If the quantity to delete is
   * greater than or equal to the current quantity, the product is removed from the cart.
   *
   * @param product the product whose quantity is to be reduced or removed.
   * @param num     the quantity of the product to delete.
   */
  public void deleteProduct(Product product, int num) {
    if (products.containsKey(product)) {
      if (getQuantity(product) > num) {
        products.put(product, getQuantity(product) - num);
      } else {
        products.remove(product);
      }
    }
  }

  /**
   * Removes a product completely from the shopping cart.
   *
   * @param product the product to remove from the cart.
   */
  public void removeProduct(Product product) {
    if (products.containsKey(product)) {
      products.remove(product);
    }
  }

  /**
   * Clears all products from the shopping cart.
   */
  public void clearCart() {
    products.clear();
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
    ShoppingCart that = (ShoppingCart) o;
    return Objects.equals(products, that.products);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(products);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "ShoppingCart{" + "products=" + products.toString() + '}';
  }
}
