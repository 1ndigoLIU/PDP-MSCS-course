package sequentialSolution;

import org.junit.jupiter.api.Test;

class SequentialOuladProcessorTest {

  SequentialOuladProcessor processor = new SequentialOuladProcessor();

  @Test
  void mainTest() {
    String[] args = new String[]{"./testFile/courses_test.csv", "./testFile/studentVle_test.csv",
        "./testOutput/"};
    SequentialOuladProcessor.main(args);
  }

  @Test
  void mainTestInvalidArgs() {
    String[] args = new String[]{};
    SequentialOuladProcessor.main(args);

    args = new String[]{"invalid_courses_path", "./testFile/studentVle_test.csv", "./testOutput/"};
    SequentialOuladProcessor.main(args);

    args = new String[]{"./testFile/courses_test.csv", "invalid_studentVle_path", "./testOutput/"};
    SequentialOuladProcessor.main(args);
  }
}