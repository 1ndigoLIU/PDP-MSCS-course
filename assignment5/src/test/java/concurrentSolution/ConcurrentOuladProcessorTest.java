package concurrentSolution;

import org.junit.jupiter.api.Test;

class ConcurrentOuladProcessorTest {

  @Test
  void mainTest() {
    String[] args = new String[]{"./testFile/courses_test.csv", "./testFile/studentVle_test.csv",
        "./testOutput/"};
    ConcurrentOuladProcessor.main(args);

    args = new String[]{"./testFile/courses_test.csv", "./testFile/studentVle_test.csv",
        "./testOutput/", "20"};
    ConcurrentOuladProcessor.main(args);
  }

  @Test
  void mainTestWithInvalidArgs() {
    String[] args = new String[]{};
    ConcurrentOuladProcessor.main(args);

    args = new String[]{"invalid_courses_path", "./testFile/studentVle_test.csv", "./testOutput/"};
    ConcurrentOuladProcessor.main(args);

    args = new String[]{"./testFile/courses_test.csv", "invalid_studentVle_path", "./testOutput/"};
    ConcurrentOuladProcessor.main(args);
  }
}