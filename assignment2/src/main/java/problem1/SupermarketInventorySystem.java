package problem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents the supermarket inventory system that manages multiple inventories. This class follows
 * the Singleton pattern to ensure only one instance of the system is used.
 *
 * @author Xihao Liu
 */
public class SupermarketInventorySystem {

  /**
   * The single instance of the SupermarketInventorySystem.
   */
  private static SupermarketInventorySystem systemInstance;

  /**
   * The list of all inventories managed by the supermarket system.
   */
  private List<Inventory> inventories;

  /**
   * Private constructor to enforce Singleton pattern. Initializes the list of inventories.
   */
  private SupermarketInventorySystem() {
    inventories = new ArrayList<>();
  }

  /**
   * Returns the singleton instance of the supermarket inventory system.
   *
   * @return the singleton instance of {@link SupermarketInventorySystem}.
   */
  public static SupermarketInventorySystem getInstance() {
    if (systemInstance == null) {
      systemInstance = new SupermarketInventorySystem();
    }
    return systemInstance;
  }

  /**
   * Gets the list of all inventories managed by the supermarket system
   *
   * @return the list of all inventories
   */
  public List<Inventory> getInventories() {
    return inventories;
  }

  /**
   * Returns the default inventory used for a customer. For now, the default inventory is the first
   * one in the inventory list. Can update the method to suit specific requirements.
   *
   * @param customer the customer for whom the default inventory is being fetched.
   * @return the default {@link Inventory} for the customer.
   */
  public Inventory getDefaultInventory(Customer customer) {
    return getInventory(0);
  }

  /**
   * Retrieves a specific inventory by index from the list of inventories.
   *
   * @param index the index of the inventory.
   * @return the {@link Inventory} at the specified index.
   */
  public Inventory getInventory(int index) {
    return inventories.get(index);
  }

  /**
   * Adds a specified quantity of a product to the customer's shopping cart, ensuring that the
   * product is available in stock. If the product is out of stock or insufficient quantity is
   * available, a {@link OutOfStockException} is thrown.
   * <p>
   * When the product is out of stock, the class {@link Customer} can handle this exception using a
   * try-catch block to ensure that the shopping process can continue smoothly without terminating
   * the transaction.
   * </p>
   *
   * @param customer the customer adding the product to their cart.
   * @param product  the product to be added to the cart.
   * @param quantity the quantity of the product to add.
   * @throws OutOfStockException if there is insufficient stock or no stock for the product.
   */
  public void addProductToCart(Customer customer, Product product, int quantity)
      throws OutOfStockException {
    Inventory inventory = getDefaultInventory(customer);
    Optional<StockItem> stockItem = inventory.getStockItem(product);
    if (stockItem.isPresent()) {
      if (stockItem.get().isAvailable(quantity)) {
        customer.getShoppingCart().addProduct(product, quantity);
      } else {
        throw new OutOfStockException("Error: No enough stock of the product " + product.getName());
      }
    } else {
      throw new OutOfStockException("Error: No stock item found for product " + product.getName());
    }
  }

  /**
   * Processes the order for a customer, substituting unavailable products, calculating total price,
   * and handling age restrictions.
   *
   * @param customer the customer placing the order.
   * @return a {@link Receipt} containing the details of the purchase.
   */
  public Receipt processOrder(Customer customer) {
    Inventory inventory = getDefaultInventory(customer);
    ShoppingCart cart = customer.getShoppingCart();
    Map<Product, Integer> purchasedMap = new HashMap<>();
    double totalPrice = 0;
    Map<Product, Integer> purchasedProducts = new HashMap<>();
    Map<Product, Integer> outOfStockProducts = new HashMap<>();
    Map<Product, Integer> ageRestrictedProducts = new HashMap<>();
    boolean wait4Substitute = false;

    // Step 1: Substitute the original item with an equivalent item
    for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
      wait4Substitute = false;
      Product product = entry.getKey();
      int quantity = entry.getValue();
      Optional<StockItem> stockItem = inventory.getStockItem(product);
      if (stockItem.isPresent()) {
        if (stockItem.get().isAvailable(quantity)) {
          purchasedMap.put(product, quantity);
        } else {
          wait4Substitute = true;
        }
      } else {
        wait4Substitute = true;
      }
      if (wait4Substitute) {
        Optional<StockItem> substitute = inventory.findSubstituteProduct(product, quantity,
            cart.getProducts());
        if (substitute.isPresent()) {
          purchasedMap.put(substitute.get().getProduct(), quantity);
        } else {
          outOfStockProducts.put(product, quantity);
        }
      }
    }

    // Step 2: Calculate total price of the purchased products,
    //         remove items which the customer is not old enough to buy
    //         and reduce stock quantity in the inventory
    for (Map.Entry<Product, Integer> entry : purchasedMap.entrySet()) {
      Product product = entry.getKey();
      int quantity = entry.getValue();
      if (customer.getAge() >= product.getMinAge()) {
        Optional<StockItem> stockItem = inventory.getStockItem(product);
        stockItem.ifPresent(item -> item.setQuantity(item.getQuantity() - quantity));
        totalPrice += product.getPrice() * quantity;
        purchasedProducts.put(product, quantity);
      } else {
        ageRestrictedProducts.put(product, quantity);
      }
    }

    // Step 3: Clear the customer's cart
    cart.clearCart();

    // Step 5: Create and return the receipt
    return new Receipt(totalPrice, purchasedProducts, outOfStockProducts, ageRestrictedProducts);
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
    SupermarketInventorySystem that = (SupermarketInventorySystem) o;
    return Objects.equals(inventories, that.inventories);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(inventories);
  }
}
