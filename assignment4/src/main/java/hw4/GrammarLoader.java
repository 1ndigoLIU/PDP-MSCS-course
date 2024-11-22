package hw4;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The GrammarLoader class is responsible for loading and parsing a JSON grammar file. It extracts
 * the grammar title, description, and non-terminal rules, converting them into a map of expressions
 * for use in sentence generation.
 *
 * @author Xihao Liu
 */
public class GrammarLoader {

  /**
   * Path to the grammar JSON file to be loaded.
   */
  private final String filePath;
  /**
   * Gson instance for parsing JSON data.
   */
  private final Gson gson = new Gson();
  /**
   * Factory to create Expression objects based on grammar rules.
   */
  private final ExpressionFactory expressionFactory = new ExpressionFactory();
  /**
   * The title of the grammar, extracted from the JSON file.
   */
  private String grammarTitle;
  /**
   * The description of the grammar, extracted from the JSON file.
   */
  private String grammarDesc;

  /**
   * Constructs a GrammarLoader with the specified file path.
   *
   * @param filePath the path to the JSON file containing grammar definitions
   */
  public GrammarLoader(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Loads and parses the grammar JSON file, extracting non-terminal rules and converting them into
   * a map of expressions. It processes each rule as a list of possible expansions.
   *
   * @return a map with non-terminal symbols as keys and lists of possible expansions as values, or
   * null if an error occurs
   */
  public Map<String, List<Expression>> loadGrammar() {
    try (FileReader reader = new FileReader(filePath)) {
      JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

      // Extract title and description
      grammarTitle = jsonObject.has(StrConstants.GRAMMAR_TITLE_KEY) ? jsonObject.get(
          StrConstants.GRAMMAR_TITLE_KEY).getAsString() : "";
      grammarDesc = jsonObject.has(StrConstants.GRAMMAR_DESCRIPTION_KEY) ? jsonObject.get(
          StrConstants.GRAMMAR_DESCRIPTION_KEY).getAsString() : "";

      // Parse the non-terminal rules
      Map<String, List<Expression>> grammar = new HashMap<>();
      for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
        String key = entry.getKey();
        JsonElement value = entry.getValue();

        // Only process arrays as non-terminal expansions
        if (value.isJsonArray()) {
          Type listType = new TypeToken<List<String>>() {
          }.getType();
          List<String> rawExpansions = gson.fromJson(value, listType);

          // Convert each String expansion into an Expression
          List<Expression> expressions = new ArrayList<>();
          for (String expansion : rawExpansions) {
            expressions.add(expressionFactory.createExpression(expansion, grammar));
          }
          grammar.put(key.toLowerCase(), expressions);
        }
      }
      return grammar;
    } catch (JsonIOException | JsonSyntaxException | IOException e) {
      System.err.println("Error loading grammar file: " + e.getMessage());
      return null;
    }
  }

  /**
   * Retrieves the title of the grammar as specified in the JSON file.
   *
   * @return the grammar title as a string, or an empty string if no title is present
   */
  public String getGrammarTitle() {
    return grammarTitle;
  }

  /**
   * Retrieves the description of the grammar as specified in the JSON file.
   *
   * @return the grammar description as a string, or an empty string if no description is present
   */
  public String getGrammarDesc() {
    return grammarDesc;
  }

  /**
   * Compares this GrammarLoader to another object for equality. Two GrammarLoaders are equal if
   * they have the same file path.
   *
   * @param o the object to be compared for equality
   * @return true if the objects are equal; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrammarLoader that = (GrammarLoader) o;
    return Objects.equals(filePath, that.filePath);
  }

  /**
   * Returns the hash code value for this GrammarLoader.
   *
   * @return the hash code based on the file path
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(filePath);
  }
}
