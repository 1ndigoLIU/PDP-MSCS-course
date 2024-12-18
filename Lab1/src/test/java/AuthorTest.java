import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorTest {
  private Author jane;
  @BeforeEach
  void setUp() {
    this.jane = new Author("Jane Doe", "j@a.com",
        "222 Main St, Seattle, Wa, 98980");
  }

  @Test
  void getName() {
    assertEquals("Jane Doe", this.jane.getName());
  }

  @Test
  void getEmail() {
    assertEquals("j@a.com", this.jane.getEmail());
  }

  @Test
  void getAddress() {
    assertEquals("222 Main St, Seattle, Wa, 98980", this.jane.getAddress());
  }
}