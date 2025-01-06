package sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CourseCSVReaderTest {

  CourseCSVReader courseCSVReader = new CourseCSVReader();
  private static final String COURSE_FILE_PATH = "./testFile/courses_test.csv";

  @Test
  void readCoursesTestWithValidFile() {
    Map<String, Map<Integer, Integer>> coursesMap = new HashMap<>();
    courseCSVReader.readCourses(COURSE_FILE_PATH, coursesMap);

    assertEquals(coursesMap.size(), 5);
    assertTrue(coursesMap.containsKey("BBB_2014J"));

    for (Map.Entry<String, Map<Integer, Integer>> entry : coursesMap.entrySet()) {
      System.out.println("Key: " + entry.getKey());
    }
  }

  @Test
  void readCoursesTestWithInvalidFile() {
    Map<String, Map<Integer, Integer>> coursesMap = new HashMap<>();
    courseCSVReader.readCourses("invalid path", coursesMap);
  }
}