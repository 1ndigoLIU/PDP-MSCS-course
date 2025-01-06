package concurrentSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores constants used throughout the application. Includes configuration values, column names,
 * and commonly used symbols.
 *
 * @author Xihao Liu
 */
public class Constants {

  /**
   * The number of producer threads for reading and producing data.
   */
  public static int PRODUCER_THREAD_NUM = 1;
  /**
   * The number of consumer threads for processing student VLE data.
   */
  public static int CONSUMER_THREAD_NUM = 5;
  /**
   * A poison pill used to signal the end of data in the blocking queue.
   */
  public static final ArrayList<String> POISON_PILL = new ArrayList<>(List.of("POISON"));
  /**
   * Represents the index value 0, typically used in arrays or lists.
   */
  public static int ZERO = 0;
  /**
   * Represents the index value 1, typically used in arrays or lists.
   */
  public static int ONE = 1;
  /**
   * Represents the index value 2, typically used in arrays or lists.
   */
  public static int TWO = 2;
  /**
   * Represents the index value 3, typically used in arrays or lists.
   */
  public static int THREE = 3;

  /**
   * Represents the index value 4, typically used in arrays or lists.
   */
  public static int FOUR = 4;
  /**
   * The file extension for CSV files.
   */
  public static final String CSV_FILE_EXTENSION = ".csv";
  /**
   * Double quotation mark used in CSV formatting.
   */
  public static final String DOUBLE_QUOTA = "\"";
  /**
   * Newline character for CSV formatting.
   */
  public static final String NEW_LINE = "\n";
  /**
   * The delimiter used in CSV files.
   */
  public static final String CSV_DELIMITER = ",";
  /**
   * An empty string constant.
   */
  public static final String EMPTY_STRING = "";
  /**
   * Underscore character used for joining strings or identifiers.
   */
  public static final String UNDERSCORE = "_";
  /**
   * The maximum capacity of the student VLE blocking queue.
   */
  public static int STUDENT_VLE_QUEUE_CAPACITY = 10;
  /**
   * Column name for the "code_module" field in CSV files.
   */
  public static final String CODE_MODULE_COLUMN = "code_module";
  /**
   * Column name for the "code_presentation" field in CSV files.
   */
  public static final String CODE_PRESENTATION_COLUMN = "code_presentation";
  /**
   * Column name for the "date" field in CSV files.
   */
  public static final String DATE_COLUMN = "date";
  /**
   * Column name for the "sum_click" field in CSV files.
   */
  public static final String SUM_CLICK_COLUMN = "sum_click";
  /**
   * Column name for the "module_presentation" field in CSV files.
   */
  public static final String MODULE_PRESENTATION_COLUMN = "module_presentation";
  /**
   * Column name for the "total_clicks" field in CSV files.
   */
  public static final String TOTAL_CLICKS_COLUMN = "total_clicks";
  /**
   * Prefix for high-activity output file names.
   */
  public static final String HIGH_ACTIVITY_FILE_PREFIX = "activity-";
}

