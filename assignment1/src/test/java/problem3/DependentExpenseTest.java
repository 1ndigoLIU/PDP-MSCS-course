package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DependentExpenseTest {

  private double childcareExpense;
  private double dependentCareExpense;
  private DependentExpense dependentExpense;

  @BeforeEach
  void setUp() {
    childcareExpense = 6000;
    dependentCareExpense = 3000;
    dependentExpense = new DependentExpense(childcareExpense, dependentCareExpense);
  }

  @Test
  void getChildcareExpense() {
    assertEquals(childcareExpense, dependentExpense.getChildcareExpense());
  }

  @Test
  void getDependentCareExpense() {
    assertEquals(dependentCareExpense, dependentExpense.getDependentCareExpense());
  }

  @Test
  void testEquals() {
    assertTrue(dependentExpense.equals(dependentExpense));
    Object obj1 = null;
    assertFalse(dependentExpense.equals(obj1));
    Object obj2 = new Object();
    assertFalse(dependentExpense.equals(obj2));
    DependentExpense sameDependentExpense = new DependentExpense(childcareExpense,
        dependentCareExpense);
    assertTrue(dependentExpense.equals(sameDependentExpense));
    DependentExpense diffDependentExpense1 = new DependentExpense(childcareExpense + 1,
        dependentCareExpense);
    assertFalse(dependentExpense.equals(diffDependentExpense1));
    DependentExpense diffDependentExpense2 = new DependentExpense(childcareExpense,
        dependentCareExpense + 1);
    assertFalse(dependentExpense.equals(diffDependentExpense2));
  }

  @Test
  void testHashCode() {
    DependentExpense sameDependentExpense = new DependentExpense(childcareExpense,
        dependentCareExpense);
    assertEquals(dependentExpense.hashCode(), sameDependentExpense.hashCode());
  }

  @Test
  void testToString() {
    String exceptedString = "DependentExpense{childcareExpense=6000.0, dependentCareExpense=3000.0}";
    assertEquals(exceptedString, dependentExpense.toString());
  }
}