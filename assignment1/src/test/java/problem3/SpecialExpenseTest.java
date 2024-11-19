package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialExpenseTest {
  private double mortgageInterestPaid;
  private double propertyTaxPaid;
  private double studLoanTuitionPaid;
  private double retireSavingContr;
  private double healthSavingContr;
  private double charitableExpenses;
  private SpecialExpense specialExpense;

  @BeforeEach
  void setUp() {
    mortgageInterestPaid = 1200;
    propertyTaxPaid = 26000;
    studLoanTuitionPaid = 0;
    retireSavingContr = 15000;
    healthSavingContr = 15000;
    charitableExpenses = 0;
    specialExpense = new SpecialExpense(mortgageInterestPaid, propertyTaxPaid,
        studLoanTuitionPaid, retireSavingContr, healthSavingContr, charitableExpenses);
  }

  @Test
  void getMortgageInterestPaid() {
    assertEquals(mortgageInterestPaid, specialExpense.getMortgageInterestPaid());
  }

  @Test
  void getPropertyTaxPaid() {
    assertEquals(propertyTaxPaid, specialExpense.getPropertyTaxPaid());
  }

  @Test
  void getStudLoanTuitionPaid() {
    assertEquals(studLoanTuitionPaid, specialExpense.getStudLoanTuitionPaid());
  }

  @Test
  void getRetireSavingContr() {
    assertEquals(retireSavingContr, specialExpense.getRetireSavingContr());
  }

  @Test
  void getHealthSavingContr() {
    assertEquals(healthSavingContr, specialExpense.getHealthSavingContr());
  }

  @Test
  void getCharitableExpenses() {
    assertEquals(charitableExpenses, specialExpense.getCharitableExpenses());
  }

  @Test
  void testEquals() {
    assertTrue(specialExpense.equals(specialExpense));
    Object testObj1 = new Object();
    assertFalse(specialExpense.equals(testObj1));
    Object testObj2 = null;
    assertFalse(specialExpense.equals(testObj2));
    SpecialExpense sameSpecialExpense = new SpecialExpense(mortgageInterestPaid, propertyTaxPaid,
        studLoanTuitionPaid, retireSavingContr, healthSavingContr, charitableExpenses);
    assertTrue(specialExpense.equals(sameSpecialExpense));
    SpecialExpense diffSpecialExpense1 = new SpecialExpense(
        mortgageInterestPaid + 1, propertyTaxPaid, studLoanTuitionPaid,
        retireSavingContr, healthSavingContr, charitableExpenses);
    assertFalse(specialExpense.equals(diffSpecialExpense1));
    SpecialExpense diffSpecialExpense2 = new SpecialExpense(
        mortgageInterestPaid, propertyTaxPaid + 1, studLoanTuitionPaid,
        retireSavingContr, healthSavingContr, charitableExpenses);
    assertFalse(specialExpense.equals(diffSpecialExpense2));
    SpecialExpense diffSpecialExpense3 = new SpecialExpense(
        mortgageInterestPaid, propertyTaxPaid, studLoanTuitionPaid + 1,
        retireSavingContr, healthSavingContr, charitableExpenses);
    assertFalse(specialExpense.equals(diffSpecialExpense3));
    SpecialExpense diffSpecialExpense4 = new SpecialExpense(
        mortgageInterestPaid, propertyTaxPaid, studLoanTuitionPaid,
        retireSavingContr + 1, healthSavingContr, charitableExpenses);
    assertFalse(specialExpense.equals(diffSpecialExpense4));
    SpecialExpense diffSpecialExpense5 = new SpecialExpense(
        mortgageInterestPaid, propertyTaxPaid, studLoanTuitionPaid,
        retireSavingContr, healthSavingContr + 1, charitableExpenses);
    assertFalse(specialExpense.equals(diffSpecialExpense5));
    SpecialExpense diffSpecialExpense6 = new SpecialExpense(
        mortgageInterestPaid, propertyTaxPaid, studLoanTuitionPaid,
        retireSavingContr, healthSavingContr, charitableExpenses + 1);
    assertFalse(specialExpense.equals(diffSpecialExpense6));


  }

  @Test
  void testHashCode() {
    SpecialExpense otherSpecialExpense = new SpecialExpense(mortgageInterestPaid, propertyTaxPaid,
        studLoanTuitionPaid, retireSavingContr, healthSavingContr, charitableExpenses);
    assertEquals(specialExpense.hashCode(), otherSpecialExpense.hashCode());
  }
}