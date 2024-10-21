package problem1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProductTest {

  private Product baseProduct;

  @BeforeEach
  void setUp() {
    baseProduct = new Product("Manufacturer A", "Product A", 10.99, 18) {
    };
  }

  @Test
  void getType() {
    Product testProduct = new Beer("Manufacturer A", "Product A", 10.99, 18, 1.0);
    assertEquals(ProductType.GROCERY, testProduct.getType());
  }

  @Test
  void getManufacturer() {
    assertEquals("Manufacturer A", baseProduct.getManufacturer());
  }

  @Test
  void getName() {
    assertEquals("Product A", baseProduct.getName());
  }

  @Test
  void getPrice() {
    assertEquals(10.99, baseProduct.getPrice());
  }

  @Test
  void getMinAge() {
    assertEquals(18, baseProduct.getMinAge());
  }

  @Test
  void testEquals_DiffManufacturer() {
    Product diffManufacturer = new Product("Manufacturer B", "Product A", 10.99, 18) {
    };
    assertNotEquals(baseProduct, diffManufacturer);
  }

  @Test
  void testEquals_DiffName() {
    Product diffName = new Product("Manufacturer A", "Product B", 10.99, 18) {
    };
    assertNotEquals(baseProduct, diffName);
  }

  @Test
  void testEquals_DiffPrice() {
    Product diffPrice = new Product("Manufacturer A", "Product A", 15.99, 18) {
    };
    assertNotEquals(baseProduct, diffPrice);
  }

  @Test
  void testEquals_DiffMinAge() {
    Product diffMinAge = new Product("Manufacturer A", "Product A", 10.99, 21) {
    };
    assertNotEquals(baseProduct, diffMinAge);
  }

  @Test
  void testEquals_SameValues() {
    Product sameProduct = new Product("Manufacturer A", "Product A", 10.99, 18) {
    };
    assertEquals(baseProduct, sameProduct);
  }

  @Test
  void testEquals_DiffClass() {
    String notAProduct = "I am not a product";
    assertNotEquals(baseProduct, notAProduct);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(baseProduct, null);
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(baseProduct, baseProduct);
  }

  @Test
  void testHashCode() {
    Product sameProduct = new Product("Manufacturer A", "Product A", 10.99, 18) {
    };
    assertEquals(baseProduct.hashCode(), sameProduct.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Product{type=DEFAULT, manufacturer='Manufacturer A', name='Product A', price=10.99, minAge=18}";
    assertEquals(expectedString, baseProduct.toString());
  }
}
