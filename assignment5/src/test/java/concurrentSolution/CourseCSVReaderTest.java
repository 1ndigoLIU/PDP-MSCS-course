package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class CourseCSVReaderTest {

  CourseCSVReader courseCSVReader = new CourseCSVReader();
  private static final String COURSE_FILE_PATH = "./testFile/courses_test.csv";

  @Test
  void readCoursesTestWithValidFile() {
    ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap = new ConcurrentHashMap<>();
    courseCSVReader.readCourses(COURSE_FILE_PATH, coursesMap);

    assertEquals(coursesMap.size(), 5);
    assertTrue(coursesMap.containsKey("BBB_2014J"));
  }

  @Test
  void readCoursesTestWithInValidFile() {
    ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap = new ConcurrentHashMap<>();
    courseCSVReader.readCourses("invalid path", coursesMap);
  }
}