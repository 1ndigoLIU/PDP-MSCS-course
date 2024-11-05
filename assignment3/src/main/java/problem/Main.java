package problem;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * The Main class is the entry point for the program that processes customer information from a CSV
 * file and generates personalized emails or letters based on the provided templates.
 *
 * @author Xihao Liu
 */
public class Main {

  /**
   * The main method coordinates the entire flow of the program. It processes command-line
   * arguments, reads customer data from a CSV file, reads an email or letter template, and
   * generates personalized emails or letters for each customer. The generated files are saved to
   * the specified output directory.
   *
   * @param args Command-line arguments for configuring the program. Arguments include: --email or
   *             --letter (to specify the type of document to generate), --email-template or
   *             --letter-template (for specifying the template file), --output-dir (the directory
   *             to save generated files), and --csv-file (the CSV file containing customer data).
   */
  public static void main(String[] args) {
    CLIHandler cliHandler = new CLIHandler();

    try {
      // Parse command-line arguments using CLIHandler
      cliHandler.parseArguments(args);
      Map<String, String> options = cliHandler.getOptions();

      // Parse the CSV file to retrieve customer data using CSVParser
      CSVParser csvParser = new CSVParser(options.get(CommandConstant.CSV_IMPORT_COMMAND));
      csvParser.parseCSV();

      // Read template from file using class TemplateEngine
      TemplateEngine templateEngine = new TemplateEngine(cliHandler.getTemplatePath());
      templateEngine.readTemplate();

      // Process each customer from the CSV file and generate email or letter
      for (int i = 0; i < csvParser.getRecordCount(); i++) {
        Map<String, String> recordMapWithColName = csvParser.getRecordMapByIndex(i);
        // Generate personalized content using the TemplateEngine
        String generatedContent = templateEngine.processTemplate(recordMapWithColName);
        // Output the final content to specified path
        templateEngine.outputToFile(generatedContent, cliHandler.getCoreCommand(),
            options.get(CommandConstant.OUTPUT_DIRECTORY_COMMAND));
      }

      // Print stub when successfully execute
      if (Objects.equals(cliHandler.getCoreCommand(), CommandConstant.EMAIL_COMMAND)) {
        System.out.println(CommandConstant.SEND_EMAIL_STUB);
      } else if (Objects.equals(cliHandler.getCoreCommand(), CommandConstant.LETTER_COMMAND)) {
        System.out.println(CommandConstant.SEND_LETTER_STUB);
      }
    } catch (IllegalArgumentException | IOException e) {
      // Handle any errors that occur during execution
      // Prints usage information to guide the user on how to use the CLI tool.
      System.err.println(e.getMessage() + AppConstants.NEWLINE_SYMBOL + CommandConstant.USAGE);
    }
  }
}


