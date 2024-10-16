package problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {
  private Menu menu;
  private List<String> meals;
  private List<String> desserts;
  private List<String> beverages;
  private List<String> drinks;

  @BeforeEach
  void setUp() {
    meals = new ArrayList<String>();
    desserts = new ArrayList<String>();
    beverages = new ArrayList<String>();
    drinks = new ArrayList<String>();
    meals.add("mealA");
    desserts.add("dessertA");
    beverages.add("beverageA");
    drinks.add("drinkA");
    menu = new Menu(meals, desserts, beverages, drinks);
  }

  @Test
  void addMeal() {
    menu.addMeal("mealB");
    assertTrue(menu.getMeals().contains("mealB"));
  }

  @Test
  void addMeal_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.addMeal("mealA");
    });
    String expectedMessage = "Meal already exists!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void removeMeal() {
    menu.removeMeal("mealA");
    assertFalse(menu.getMeals().contains("mealA"));
  }

  @Test
  void removeMeal_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.removeMeal("mealB");
    });
    String expectedMessage = "Meal does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void addDessert() {
    menu.addDessert("dessertB");
    assertTrue(menu.getDesserts().contains("dessertB"));
  }

  @Test
  void addDessert_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.addDessert("dessertA");
    });
    String expectedMessage = "Dessert already exists!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void removeDessert() {
    menu.removeDessert("dessertA");
    assertFalse(menu.getDesserts().contains("dessertA"));
  }

  @Test
  void removeDessert_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.removeDessert("dessertB");
    });
    String expectedMessage = "Dessert does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void addBeverage() {
    menu.addBeverage("beverageB");
    assertTrue(menu.getBeverages().contains("beverageB"));
  }

  @Test
  void addBeverage_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.addBeverage("beverageA");
    });
    String expectedMessage = "Beverage already exists!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void removeBeverage() {
    menu.removeBeverage("beverageA");
    assertFalse(menu.getBeverages().contains("beverageA"));
  }

  @Test
  void removeBeverage_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.removeBeverage("beverageB");
    });
    String expectedMessage = "Beverage does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void addDrink() {
    menu.addDrink("drinkB");
    assertTrue(menu.getDrinks().contains("drinkB"));
  }

  @Test
  void addDrink_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.addDrink("drinkA");
    });
    String expectedMessage = "Drink already exists!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void removeDrink() {
    menu.removeDrink("drinkA");
    assertFalse(menu.getDrinks().contains("drinkA"));
  }

  @Test
  void removeDrink_ThrowException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      menu.removeDrink("drinkB");
    });
    String expectedMessage = "Drink does not exist!";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void getMeals() {
    assertEquals(meals, menu.getMeals());
  }

  @Test
  void setMeals() {
    List<String> testMeals = new ArrayList<String>();
    testMeals.add("testMealA");
    menu.setMeals(testMeals);
    assertEquals(testMeals, menu.getMeals());
  }

  @Test
  void getDesserts() {
    assertEquals(desserts, menu.getDesserts());
  }

  @Test
  void setDesserts() {
    List<String> testDesserts = new ArrayList<String>();
    testDesserts.add("testDessertA");
    menu.setDesserts(testDesserts);
    assertEquals(testDesserts, menu.getDesserts());
  }

  @Test
  void getBeverages() {
    assertEquals(beverages, menu.getBeverages());
  }

  @Test
  void setBeverages() {
    List<String> testBeverages = new ArrayList<String>();
    testBeverages.add("testBeverageA");
    menu.setBeverages(testBeverages);
    assertEquals(testBeverages, menu.getBeverages());
  }

  @Test
  void getDrinks() {
    assertEquals(drinks, menu.getDrinks());
  }

  @Test
  void setDrinks() {
    List<String> testDrinks = new ArrayList<String>();
    testDrinks.add("testDrinkA");
    menu.setDrinks(testDrinks);
    assertEquals(testDrinks, menu.getDrinks());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(menu.equals(menu));
  }

  @Test
  public void testEquals_NullObject() {
    assertFalse(menu.equals(null));
  }

  @Test
  public void testEquals_DiffClass() {
    String otherClassObject = "Not a Menu Object";
    assertFalse(menu.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffMeals() {
    Menu testMenu = new Menu(List.of("mealB"), List.of("dessertA"), List.of("beverageA"), List.of("drinkA"));
    assertFalse(menu.equals(testMenu));
  }

  @Test
  public void testEquals_DiffDesserts() {
    Menu testMenu = new Menu(List.of("mealA"), List.of("dessertB"), List.of("beverageA"), List.of("drinkA"));
    assertFalse(menu.equals(testMenu));
  }

  @Test
  public void testEquals_DiffBeverages() {
    Menu testMenu = new Menu(List.of("mealA"), List.of("dessertA"), List.of("beverageB"), List.of("drinkA"));
    assertFalse(menu.equals(testMenu));
  }

  @Test
  public void testEquals_DiffDrinks() {
    Menu testMenu = new Menu(List.of("mealA"), List.of("dessertA"), List.of("beverageA"), List.of("drinkB"));
    assertFalse(menu.equals(testMenu));
  }

  @Test
  public void testEquals_AllEqual() {
    Menu testMenu = new Menu(List.of("mealA"), List.of("dessertA"), List.of("beverageA"), List.of("drinkA"));
    assertTrue(menu.equals(testMenu));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(meals, desserts, beverages, drinks);
    assertEquals(expectedHash, menu.hashCode());
  }

  @Test
  void testToString() {
    String expectedStr = "Menu: {meals: [mealA], desserts: [dessertA], beverages: [beverageA], drinks: [drinkA]}";
    assertEquals(expectedStr, menu.toString());
  }
}