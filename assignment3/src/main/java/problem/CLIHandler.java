package problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A CLIHandler class for handling and validating command-line arguments.
 *
 * @author Xihao Liu
 */
public class CLIHandler {

  // Class to store the parsed options from command line
  private Map<String, String> options;

  /**
   * Constructor to initialize the CLIHandler and the internal storage for options.
   */
  public CLIHandler() {
    this.options = new HashMap<>();
  }

  /**
   * Parses the command line arguments and validates them.
   *
   * @param args The command line arguments provided by the user.
   * @throws IllegalArgumentException if there are missing required arguments or invalid
   *                                  combinations.
   */
  public void parseArguments(String[] args) throws IllegalArgumentException {
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case CommandConstant.EMAIL_COMMAND:
          options.put(CommandConstant.EMAIL_COMMAND, null);
          break;
        case CommandConstant.LETTER_COMMAND:
          options.put(CommandConstant.LETTER_COMMAND, null);
          break;
        case CommandConstant.EMAIL_TEMPLATE_COMMAND:
          if (i + 1 < args.length) {
            options.put(CommandConstant.EMAIL_TEMPLATE_COMMAND, args[++i]);
          } else {
            throw new IllegalArgumentException(
                CommandConstant.EMAIL_TEMPLATE_NO_VALUE_ERROR_MESSAGE);
          }
          break;
        case CommandConstant.LETTER_TEMPLATE_COMMAND:
          if (i + 1 < args.length) {
            options.put(CommandConstant.LETTER_TEMPLATE_COMMAND, args[++i]);
          } else {
            throw new IllegalArgumentException(
                CommandConstant.LETTER_TEMPLATE_NO_VALUE_ERROR_MESSAGE);
          }
          break;
        case CommandConstant.OUTPUT_DIRECTORY_COMMAND:
          if (i + 1 < args.length) {
            options.put(CommandConstant.OUTPUT_DIRECTORY_COMMAND, args[++i]);
          } else {
            throw new IllegalArgumentException(CommandConstant.OUTPUT_DIR_NO_VALUE_ERROR_MESSAGE);
          }
          break;
        case CommandConstant.CSV_IMPORT_COMMAND:
          if (i + 1 < args.length) {
            options.put(CommandConstant.CSV_IMPORT_COMMAND, args[++i]);
          } else {
            throw new IllegalArgumentException(CommandConstant.CSV_FILE_NO_VALUE_ERROR_MESSAGE);
          }
          break;
        default:
          throw new IllegalArgumentException(CommandConstant.UNKNOWN_ARG_ERROR_MESSAGE + args[i]);
      }
    }
    validateOptions();
  }

  /**
   * Validates the parsed options to ensure the required arguments are present.
   *
   * @throws IllegalArgumentException if required arguments are missing or invalid combinations are
   *                                  provided.
   */
  private void validateOptions() {
    // Ensure either --email or --letter is provided, but not both
    if (!options.containsKey(CommandConstant.EMAIL_COMMAND) && !options.containsKey(
        CommandConstant.LETTER_COMMAND)) {
      throw new IllegalArgumentException(CommandConstant.NO_CORE_COMMAND_ERROR_MESSAGE);
    } else if (options.containsKey(CommandConstant.EMAIL_COMMAND) && options.containsKey(
        CommandConstant.LETTER_COMMAND)) {
      throw new IllegalArgumentException(CommandConstant.MULTI_CORE_COMMAND_ERROR_MESSAGE);
    }

    // Check required arguments for email generation
    if (options.containsKey(CommandConstant.EMAIL_COMMAND) && !options.containsKey(
        CommandConstant.EMAIL_TEMPLATE_COMMAND)) {
      throw new IllegalArgumentException(CommandConstant.EMAIL_NO_TEMPLATE_ERROR_MESSAGE);
    }

    // Check required arguments for letter generation
    if (options.containsKey(CommandConstant.LETTER_COMMAND) && !options.containsKey(
        CommandConstant.LETTER_TEMPLATE_COMMAND)) {
      throw new IllegalArgumentException(CommandConstant.LETTER_NO_TEMPLATE_ERROR_MESSAGE);
    }

    // Ensure the CSV file is provided
    if (!options.containsKey(CommandConstant.CSV_IMPORT_COMMAND)) {
      throw new IllegalArgumentException(CommandConstant.NO_CSV_FILE_ERROR_MESSAGE);
    }
    // Ensure the output directory is provided
    if (!options.containsKey(CommandConstant.OUTPUT_DIRECTORY_COMMAND)) {
      throw new IllegalArgumentException(CommandConstant.NO_OUTPUT_DIR_ERROR_MESSAGE);
    }
  }

  /**
   * Return the path of template file
   *
   * @return the path of template file
   */
  public String getTemplatePath() {
    // Determine if we are generating emails or letters based on the options
    if (options.containsKey(CommandConstant.EMAIL_COMMAND)) {
      // If --email is specified, use the email template
      return options.get(CommandConstant.EMAIL_TEMPLATE_COMMAND);
    } else if (options.containsKey(CommandConstant.LETTER_COMMAND)) {
      // If --letter is specified, use the letter template
      return options.get(CommandConstant.LETTER_TEMPLATE_COMMAND);
    } else {
      throw new IllegalArgumentException(CommandConstant.NO_CORE_COMMAND_ERROR_MESSAGE);
    }
  }

  /**
   * Returns the core command of all command args
   *
   * @return The core command string
   */
  public String getCoreCommand() {
    if (options.containsKey(CommandConstant.EMAIL_COMMAND)) {
      return CommandConstant.EMAIL_COMMAND;
    } else if (options.containsKey(CommandConstant.LETTER_COMMAND)) {
      return CommandConstant.LETTER_COMMAND;
    } else {
      throw new IllegalArgumentException(CommandConstant.NO_CORE_COMMAND_ERROR_MESSAGE);
    }
  }

  /**
   * Gets the map store all options
   *
   * @return The map store all options
   */
  public Map<String, String> getOptions() {
    return this.options;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CLIHandler that = (CLIHandler) o;
    return Objects.equals(options, that.options);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(options);
  }
}