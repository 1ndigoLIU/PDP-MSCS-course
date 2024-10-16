package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {
  private Name name;
  private Name nameMidEmpty;
  private Name nameMidNull;
  private String firstName;
  private String middleName;
  private String lastName;

  @BeforeEach
  void setUp() {
    firstName = "George";
    middleName = "Herman";
    lastName = "Ruth Jr.";
    name = new Name(firstName, middleName, lastName);
    nameMidEmpty = new Name(firstName, "", lastName);
    nameMidNull = new Name(firstName, null, lastName);
  }

  @Test
  void getFirstName() {
    assertEquals(firstName, name.getFirstName());
  }

  @Test
  void getMiddleName() {
    assertEquals(middleName, name.getMiddleName());
  }

  @Test
  void getLastName() {
    assertEquals(lastName, name.getLastName());
  }

  @Test
  public void testEquals_SameObject() {
    assertTrue(name.equals(name));
  }

  @Test
  public void testEquals_NullObject() {
    Name testName = null;
    assertFalse(name.equals(testName));
  }

  @Test
  public void testEquals_DifferentClass() {
    String otherClassObject = "Not a Name Object";
    assertFalse(name.equals(otherClassObject));
  }

  @Test
  public void testEquals_DiffFirstName() {
    Name testName = new Name("diff", "Herman", "Ruth Jr.");
    assertFalse(testName.equals(name));
  }

  @Test
  public void testEquals_DiffMiddleName() {
    Name testName = new Name("George", "diff", "Ruth Jr.");
    assertFalse(testName.equals(name));
  }

  @Test
  public void testEquals_DiffLastName() {
    Name testName = new Name("George", "Herman", "diff");
    assertFalse(testName.equals(name));
  }

  @Test
  public void testEquals_AllEqual() {
    Name testName = new Name("George", "Herman", "Ruth Jr.");
    assertTrue(testName.equals(name));
  }

  @Test
  void testHashCode() {
    int expectedHash = Objects.hash(firstName, middleName, lastName);
    assertEquals(expectedHash, name.hashCode());
  }

  @Test
  void testToString_MiddleNameExist() {
    String expectedStr = "George Herman Ruth Jr.";
    assertEquals(expectedStr, name.toString());
  }

  @Test
  void testToString_MiddleNameEmpty() {
    String expectedMidEmptyStr = "George Ruth Jr.";
    assertEquals(expectedMidEmptyStr, nameMidEmpty.toString());
  }

  @Test
  void testToString_MiddleNameNull() {
    String expectedMidNullStr = "George Ruth Jr.";
    assertEquals(expectedMidNullStr, nameMidNull.toString());
  }
}