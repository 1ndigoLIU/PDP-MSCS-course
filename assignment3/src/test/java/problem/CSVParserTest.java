package problem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class CSVParserTest {

  @Test
  void getRecordCount() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    assertDoesNotThrow(() -> {
      csvParser.parseCSV();
    });
    assertEquals(3, csvParser.getRecordCount());
  }

  @Test
  void parseCSV() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    assertDoesNotThrow(() -> {
      csvParser.parseCSV();
    });
  }

  @Test
  void getRecordMapByIndex_Success() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    assertDoesNotThrow(() -> {
      csvParser.parseCSV();
    });
    Map<String, String> recordMap = csvParser.getRecordMapByIndex(0);
    Map<String, String> expectedMap = new HashMap<>();
    String[] headers = {"first_name", "last_name", "company_name", "address", "city", "county",
        "state", "zip", "phone1", "phone2", "email", "web"};
    String[] record = {"James", "Butt", "Benton, John B Jr", "6649 N Blue Gum St", "New Orleans",
        "Orleans", "LA", "70116", "504-621-8927", "504-845-1427", "jbutt@gmail.com",
        "http://www.bentonjohnbjr.com"};
    for (int i = 0; i < headers.length; i++) {
      expectedMap.put(headers[i], record[i]);
    }
    assertEquals(recordMap, expectedMap);
  }

  @Test
  void getRecordMapByIndex_LessThanRangeError() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    assertDoesNotThrow(() -> {
      csvParser.parseCSV();
    });
    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
      csvParser.getRecordMapByIndex(-1);
    });
    assertEquals(exception.getMessage(), AppConstants.RECORD_INDEX_OUT_OF_RANGE_ERROR_MESSAGE);
  }

  @Test
  void getRecordMapByIndex_MoreThanRangeError() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    assertDoesNotThrow(() -> {
      csvParser.parseCSV();
    });
    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
      csvParser.getRecordMapByIndex(5);
    });
    assertEquals(exception.getMessage(), AppConstants.RECORD_INDEX_OUT_OF_RANGE_ERROR_MESSAGE);
  }

  @Test
  void testEquals() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    CSVParser csvParserSame = new CSVParser("csv/csv-test.csv");
    CSVParser csvParserDiff = new CSVParser("csv/insurance-company-members.csv");
    String diffClass = "this is a different class Obj.";
    assertTrue(csvParser.equals(csvParser));
    assertTrue(csvParser.equals(csvParserSame));
    assertFalse(csvParser.equals(csvParserDiff));
    assertFalse(csvParser.equals(null));
    assertFalse(csvParser.equals(diffClass));
  }

  @Test
  void testHashCode() {
    CSVParser csvParser = new CSVParser("csv/csv-test.csv");
    assertEquals(csvParser.hashCode(), Objects.hashCode("csv/csv-test.csv"));
  }
}