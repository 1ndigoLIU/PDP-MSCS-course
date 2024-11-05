package problem;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A TemplateEngine class that processes templates by replacing placeholders with actual values.
 *
 * @author Xihao Liu
 */
public class TemplateEngine {

  /**
   * The relative or absolute path to the template file to be used.
   */
  private String templatePath;
  /**
   * The template string with placeholders in the format [[placeholder]].
   */
  private String template;

  /**
   * Constructor of class TemplateEngine.
   *
   * @param templatePath The relative or absolute path to the template file to be used.
   */
  public TemplateEngine(String templatePath) {
    this.templatePath = templatePath;
  }

  /**
   * Reads template file from path
   *
   * @throws IOException throw when the file path is invalid
   */
  public void readTemplate() throws IOException {
    Path path = Paths.get(templatePath);
    if (!Files.exists(path)) {
      throw new FileNotFoundException(AppConstants.INVALID_FILE_PATH_ERROR_MESSAGE + templatePath);
    }
    // Read the file content as a string and store it
    template = new String(Files.readAllBytes(path));
  }

  /**
   * Processes the template by replacing placeholders with the actual values from the provided data
   * map.
   *
   * @param dataMap A map containing the actual values for the placeholders.
   * @return The processed string with placeholders replaced by actual values.
   * @throws IllegalArgumentException if a placeholder has no corresponding value in the data map.
   */
  public String processTemplate(Map<String, String> dataMap) {
    StringBuilder processedTemplate = new StringBuilder();  // To build the final processed template
    int startIndex = 0;  // Index to track the start of the current placeholder

    // Iterate through the template and search for placeholders
    while (startIndex < template.length()) {
      int placeholderStart = template.indexOf(AppConstants.LEFT_PLACEHOLDER_WRAPPING_SYMBOL,
          startIndex);  // Find the start of the placeholder
      if (placeholderStart == -1) {
        // No more placeholders, append the rest of the template
        processedTemplate.append(template.substring(startIndex));
        break;
      }

      // Append the text before the placeholder
      processedTemplate.append(template.substring(startIndex, placeholderStart));

      int placeholderEnd = template.indexOf(AppConstants.RIGHT_PLACEHOLDER_WRAPPING_SYMBOL,
          placeholderStart);  // Find the end of the placeholder
      if (placeholderEnd == -1) {
        // If there is a no closing placeholder, throw an exception (invalid template)
        throw new IllegalArgumentException(AppConstants.PLACEHOLDER_NO_CLOSE_ERROR_MESSAGE);
      }

      // Extract the placeholder key without the enclosing '[[' and ']]'
      String placeholderKey = template.substring(
          placeholderStart + AppConstants.LEFT_PLACEHOLDER_WRAPPING_SYMBOL.length(),
          placeholderEnd);

      // Check if the placeholder key exists in the data map
      if (!dataMap.containsKey(placeholderKey)) {
        // Throw an exception if there is no corresponding value for the placeholder
        throw new IllegalArgumentException(
            AppConstants.PLACEHOLDER_NO_DATA_MATCH_ERROR_MESSAGE + placeholderKey);
      }

      // Append the value corresponding to the placeholder from the map
      processedTemplate.append(dataMap.get(placeholderKey));

      // Move the start index to the end of the current placeholder
      startIndex = placeholderEnd + AppConstants.RIGHT_PLACEHOLDER_WRAPPING_SYMBOL.length();
    }

    return processedTemplate.toString();
  }

  /**
   * Writes the given content to a file. If the file or its directories do not exist, they will be
   * created.
   *
   * @param content    The content to be written into the file.
   * @param command    The key command to generate the content.
   * @param outputPath The path to the directory where the output files will be saved.
   * @throws IOException if the file cannot be written.
   */
  public void outputToFile(String content, String command, String outputPath) throws IOException {
    // Generate the output file name with timestamp and random case number
    // Get timestamp
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.TIMESTAMP_FORMAT);
    String formattedTimestamp = now.format(formatter);
    // Get random case number
    int caseNumber = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    // Generate file name
    String fileName =
        command + AppConstants.SPACE_SYMBOL + formattedTimestamp + AppConstants.SPACE_SYMBOL
            + AppConstants.CASE_NUMBER_KEY + caseNumber + AppConstants.TXT_EXT;
    Path dirPath = Paths.get(outputPath);
    // Ensure the directory exists, if not, create it
    if (!Files.exists(dirPath)) {
      Files.createDirectories(dirPath);
    }
    Path filePath = dirPath.resolve(fileName);
    // Write the content to the file
    try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
      writer.write(content);
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
    TemplateEngine that = (TemplateEngine) o;
    return Objects.equals(templatePath, that.templatePath);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(templatePath);
  }
}
