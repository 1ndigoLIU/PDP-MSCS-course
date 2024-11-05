package problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A CSVParser class for parsing CSV files and storing the parsed data into an internal data
 * structure.
 *
 * @author Xihao Liu
 */
public class CSVParser {

  /**
   * The relative or absolute path to the CSV file to be parsed.
   */
  private String filePath;
  /**
   * List to store the column names of all records
   */
  private List<String> columnNames;
  /**
   * List to store all parsed CSV records. Each row is represented as a List<String>.
   */
  private List<List<String>> records;

  /**
   * Constructor to initialize the CSVParser and the internal storage for records.
   */
  CSVParser(String filePath) {
    this.filePath = filePath;
    this.columnNames = new ArrayList<>();
    this.records = new ArrayList<>();
  }

  /**
   * Gets the number of all records in csv file
   *
   * @return the records' count
   */
  public int getRecordCount() {
    return records.size();
  }

  /**
   * Parses a CSV file from the file path and stores the parsed records internally.
   *
   * @throws IOException if there is an issue reading the file.
   */
  public void parseCSV() throws IOException {
    records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      // Read each line from the CSV file and parse it
      while ((line = br.readLine()) != null) {
        // Parse the current line
        List<String> parsedLine = parseCSVLine(line);
        // Handle empty lines
        if (parsedLine != null) {
          // Add parsed line to the list of records
          records.add(parsedLine);
        }
      }
      // Use the first row as column names (header)
      columnNames = records.remove(0);
    }
  }

  /**
   * Parses a single line of CSV data and splits it into individual fields based on commas and
   * quotes.
   *
   * @param line A single line of CSV data to be parsed.
   * @return A List<String> containing the fields of the CSV line.
   */
  private List<String> parseCSVLine(String line) {
    // Handle empty lines
    if (line.isEmpty()) {
      return null;
    }
    List<String> fields = new ArrayList<>(); // List to store the fields of the current line
    StringBuilder currentField = new StringBuilder(); // StringBuilder for the current field being parsed
    boolean insideQuotes = false; // Flag to track if the parser is inside double quotes
    // Iterate through each character in the line
    for (char c : line.toCharArray()) {
      if (c == AppConstants.DOUBLE_QUOTE_SYMBOL) {
        // Toggle the isInsideQuotes flag when encountering a double quote
        insideQuotes = !insideQuotes;
      } else if (c == AppConstants.COMMA_SYMBOL && !insideQuotes) {
        // If we encounter a comma and are not inside quotes, finalize the current field
        fields.add(currentField.toString().trim()); // Trim the field and add it to the list
        currentField = new StringBuilder(); // Reset the current field
      } else {
        // Append the current character to the current field
        currentField.append(c);
      }
    }
    // Add the last field to the list
    fields.add(currentField.toString().trim());
    return fields;
  }

  /**
   * Returns the map of column names to customer data in specific row
   *
   * @param rowIndex the index of specific row
   * @return The map of column names to customer data
   */
  public Map<String, String> getRecordMapByIndex(int rowIndex) {
    if (rowIndex >= 0 && rowIndex < records.size()) {
      Map<String, String> recordWithColName = new HashMap<>();
      for (int j = 0; j < columnNames.size(); j++) {
        recordWithColName.put(columnNames.get(j), records.get(rowIndex).get(j));
      }
      return recordWithColName;
    } else {
      throw new IndexOutOfBoundsException(AppConstants.RECORD_INDEX_OUT_OF_RANGE_ERROR_MESSAGE);
    }
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CSVParser csvParser = (CSVParser) o;
    return Objects.equals(filePath, csvParser.filePath);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(filePath);
  }
}
