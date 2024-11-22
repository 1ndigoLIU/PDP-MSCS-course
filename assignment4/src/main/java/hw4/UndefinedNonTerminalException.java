package hw4;

/**
 * This exception is thrown when a non-terminal symbol in a grammar is undefined. It extends
 * {@link RuntimeException} and is used to indicate issues in grammar expansion where a referenced
 * non-terminal cannot be found in the provided grammar rules.
 *
 * @author Xihao Liu
 */
public class UndefinedNonTerminalException extends RuntimeException {

  /**
   * Constructs an UndefinedNonTerminalException with the specified detail message.
   *
   * @param message the detail message, typically identifying the undefined non-terminal
   */
  public UndefinedNonTerminalException(String message) {
    super(message);
  }
}
