package hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The GrammarExpander class is responsible for expanding grammar rules based on a provided set of
 * grammar definitions. Given a starting rule, it interprets and expands it to produce a full
 * sentence or phrase.
 *
 * @author Xihao Liu
 */
public class GrammarExpander {

  /**
   * A map representing grammar rules, with non-terminal symbols as keys and their possible
   * expansions as values.
   */
  private final Map<String, List<Expression>> grammar;

  /**
   * Constructs a GrammarExpander with the provided grammar definitions.
   *
   * @param grammar a map where each key is a non-terminal symbol, and the value is a list of
   *                possible expansions.
   */
  public GrammarExpander(Map<String, List<Expression>> grammar) {
    this.grammar = grammar;
  }

  /**
   * Expands the given rule by interpreting it using the grammar definitions. Starts with the
   * specified rule and recursively expands non-terminal symbols.
   *
   * @param rule the starting rule or non-terminal to be expanded.
   * @return the expanded result as a string.
   */
  public String expand(String rule) {
    Expression expression = new NonTerminalExpression(rule, grammar);
    return expression.interpret();
  }

  /**
   * Compares this GrammarExpander to another object for equality. Two GrammarExpanders are equal if
   * they have the same grammar map.
   *
   * @param o the object to be compared for equality.
   * @return true if the objects are equal; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrammarExpander that = (GrammarExpander) o;
    return Objects.equals(grammar, that.grammar);
  }

  /**
   * Returns the hash code value for this GrammarExpander.
   *
   * @return the hash code based on the grammar map.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(grammar);
  }
}
