package hw4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class SentenceGeneratorTest {

  @Test
  void grammarChoice() {
    InputStream sysInBackup = System.in;
    String input = "2\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    try {
      SentenceGenerator sentenceGenerator = new SentenceGenerator();
      int result = sentenceGenerator.grammarChoice(5);
      assertEquals(2, result);
    } finally {
      System.setIn(sysInBackup);
    }
  }

  @Test
  void generateSentence() {
    InputStream sysInBackup = System.in;
    String input = "n\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    try {
      SentenceGenerator sentenceGenerator = new SentenceGenerator();
      File file = new File("grammar/insult_grammar.json");
      sentenceGenerator.generateSentence(file);
    } finally {
      System.setIn(sysInBackup);
    }
    String output = outputStream.toString();
    assertTrue(output.contains("Grammar Title:"));
    assertTrue(output.contains("Description:"));
    System.setOut(System.out);
  }

  @Test
  void generateSentence_NoDesc() {
    InputStream sysInBackup = System.in;
    String input = "n\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    try {
      SentenceGenerator sentenceGenerator = new SentenceGenerator();
      File file = new File("grammar/unit_test_grammar.json");
      sentenceGenerator.generateSentence(file);
    } finally {
      System.setIn(sysInBackup);
    }
    String output = outputStream.toString();
    assertTrue(output.contains("Grammar Title:"));
    assertFalse(output.contains("Description:"));
    System.setOut(System.out);
  }

  @Test
  void generateSentence_LoadFail() {
    File file = new File("wrong_file_name.json");
    SentenceGenerator sentenceGenerator = new SentenceGenerator();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    sentenceGenerator.generateSentence(file);
    String output = outputStream.toString();
    assertTrue(output.contains("Failed to load grammar. Please try again."));
    System.setOut(System.out);
  }

  @Test
  void testEquals() {
    SentenceGenerator sentenceGenerator = new SentenceGenerator();
    SentenceGenerator sentenceGenerator2 = new SentenceGenerator();
    assertEquals(sentenceGenerator, sentenceGenerator);
    assertNotEquals(sentenceGenerator, sentenceGenerator2);
    assertNotEquals(sentenceGenerator, null);
    assertNotEquals(sentenceGenerator, new Object());
  }

  @Test
  void testHashCode() {
    SentenceGenerator sentenceGenerator = new SentenceGenerator();
    assertEquals(sentenceGenerator.hashCode(), sentenceGenerator.hashCode());
  }
}