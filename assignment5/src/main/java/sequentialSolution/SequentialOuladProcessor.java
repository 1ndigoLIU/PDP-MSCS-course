package sequentialSolution;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class for sequential processing of OULAD (Open University Learning Analytics Dataset). Reads
 * course and student data from CSV files, processes it, and generates output files.
 *
 * @author Xihao Liu
 */
public class SequentialOuladProcessor {

  /**
   * The main entry point of the application. Validates input arguments, processes the data, and
   * outputs the results.
   *
   * @param args Command-line arguments:
   *             1. Path to the courses CSV file.
   *             2. Path to the student VLE CSV file.
   *             3. Output directory path.
   */
  public static void main(String[] args) {
    if (args.length != Constants.THREE) {
      System.err.println(
          "Error: Incorrect number of arguments. Expected 3 or 4 arguments, but got " + args.length
              + ".");
      return;
    }

    String coursesCsvPath = args[Constants.ZERO];
    String studentVleCsvPath = args[Constants.ONE];
    String outputPath = args[Constants.TWO];
    if (!FileUtils.isCSVFileValid(coursesCsvPath)) {
      System.err.println(
          "Error: The file at path '" + coursesCsvPath + "' is not a valid CSV file.");
      return;
    }
    if (!FileUtils.isCSVFileValid(studentVleCsvPath)) {
      System.err.println(
          "Error: The file at path '" + studentVleCsvPath + "' is not a valid CSV file.");
      return;
    }
    FileUtils.checkAndCreateOutputPath(outputPath);

    CourseCSVReader courseCSVReader = new CourseCSVReader();
    Map<String, Map<Integer, Integer>> coursesMap = new HashMap<>();
    courseCSVReader.readCourses(coursesCsvPath, coursesMap);

    StudentCSVProcessor studentCSVProcessor = new StudentCSVProcessor(coursesMap);
    studentCSVProcessor.readCSVFile(studentVleCsvPath);
    studentCSVProcessor.outputCourseClickCSV(outputPath);
  }
}
