package problem2;

import java.util.List;
import java.util.Objects;

/**
 * Represent restaurant's menu containing
 * meals list, desserts list, beverages list, and drinks list
 * @author Xihao Liu
 */
public class Menu {
  private List<String> meals;
  private List<String> desserts;
  private List<String> beverages;
  private List<String> drinks;

  /**
   * Constructs a menu, based upon all the provided input parameters
   * Return Menu object
   * @param meals - menu's meals list, expressed as a List of String
   * @param desserts - menu's desserts list, expressed as a List of String
   * @param beverages - menu's beverages list, expressed as a List of String
   * @param drinks - menu's drinks list, expressed as a List of String
   */
  public Menu(List<String> meals, List<String> desserts, List<String> beverages, List<String> drinks) {
    this.meals = meals;
    this.desserts = desserts;
    this.beverages = beverages;
    this.drinks = drinks;
  }

  /**
   * Add new meal to meals list
   * @param meal new meal
   * @throws IllegalArgumentException throws when the meal argument already exists in list
   */
  public void addMeal(String meal) {
    if (meals.contains(meal)) {
      throw new IllegalArgumentException("Meal already exists!");
    }
    meals.add(meal);
  }

  /**
   * Remove meal from meals list
   * @param meal meal for removal
   * @throws IllegalArgumentException throws when the meal argument doesn't exist in list
   */
  public void removeMeal(String meal) {
    if (!meals.contains(meal)) {
      throw new IllegalArgumentException("Meal does not exist!");
    }
    meals.remove(meal);
  }

  /**
   * Add new dessert to desserts list
   * @param dessert new dessert
   * @throws IllegalArgumentException throws when the dessert argument already exists in list
   */
  public void addDessert(String dessert) {
    if (desserts.contains(dessert)) {
      throw new IllegalArgumentException("Dessert already exists!");
    }
    desserts.add(dessert);
  }

  /**
   * Remove dessert from desserts list
   * @param dessert dessert for removal
   * @throws IllegalArgumentException throws when the dessert argument doesn't exist in list
   */
  public void removeDessert(String dessert) {
    if (!desserts.contains(dessert)) {
      throw new IllegalArgumentException("Dessert does not exist!");
    }
    desserts.remove(dessert);
  }

  /**
   * Add new beverage to beverages list
   * @param beverage new beverage
   * @throws IllegalArgumentException throws when the beverage argument already exists in list
   */
  public void addBeverage(String beverage) {
    if (beverages.contains(beverage)) {
      throw new IllegalArgumentException("Beverage already exists!");
    }
    beverages.add(beverage);
  }

  /**
   * Remove beverage from beverages list
   * @param beverage beverage for removal
   * @throws IllegalArgumentException throws when the beverage argument doesn't exist in list
   */
  public void removeBeverage(String beverage) {
    if (!beverages.contains(beverage)) {
      throw new IllegalArgumentException("Beverage does not exist!");
    }
    beverages.remove(beverage);
  }

  /**
   * Add new drink to drinks list
   * @param drink new drink
   * @throws IllegalArgumentException throws when the drink argument already exists in list
   */
  public void addDrink(String drink) {
    if (drinks.contains(drink)) {
      throw new IllegalArgumentException("Drink already exists!");
    }
    drinks.add(drink);
  }

  /**
   * Remove drink from drinks list
   * @param drink drink for removal
   * @throws IllegalArgumentException throws when the drink argument doesn't exist in list
   */
  public void removeDrink(String drink) {
    if (!drinks.contains(drink)) {
      throw new IllegalArgumentException("Drink does not exist!");
    }
    drinks.remove(drink);
  }

  /**
   * Getter method to get the meals list
   * @return the meals list
   */
  public List<String> getMeals() {
    return meals;
  }

  /**
   * Setter method to set the meals list
   * @param meals - new meals list
   */
  public void setMeals(List<String> meals) {
    this.meals = meals;
  }

  /**
   * Getter method to get the desserts list
   * @return the desserts list
   */
  public List<String> getDesserts() {
    return desserts;
  }

  /**
   * Setter method to set the desserts list
   * @param desserts - new desserts list
   */
  public void setDesserts(List<String> desserts) {
    this.desserts = desserts;
  }

  /**
   * Getter method to get the beverages list
   * @return the beverages list
   */
  public List<String> getBeverages() {
    return beverages;
  }

  /**
   * Setter method to set the beverages list
   * @param beverages - new beverages list
   */
  public void setBeverages(List<String> beverages) {
    this.beverages = beverages;
  }

  /**
   * Getter method to get the drinks list
   * @return the drinks list
   */
  public List<String> getDrinks() {
    return drinks;
  }

  /**
   * Setter method to set the drinks list
   * @param drinks - new drinks list
   */
  public void setDrinks(List<String> drinks) {
    this.drinks = drinks;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
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
    Menu menu = (Menu) o;
    return Objects.equals(meals, menu.getMeals()) &&
        Objects.equals(desserts, menu.getDesserts()) &&
        Objects.equals(beverages, menu.getBeverages()) &&
        Objects.equals(drinks, menu.getDrinks());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(meals, desserts, beverages, drinks);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Menu: {" +
        "meals: " + meals.toString() +
        ", desserts: " + desserts.toString() +
        ", beverages: " + beverages.toString() +
        ", drinks: " + drinks.toString() +
        '}';
  }
}

