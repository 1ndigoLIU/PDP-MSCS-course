package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class NonTerminalExpressionTest {

  private Map<String, List<Expression>> grammar;

  @BeforeEach
  void setUp() {
    grammar = new HashMap<>();
    grammar.put("object",
        Arrays.asList(new TerminalExpression("waves"), new TerminalExpression("flowers")));
    grammar.put("verb",
        Arrays.asList(new TerminalExpression("sigh"), new TerminalExpression("whisper")));
    grammar.put("sentence", Arrays.asList(new TerminalExpression("The <object> <verb>.")));
  }

  @Test
  public void testInterpretSuccess() {
    NonTerminalExpression nonTerminalExpression = new NonTerminalExpression("object", grammar);
    String result = nonTerminalExpression.interpret();
    assertTrue(result.equals("waves") || result.equals("flowers"));
  }

  @Test
  public void testUndefinedNonTerminalException() {
    NonTerminalExpression nonTerminalExpression = new NonTerminalExpression("undefined", grammar);
    assertThrows(UndefinedNonTerminalException.class, nonTerminalExpression::interpret);
  }

  @Test
  public void testNestedNonTerminalExpansion() {
    grammar.put("nested", Arrays.asList(new NonTerminalExpression("sentence", grammar)));
    NonTerminalExpression nonTerminalExpression = new NonTerminalExpression("nested", grammar);
    String result = nonTerminalExpression.interpret();
    Pattern pattern = Pattern.compile("The (waves|flowers) (sigh|whisper).");
    Matcher matcher = pattern.matcher(result);
    assertTrue(matcher.matches());
  }

  @Test
  public void testUndefinedNestedNonTerminalException() {
    grammar.put("sentence", Arrays.asList(new TerminalExpression("The <undefined> <verb>.")));
    NonTerminalExpression nonTerminalExpression = new NonTerminalExpression("sentence", grammar);
    assertThrows(UndefinedNonTerminalException.class, nonTerminalExpression::interpret);
  }

  @Test
  public void testEqualsAndHashCode() {
    NonTerminalExpression expression1 = new NonTerminalExpression("object", grammar);
    NonTerminalExpression expression2 = new NonTerminalExpression("object", grammar);
    NonTerminalExpression expression3 = new NonTerminalExpression("verb", grammar);
    assertEquals(expression1, expression1);
    assertEquals(expression1, expression2);
    assertEquals(expression1.hashCode(), expression2.hashCode());
    assertNotEquals(expression1, expression3);
    assertNotEquals(expression1, new Object());
    assertNotEquals(expression1, null);
    assertNotEquals(expression1.hashCode(), expression3.hashCode());
  }

  @Test
  public void testToString() {
    NonTerminalExpression nonTerminalExpression = new NonTerminalExpression("object", grammar);
    String toStringResult = nonTerminalExpression.toString();
    assertTrue(toStringResult.contains("nonTerminal='object'"));
  }
}