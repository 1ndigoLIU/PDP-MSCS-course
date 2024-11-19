package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaxFilerTest {
  private String taxId;
  private FilerType filerType;
  private ContactInfo contactInfo;
  private double lastYearEarnings;
  private double incomeTaxPaid;
  private SpecialExpense specialExpense;
  private TaxFiler taxFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    filerType = FilerType.EMPLOYEE;
    contactInfo = new ContactInfo(new Name("Xihao", "Liu"), "225 Terry Ave N",
        "6173732462", "liu.xiha@northeastern.edu");
    lastYearEarnings = 72000;
    incomeTaxPaid = 1000;
    specialExpense = new SpecialExpense(600,13000,1000,
        900, 600, 0);
    taxFiler = new EmployeeFiler(taxId, filerType, contactInfo,
        lastYearEarnings, incomeTaxPaid, specialExpense);
  }

  @Test
  void getTaxId() {
    assertEquals(taxId, taxFiler.getTaxId());
  }

  @Test
  void getFilerType() {
    assertEquals(filerType, taxFiler.getFilerType());
  }

  @Test
  void getContactInfo() {
    assertEquals(contactInfo, taxFiler.getContactInfo());
  }

  @Test
  void getLastYearEarnings() {
    assertEquals(lastYearEarnings, taxFiler.getLastYearEarnings());
  }

  @Test
  void getIncomeTaxPaid() {
    assertEquals(incomeTaxPaid, taxFiler.getIncomeTaxPaid());
  }

  @Test
  void getSpecialExpense() {
    assertEquals(specialExpense, taxFiler.getSpecialExpense());
  }

  @Test
  void calculateTaxes() {
    double taxes = taxFiler.calculateTaxes();
    assertEquals(12815.5, taxes);
  }

  @Test
  void calcBasicTaxableIncome() {
    double basicTaxIncome = taxFiler.calcBasicTaxableIncome();
    assertEquals(71000, basicTaxIncome);
  }

  @Test
  void calcSavingDeduction() {
    double savingDeduction = taxFiler.calcSavingDeduction();
    assertEquals(1050, savingDeduction);
  }

  @Test
  void calcMortgageInterestAndPropertyDeduction() {
    double mortgageInterestPropDeduction = taxFiler.calcMortgageInterestAndPropertyDeduction();
    assertEquals(2500, mortgageInterestPropDeduction);

    TaxFiler taxFilerTest1 = new EmployeeFiler(taxId, filerType, contactInfo,
        lastYearEarnings + 250000, incomeTaxPaid, specialExpense);
    assertEquals(0, taxFilerTest1.calcMortgageInterestAndPropertyDeduction());

    SpecialExpense specialExpenseTest2 = new SpecialExpense(
        600, 600, 0,
        900, 600, 0);
    TaxFiler taxFilerTest2 = new EmployeeFiler(taxId, filerType, contactInfo,
        lastYearEarnings, incomeTaxPaid, specialExpenseTest2);
    assertEquals(0, taxFilerTest2.calcMortgageInterestAndPropertyDeduction());
  }

  @Test
  void testEquals() {
    assertTrue(taxFiler.equals(taxFiler));

    TaxFiler sameFiler = new EmployeeFiler(taxId, filerType, contactInfo,
          lastYearEarnings, incomeTaxPaid, specialExpense);
    assertTrue(taxFiler.equals(sameFiler));

    Object testObj = new Object();
    assertFalse(taxFiler.equals(testObj));
  }

  @Test
  void testHashCode() {
    TaxFiler diffFiler = new EmployeeFiler(taxId + "diff", filerType, contactInfo,
        lastYearEarnings, incomeTaxPaid, specialExpense);
    assertNotEquals(taxFiler.hashCode(), diffFiler.hashCode());
  }
}