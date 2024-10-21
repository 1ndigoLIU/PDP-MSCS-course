package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SupermarketInventorySystemTest {

  private SupermarketInventorySystem system;
  private Inventory inventory;
  private Customer customer;
  private Product product1;
  private Product product2;
  private StockItem stockItem1;
  private StockItem stockItem2;

  @BeforeEach
  void setUp() {
    system = SupermarketInventorySystem.getInstance();
    inventory = new Inventory();
    customer = new Customer("Indigo Liu", 25);
    product1 = new Beer("Manufacturer A", "Beer A", 12.99, 18, 500);
    product2 = new Shampoo("Manufacturer B", "Shampoo A", 20.0, 0, 2);
    stockItem1 = new StockItem(product1, 5);
    stockItem2 = new StockItem(product2, 5);
    inventory.addStockItem(stockItem1);
    inventory.addStockItem(stockItem2);
    system.getInventories().add(inventory);
  }

  @AfterEach
  void tearDown() {
    system.getInventories().clear();
  }

  @Test
  void getInstance_Singleton() {
    SupermarketInventorySystem system2 = SupermarketInventorySystem.getInstance();
    assertSame(system, system2);
  }

  @Test
  void getInventories() {
    List<Inventory> inventories = system.getInventories();
    assertFalse(inventories.isEmpty());
    assertEquals(inventory, inventories.get(0));
  }

  @Test
  void addProductToCart_Success() throws OutOfStockException {
    system.addProductToCart(customer, product1, 3);
    assertEquals(3, customer.getShoppingCart().getQuantity(product1));
  }

  @Test
  void addProductToCart_OutOfStock() {
    OutOfStockException thrown = assertThrows(OutOfStockException.class, () -> {
      system.addProductToCart(customer, product1, 15);
    });
    assertEquals("Error: No enough stock of the product Beer A", thrown.getMessage());
  }

  @Test
  void processOrder_Success() throws OutOfStockException {
    customer.addProduct(product1, 3);
    Receipt receipt = system.processOrder(customer);
    assertEquals(3, receipt.getReceivedProducts().get(product1));
    assertEquals(38.97, receipt.getTotalPrice(), 0.01);
    assertEquals(2, stockItem1.getQuantity()); // Stock reduced after purchase
  }

  @Test
  void processOrder_Substitute() {
    customer.addProduct(product1, 5);
    Product product3 = new Beer("Manufacturer A", "Beer B", 12.00, 18, 500);
    StockItem substituteItem1 = new StockItem(product3, 5);
    inventory.getGroceryStock().clear();
    inventory.addStockItem(substituteItem1);
    customer.addProduct(product2, 5);
    Product product4 = new Shampoo("Manufacturer B", "Shampoo B", 20.0, 0, 2);
    StockItem substituteItem2 = new StockItem(product4, 10);
    inventory.addStockItem(substituteItem2);
    customer.addProduct(product4, 3);
    stockItem2.setQuantity(3);
    Receipt receipt = system.processOrder(customer);
    assertEquals(5, receipt.getReceivedProducts().get(product3));
    assertEquals(5, receipt.getNoStockProducts().get(product2));
  }

  @Test
  void processOrder_AgeRestrictedProduct() {
    Customer testCustomer = new Customer("David Mark", 13);
    testCustomer.addProduct(product1, 2);
    testCustomer.addProduct(product2, 3);
    Receipt receipt = system.processOrder(testCustomer);
    assertEquals(2, receipt.getAgeRestrictProducts().get(product1));
    assertEquals(3, receipt.getReceivedProducts().get(product2));
  }

  @Test
  void testEquals_SameValues() {
    SupermarketInventorySystem system2 = SupermarketInventorySystem.getInstance();
    assertEquals(system, system2);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(system, null);
  }

  @Test
  void testEquals_DiffClass() {
    String notSameClassObj = "I am not a Supermarket System";
    assertNotEquals(system, notSameClassObj);
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(system, system);
  }

  @Test
  void testHashCode() {
    SupermarketInventorySystem system2 = SupermarketInventorySystem.getInstance();
    assertEquals(system2.hashCode(), system.hashCode());
  }
}
