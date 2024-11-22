package hw4;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles user input operations for the application. This class includes methods for prompting the
 * user to select a file, confirm yes/no responses, and match specific inputs such as integers and
 * quit commands.
 *
 * @author Xihao Liu
 */
public class UserInputHandler {

  /**
   * Scanner instance for reading user input from the console.
   */
  private final Scanner scanner;

  /**
   * Constructs a UserInputHandler with a new Scanner instance for reading input.
   */
  public UserInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Prompts the user to select a file by entering a number within the range of available files.
   * Continues to prompt until the user enters a valid number or the quit command ('q').
   *
   * @param maxIndex the maximum valid file index, inclusive
   * @return the selected file number as a string, or null if the user chooses to quit
   */
  public String getFileChoice(int maxIndex) {
    while (true) {
      System.out.println("\nWhich would you like to use? (q to quit)");
      String userInput = scanner.nextLine().trim();
      if (matchQuit(userInput)) {
        return null;
      } else if (matchInt(userInput)) {
        Integer number = Integer.parseInt(userInput);
        if (number < 1 || number > maxIndex) {
          System.out.println("Invalid number, please re-enter!");
        } else {
          return userInput;
        }
      } else {
        System.out.println("Please re-enter the number of grammar!");
      }
    }
  }

  /**
   * Prompts the user to enter 'y' or 'n' for a yes/no question. Continues to prompt until the user
   * enters a valid response.
   *
   * @return true if the user enters 'y', false if the user enters 'n'
   */
  public boolean getYesOrNo() {
    while (true) {
      String input = scanner.nextLine().trim();
      if (matchYes(input)) {
        return true;
      } else if (matchNo(input)) {
        return false;
      } else {
        System.out.println("Invalid input. Please enter 'y' or 'n':");
      }
    }
  }

  /**
   * Checks if the input matches the quit command.
   *
   * @param input the input string to check
   * @return true if the input is 'q' (case-insensitive), false otherwise
   */
  public boolean matchQuit(final String input) {
    return StrConstants.QUIT.equals(input.toLowerCase(Locale.US));
  }

  /**
   * Checks if the input is an unsigned integer.
   *
   * @param input the input string to check
   * @return true if the input is an unsigned integer, false otherwise
   */
  public boolean matchInt(final String input) {
    final Pattern pattern = Pattern.compile(StrConstants.REGEX_UNSIGNED_INT,
        Pattern.CASE_INSENSITIVE);
    final Matcher matcher = pattern.matcher(input);
    return matcher.find();
  }

  /**
   * Checks if the input matches the "yes" command.
   *
   * @param input the input string to check
   * @return true if the input is 'y' (case-insensitive), false otherwise
   */
  public boolean matchYes(final String input) {
    return StrConstants.YES.equals(input.toLowerCase(Locale.US));
  }

  /**
   * Checks if the input matches the "no" command.
   *
   * @param input the input string to check
   * @return true if the input is 'n' (case-insensitive), false otherwise
   */
  public boolean matchNo(final String input) {
    return StrConstants.NO.equals(input.toLowerCase(Locale.US));
  }

  /**
   * Checks if this UserInputHandler is equal to another object. Two UserInputHandlers are equal if
   * their scanner instances are equal.
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
    UserInputHandler that = (UserInputHandler) o;
    return Objects.equals(scanner, that.scanner);
  }

  /**
   * Returns the hash code for this UserInputHandler, based on the scanner.
   *
   * @return the hash code for this object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(scanner);
  }
}
