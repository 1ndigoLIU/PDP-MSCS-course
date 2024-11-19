package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {
  private String firstName;
  private String lastName;
  private Name name;

  @BeforeEach
  void setUp() {
    firstName = "Xihao";
    lastName = "Liu";
    name = new Name(firstName, lastName);
  }

  @Test
  void getFirstName() {
    assertEquals(firstName, name.getFirstName());
  }

  @Test
  void getLastName() {
    assertEquals(lastName, name.getLastName());
  }

  @Test
  void testEquals() {
    assertTrue(name.equals(name));
    Object testObj1 = new Object();
    assertFalse(name.equals(testObj1));
    Object testObj2 = null;
    assertFalse(name.equals(testObj2));
    Name sameName = new Name(firstName, lastName);
    assertTrue(name.equals(sameName));
    Name diffName1 = new Name("diff1stName", lastName);
    assertFalse(name.equals(diffName1));
    Name diffName2 = new Name(firstName, "diffLastName");
    assertFalse(name.equals(diffName2));
  }

  @Test
  void testHashCode() {
    Name sameName = new Name(firstName, lastName);
    assertEquals(name.hashCode(), sameName.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "Name{firstName='Xihao', lastName='Liu'}";
    assertEquals(exceptedString, name.toString());
  }
}