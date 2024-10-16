package problem2;

import java.util.Objects;

/**
 * Represent restaurant containing restaurant's name, address, menu, and open status
 * @author Xihao Liu
 */
public class Restaurant {
  private String name;
  private Address address;
  private Menu menu;
  private boolean open;

  /**
   * Constructs a restaurant, based upon all the provided input parameters
   * Return Restaurant object
   * @param name - restaurant's name, expressed as a String
   * @param address - restaurant's address, expressed as an Address
   * @param menu - restaurant's menu, expressed as a Menu
   * @param open - restaurant's open status, expressed as a boolean
   */
  public Restaurant(String name, Address address, Menu menu, boolean open) {
    this.name = name;
    this.address = address;
    this.menu = menu;
    this.open = open;
  }

  /**
   * Getter method to get the name
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter method to set the name
   * @param name - new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method to get the address
   * @return the address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Setter method to set the address
   * @param address - new address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Getter method to get the menu
   * @return the menu
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Setter method to set the menu
   * @param menu - new menu
   */
  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  /**
   * Getter method to get the open status
   * @return the open status
   */
  public boolean isOpen() {
    return open;
  }

  /**
   * Setter method to set the open status
   * @param open - new open status
   */
  public void setOpen(boolean open) {
    this.open = open;
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
    Restaurant restaurant = (Restaurant) o;
    return Objects.equals(name, restaurant.getName()) &&
        Objects.equals(address, restaurant.getAddress()) &&
        Objects.equals(menu, restaurant.getMenu()) &&
        open == restaurant.open;
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, address.hashCode(), menu.hashCode(), open);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Restaurant: {" +
        "name: " + name +
        ", " + address.toString() +
        ", " + menu.toString() +
        ", open: " + open +
        '}';
  }
}
