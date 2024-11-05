package problem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class CLIHandlerTest {

  @Test
  void parseArguments_EmailCommandSuccess() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    Map<String, String> expectedOptions = new HashMap<>();
    expectedOptions.put("--email", null);
    expectedOptions.put("--email-template", "template/template_testCLIHandler.txt");
    expectedOptions.put("--csv-file", "csv/csv-test.csv");
    expectedOptions.put("--output-dir", "output/");
    assertEquals(expectedOptions, cliHandler.getOptions());
  }

  @Test
  void parseArguments_LetterCommandSuccess() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--letter", "--letter-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    Map<String, String> expectedOptions = new HashMap<>();
    expectedOptions.put("--letter", null);
    expectedOptions.put("--letter-template", "template/template_testCLIHandler.txt");
    expectedOptions.put("--csv-file", "csv/csv-test.csv");
    expectedOptions.put("--output-dir", "output/");
    assertEquals(expectedOptions, cliHandler.getOptions());
  }

  @Test
  void parseArguments_EmailTemplateNoValueError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--csv-file", "csv/csv-test.csv", "--output-dir", "output/",
        "--email-template"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.EMAIL_TEMPLATE_NO_VALUE_ERROR_MESSAGE);
  }

  @Test
  void parseArguments_LetterTemplateNoValueError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--letter", "--csv-file", "csv/csv-test.csv", "--output-dir", "output/",
        "--letter-template"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.LETTER_TEMPLATE_NO_VALUE_ERROR_MESSAGE);
  }

  @Test
  void parseArguments_OutputDirNoValueError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--csv-file", "csv/csv-test.csv", "--email-template",
        "template/template_testCLIHandler.txt", "--output-dir"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.OUTPUT_DIR_NO_VALUE_ERROR_MESSAGE);
  }

  @Test
  void parseArguments_CSVFileNoValueError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--output-dir", "output/", "--csv-file"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.CSV_FILE_NO_VALUE_ERROR_MESSAGE);
  }

  @Test
  void parseArguments_UnknownArgumentError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--output-dir", "output/", "--csv-file", "csv/csv-test.csv", "--unknown-argument"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(),
        CommandConstant.UNKNOWN_ARG_ERROR_MESSAGE + "--unknown-argument");
  }

  @Test
  void validateOptions_NoCoreCommandError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email-template", "template/template_testCLIHandler.txt", "--csv-file",
        "csv/csv-test.csv", "--output-dir", "output/"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.NO_CORE_COMMAND_ERROR_MESSAGE);
  }

  @Test
  void validateOptions_MultiCoreCommandError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--letter", "--email-template",
        "template/template_testCLIHandler.txt", "--csv-file", "csv/csv-test.csv", "--output-dir",
        "output/"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.MULTI_CORE_COMMAND_ERROR_MESSAGE);
  }

  @Test
  void validateOptions_EmailNoTemplateError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.EMAIL_NO_TEMPLATE_ERROR_MESSAGE);
  }

  @Test
  void validateOptions_LetterNoTemplateError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--letter", "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.LETTER_NO_TEMPLATE_ERROR_MESSAGE);
  }

  @Test
  void validateOptions_NoCSVFileError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--output-dir", "output/"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.NO_CSV_FILE_ERROR_MESSAGE);
  }

  @Test
  void validateOptions_NoOutputDirError() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv"};
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      cliHandler.parseArguments(args);
    });
    assertEquals(exception.getMessage(), CommandConstant.NO_OUTPUT_DIR_ERROR_MESSAGE);
  }

  @Test
  void getTemplatePath_Email() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    assertEquals(cliHandler.getTemplatePath(), "template/template_testCLIHandler.txt");
  }

  @Test
  void getTemplatePath_Letter() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--letter", "--letter-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    assertEquals(cliHandler.getTemplatePath(), "template/template_testCLIHandler.txt");
  }

  @Test
  void getTemplatePath_Error() {
    CLIHandler cliHandler = new CLIHandler();
    Exception exception = assertThrows(IllegalArgumentException.class, cliHandler::getTemplatePath);
    assertEquals(exception.getMessage(), CommandConstant.NO_CORE_COMMAND_ERROR_MESSAGE);
  }

  @Test
  void getCoreCommand_Email() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    assertEquals(cliHandler.getCoreCommand(), CommandConstant.EMAIL_COMMAND);
  }

  @Test
  void getCoreCommand_Letter() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--letter", "--letter-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    assertEquals(cliHandler.getCoreCommand(), CommandConstant.LETTER_COMMAND);
  }

  @Test
  void getCoreCommand_Error() {
    CLIHandler cliHandler = new CLIHandler();
    Exception exception = assertThrows(IllegalArgumentException.class, cliHandler::getCoreCommand);
    assertEquals(exception.getMessage(), CommandConstant.NO_CORE_COMMAND_ERROR_MESSAGE);
  }

  @Test
  void getOptions() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    Map<String, String> expectedOptions = new HashMap<>();
    expectedOptions.put("--email", null);
    expectedOptions.put("--email-template", "template/template_testCLIHandler.txt");
    expectedOptions.put("--csv-file", "csv/csv-test.csv");
    expectedOptions.put("--output-dir", "output/");
    assertEquals(expectedOptions, cliHandler.getOptions());
  }

  @Test
  void testEquals() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    CLIHandler cliHandler_diff = new CLIHandler();
    String[] args_diff = {"--letter", "--letter-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler_diff.parseArguments(args_diff);
    CLIHandler cliHandler_same = new CLIHandler();
    cliHandler_same.parseArguments(args);
    String diffClass = "this is different class";
    assertTrue(cliHandler.equals(cliHandler));
    assertTrue(cliHandler.equals(cliHandler_same));
    assertFalse(cliHandler.equals(null));
    assertFalse(cliHandler.equals(diffClass));
    assertFalse(cliHandler.equals(cliHandler_diff));
  }

  @Test
  void testHashCode() {
    CLIHandler cliHandler = new CLIHandler();
    String[] args = {"--email", "--email-template", "template/template_testCLIHandler.txt",
        "--csv-file", "csv/csv-test.csv", "--output-dir", "output/"};
    cliHandler.parseArguments(args);
    assertEquals(cliHandler.hashCode(), Objects.hashCode(cliHandler.getOptions()));
  }
}