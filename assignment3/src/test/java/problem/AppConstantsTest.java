package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppConstantsTest {

  @Test
  void testAppConstants() {
    AppConstants appConstants = new AppConstants();
    assertNotNull(appConstants);
  }
}