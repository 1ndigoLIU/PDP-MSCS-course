package hw4;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * FileReader is responsible for loading, managing, and providing access to grammar files stored in
 * a specified directory. It supports listing grammar files in JSON format and printing their
 * titles.
 *
 * @author Xihao Liu
 */
public class FileReader {

  /**
   * Array storing the list of grammar files in the specified directory.
   */
  private File[] grammarFiles;

  /**
   * Default constructor for FileReader.
   */
  public FileReader() {
  }

  /**
   * Retrieves the array of grammar files loaded from the specified directory.
   *
   * @return an array of File objects representing grammar files.
   */
  public File[] getGrammarFiles() {
    return grammarFiles;
  }

  /**
   * Retrieves a specific grammar file by its index.
   *
   * @param index the index of the file in the grammarFiles array.
   * @return the File object at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public File getGrammarFile(Integer index) {
    return grammarFiles[index];
  }

  /**
   * Retrieves a specific grammar file by its index with boundary checking.
   *
   * @param index the index of the file in the grammarFiles array.
   * @return the File object at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public File getGrammarFile(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= grammarFiles.length) {
      throw new IndexOutOfBoundsException("Grammar File Index is out of bounds: " + index);
    }
    return grammarFiles[index];
  }

  /**
   * Returns the number of grammar files loaded.
   *
   * @return the count of grammar files.
   */
  public int getFileCount() {
    return grammarFiles.length;
  }

  /**
   * Loads all JSON grammar files from a specified directory path provided in the arguments. Only
   * files ending with ".json" are considered.
   *
   * @param args a string array containing the directory path at index 0.
   * @throws IllegalArgumentException if the path is invalid, not a directory, or contains no JSON
   *                                  files.
   */
  public void loadGrammarFiles(String[] args) {
    System.out.println("Loading grammars...");
    // Check if the path argument is provided
    if (args.length == 0) {
      throw new IllegalArgumentException("Error: No grammar file path provided in arguments.");
    }

    String directoryPath = args[0];
    File directory = new File(directoryPath);

    // Verify if the provided path is a valid directory
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException(
          "Error: Provided path is not a directory or does not exist: " + directoryPath);
    }

    // List all JSON files in the specified directory
    grammarFiles = directory.listFiles((dir, name) -> name.endsWith(".json"));
    if (grammarFiles == null || grammarFiles.length == 0) {
      throw new IllegalArgumentException(
          "Error: No JSON grammar files found in path: " + directoryPath);
    }
  }

  /**
   * Prints the title of each grammar file in the grammarFiles array. The title is extracted from
   * the "grammarTitle" field in each JSON file. If the field or file is unavailable, an appropriate
   * message is displayed.
   */
  public void printGrammarTitles() {
    Gson gson = new Gson();
    System.out.println("\nThe following grammars are available:");
    for (int i = 0; i < grammarFiles.length; i++) {
      try (java.io.FileReader reader = new java.io.FileReader(grammarFiles[i])) {
        // Parse only the grammarTitle field from each file
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        if (jsonObject.has(StrConstants.GRAMMAR_TITLE_KEY)) {
          String title = jsonObject.get(StrConstants.GRAMMAR_TITLE_KEY).getAsString();
          System.out.println((i + 1) + ". " + title);
        } else {
          System.out.println((i + 1) + ". " + "No " + StrConstants.GRAMMAR_TITLE_KEY + " found in "
              + grammarFiles[i].getName());
        }
      } catch (IOException e) {
        System.err.println((i + 1) + ". " + "Error reading file: " + grammarFiles[i].getName());
      }
    }
  }

  /**
   * Compares this FileReader to another object for equality. Two FileReaders are equal if they
   * contain the same grammar files.
   *
   * @param o the object to be compared for equality.
   * @return true if the objects are equal; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileReader that = (FileReader) o;
    return Objects.deepEquals(grammarFiles, that.grammarFiles);
  }

  /**
   * Returns the hash code value for this FileReader.
   *
   * @return the hash code based on grammarFiles.
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(grammarFiles);
  }
}
