package hw4;

import java.util.Objects;

/**
 * Represents a terminal expression in a grammar. This class is used for non-expandable words or
 * symbols, returning the word itself when interpreted.
 *
 * @author Xihao Liu
 */
public class TerminalExpression implements Expression {

  /**
   * The word represented by this terminal expression.
   */
  private final String word;

  /**
   * Constructs a TerminalExpression with the specified word.
   *
   * @param word the word represented by this terminal expression
   */
  public TerminalExpression(String word) {
    this.word = word;
  }

  /**
   * Interprets this terminal expression by returning the word itself.
   *
   * @return the word as a string
   */
  @Override
  public String interpret() {
    return word;
  }

  /**
   * Checks if this TerminalExpression is equal to another object. Two TerminalExpressions are equal
   * if they represent the same word.
   *
   * @param o the object to compare for equality
   * @return true if the objects are equal; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminalExpression that = (TerminalExpression) o;
    return Objects.equals(word, that.word);
  }

  /**
   * Returns the hash code for this TerminalExpression, based on the word.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(word);
  }

  /**
   * Returns a string representation of this TerminalExpression.
   *
   * @return a string describing the terminal expression and its word
   */
  @Override
  public String toString() {
    return "TerminalExpression{" + "word='" + word + '\'' + '}';
  }
}