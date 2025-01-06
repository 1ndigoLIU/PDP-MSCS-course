package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class FileUtilsTest {

  FileUtils fileUtils = new FileUtils();
  private static final String COURSE_FILE_PATH = "./testFile/courses_test.csv";
  private static final String OUTPUT_FILE_PATH = "./testOutput/";
  Path pathTest = Paths.get("./output_test/");

  @Test
  void isCSVFileValid() {
    assertTrue(fileUtils.isCSVFileValid(COURSE_FILE_PATH));
    assertFalse(fileUtils.isCSVFileValid(""));
    assertFalse(fileUtils.isCSVFileValid(null));
  }

  @Test
  void checkAndCreateOutputPath() throws IOException {
    fileUtils.checkAndCreateOutputPath(OUTPUT_FILE_PATH);

    fileUtils.checkAndCreateOutputPath("./output_test/");
    Files.delete(pathTest);
  }
}