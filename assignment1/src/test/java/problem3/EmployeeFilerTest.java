package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeFilerTest {

  private String taxId;
  private FilerType illegalFilerType;
  private ContactInfo contactInfo;
  private double lastYearEarnings;
  private double incomeTaxPaid;
  private SpecialExpense specialExpense;
  private EmployeeFiler employeeFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    illegalFilerType = FilerType.HEAD_OF_THE_HOUSEHOLD;
    contactInfo = new ContactInfo(new Name("Xihao", "Liu"), "225 Terry Ave N", "6173732462",
        "liu.xiha@northeastern.edu");
    lastYearEarnings = 72000;
    incomeTaxPaid = 1000;
    specialExpense = new SpecialExpense(600, 13000, 1000, 900, 600, 0);
  }

  @Test
  void testConstructorException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      employeeFiler = new EmployeeFiler(taxId, illegalFilerType, contactInfo, lastYearEarnings,
          incomeTaxPaid, specialExpense);
    });
    String expectedMessage = "Individual Filer Type Wrong! It should be EMPLOYEE.";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void testToString() {
    EmployeeFiler employeeFiler1 = new EmployeeFiler(taxId, FilerType.EMPLOYEE, contactInfo,
        lastYearEarnings, incomeTaxPaid, specialExpense);
    String exceptedString = "EmployeeFiler{taxId='aptx4869', filerType=EMPLOYEE, "
        + "contactInfo=ContactInfo{name=Name{firstName='Xihao', lastName='Liu'}, "
        + "address='225 Terry Ave N', phoneNumber='6173732462', "
        + "email='liu.xiha@northeastern.edu'}, lastYearEarnings=72000.0, "
        + "incomeTaxPaid=1000.0, specialExpense=SpecialExpense{mortgageInterestPaid=600.0, "
        + "propertyTaxPaid=13000.0, studLoanTuitionPaid=1000.0, retireSavingContr=900.0, "
        + "healthSavingContr=600.0, charitableExpenses=0.0}}";
    assertEquals(exceptedString, employeeFiler1.toString());
  }
}