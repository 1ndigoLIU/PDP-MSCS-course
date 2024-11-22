package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminalExpressionTest {

  private TerminalExpression terminalExpression;

  @BeforeEach
  void setUp() {
    terminalExpression = new TerminalExpression("apple");
  }

  @Test
  void interpret() {
    assertEquals("apple", terminalExpression.interpret());
  }

  @Test
  void testEquals() {
    assertTrue(terminalExpression.equals(terminalExpression));
    assertTrue(terminalExpression.equals(new TerminalExpression("apple")));
    assertFalse(terminalExpression.equals(new TerminalExpression("hello")));
    assertFalse(terminalExpression.equals(new Object()));
    assertFalse(terminalExpression.equals(null));
  }

  @Test
  void testHashCode() {
    assertEquals(terminalExpression.hashCode(), new TerminalExpression("apple").hashCode());
    assertNotEquals(terminalExpression.hashCode(), new TerminalExpression("hello").hashCode());
  }

  @Test
  void testToString() {
    assertEquals("TerminalExpression{word='apple'}", terminalExpression.toString());
  }
}