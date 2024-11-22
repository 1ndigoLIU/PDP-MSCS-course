package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ExpressionFactoryTest {

  private ExpressionFactory expressionFactory;
  private Map<String, List<Expression>> grammar;

  @BeforeEach
  void setUp() {
    expressionFactory = new ExpressionFactory();
    grammar = new HashMap<>();
  }

  @Test
  public void testCreateTerminalExpression() {
    String token = "hello";
    Expression expression = expressionFactory.createExpression(token, grammar);
    assertTrue(expression instanceof TerminalExpression);
    assertEquals("hello", ((TerminalExpression) expression).interpret());
  }

  @Test
  public void testCreateNonTerminalExpression() {
    String token = "<object>";
    grammar.put("object", Collections.singletonList(new TerminalExpression("hello")));
    Expression expression = expressionFactory.createExpression(token, grammar);
    assertTrue(expression instanceof NonTerminalExpression);
    assertEquals("hello", ((NonTerminalExpression) expression).interpret());
  }

  @Test
  public void testCreateExpressionWithInvalidNonTerminalFormat() {
    String token = "<<object>>";
    Expression expression = expressionFactory.createExpression(token, grammar);
    assertTrue(expression instanceof TerminalExpression);
    assertEquals("<<object>>", ((TerminalExpression) expression).interpret());
  }

  @Test
  public void testCreateExpressionWithEmptyToken() {
    String token = "";
    Expression expression = expressionFactory.createExpression(token, grammar);
    assertTrue(expression instanceof TerminalExpression);
    assertEquals("", ((TerminalExpression) expression).interpret());
  }

  @Test
  public void testCreateExpressionWithNullToken() {
    String token = null;
    assertThrows(NullPointerException.class, () -> {
      expressionFactory.createExpression(token, grammar);
    });
  }

  @Test
  public void testCreateExpressionWithIncompleteBracket() {
    String token = "<object";
    Expression expression = expressionFactory.createExpression(token, grammar);
    assertTrue(expression instanceof TerminalExpression);
    assertEquals("<object", ((TerminalExpression) expression).interpret());
  }
}