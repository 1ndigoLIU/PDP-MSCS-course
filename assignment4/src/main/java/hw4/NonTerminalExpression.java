package hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a non-terminal expression in a grammar. This class is responsible for recursively
 * expanding non-terminal symbols based on the provided grammar rules. Each instance of
 * NonTerminalExpression corresponds to a specific non-terminal in the grammar.
 *
 * @author Xihao Liu
 */
public class NonTerminalExpression implements Expression {

  /**
   * The non-terminal symbol represented by this expression.
   */
  private final String nonTerminal;
  /**
   * The grammar rules mapping non-terminals to their possible expansions.
   */
  private final Map<String, List<Expression>> grammar;
  /**
   * Random instance to select a random expansion for the non-terminal.
   */
  private final Random random = new Random();

  /**
   * Constructs a NonTerminalExpression with the specified non-terminal and grammar.
   *
   * @param nonTerminal the non-terminal symbol for this expression
   * @param grammar     the grammar rules for expanding non-terminals
   */
  public NonTerminalExpression(String nonTerminal, Map<String, List<Expression>> grammar) {
    this.nonTerminal = nonTerminal;
    this.grammar = grammar;
  }

  /**
   * Interprets this non-terminal expression by expanding it according to the grammar. It
   * recursively replaces nested non-terminals until only terminal expressions remain.
   *
   * @return the fully expanded result of this non-terminal as a string
   * @throws UndefinedNonTerminalException if a non-terminal is not defined in the grammar
   */
  @Override
  public String interpret() {
    if (!grammar.containsKey(nonTerminal.toLowerCase())) {
      throw new UndefinedNonTerminalException(
          "Undefined non-terminal: " + StrConstants.LEFT_ANGLE_BRACKET + nonTerminal
              + StrConstants.RIGHT_ANGLE_BRACKET);
    }

    // Randomly choose one of the expansions for this non-terminal
    List<Expression> expansions = grammar.get(nonTerminal.toLowerCase());
    Expression chosenExpression = expansions.get(random.nextInt(expansions.size()));

    // Interpret the chosen expansion to get the initial result
    String result = chosenExpression.interpret();

    // Regular expression to find <non_terminal> patterns
    Pattern pattern = Pattern.compile(StrConstants.REGEX_NON_TERMINAL, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(result);

    // Recursive replacement of all <non_terminals> within the interpreted result
    while (matcher.find()) {
      // Get the non-terminal name inside <>
      String nestedNonTerminal = matcher.group(1).toLowerCase();

      // Check if this non-terminal exists in the grammar
      if (!grammar.containsKey(nestedNonTerminal)) {
        throw new UndefinedNonTerminalException(
            "Undefined non-terminal: " + StrConstants.LEFT_ANGLE_BRACKET + nestedNonTerminal
                + StrConstants.RIGHT_ANGLE_BRACKET);
      }

      // Expand the nested non-terminal
      NonTerminalExpression nestedExpression = new NonTerminalExpression(nestedNonTerminal,
          grammar);
      String expanded = nestedExpression.interpret();

      // Replace the first occurrence of <non_terminal> with its expanded form
      result = result.substring(0, matcher.start()) + expanded + result.substring(matcher.end());

      // Re-run the matcher from the beginning to find any new non-terminals
      matcher = pattern.matcher(result);
    }

    return result;
  }

  /**
   * Checks if this NonTerminalExpression is equal to another object. Two NonTerminalExpressions are
   * considered equal if they represent the same non-terminal symbol.
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
    NonTerminalExpression that = (NonTerminalExpression) o;
    return Objects.equals(nonTerminal, that.nonTerminal);
  }

  /**
   * Returns the hash code for this NonTerminalExpression, based on the non-terminal symbol.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(nonTerminal);
  }

  /**
   * Returns a string representation of this NonTerminalExpression.
   *
   * @return a string describing the non-terminal, grammar, and random instance
   */
  @Override
  public String toString() {
    return "NonTerminalExpression{" + "nonTerminal='" + nonTerminal + '\'' + ", grammar=" + grammar
        + ", random=" + random + '}';
  }
}
