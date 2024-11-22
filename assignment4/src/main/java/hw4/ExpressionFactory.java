package hw4;

import java.util.List;
import java.util.Map;

/**
 * A factory class to create instances of Expression based on the given token. This factory
 * determines whether the token is a non-terminal or a terminal and returns the appropriate
 * Expression type.
 * <p>
 * Non-terminals are expected to be enclosed in angle brackets (e.g., {@code <nonTerminal>}) without
 * any additional angle brackets within.
 *
 * @author Xihao Liu
 */
public class ExpressionFactory {

  /**
   * Creates an Expression based on the specified token. If the token represents a non-terminal
   * (enclosed within angle brackets and not containing any additional brackets inside), it creates
   * and returns a {@link NonTerminalExpression}. Otherwise, it returns a
   * {@link TerminalExpression}.
   *
   * @param token   the token to be interpreted as either a terminal or non-terminal expression
   * @param grammar a map of grammar rules, with non-terminals as keys and their possible expansions
   *                as values
   * @return an Expression instance representing either a non-terminal or a terminal
   */
  public Expression createExpression(String token, Map<String, List<Expression>> grammar) {
    // Check if the token itself is a non-terminal
    if (token.startsWith(StrConstants.LEFT_ANGLE_BRACKET) && token.endsWith(
        StrConstants.RIGHT_ANGLE_BRACKET) && !token.substring(1, token.length() - 1)
        .contains(StrConstants.RIGHT_ANGLE_BRACKET)) {
      String nonTerminal = token.substring(1, token.length() - 1);
      return new NonTerminalExpression(nonTerminal, grammar);
    } else {
      return new TerminalExpression(token);
    }
  }
}