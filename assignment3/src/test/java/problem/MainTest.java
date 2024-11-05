package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

  @Test
  void testConstructor() {
    Main testMain = new Main();
    assertNotNull(testMain);
  }

  @Test
  void testMain_EmailCommand() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    String[] args = {"--email", "--email-template", "template/email-template.txt", "--csv-file",
        "csv/insurance-company-members.csv", "--output-dir", "output/generate_from_canvas_file/"};
    Main.main(args);
    assertEquals(CommandConstant.SEND_EMAIL_STUB.trim(), outContent.toString().trim());
    System.setOut(originalOut);
  }

  @Test
  void testMain_LetterCommand() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    String[] args = {"--letter", "--letter-template",
        "template/template_testTemplateEngine_normal.txt", "--csv-file", "csv/csv-test.csv",
        "--output-dir", "output/generate_from_canvas_file/"};
    Main.main(args);
    assertEquals(CommandConstant.SEND_LETTER_STUB.trim(), outContent.toString().trim());
    System.setOut(originalOut);
  }

  @Test
  void testMain_CatchException() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    // insurance-company-members.txt
    String[] args = {};
    Main.main(args);
    assertTrue(outContent.toString().contains(CommandConstant.USAGE));
    System.setErr(originalErr);
  }
}