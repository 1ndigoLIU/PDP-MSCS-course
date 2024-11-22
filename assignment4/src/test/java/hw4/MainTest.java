package hw4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void testConstructor() {
    Main testMain = new Main();
    assertNotNull(testMain);
  }

  @Test
  void testMainWithIllegalArgs() {
    String[] argsWrongPath = {""};
    assertDoesNotThrow(() -> Main.main(argsWrongPath));
  }

  @Test
  void testMain_Quit() {
    String[] args = {"grammar/"};
    String input = "q\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    assertDoesNotThrow(() -> Main.main(args));
  }

  @Test
  void testMain_Sentence() {
    String[] args = {"grammar/"};
    String inputs = "1\nn\nq\n";
    InputStream in = new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8));
    System.setIn(in);
    assertDoesNotThrow(() -> Main.main(args));
  }
}