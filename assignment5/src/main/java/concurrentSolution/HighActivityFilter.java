package concurrentSolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages high-activity data records and writes them to a file.
 *
 * @author Xihao Liu
 */
public class HighActivityFilter {

  /**
   * A thread-safe list to store high-activity data records. Each record is represented as a map of
   * column names to values.
   */
  private static final List<Map<String, Object>> highActivities = Collections.synchronizedList(
      new ArrayList<>());

  /**
   * Adds a high-activity record to the list.
   *
   * @param modulePresentation The module-presentation identifier.
   * @param date               The date of high activity.
   * @param clicks             The number of clicks recorded.
   */
  public static void addActivity(String modulePresentation, int date, int clicks) {
    Map<String, Object> record = new HashMap<>();
    record.put(Constants.MODULE_PRESENTATION_COLUMN, modulePresentation);
    record.put(Constants.DATE_COLUMN, date);
    record.put(Constants.TOTAL_CLICKS_COLUMN, clicks);
    highActivities.add(record);
  }

  /**
   * Writes the high-activity data to a specified file after sorting by module-presentation and
   * date.
   *
   * @param filePath The path to the output file.
   */
  public static void writeToFile(String filePath) {
    highActivities.sort(Comparator.comparing(
            (Map<String, Object> record) -> (String) record.get(Constants.MODULE_PRESENTATION_COLUMN))
        .thenComparing(record -> (Integer) record.get(Constants.DATE_COLUMN)));

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(
          Constants.DOUBLE_QUOTA + Constants.MODULE_PRESENTATION_COLUMN + Constants.DOUBLE_QUOTA
              + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA + Constants.DATE_COLUMN
              + Constants.DOUBLE_QUOTA + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA
              + Constants.TOTAL_CLICKS_COLUMN + Constants.DOUBLE_QUOTA + Constants.NEW_LINE);
      for (Map<String, Object> record : highActivities) {
        String highActivityOutputLine =
            Constants.DOUBLE_QUOTA + record.get(Constants.MODULE_PRESENTATION_COLUMN)
                + Constants.DOUBLE_QUOTA + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA
                + record.get(Constants.DATE_COLUMN) + Constants.DOUBLE_QUOTA
                + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA + record.get(
                Constants.TOTAL_CLICKS_COLUMN) + Constants.DOUBLE_QUOTA;
        writer.write(highActivityOutputLine);
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
