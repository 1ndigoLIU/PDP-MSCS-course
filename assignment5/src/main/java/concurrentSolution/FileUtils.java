package concurrentSolution;

import java.io.File;

/**
 * Utility class for file operations. Provides methods to validate and handle CSV files.
 *
 * @author Xihao Liu
 */
public class FileUtils {

  /**
   * Checks if the given file path is a valid .csv file.
   *
   * @param path File path to check.
   * @return true if the file path is valid and ends with .csv; false otherwise.
   */
  public static boolean isCSVFileValid(String path) {
    try {
      File file = new File(path);
      String canonicalPath = file.getCanonicalPath();
      return canonicalPath.endsWith(Constants.CSV_FILE_EXTENSION);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks if the output path exists, and creates it if it doesn't.
   *
   * @param path Output path to check and create.
   * @throws IllegalArgumentException If the path is invalid.
   */
  public static void checkAndCreateOutputPath(String path) {
    File outputDir = new File(path);
    if (!outputDir.exists()) {
      outputDir.mkdirs();
    }
  }
}