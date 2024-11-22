package hw4;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Generates sentences based on grammar rules loaded from a specified file. This class interacts
 * with the user to select a grammar file, generate sentences, and optionally generate additional
 * sentences based on the same grammar.
 *
 * @author Xihao Liu
 */
public class SentenceGenerator {

  /**
   * Handles user input for selecting files and confirming prompts.
   */
  private UserInputHandler userInputHandler;

  /**
   * Constructs a SentenceGenerator with a new UserInputHandler instance.
   */
  public SentenceGenerator() {
    this.userInputHandler = new UserInputHandler();
  }

  /**
   * Prompts the user to select a grammar file by its index.
   *
   * @param maxIndex the maximum valid file index, inclusive
   * @return the selected file index as an Integer, or null if the user chooses to quit
   */
  public Integer grammarChoice(int maxIndex) {
    String num = userInputHandler.getFileChoice(maxIndex);
    if (num == null) {
      return null;
    }
    return Integer.parseInt(num);
  }

  /**
   * Generates and displays a random sentence using the grammar loaded from the specified file.
   * After generating a sentence, it prompts the user to generate another sentence or quit.
   *
   * @param file the grammar file to load and use for sentence generation
   */
  public void generateSentence(File file) {
    GrammarLoader loader = new GrammarLoader(file.getAbsolutePath());
    Map<String, List<Expression>> grammar = loader.loadGrammar();
    if (grammar == null) {
      System.out.println("Failed to load grammar. Please try again.");
      return;
    }

    String grammarTitle = loader.getGrammarTitle();
    String grammarDesc = loader.getGrammarDesc();
    String sentence = null;

    // Sentence generation loop
    while (true) {
      try {
        GrammarExpander expander = new GrammarExpander(grammar);
        sentence = expander.expand("start");
      } catch (UndefinedNonTerminalException e) {
        System.err.println("Error: " + e.getMessage() + ", grammar file: " + file.getName());
        break;
      }

      System.out.println("\nGrammar Title:   " + grammarTitle);
      if (!grammarDesc.isEmpty()) {
        System.out.println("Description:     " + grammarDesc);
      }
      System.out.println("Random sentence: " + sentence);

      // Ask user if they want to generate another sentence
      System.out.println("\nWould you like to generate another sentence? (y/n)");
      if (!userInputHandler.getYesOrNo()) {
        break;
      }
    }
  }

  /**
   * Checks if this SentenceGenerator is equal to another object. Two SentenceGenerators are equal
   * if they have the same user input handler.
   *
   * @param o the object to compare for equality
   * @return true if the objects are equal; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SentenceGenerator that = (SentenceGenerator) o;
    return Objects.equals(userInputHandler, that.userInputHandler);
  }

  /**
   * Returns the hash code for this SentenceGenerator, based on the user input handler.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(userInputHandler);
  }
}
