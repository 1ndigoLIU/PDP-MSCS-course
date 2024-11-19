package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndividualFilerTest {

  private String taxId;
  private FilerType filerType;
  private ContactInfo contactInfo;
  private double lastYearEarnings;
  private double incomeTaxPaid;
  private SpecialExpense specialExpense;
  private IndividualFiler individualFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    filerType = FilerType.EMPLOYEE;
    contactInfo = new ContactInfo(new Name("Xihao", "Liu"), "225 Terry Ave N", "6173732462",
        "liu.xiha@northeastern.edu");
    lastYearEarnings = 72000;
    incomeTaxPaid = 1000;
    specialExpense = new SpecialExpense(600, 13000, 1000, 900, 600, 0);
    individualFiler = new EmployeeFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense);
  }

  @Test
  void calculateTaxes() {
    double taxes = individualFiler.calculateTaxes();
    assertEquals(12815.5, taxes);

    IndividualFiler individualFiler2 = new EmployeeFiler(taxId, filerType, contactInfo, 52000,
        incomeTaxPaid, specialExpense);
    assertEquals(7117.5, individualFiler2.calculateTaxes());
  }

  @Test
  void calcSavingDeduction() {
    double savingDeduction = individualFiler.calcSavingDeduction();
    assertEquals(1050, savingDeduction);

    IndividualFiler individualFiler2 = new EmployeeFiler(taxId, filerType, contactInfo, 1000, 100,
        specialExpense);
    assertEquals(630, individualFiler2.calcSavingDeduction());
  }
}