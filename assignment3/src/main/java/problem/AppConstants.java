package problem;

/**
 * The AppConstants class stores the public static final constants used in this application.
 *
 * @author Xihao Liu
 */
public class AppConstants {

  /**
   * The newline symbol for Java print
   */
  public static final String NEWLINE_SYMBOL = "\n";
  /**
   * The double quote symbol
   */
  public static final char DOUBLE_QUOTE_SYMBOL = '"';
  /**
   * The comma symbol
   */
  public static final char COMMA_SYMBOL = ',';
  /**
   * The space symbol
   */
  public static final char SPACE_SYMBOL = ' ';
  /**
   * The timestamp format to generate output file name
   */
  public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH-mm-ss";
  /**
   * The case number key string used for generating output file name
   */
  public static final String CASE_NUMBER_KEY = "case number = ";
  /**
   * The file extension of txt file
   */
  public static final String TXT_EXT = ".txt";
  /**
   * The left placeholder wrapping symbol in template
   */
  public static final String LEFT_PLACEHOLDER_WRAPPING_SYMBOL = "[[";
  /**
   * The right placeholder wrapping symbol in template
   */
  public static final String RIGHT_PLACEHOLDER_WRAPPING_SYMBOL = "]]";
  /**
   * Error message about getting a record from csv file records by index out of range
   */
  public static final String RECORD_INDEX_OUT_OF_RANGE_ERROR_MESSAGE = "Row index of records list is out of bounds";
  /**
   * Error message about missing right placeholder wrapping symbol for a placeholder
   */
  public static final String PLACEHOLDER_NO_CLOSE_ERROR_MESSAGE = "Error: Invalid template format. Missing closing ']]' for a placeholder.";
  /**
   * Error message about missing data to fill a placeholder in template
   */
  public static final String PLACEHOLDER_NO_DATA_MATCH_ERROR_MESSAGE = "Error: No record's date provided for placeholder: ";
  /**
   * Error message about invalid file path
   */
  public static final String INVALID_FILE_PATH_ERROR_MESSAGE = "Error: File not found, file path: ";
}
