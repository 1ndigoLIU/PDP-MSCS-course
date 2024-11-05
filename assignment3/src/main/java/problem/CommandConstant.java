package problem;

/**
 * The CommandConstant class stores the public static final constants about commands used throughout
 * the application.
 *
 * @author Xihao Liu
 */
public class CommandConstant {

  /**
   * The --email command
   */
  public static final String EMAIL_COMMAND = "--email";
  /**
   * The --letter command
   */
  public static final String LETTER_COMMAND = "--letter";
  /**
   * The email template importing command
   */
  public static final String EMAIL_TEMPLATE_COMMAND = "--email-template";
  /**
   * The letter template importing command
   */
  public static final String LETTER_TEMPLATE_COMMAND = "--letter-template";
  /**
   * The csv file importing command
   */
  public static final String CSV_IMPORT_COMMAND = "--csv-file";
  /**
   * The output directory command
   */
  public static final String OUTPUT_DIRECTORY_COMMAND = "--output-dir";
  /**
   * Error message about --email-template has no value
   */
  public static final String EMAIL_TEMPLATE_NO_VALUE_ERROR_MESSAGE = "Error: --email-template requires a file argument.";
  /**
   * Error message about --letter-template has no value
   */
  public static final String LETTER_TEMPLATE_NO_VALUE_ERROR_MESSAGE = "Error: --letter-template requires a file argument.";
  /**
   * Error message about --output-dir has no value
   */
  public static final String OUTPUT_DIR_NO_VALUE_ERROR_MESSAGE = "Error: --output-dir requires a directory path.";
  /**
   * Error message about --csv-file has no value
   */
  public static final String CSV_FILE_NO_VALUE_ERROR_MESSAGE = "Error: --csv-file requires a file path.";
  /**
   * Error message about unknown argument
   */
  public static final String UNKNOWN_ARG_ERROR_MESSAGE = "Error: unknown argument: ";
  /**
   * Error message about no core command
   */
  public static final String NO_CORE_COMMAND_ERROR_MESSAGE = "Error: You must specify core command either --email or --letter.";
  /**
   * Error message about multiple core command
   */
  public static final String MULTI_CORE_COMMAND_ERROR_MESSAGE = "Error: Cannot specify both --email and --letter.";
  /**
   * Error message about use core command --email but no template command
   */
  public static final String EMAIL_NO_TEMPLATE_ERROR_MESSAGE = "Error: --email-template is required when using --email.";
  /**
   * Error message about use core command --letter but no template command
   */
  public static final String LETTER_NO_TEMPLATE_ERROR_MESSAGE = "Error: --letter-template is required when using --letter.";
  /**
   * Error message about no --csv-file command
   */
  public static final String NO_CSV_FILE_ERROR_MESSAGE = "Error: command --csv-file is required.";
  /**
   * Error message about no --output-dir command
   */
  public static final String NO_OUTPUT_DIR_ERROR_MESSAGE = "Error: command --output-dir is required.";
  /**
   * The string to output when successfully execute --email command
   */
  public static final String SEND_EMAIL_STUB = "[stub] Sending generated emails to clients.";
  /**
   * The string to output when successfully execute --letter command
   */
  public static final String SEND_LETTER_STUB = "[stub] Printing generated letters to send to clients.";
  /**
   * A short explanation of how to use the program along with examples
   */
  public static final String USAGE = """
      
      Usage:
          --email                      only generate email messages
          --email-template <file>      accept a filename that holds the email template.
                                       Required if --email is used
      
          --letter                     only generate letters
          --letter-template <file>     accept a filename that holds the letter template.
                                       Required if --letter is used
      
          --output-dir <path>          accept the name of a folder, all output is placed in this folder
      
          --csv-file <path>            accept the name of the csv file to process
      
      Examples:
      
          --email --email-template email-template.txt --output-dir emails --csv-file customer.csv
      
          --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv
      """;
}
