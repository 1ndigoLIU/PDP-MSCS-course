package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

class HighActivityFilterTest {

  @Test
  void addActivityAndWriteToFile() {
    FileUtils.checkAndCreateOutputPath("./testOutput/");
    HighActivityFilter highActivityFilter = new HighActivityFilter();
    HighActivityFilter.addActivity("AAA_2013J", 1, 2);
    HighActivityFilter.addActivity("AAA_2013J", 2, 3);
    HighActivityFilter.writeToFile("./testOutput/test_HighActivityFilter.csv");
    File file = new File("./testOutput/test_HighActivityFilter.csv");
    assertTrue(file.exists());
  }
}