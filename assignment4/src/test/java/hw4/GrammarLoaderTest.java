package hw4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class GrammarLoaderTest {

  private static final String TEST_JSON_PATH = "grammar/poem_grammar.json";

  @Test
  public void testLoadGrammar() {
    GrammarLoader grammarLoader = new GrammarLoader(TEST_JSON_PATH);
    Map<String, List<Expression>> grammar = grammarLoader.loadGrammar();

    assertNotNull(grammar);
    assertTrue(grammar.containsKey("start"));
    assertTrue(grammar.containsKey("object"));
    assertTrue(grammar.containsKey("verb"));
    assertTrue(grammar.containsKey("adverb"));

    assertEquals(1, grammar.get("start").size());
    assertEquals(3, grammar.get("object").size());
    assertEquals(3, grammar.get("verb").size());
    assertEquals(2, grammar.get("adverb").size());
  }

  @Test
  public void testGetGrammarTitleAndDesc() {
    GrammarLoader grammarLoader = new GrammarLoader(TEST_JSON_PATH);
    grammarLoader.loadGrammar();
    assertEquals("Poem Generator", grammarLoader.getGrammarTitle());
    assertEquals("A grammar that generates poems. ", grammarLoader.getGrammarDesc());
  }

  @Test
  public void testLoadGrammarWithInvalidFile() {
    GrammarLoader grammarLoader = new GrammarLoader("invalid_path.json");
    assertNull(grammarLoader.loadGrammar());
  }

  @Test
  public void testEqualsAndHashCode() {
    GrammarLoader loader1 = new GrammarLoader(TEST_JSON_PATH);
    GrammarLoader loader2 = new GrammarLoader(TEST_JSON_PATH);
    GrammarLoader loader3 = new GrammarLoader("different_path.json");

    assertEquals(loader1, loader1);
    assertEquals(loader1, loader2);
    assertEquals(loader1.hashCode(), loader2.hashCode());

    assertNotEquals(loader1, loader3);
    assertNotEquals(loader1, new Object());
    assertNotEquals(loader1, null);
    assertNotEquals(loader1.hashCode(), loader3.hashCode());
  }

  @Test
  public void testLoadGrammarWithoutDesc() throws IOException {
    GrammarLoader grammarLoader = new GrammarLoader("grammar/unit_test_grammar.json");
    grammarLoader.loadGrammar();
    assertEquals("", grammarLoader.getGrammarDesc());
  }
}