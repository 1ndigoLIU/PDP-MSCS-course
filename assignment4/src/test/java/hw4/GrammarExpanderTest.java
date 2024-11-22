package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class GrammarExpanderTest {

  private Map<String, List<Expression>> grammar;
  private GrammarExpander expander;

  @BeforeEach
  void setUp() {
    // Initialize the grammar map
    grammar = new HashMap<>();

    // Non-terminal: greeting -> "hello" | "hi"
    grammar.put("greeting",
        Arrays.asList(new TerminalExpression("hello"), new TerminalExpression("hi")));

    // Non-terminal: noun -> "world" | "universe"
    grammar.put("noun",
        Arrays.asList(new TerminalExpression("world"), new TerminalExpression("universe")));

    // Non-terminal: sentence -> "<greeting>, <noun>!"
    grammar.put("sentence", Arrays.asList(new TerminalExpression("<greeting>, <noun>!")));

    // Non-terminal: recursive -> "base" | "<recursive> and base"
    grammar.put("recursive", Arrays.asList(new TerminalExpression("base"),
        new TerminalExpression("<recursive> and base")));

    expander = new GrammarExpander(grammar);
  }

  @Test
  public void testExpandSimpleRule() {
    String result = expander.expand("greeting");
    assertTrue(result.equals("hello") || result.equals("hi"));
  }

  @Test
  public void testExpandUndefinedNonTerminal() {
    assertThrows(UndefinedNonTerminalException.class, () -> {
      expander.expand("undefined");
    });
  }

  @Test
  public void testExpandSentence() {
    String result = expander.expand("sentence");
    assertTrue(result.matches("(hello|hi), (world|universe)!"));
  }

  @Test
  public void testExpandWithNestedNonTerminals() {
    String result = expander.expand("sentence");
    assertTrue(result.matches("(hello|hi), (world|universe)!"));
  }

  @Test
  public void testExpandRecursiveRule() {
    // Since the recursion can be infinite, we need to prevent infinite loops.
    // We can modify the NonTerminalExpression to handle recursion depth.
    // For this test, we'll just check that the result contains "base"
    String result = expander.expand("recursive");
    assertTrue(result.contains("base"));
  }

  @Test
  public void testExpandRandomChoice() {
    Set<String> results = new HashSet<>();
    for (int i = 0; i < 10; i++) {
      results.add(expander.expand("greeting"));
    }
    // Expect both "hello" and "hi" to have appeared
    assertTrue(results.contains("hello"));
    assertTrue(results.contains("hi"));
  }

  @Test
  public void testEqualsSameGrammar() {
    // Create another GrammarExpander with the same grammar
    GrammarExpander anotherExpander = new GrammarExpander(new HashMap<>(grammar));
    assertEquals(expander, anotherExpander);
  }

  @Test
  public void testEqualsDifferentGrammar() {
    // Modify the grammar for the second expander
    Map<String, List<Expression>> differentGrammar = new HashMap<>(grammar);
    differentGrammar.put("newRule", Arrays.asList(new TerminalExpression("newExpansion")));
    GrammarExpander differentExpander = new GrammarExpander(differentGrammar);

    assertNotEquals(expander, differentExpander);
  }

  @Test
  public void testHashCode() {
    GrammarExpander anotherExpander = new GrammarExpander(new HashMap<>(grammar));
    assertEquals(expander.hashCode(), anotherExpander.hashCode());
  }

  @Test
  public void testEqualsSelf() {
    assertEquals(expander, expander);
  }

  @Test
  public void testNotEqualsNull() {
    assertNotEquals(expander, null);
  }

  @Test
  public void testNotEqualsDifferentType() {
    assertNotEquals(expander, "Some String");
  }
}