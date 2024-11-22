package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UndefinedNonTerminalExceptionTest {

  @Test
  public void testUndefinedNonTerminalExceptionThrown() {
    String message = "Non-terminal '<undefined>' is not defined in the grammar.";
    UndefinedNonTerminalException exception = new UndefinedNonTerminalException(message);
    assertEquals(message, exception.getMessage());
  }

  @Test
  public void testThrowUndefinedNonTerminalException() {
    assertThrows(UndefinedNonTerminalException.class, () -> {
      throw new UndefinedNonTerminalException("This is a test exception.");
    });
  }
}