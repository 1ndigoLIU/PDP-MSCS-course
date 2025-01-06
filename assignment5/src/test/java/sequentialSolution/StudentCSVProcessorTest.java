package sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import concurrentSolution.FileUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;

class StudentCSVProcessorTest {

  private Map<String, Map<Integer, Integer>> coursesMap = null;
  private StudentCSVProcessor studentCSVProcessor = null;
  private static final String COURSE_FILE_PATH = "./testFile/courses_test.csv";
  private static final String STUDENT_VLE_FILE_PATH = "./testFile/studentVle_test.csv";
  private static final String OUTPUT_PATH = "./testOutput/";

  @BeforeEach
  void setUp() {
    FileUtils.checkAndCreateOutputPath(OUTPUT_PATH);
    coursesMap = new HashMap<>();
    studentCSVProcessor = new StudentCSVProcessor(coursesMap);
    CourseCSVReader courseCSVReader = new CourseCSVReader();
    courseCSVReader.readCourses(COURSE_FILE_PATH, coursesMap);
  }

  @Test
  void readCSVFile() {
    studentCSVProcessor.readCSVFile(STUDENT_VLE_FILE_PATH);
    assertTrue(coursesMap.containsKey("BBB_2014J"));
    Map<Integer, Integer> innerMap = coursesMap.get("BBB_2014J");
    assertTrue(innerMap.containsKey(138));
    assertEquals(innerMap.get(138), 11);
  }

  @Test
  void outputCourseClickCSV() {
    studentCSVProcessor.readCSVFile(STUDENT_VLE_FILE_PATH);
    studentCSVProcessor.outputCourseClickCSV(OUTPUT_PATH);

    String newPath = OUTPUT_PATH + "BBB_2014J.csv";
    String targetRow = "\"138\",\"11\"";
    File file = new File(newPath);
    assertTrue(file.exists());
    try (BufferedReader br = new BufferedReader(new FileReader(newPath))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.equals(targetRow)) {
          System.out.println("The target row exists.");
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void equalsAndHashCodeTest() {
    assertEquals(studentCSVProcessor, studentCSVProcessor);
    assertEquals(studentCSVProcessor, new StudentCSVProcessor(coursesMap));
    assertNotEquals(studentCSVProcessor, new StudentCSVProcessor(new HashMap<>()));
    assertNotEquals(studentCSVProcessor, null);
    assertNotEquals(studentCSVProcessor, new Object());

    assertEquals(studentCSVProcessor.hashCode(), new StudentCSVProcessor(coursesMap).hashCode());
    assertNotEquals(studentCSVProcessor.hashCode(),
        new StudentCSVProcessor(new HashMap<>()).hashCode());
  }

  @Test
  void toStringTest() {
    System.out.println(studentCSVProcessor.toString());
  }
}