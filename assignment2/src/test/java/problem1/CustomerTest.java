package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

  private Inventory inventory;
  private Customer customer;
  private Product product1;
  private Product product2;
  private ShoppingCart shoppingCart;

  @BeforeEach
  void setUp() {
    inventory = new Inventory();
    SupermarketInventorySystem.getInstance().getInventories().add(inventory);
    customer = new Customer("Indigo Liu", 25);
    product1 = new Beer("Manufacturer A", "Beer A", 12.99, 18, 500);
    product2 = new Beer("Manufacturer B", "Beer B", 19.99, 21, 750);
    shoppingCart = customer.getShoppingCart();
  }

  @AfterEach
  void tearDown() {
    SupermarketInventorySystem.getInstance().getInventories().clear();
  }

  @Test
  void getName() {
    assertEquals("Indigo Liu", customer.getName());
  }

  @Test
  void getAge() {
    assertEquals(25, customer.getAge());
  }

  @Test
  void getShoppingCart() {
    assertNotNull(customer.getShoppingCart());
  }

  @Test
  void addProduct_DefaultQuantity() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    customer.addProduct(product1);
    assertEquals(1, shoppingCart.getQuantity(product1));
  }

  @Test
  void addProduct_SpecifiedQuantity() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    customer.addProduct(product1, 5);
    assertEquals(5, shoppingCart.getQuantity(product1));
  }

  @Test
  void deleteProduct_DefaultQuantity() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    customer.addProduct(product1, 5);
    customer.deleteProduct(product1);
    assertEquals(4, shoppingCart.getQuantity(product1));
  }

  @Test
  void deleteProduct_SpecifiedQuantity() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    customer.addProduct(product1, 5);
    customer.deleteProduct(product1, 3);
    assertEquals(2, shoppingCart.getQuantity(product1));
  }

  @Test
  void removeProduct() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    customer.addProduct(product1, 5);
    customer.removeProduct(product1);
    assertEquals(0, shoppingCart.getQuantity(product1));
  }

  @Test
  void placeOrder() {
    StockItem stockItem1 = new StockItem(product1, 20);
    StockItem stockItem2 = new StockItem(product2, 20);
    inventory.addStockItem(stockItem1);
    inventory.addStockItem(stockItem2);
    customer.addProduct(product1, 1);
    customer.addProduct(product2, 2);
    Receipt receipt = customer.PlaceOrder();
    assertNotNull(receipt);
    assertEquals(1, receipt.getReceivedProducts().get(product1));
    assertEquals(2, receipt.getReceivedProducts().get(product2));
  }

  @Test
  void testEquals_SameValues() {
    Customer sameCustomer = new Customer("Indigo Liu", 25);
    sameCustomer.addProduct(product1, 5);
    customer.addProduct(product1, 5);
    assertEquals(customer, sameCustomer);
  }

  @Test
  void testEquals_DiffName() {
    Customer diffCustomer = new Customer("Jane Doe", 25);
    assertNotEquals(customer, diffCustomer);
  }

  @Test
  void testEquals_DiffAge() {
    Customer diffCustomer = new Customer("Indigo Liu", 30);
    assertNotEquals(customer, diffCustomer);
  }

  @Test
  void testEquals_DiffShoppingCart() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    Customer diffCustomer = new Customer("Indigo Liu", 25);
    diffCustomer.addProduct(product1, 3);
    assertNotEquals(customer, diffCustomer);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(customer, null);
  }

  @Test
  void testEquals_DiffClass() {
    assertNotEquals(customer, "Not a customer");
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(customer, customer);
  }

  @Test
  void testHashCode() {
    Customer sameCustomer = new Customer("Indigo Liu", 25);
    sameCustomer.addProduct(product1, 5);
    customer.addProduct(product1, 5);
    assertEquals(customer.hashCode(), sameCustomer.hashCode());
  }

  @Test
  void testToString() {
    StockItem stockItem = new StockItem(product1, 20);
    inventory.addStockItem(stockItem);
    customer.addProduct(product1, 5);
    String expectedString =
        "Customer{name=Indigo Liu, age=25, " + "ShoppingCart{" + "products={Bear" + "{manufacturer="
            + "Manufacturer A, name=Beer A, price=12.99, minimum age=18, weight=500.0}=5}}}";
    assertEquals(expectedString, customer.toString());
  }
}
