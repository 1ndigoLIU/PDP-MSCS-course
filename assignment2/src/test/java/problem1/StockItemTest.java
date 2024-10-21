package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockItemTest {

  private StockItem stockItem;
  private Product product;

  @BeforeEach
  void setUp() {
    product = new Beer("Manufacturer A", "Beer A", 12.99, 18, 5.0);
    stockItem = new StockItem(product, 10);
  }

  @Test
  void getProduct() {
    assertEquals(product, stockItem.getProduct());
  }

  @Test
  void getQuantity() {
    assertEquals(10, stockItem.getQuantity());
  }

  @Test
  void setQuantity() {
    stockItem.setQuantity(15);
    assertEquals(15, stockItem.getQuantity());
  }

  @Test
  void isAvailable_True() {
    assertTrue(stockItem.isAvailable(5));
  }

  @Test
  void isAvailable_False() {
    assertFalse(stockItem.isAvailable(15));
  }

  @Test
  void reduceQuantity() {
    stockItem.reduceQuantity(3);
    assertEquals(7, stockItem.getQuantity());
  }

  @Test
  void testEquals_SameValues() {
    StockItem sameStockItem = new StockItem(product, 10);
    assertEquals(stockItem, sameStockItem);
  }

  @Test
  void testEquals_DiffProduct() {
    Product diffProduct = new Beer("Manufacturer B", "Beer B", 12.99, 18, 5.0);
    StockItem diffStockItem = new StockItem(diffProduct, 10);
    assertNotEquals(stockItem, diffStockItem);
  }

  @Test
  void testEquals_DiffQuantity() {
    StockItem diffQuantityStockItem = new StockItem(product, 5);
    assertNotEquals(stockItem, diffQuantityStockItem);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(stockItem, null);
  }

  @Test
  void testEquals_DiffClass() {
    String notAStockItem = "I am not a StockItem";
    assertNotEquals(stockItem, notAStockItem);
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(stockItem, stockItem);
  }

  @Test
  void testHashCode() {
    StockItem sameStockItem = new StockItem(product, 10);
    assertEquals(stockItem.hashCode(), sameStockItem.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "StockItem{product=Beer A, quantity=10}";
    assertEquals(expectedString, stockItem.toString());
  }
}
