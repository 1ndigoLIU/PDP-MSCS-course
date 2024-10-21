package problem1;

import java.util.Objects;

/**
 * Represents a customer making purchases in the supermarket. Each customer has a name, an age, and
 * a shopping cart to hold products they want to purchase.
 *
 * @author Xihao Liu
 */
public class Customer {

  /**
   * The default quantity of a product to be added or deleted   when no quantity is specified.
   */
  private static final int DEFAULT_CHANGE_PRODUCT_QUANTITY = 1;
  private String name;
  private int age;
  private ShoppingCart shoppingCart;

  /**
   * Constructs a new Customer with the specified name and age.
   *
   * @param name the name of the customer.
   * @param age  the age of the customer.
   */
  public Customer(String name, int age) {
    this.name = name;
    this.age = age;
    this.shoppingCart = new ShoppingCart();
  }

  /**
   * Gets the name of the customer.
   *
   * @return the customer's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the age of the customer.
   *
   * @return the customer's age.
   */
  public int getAge() {
    return age;
  }

  /**
   * Gets the customer's shopping cart.
   *
   * @return the customer's {@link ShoppingCart}.
   */
  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Adds a product to the customer's shopping cart with a default quantity.
   *
   * @param product the product to be added to the cart.
   */
  public void addProduct(Product product) {
    addProduct(product, DEFAULT_CHANGE_PRODUCT_QUANTITY);
  }

  /**
   * Adds a specified quantity of a product to the customer's shopping cart. An error message will
   * be thrown, but the customer should be able to continue shopping.
   *
   * @param product  the product to be added to the cart.
   * @param quantity the quantity of the product to add.
   */
  public void addProduct(Product product, int quantity) {
    try {
      SupermarketInventorySystem.getInstance().addProductToCart(this, product, quantity);
    } catch (OutOfStockException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Deletes a product from the customer's shopping cart with a default quantity.
   *
   * @param product the product to be deleted.
   */
  public void deleteProduct(Product product) {
    shoppingCart.deleteProduct(product, DEFAULT_CHANGE_PRODUCT_QUANTITY);
  }

  /**
   * Deletes a specified quantity of a product from the shopping cart.
   *
   * @param product  the product to be deleted.
   * @param quantity the quantity of the product to delete.
   */
  public void deleteProduct(Product product, int quantity) {
    shoppingCart.deleteProduct(product, quantity);
  }

  /**
   * Completely removes a product from the shopping cart, regardless of quantity.
   *
   * @param product the product to be removed from the cart.
   */
  public void removeProduct(Product product) {
    shoppingCart.removeProduct(product);
  }

  /**
   * Places an order for the items in the customer's shopping cart. This method processes the
   * customer's order through the {@link SupermarketInventorySystem}.
   *
   * @return a {@link Receipt} object representing the completed order.
   */
  public Receipt PlaceOrder() {
    return SupermarketInventorySystem.getInstance().processOrder(this);
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
    Customer customer = (Customer) o;
    return Objects.equals(name, customer.getName()) && age == customer.getAge() && Objects.equals(
        shoppingCart, customer.getShoppingCart());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, age, shoppingCart);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Customer{" + "name=" + name + ", age=" + age + ", " + shoppingCart.toString() + '}';
  }
}
