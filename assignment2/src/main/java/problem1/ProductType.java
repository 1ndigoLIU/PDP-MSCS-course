package problem1;

/**
 * Represent the different types of products available in the inventory system. Help to categorize
 * products.
 *
 * @author Xihao Liu
 */
public enum ProductType {
  /**
   * A placeholder value representing an unspecified, unknown, or default product type. It does not
   * correspond to any actual product category. This value can be used to handle cases where a
   * product type is not yet set or is invalid.
   */
  DEFAULT,
  /**
   * Represents grocery items such as salmon, cheese, and beer
   */
  GROCERY,
  /**
   * Represents household items such as paper towels, and shampoo
   */
  HOUSEHOLD
}
