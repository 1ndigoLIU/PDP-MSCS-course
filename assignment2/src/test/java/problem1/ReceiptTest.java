package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReceiptTest {

  private Receipt baseReceipt;
  private Beer beer1;
  private Beer beer2;

  @BeforeEach
  void setUp() {
    beer1 = new Beer("Manufacturer A", "Beer A", 12.99, 18, 5.0);
    beer2 = new Beer("Manufacturer B", "Beer B", 15.99, 21, 7.0);

    Map<Product, Integer> purchasedProducts = new HashMap<>();
    Map<Product, Integer> noStockProducts = new HashMap<>();
    Map<Product, Integer> ageRestrictProducts = new HashMap<>();

    purchasedProducts.put(beer1, 1);
    noStockProducts.put(beer2, 1);
    ageRestrictProducts.put(beer2, 1);

    baseReceipt = new Receipt(12.99, purchasedProducts, noStockProducts, ageRestrictProducts);
  }

  @Test
  void getTotalPrice() {
    assertEquals(12.99, baseReceipt.getTotalPrice());
  }

  @Test
  void getReceivedProducts() {
    assertEquals(1, baseReceipt.getReceivedProducts().get(beer1));
  }

  @Test
  void getNoStockProducts() {
    assertEquals(1, baseReceipt.getNoStockProducts().size());
  }

  @Test
  void getAgeRestrictProducts() {
    assertEquals(1, baseReceipt.getAgeRestrictProducts().size());
  }

  @Test
  void testEquals_DiffTotalPrice() {
    Receipt diffTotalPrice = new Receipt(15.99, baseReceipt.getReceivedProducts(),
        baseReceipt.getNoStockProducts(), baseReceipt.getAgeRestrictProducts());
    assertNotEquals(baseReceipt, diffTotalPrice);
  }

  @Test
  void testEquals_DiffPurchasedProducts() {
    Map<Product, Integer> diffPurchasedProducts = new HashMap<>();
    diffPurchasedProducts.put(beer1, 2);
    Receipt diffProductsReceipt = new Receipt(12.99, diffPurchasedProducts,
        baseReceipt.getNoStockProducts(), baseReceipt.getAgeRestrictProducts());
    assertNotEquals(baseReceipt, diffProductsReceipt);
  }

  @Test
  void testEquals_DiffNoStockProducts() {
    Map<Product, Integer> diffNoStockProducts = new HashMap<>();
    diffNoStockProducts.put(beer1, 1);
    Receipt diffNoStockReceipt = new Receipt(12.99, baseReceipt.getReceivedProducts(),
        diffNoStockProducts, baseReceipt.getAgeRestrictProducts());
    assertNotEquals(baseReceipt, diffNoStockReceipt);
  }

  @Test
  void testEquals_DiffAgeRestrictProducts() {
    Map<Product, Integer> diffAgeRestrictProducts = new HashMap<>();
    diffAgeRestrictProducts.put(beer1, 1);
    Receipt diffAgeRestrictReceipt = new Receipt(12.99, baseReceipt.getReceivedProducts(),
        baseReceipt.getNoStockProducts(), diffAgeRestrictProducts);
    assertNotEquals(baseReceipt, diffAgeRestrictReceipt);
  }

  @Test
  void testEquals_SameValues() {
    Receipt sameReceipt = new Receipt(12.99, baseReceipt.getReceivedProducts(),
        baseReceipt.getNoStockProducts(), baseReceipt.getAgeRestrictProducts());
    assertEquals(baseReceipt, sameReceipt);
  }

  @Test
  void testEquals_Null() {
    assertNotEquals(baseReceipt, null);
  }

  @Test
  void testEquals_DiffClass() {
    String notAReceipt = "I am not a receipt";
    assertNotEquals(baseReceipt, notAReceipt);
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(baseReceipt, baseReceipt);
  }

  @Test
  void testHashCode() {
    Receipt sameReceipt = new Receipt(12.99, baseReceipt.getReceivedProducts(),
        baseReceipt.getNoStockProducts(), baseReceipt.getAgeRestrictProducts());
    assertEquals(baseReceipt.hashCode(), sameReceipt.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Receipt{" + "totalPrice=12.99, " + "purchasedProducts={"
        + "Bear{manufacturer=Manufacturer A, name=Beer A, "
        + "price=12.99, minimum age=18, weight=5.0}=1}, " + "noStockProducts={"
        + "Bear{manufacturer=Manufacturer B, name=Beer B, "
        + "price=15.99, minimum age=21, weight=7.0}=1}, " + "ageRestrictProducts={"
        + "Bear{manufacturer=Manufacturer B, name=Beer B, "
        + "price=15.99, minimum age=21, weight=7.0}=1}}";
    assertEquals(expectedString, baseReceipt.toString());
  }
}