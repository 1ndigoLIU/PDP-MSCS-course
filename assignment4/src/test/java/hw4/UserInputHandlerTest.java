package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class UserInputHandlerTest {

  @Test
  public void testMatchQuit() {
    UserInputHandler handler = new UserInputHandler();
    assertTrue(handler.matchQuit("q"));
    assertTrue(handler.matchQuit("Q"));
    assertFalse(handler.matchQuit("quit"));
    assertFalse(handler.matchQuit("n"));
  }

  @Test
  public void testMatchInt() {
    UserInputHandler handler = new UserInputHandler();
    assertTrue(handler.matchInt("123"));
    assertTrue(handler.matchInt("0"));
    assertFalse(handler.matchInt("abc"));
  }

  @Test
  public void testMatchYes() {
    UserInputHandler handler = new UserInputHandler();
    assertTrue(handler.matchYes("y"));
    assertTrue(handler.matchYes("Y"));
    assertFalse(handler.matchYes("yes"));
    assertFalse(handler.matchYes("n"));
  }

  @Test
  public void testMatchNo() {
    UserInputHandler handler = new UserInputHandler();
    assertTrue(handler.matchNo("n"));
    assertTrue(handler.matchNo("N"));
    assertFalse(handler.matchNo("no"));
    assertFalse(handler.matchNo("y"));
  }

  @Test
  public void testGetYesOrNoYes() {
    InputStream sysInBackup = System.in;
    String input = "y\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      assertTrue(handler.getYesOrNo());
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testGetYesOrNoNo() {
    InputStream sysInBackup = System.in;
    String input = "n\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      assertFalse(handler.getYesOrNo());
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testGetYesOrNoInvalidThenYes() {
    InputStream sysInBackup = System.in;
    String input = "maybe\ny\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      assertTrue(handler.getYesOrNo());
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testGetFileChoiceValidNumber() {
    InputStream sysInBackup = System.in;
    String input = "2\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      String choice = handler.getFileChoice(5);
      assertEquals("2", choice);
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testGetFileChoiceInvalidThenValid() {
    InputStream sysInBackup = System.in;
    String input = "abc\n3\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      String choice = handler.getFileChoice(5);
      assertEquals("3", choice);
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testGetFileChoiceOutOfRangeThenValid() {
    InputStream sysInBackup = System.in;
    String input = "6\n4\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      String choice = handler.getFileChoice(5);
      assertEquals("4", choice);
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testGetFileChoiceQuit() {
    InputStream sysInBackup = System.in;
    String input = "q\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      UserInputHandler handler = new UserInputHandler();
      String choice = handler.getFileChoice(5);
      assertNull(choice);
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  public void testEqualsAndHashCode() {
    UserInputHandler handler1 = new UserInputHandler();
    UserInputHandler handler2 = new UserInputHandler();
    UserInputHandler handler3 = handler1;
    assertEquals(handler1, handler3);
    assertEquals(handler1.hashCode(), handler3.hashCode());
    assertNotEquals(handler1, handler2);
  }

  @Test
  public void testNotEqualsNull() {
    UserInputHandler handler = new UserInputHandler();
    assertNotEquals(handler, null);
  }


  @Test
  public void testNotEqualsDifferentType() {
    UserInputHandler handler = new UserInputHandler();
    assertNotEquals(handler, "Some String");
  }
}