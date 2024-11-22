package hw4;

/**
 * Represents an expression in a grammar. Each expression should implement the interpret method to
 * return its evaluated result.
 *
 * @author Xihao Liu
 */
public interface Expression {

  /**
   * Interprets the expression and returns the evaluated string result.
   *
   * @return the interpreted result as a string
   */
  String interpret();
}