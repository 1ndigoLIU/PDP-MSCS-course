package problem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class TemplateEngineTest {

  @Test
  void readTemplate_Success() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    assertDoesNotThrow(() -> {
      templateEngine.readTemplate();
    });
  }

  @Test
  void readTemplate_Error() {
    TemplateEngine templateEngine = new TemplateEngine("template/not_exist_template_file.txt");
    Exception exception = assertThrows(IOException.class, () -> {
      templateEngine.readTemplate();
    });
    assertEquals(exception.getMessage(),
        AppConstants.INVALID_FILE_PATH_ERROR_MESSAGE + "template/not_exist_template_file.txt");
  }

  @Test
  void processTemplate_Normal() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    assertDoesNotThrow(() -> {
      templateEngine.readTemplate();
    });
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("first_name", "James");
    dataMap.put("last_name", "Butt");
    String resultStr = templateEngine.processTemplate(dataMap);
    String exceptedResult = "this file is used for testing class TemplateEngine. Full Name: James Butt";
    assertEquals(resultStr, exceptedResult);
  }

  @Test
  void processTemplate_NoDataMatchError() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    assertDoesNotThrow(() -> {
      templateEngine.readTemplate();
    });
    Map<String, String> dataMap = new HashMap<>();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      String resultStr = templateEngine.processTemplate(dataMap);
    });
    assertEquals(exception.getMessage(),
        AppConstants.PLACEHOLDER_NO_DATA_MATCH_ERROR_MESSAGE + "first_name");
  }

  @Test
  void processTemplate_NoPlaceholder() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_noPlaceholder.txt");
    assertDoesNotThrow(() -> {
      templateEngine.readTemplate();
    });
    Map<String, String> dataMap = new HashMap<>();
    String resultStr = templateEngine.processTemplate(dataMap);
    String exceptedResult = "this file is used for testing class TemplateEngine. And no placeholder here.";
    assertEquals(resultStr, exceptedResult);
  }

  @Test
  void processTemplate_NoClosePlaceholderError() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_placeholderNoCloseError.txt");
    assertDoesNotThrow(() -> {
      templateEngine.readTemplate();
    });
    Map<String, String> dataMap = new HashMap<>();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      String resultStr = templateEngine.processTemplate(dataMap);
    });
    assertEquals(exception.getMessage(), AppConstants.PLACEHOLDER_NO_CLOSE_ERROR_MESSAGE);
  }


  @Test
  void outputToFile() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    assertDoesNotThrow(() -> {
      templateEngine.readTemplate();
      Map<String, String> dataMap = new HashMap<>();
      dataMap.put("first_name", "James");
      dataMap.put("last_name", "Butt");
      String resultStr = templateEngine.processTemplate(dataMap);
      templateEngine.outputToFile(resultStr, "--letter", "output/");
    });
  }

  @Test
  void testEquals() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    TemplateEngine templateEngine_Same = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    TemplateEngine templateEngine_Diff = new TemplateEngine("template/email-template.txt");
    String diffClass = "this is a different class Obj.";
    assertTrue(templateEngine.equals(templateEngine));
    assertTrue(templateEngine.equals(templateEngine_Same));
    assertFalse(templateEngine.equals(templateEngine_Diff));
    assertFalse(templateEngine.equals(diffClass));
    assertFalse(templateEngine.equals(null));
  }

  @Test
  void testHashCode() {
    TemplateEngine templateEngine = new TemplateEngine(
        "template/template_testTemplateEngine_normal.txt");
    assertEquals(templateEngine.hashCode(),
        Objects.hashCode("template/template_testTemplateEngine_normal.txt"));

  }
}