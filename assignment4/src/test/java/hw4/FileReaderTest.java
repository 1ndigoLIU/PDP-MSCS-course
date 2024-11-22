package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

class FileReaderTest {

  private FileReader fileReader;
  private static final String VALID_JSON_PATH = "grammar/";

  @BeforeEach
  void setUp() {
    fileReader = new FileReader();
  }

  @Test
  public void testLoadGrammarFiles_ValidDirectory() {
    String[] args = {VALID_JSON_PATH};
    fileReader.loadGrammarFiles(args);
    assertNotNull(fileReader.getGrammarFiles());
    assertTrue(fileReader.getFileCount() > 0);
  }

  @Test
  public void testLoadGrammarFiles_InvalidDirectory() {
    String[] args = {"invalid/path/to/directory"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      fileReader.loadGrammarFiles(args);
    });
    assertEquals("Error: Provided path is not a directory or does not exist: " + args[0],
        exception.getMessage());
  }

  @Test
  public void testLoadGrammarFiles_NoArguments() {
    String[] args = {};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      fileReader.loadGrammarFiles(args);
    });
    assertEquals("Error: No grammar file path provided in arguments.", exception.getMessage());
  }

  @Test
  public void testLoadGrammarFiles_EmptyDirectory() {
    String[] args = {"uml/"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      fileReader.loadGrammarFiles(args);
    });
    assertEquals("Error: No JSON grammar files found in path: " + args[0], exception.getMessage());
  }

  @Test
  public void testGetGrammarFile_ValidIndex() {
    String[] args = {VALID_JSON_PATH};
    fileReader.loadGrammarFiles(args);
    File file = fileReader.getGrammarFile(0);
    assertNotNull(file);
  }

  @Test
  public void testGetGrammarFile_InvalidIndex() {
    String[] args = {VALID_JSON_PATH};
    fileReader.loadGrammarFiles(args);
    assertThrows(IndexOutOfBoundsException.class, () -> {
      fileReader.getGrammarFile(fileReader.getFileCount());
    });
  }

  @Test
  public void testGetGrammarFile_ValidIndex_Integer() {
    String[] args = {VALID_JSON_PATH};
    fileReader.loadGrammarFiles(args);
    File file = fileReader.getGrammarFile(Integer.valueOf(0));
    assertNotNull(file);
  }

  @Test
  public void testPrintGrammarTitles() {
    String[] args = {VALID_JSON_PATH};
    fileReader.loadGrammarFiles(args);
    assertDoesNotThrow(() -> fileReader.printGrammarTitles());
  }

  @Test
  public void testEqualsAndHashCode() {
    String[] args = {VALID_JSON_PATH};
    fileReader.loadGrammarFiles(args);
    FileReader anotherReader2 = new FileReader();
    anotherReader2.loadGrammarFiles(args);
    assertEquals(fileReader, fileReader);
    assertEquals(fileReader, anotherReader2);
    assertEquals(fileReader.hashCode(), anotherReader2.hashCode());
    assertNotEquals(fileReader, new Object());
    assertNotEquals(fileReader, null);
  }
}