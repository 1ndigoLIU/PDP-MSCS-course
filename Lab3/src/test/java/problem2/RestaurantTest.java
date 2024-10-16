package problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantTest {
  private Restaurant restaurant;
  private String name;
  private Address address;
  private Menu menu;
  private List<String> meals;
  private List<String> desserts;
  private List<String> beverages;
  private List<String> drinks;
  private boolean open;

  @BeforeEach
  void setUp() {
    name = "Chipotle Mexican Grill";
    address = new Address("212 Westlake Ave N",
        "Seattle", "98109", "WA", "USA");
    meals = new ArrayList<String>();
    desserts = new ArrayList<String>();
    beverages = new ArrayList<String>();
    drinks = new ArrayList<String>();
    meals.add("mealA");
    desserts.add("dessertA");
    beverages.add("beverageA");
    drinks.add("drinkA");
    menu = new Menu(meals, desserts, beverages, drinks);
    open = true;
    restaurant = new Restaurant(name, address, menu, open);
  }

  @Test
  void getName() {
    assertEquals(name, restaurant.getName());
  }

  @Test
  void setName() {
    String testName = "Stone Korean Restaurant";
    restaurant.setName(testName);
    assertEquals(testName, restaurant.getName());
  }

  @Test
  void getAddress() {
    assertEquals(address, restaurant.getAddress());
  }

  @Test
  void setAddress() {
    Address testAddress = new Address("900 Dexter Ave N",
        "Seattle", "98109", "WA", "USA");
    restaurant.setAddress(testAddress);
    assertEquals(testAddress, restaurant.getAddress());
  }

  @Test
  void getMenu() {
    assertEquals(menu, restaurant.getMenu());
  }

  @Test
  void setMenu() {
    List<String> testMeals = new ArrayList<String>();
    testMeals.add("mealA");
    testMeals.add("mealB");
    Menu testMenu = new Menu(testMeals, desserts, beverages, drinks);
    restaurant.setMenu(testMenu);
    assertNotEquals(menu, restaurant.getMenu());
    assertEquals(testMenu, restaurant.getMenu());
  }

  @Test
  void isOpen() {
    assertEquals(open, restaurant.isOpen());
  }

  @Test
  void setOpen() {
    restaurant.setOpen(!open);
    assertEquals(!open, restaurant.isOpen());
  }

  @Test
  void testEquals_SameObject() {
    assertTrue(restaurant.equals(restaurant));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(restaurant.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not a Restaurant Object";
    assertFalse(restaurant.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffName() {
    String testName = "Stone Korean Restaurant";
    Restaurant testRestaurant = new Restaurant(testName, address, menu, open);
    assertFalse(restaurant.equals(testRestaurant));
  }

  @Test
  public void testEquals_DiffAddress() {
    Address testAddress = new Address("900 Dexter Ave N",
        "Seattle", "98109", "WA", "USA");
    Restaurant testRestaurant = new Restaurant(name, testAddress, menu, open);
    assertFalse(restaurant.equals(testRestaurant));
  }

  @Test
  public void testEquals_DiffMenu() {
    List<String> testMeals = new ArrayList<String>();
    testMeals.add("mealA");
    testMeals.add("mealB");
    Menu testMenu = new Menu(testMeals, desserts, beverages, drinks);
    Restaurant testRestaurant = new Restaurant(name, address, testMenu, open);
    assertFalse(restaurant.equals(testRestaurant));
  }

  @Test
  public void testEquals_DiffOpenStatus() {
    Restaurant testRestaurant = new Restaurant(name, address, menu, !open);
    assertFalse(restaurant.equals(testRestaurant));
  }

  @Test
  public void testEquals_AllEqual() {
    Restaurant testRestaurant = new Restaurant(name, address, menu, open);
    assertTrue(restaurant.equals(testRestaurant));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(name, address.hashCode(), menu.hashCode(), open);
    assertEquals(expectedHash, restaurant.hashCode());
  }

  @Test
  void testToString() {
    String expectedStr = "Restaurant: {name: Chipotle Mexican Grill, "
        + "address: {street and number: 212 Westlake Ave N, "
        + "city: Seattle, zipCode: 98109, state: WA, country: USA}, "
        + "Menu: {meals: [mealA], desserts: [dessertA], "
        + "beverages: [beverageA], drinks: [drinkA]}, open: true}";
    assertEquals(expectedStr, restaurant.toString());
  }
}