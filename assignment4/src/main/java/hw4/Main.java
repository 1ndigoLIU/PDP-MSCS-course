package hw4;

/**
 * The entry point of the Random Sentence Generator application. This class loads grammar files from
 * a specified directory and allows the user to select a grammar file and generate random
 * sentences.
 *
 * @author Xihao Liu
 */
public class Main {

  /**
   * The main method for starting the application. It loads grammar files from a directory provided
   * in the program arguments, displays available grammar files, prompts the user to select one, and
   * then generates random sentences based on the selected grammar.
   *
   * @param args program arguments, where the first argument is expected to be the path to the
   *             directory containing grammar files in JSON format
   */
  public static void main(String[] args) {
    System.out.println(StrConstants.WELCOME_MESSAGE);

    FileReader fileReader = new FileReader();
    SentenceGenerator sentenceGenerator = new SentenceGenerator();
    try {
      fileReader.loadGrammarFiles(args);
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      System.err.println(
          "Run Configuration should contain one Program Argument, which is the name of a folder/directory, where all grammars in that directory are loaded/read in.");
      return;
    }

    while (true) {
      // Display available grammar files and prompt for file choice
      fileReader.printGrammarTitles();
      Integer grammarId = sentenceGenerator.grammarChoice(fileReader.getFileCount());
      if (grammarId == null) {
        System.out.println(StrConstants.FAREWELL_MESSAGE);
        return;
      }
      // Sentence generation
      sentenceGenerator.generateSentence(fileReader.getGrammarFile(grammarId - 1));
    }
  }
}
