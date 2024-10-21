package problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents the inventory of the supermarket. The inventory is divided into different types of
 * items (e.g. grocery and household...).
 *
 * @author Xihao Liu
 */
public class Inventory {

  private List<StockItem> groceryStock;
  private List<StockItem> householdStock;

  /**
   * Constructs an empty inventory for both grocery and household categories. Initializes two lists
   * to store stock items.
   */
  public Inventory() {
    this.groceryStock = new ArrayList<>();
    this.householdStock = new ArrayList<>();
  }

  /**
   * Gets the list of all grocery StockItems
   *
   * @return the list of all grocery StockItems
   */
  public List<StockItem> getGroceryStock() {
    return groceryStock;
  }

  /**
   * Gets the list of all household StockItems
   *
   * @return the list of all household StockItems
   */
  public List<StockItem> getHouseholdStock() {
    return householdStock;
  }

  /**
   * Adds a stock item to the inventory based on the product type (grocery or household).
   *
   * @param item the {@link StockItem} to be added to the inventory.
   * @throws IllegalArgumentException if the product type is invalid.
   */
  public void addStockItem(StockItem item) {
    switch (item.getProduct().getType()) {
      case GROCERY:
        groceryStock.add(item);
        break;
      case HOUSEHOLD:
        householdStock.add(item);
        break;
      default:
        throw new IllegalArgumentException("Invalid product type!");
    }
  }

  /**
   * Retrieves a stock item from the inventory for a given product, if it exists.
   *
   * @param product the {@link Product} for which the stock item is being retrieved.
   * @return an {@link Optional} containing the stock item, or {@code Optional.empty()} if not
   * found.
   */
  public Optional<StockItem> getStockItem(Product product) {
    List<StockItem> stockList;
    Optional<StockItem> stockItem = Optional.empty();
    switch (product.getType()) {
      case GROCERY:
        stockList = groceryStock;
        break;
      case HOUSEHOLD:
        stockList = householdStock;
        break;
      default:
        return stockItem;
    }
    stockItem = stockList.stream().filter(item -> item.getProduct().equals(product)).findFirst();
    return stockItem;
  }

  /**
   * Calculates the total retail value of all stock items in the inventory.
   *
   * @return the total retail value of all items in the inventory.
   */
  public double getTotalRetailValue() {
    double total = 0.0;
    for (StockItem item : groceryStock) {
      total += item.getProduct().getPrice() * item.getQuantity();
    }
    for (StockItem item : householdStock) {
      total += item.getProduct().getPrice() * item.getQuantity();
    }
    return total;
  }

  /**
   * Finds a substitute product in the inventory using the following rules:
   * 1. Products can only be substituted with the same specific type of product.
   * 2. The substituted item must be in stock.
   * 3. The substitute must be the same price as or cheaper than the original product.
   * 4. For grocery items, the substitute's weight must be greater than or equal to the original.
   * 5. For household items, the substitute's unit count must be greater than or equal to the
   * original.
   *
   * @param product  the original {@link Product} for which a substitute is sought.
   * @param quantity the required quantity to fulfill the purchase.
   * @param cart     the customer's map of items in shopping cart
   * @return an {@link Optional} containing the substitute stock item, or {@code Optional.empty()}
   * if no suitable substitute is found.
   */
  public Optional<StockItem> findSubstituteProduct(Product product, int quantity,
      Map<Product, Integer> cart) {
    List<StockItem> stockList;
    Optional<StockItem> substitute = Optional.empty();

    switch (product.getType()) {
      case GROCERY:
        stockList = groceryStock;
        break;
      case HOUSEHOLD:
        stockList = householdStock;
        break;
      default:
        return substitute;
    }

    for (StockItem item : stockList) {
      if (item.getProduct().getClass() == product.getClass()
          && item.getProduct().getPrice() <= product.getPrice() && item.isAvailable(quantity)
          && !cart.containsKey(item.getProduct())) {
        switch (product.getType()) {
          case GROCERY:
            Grocery grocerySub = (Grocery) item.getProduct();
            Grocery groceryOri = (Grocery) product;
            if (grocerySub.getWeight() >= groceryOri.getWeight()) {
              substitute = Optional.of(item);
            }
            break;
          case HOUSEHOLD:
            Household householdSub = (Household) item.getProduct();
            Household householdOri = (Household) product;
            if (householdSub.getUnits() >= householdOri.getUnits()) {
              substitute = Optional.of(item);
            }
            break;
          default:
        }
      }
    }
    return substitute;
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
    Inventory inventory = (Inventory) o;
    return Objects.equals(groceryStock, inventory.groceryStock) && Objects.equals(householdStock,
        inventory.householdStock);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(groceryStock, householdStock);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Inventory{" + "groceryStock=" + groceryStock.toString() + ", householdStock="
        + householdStock.toString() + '}';
  }
}
