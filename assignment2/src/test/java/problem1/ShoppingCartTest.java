package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

  private ShoppingCart shoppingCart;
  private Product product1;
  private Product product2;

  @BeforeEach
  void setUp() {
    product1 = new Beer("Manufacturer A", "Beer A", 10.0, 18, 500);
    product2 = new Beer("Manufacturer B", "Beer B", 20.0, 18, 750);
    shoppingCart = new ShoppingCart();
  }

  @Test
  void getProducts_EmptyCart() {
    assertTrue(shoppingCart.getProducts().isEmpty());
  }

  @Test
  void getQuantity_ProductNotInCart() {
    assertEquals(0, shoppingCart.getQuantity(product1));
  }

  @Test
  void getQuantity_ProductInCart() {
    shoppingCart.addProduct(product1, 3);
    assertEquals(3, shoppingCart.getQuantity(product1));
  }

  @Test
  void totalCost_EmptyCart() {
    assertEquals(0.0, shoppingCart.totalCost());
  }

  @Test
  void totalCost_WithProducts() {
    shoppingCart.addProduct(product1, 2);
    shoppingCart.addProduct(product2, 1);
    assertEquals(40.0, shoppingCart.totalCost());
  }

  @Test
  void addProduct() {
    shoppingCart.addProduct(product1, 5);
    assertEquals(5, shoppingCart.getQuantity(product1));
  }

  @Test
  void deleteProduct_ProductNotInCart() {
    shoppingCart.deleteProduct(product1, 1);
    assertEquals(0, shoppingCart.getQuantity(product1));
  }

  @Test
  void deleteProduct_DeleteLessThanQuantity() {
    shoppingCart.addProduct(product1, 5);
    shoppingCart.deleteProduct(product1, 3);
    assertEquals(2, shoppingCart.getQuantity(product1));
  }

  @Test
  void deleteProduct_DeleteExactQuantity() {
    shoppingCart.addProduct(product1, 3);
    shoppingCart.deleteProduct(product1, 3);
    assertEquals(0, shoppingCart.getQuantity(product1));
  }

  @Test
  void removeProduct_ProductNotInCart() {
    shoppingCart.removeProduct(product1);
    assertEquals(0, shoppingCart.getQuantity(product1));
  }

  @Test
  void removeProduct_ProductInCart() {
    shoppingCart.addProduct(product1, 3);
    shoppingCart.removeProduct(product1);
    assertEquals(0, shoppingCart.getQuantity(product1));
    assertFalse(shoppingCart.getProducts().containsKey(product1));
  }

  @Test
  void clearCart() {
    shoppingCart.addProduct(product1, 2);
    shoppingCart.addProduct(product2, 3);
    shoppingCart.clearCart();
    assertTrue(shoppingCart.getProducts().isEmpty());
  }

  @Test
  void testEquals_SameValues() {
    shoppingCart.addProduct(product1, 5);
    ShoppingCart sameCart = new ShoppingCart();
    sameCart.addProduct(product1, 5);
    assertEquals(shoppingCart, sameCart);
  }

  @Test
  void testEquals_DiffProducts() {
    shoppingCart.addProduct(product1, 5);
    ShoppingCart diffCart = new ShoppingCart();
    diffCart.addProduct(product2, 5);
    assertNotEquals(shoppingCart, diffCart);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(shoppingCart, null);
  }

  @Test
  void testEquals_DiffClass() {
    assertNotEquals(shoppingCart, "Not a cart");
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(shoppingCart, shoppingCart);
  }

  @Test
  void testHashCode() {
    shoppingCart.addProduct(product1, 5);
    ShoppingCart sameCart = new ShoppingCart();
    sameCart.addProduct(product1, 5);
    assertEquals(shoppingCart.hashCode(), sameCart.hashCode());
  }

  @Test
  void testToString() {
    shoppingCart.addProduct(product1, 5);
    String expectedString = "ShoppingCart{products={Bear{manufacturer=Manufacturer A, name=Beer A, "
        + "price=10.0, minimum age=18, weight=500.0}=5}}";
    assertEquals(expectedString, shoppingCart.toString());
  }
}