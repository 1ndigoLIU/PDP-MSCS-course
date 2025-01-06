package sequentialSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads and processes the courses CSV file. Parses the file to populate a map of course identifiers
 * with corresponding data structures.
 *
 * @author Xihao Liu
 */
public class CourseCSVReader {
  /**
   * Reads the courses CSV file and populates the provided map with course information.
   *
   * @param path       The path to the courses CSV file.
   * @param coursesMap A map to store the course data, keyed by a combination of module and
   *                   presentation.
   */
  public void readCourses(String path, Map<String, Map<Integer, Integer>> coursesMap) {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String titleLine = reader.readLine();
      String[] titles = cleanData(titleLine.split(Constants.CSV_DELIMITER));
      int codeModuleIndex = Arrays.asList(titles).indexOf(Constants.CODE_MODULE_COLUMN);
      int codePresentationIndex = Arrays.asList(titles).indexOf(Constants.CODE_PRESENTATION_COLUMN);
      String recordLine;
      while ((recordLine = reader.readLine()) != null) {
        String[] records = cleanData(recordLine.split(Constants.CSV_DELIMITER));
        coursesMap.put(
            records[codeModuleIndex] + Constants.UNDERSCORE + records[codePresentationIndex],
            new HashMap<>());
      }
    } catch (IOException e) {
      e.printStackTrace();
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
      data[i] = data[i].replace(Constants.DOUBLE_QUOTA, Constants.EMPTY_STRING);
    }
    return data;
  }
}