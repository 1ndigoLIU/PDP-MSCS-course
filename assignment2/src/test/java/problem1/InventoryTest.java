package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

public class InventoryTest {

  private Inventory inventory;
  private StockItem groceryItem;
  private StockItem householdItem;
  private Product groceryProduct;
  private Product householdProduct;

  @BeforeEach
  void setUp() {
    inventory = new Inventory();
    groceryProduct = new Beer("Manufacturer A", "Beer A", 10.0, 18, 500);
    householdProduct = new Shampoo("Manufacturer B", "Shampoo A", 20.0, 0, 2);
    groceryItem = new StockItem(groceryProduct, 5);
    householdItem = new StockItem(householdProduct, 3);
  }

  @AfterEach
  void tearDown() {
    inventory.getGroceryStock().clear();
    inventory.getHouseholdStock().clear();
  }

  @Test
  void getGroceryStock() {
    inventory.addStockItem(groceryItem);
    assertTrue(inventory.getGroceryStock().contains(groceryItem));
  }

  @Test
  void getHouseholdStock() {
    inventory.addStockItem(householdItem);
    assertTrue(inventory.getHouseholdStock().contains(householdItem));
  }

  @Test
  void addStockItem_Grocery() {
    inventory.addStockItem(groceryItem);
    assertTrue(inventory.getStockItem(groceryProduct).isPresent());
  }

  @Test
  void addStockItem_Household() {
    inventory.addStockItem(householdItem);
    assertTrue(inventory.getStockItem(householdProduct).isPresent());
  }

  @Test
  void addStockItem_InvalidType() {
    Product invalidProduct = new Product("Invalid Manufacturer", "Invalid Product", 15.0, 18) {
    };
    StockItem invalidItem = new StockItem(invalidProduct, 5);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      inventory.addStockItem(invalidItem);
    });
    String expectedMessage = "Invalid product type!";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void getStockItem_ProductExists() {
    inventory.addStockItem(groceryItem);
    assertEquals(Optional.of(groceryItem), inventory.getStockItem(groceryProduct));
  }

  @Test
  void getStockItem_ProductNotExists() {
    assertEquals(Optional.empty(), inventory.getStockItem(groceryProduct));
  }

  @Test
  void getStockItem_InvalidType() {
    Product invalidProduct = new Product("Invalid Manufacturer", "Invalid Product", 15.0, 18) {
    };
    assertEquals(Optional.empty(), inventory.getStockItem(invalidProduct));
  }

  @Test
  void getTotalRetailValue_EmptyInventory() {
    assertEquals(0.0, inventory.getTotalRetailValue());
  }

  @Test
  void getTotalRetailValue_WithItems() {
    inventory.addStockItem(groceryItem);
    inventory.addStockItem(householdItem);
    double expectedValue =
        (groceryProduct.getPrice() * groceryItem.getQuantity()) + (householdProduct.getPrice()
            * householdItem.getQuantity());
    assertEquals(expectedValue, inventory.getTotalRetailValue());
  }

  @Test
  void findSubstituteProduct_PriceFail() {
    inventory.addStockItem(groceryItem);
    Product substituteProduct = new Beer("Manufacturer A", "Beer B", 10.1, 18, 500);
    StockItem substituteItem = new StockItem(substituteProduct, 6);
    inventory.addStockItem(substituteItem);
    Optional<StockItem> substitute = inventory.findSubstituteProduct(groceryProduct, 6,
        new HashMap<>());
    assertEquals(Optional.empty(), substitute);
  }

  @Test
  void findSubstituteProduct_TypeFail() {
    inventory.addStockItem(groceryItem);
    Product substituteProduct = new Cheese("Manufacturer A", "Cheese A", 10.0, 18, 500);
    StockItem substituteItem = new StockItem(substituteProduct, 6);
    inventory.addStockItem(substituteItem);
    Optional<StockItem> substitute = inventory.findSubstituteProduct(groceryProduct, 6,
        new HashMap<>());
    assertEquals(Optional.empty(), substitute);
  }

  @Test
  void findSubstituteProduct_Grocery_Success() {
    inventory.addStockItem(groceryItem);
    Product substituteProduct = new Beer("Manufacturer A", "Beer A", 9.99, 18, 500);
    StockItem substituteItem = new StockItem(substituteProduct, 6);
    inventory.addStockItem(substituteItem);
    Optional<StockItem> substitute = inventory.findSubstituteProduct(groceryProduct, 6,
        new HashMap<>());
    assertEquals(Optional.of(substituteItem), substitute);
  }

  @Test
  void findSubstituteProduct_Grocery_NoSubstitute() {
    inventory.addStockItem(groceryItem);
    Product substituteProduct = new Beer("Manufacturer A", "Beer A", 10.0, 18, 400);
    StockItem substituteItem = new StockItem(substituteProduct, 6);
    inventory.addStockItem(substituteItem);
    Optional<StockItem> substitute = inventory.findSubstituteProduct(groceryProduct, 6,
        new HashMap<>());
    assertEquals(Optional.empty(), substitute);
  }

  @Test
  void findSubstituteProduct_Household_Success() {
    inventory.addStockItem(householdItem);
    Product substituteProduct = new Shampoo("Manufacturer B", "Shampoo B", 19.99, 0, 3);
    StockItem substituteItem = new StockItem(substituteProduct, 3);
    inventory.addStockItem(substituteItem);
    Optional<StockItem> substitute = inventory.findSubstituteProduct(householdProduct, 2,
        new HashMap<>());
    assertEquals(Optional.of(substituteItem), substitute);
  }

  @Test
  void findSubstituteProduct_Household_NoSubstitute() {
    inventory.addStockItem(householdItem);
    Product substituteProduct = new Shampoo("Manufacturer B", "Shampoo B", 20.0, 0, 1);
    StockItem substituteItem = new StockItem(substituteProduct, 5);
    inventory.addStockItem(substituteItem);
    Optional<StockItem> substitute = inventory.findSubstituteProduct(householdProduct, 4,
        new HashMap<>());
    assertEquals(Optional.empty(), substitute);
  }

  @Test
  void findSubstituteProduct_InvalidType() {
    Product invalidProduct = new Product("Invalid Manufacturer", "Invalid Product", 15.0, 18) {
    };
    assertEquals(Optional.empty(),
        inventory.findSubstituteProduct(invalidProduct, 1, new HashMap<>()));
  }

  @Test
  void testEquals_SameValues() {
    Inventory sameInventory = new Inventory();
    sameInventory.addStockItem(groceryItem);
    sameInventory.addStockItem(householdItem);
    inventory.addStockItem(groceryItem);
    inventory.addStockItem(householdItem);
    assertEquals(inventory, sameInventory);
  }

  @Test
  void testEquals_DiffGroceryStock() {
    Inventory diffInventory = new Inventory();
    diffInventory.addStockItem(householdItem);
    inventory.addStockItem(householdItem);
    diffInventory.addStockItem(groceryItem);
    assertNotEquals(inventory, diffInventory);
  }

  @Test
  void testEquals_DiffHouseholdStock() {
    Inventory diffInventory = new Inventory();
    diffInventory.addStockItem(groceryItem);
    inventory.addStockItem(groceryItem);
    diffInventory.addStockItem(householdItem);
    assertNotEquals(inventory, diffInventory);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(inventory, null);
  }

  @Test
  void testEquals_DiffClass() {
    assertNotEquals(inventory, "Not an inventory");
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(inventory, inventory);
  }

  @Test
  void testHashCode() {
    Inventory sameInventory = new Inventory();
    sameInventory.addStockItem(groceryItem);
    sameInventory.addStockItem(householdItem);
    inventory.addStockItem(groceryItem);
    inventory.addStockItem(householdItem);
    assertEquals(inventory.hashCode(), sameInventory.hashCode());
  }

  @Test
  void testToString() {
    inventory.addStockItem(groceryItem);
    inventory.addStockItem(householdItem);
    String expectedString =
        "Inventory{" + "groceryStock=" + "[StockItem{product=Beer A, quantity=5}], "
            + "householdStock=" + "[StockItem{product=Shampoo A, quantity=3}]}";
    assertEquals(expectedString, inventory.toString());
  }
}
