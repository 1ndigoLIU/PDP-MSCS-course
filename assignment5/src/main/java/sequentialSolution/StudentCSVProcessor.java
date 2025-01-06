package sequentialSolution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Processes student VLE data from a CSV file. Reads data, aggregates click counts, and writes
 * results to output files.
 *
 * @author Xihao Liu
 */
public class StudentCSVProcessor {

  /**
   * A map of course data where:
   * - Key: A course identifier (module + presentation).
   * - Value: A map of dates to total click counts.
   */
  private Map<String, Map<Integer, Integer>> coursesMap;

  /**
   * Constructs a StudentCSVProcessor with the provided courses map.
   *
   * @param coursesMap A map to store aggregated course click data.
   */
  public StudentCSVProcessor(Map<String, Map<Integer, Integer>> coursesMap) {
    this.coursesMap = coursesMap;
  }

  /**
   * Reads the student VLE CSV file and aggregates click data by course and date.
   *
   * @param studentVleCsvPath Path to the student VLE CSV file.
   */
  public void readCSVFile(String studentVleCsvPath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(studentVleCsvPath))) {
      String titleLine = reader.readLine();
      String[] titles = cleanData(titleLine.split(Constants.CSV_DELIMITER));
      int codeModuleIndex = Arrays.asList(titles).indexOf(Constants.CODE_MODULE_COLUMN);
      int codePresentationIndex = Arrays.asList(titles).indexOf(Constants.CODE_PRESENTATION_COLUMN);
      int dateIndex = Arrays.asList(titles).indexOf(Constants.DATE_COLUMN);
      int sumClickIndex = Arrays.asList(titles).indexOf(Constants.SUM_CLICK_COLUMN);
      String recordLine;
      while ((recordLine = reader.readLine()) != null) {
        String[] records = cleanData(recordLine.split(Constants.CSV_DELIMITER));
        String course =
            records[codeModuleIndex] + Constants.UNDERSCORE + records[codePresentationIndex];
        Integer date = Integer.parseInt(records[dateIndex]);
        Integer sumClick = Integer.parseInt(records[sumClickIndex]);
        this.coursesMap.get(course).merge(date, sumClick, Integer::sum);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes aggregated click data to CSV files, one for each course.
   *
   * @param outputPath The output directory path.
   */
  public void outputCourseClickCSV(String outputPath) {
    for (String key : coursesMap.keySet()) {
      Map<Integer, Integer> courseData = coursesMap.get(key);
      if (courseData != null && !courseData.isEmpty()) {
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(outputPath + key + Constants.CSV_FILE_EXTENSION))) {
          writer.write(Constants.DOUBLE_QUOTA + Constants.DATE_COLUMN + Constants.DOUBLE_QUOTA
              + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA + Constants.TOTAL_CLICKS_COLUMN
              + Constants.DOUBLE_QUOTA + Constants.NEW_LINE);
          courseData.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(data -> {
            try {
              String outputLine = Constants.DOUBLE_QUOTA + data.getKey() + Constants.DOUBLE_QUOTA
                  + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA + data.getValue()
                  + Constants.DOUBLE_QUOTA + Constants.NEW_LINE;
              writer.write(outputLine);
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Cleans an array of strings by removing double quotes.
   *
   * @param data An array of strings to clean.
   * @return The cleaned array of strings.
   */
  private String[] cleanData(String[] data) {
    for (int i = 0; i < data.length; i++) {
      data[i] = data[i].replace(sequentialSolution.Constants.DOUBLE_QUOTA,
          sequentialSolution.Constants.EMPTY_STRING);
    }
    return data;
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
    StudentCSVProcessor that = (StudentCSVProcessor) o;
    return Objects.equals(coursesMap, that.coursesMap);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(coursesMap);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "StudentCSVProcessor{" + "coursesMap=" + coursesMap + '}';
  }
}
