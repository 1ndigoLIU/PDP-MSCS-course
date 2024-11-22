package hw4;

/**
 * Defines string constants used throughout the application. These constants include symbols for
 * angle brackets, JSON keys, regular expressions, and user input commands.
 *
 * @author Xihao Liu
 */
public class StrConstants {

  /**
   * Left angle bracket symbol used for non-terminal notation in grammar.
   */
  public final static String LEFT_ANGLE_BRACKET = "<";
  /**
   * Right angle bracket symbol used for non-terminal notation in grammar.
   */
  public final static String RIGHT_ANGLE_BRACKET = ">";
  /**
   * Key for retrieving the grammar title from JSON files.
   */
  public final static String GRAMMAR_TITLE_KEY = "grammarTitle";
  /**
   * Key for retrieving the grammar description from JSON files.
   */
  public final static String GRAMMAR_DESCRIPTION_KEY = "grammarDesc";
  /**
   * Regular expression pattern for matching non-terminal symbols in grammar.
   */
  public final static String REGEX_NON_TERMINAL = "<(\\w+)>";
  /**
   * Regular expression pattern for matching unsigned integers.
   */
  public static final String REGEX_UNSIGNED_INT = "\\d+";
  /**
   * Command string for quitting the application or operation.
   */
  public static final String QUIT = "q";
  /**
   * Command string representing a "yes" response from the user.
   */
  public static final String YES = "y";
  /**
   * Command string representing a "no" response from the user.
   */
  public static final String NO = "n";
  /**
   * The farewell message printing when shutdown application.
   */
  public static final String FAREWELL_MESSAGE = """
      
      *********************************************************
      *                                                       *
      *              Thank you for using the                  *
      *             Random Sentence Generator!                *
      *                                                       *
      *                   Author:                             *
      *                     Xihao Liu                         *
      *                                                       *
      *********************************************************
      """;
  /**
   * The welcome message printing when start application.
   */
  public static final String WELCOME_MESSAGE = """
      
      *********************************************************
      *                                                       *
      *       WELCOME TO THE RANDOM SENTENCE GENERATOR        *
      *                                                       *
      *      Your tool for creative sentence generation!      *
      *                                                       *
      *           Authors:        Xihao Liu                   *
      *                                                       *
      *********************************************************
      """;
}
