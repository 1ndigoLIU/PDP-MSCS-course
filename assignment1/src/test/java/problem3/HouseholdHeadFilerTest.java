package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HouseholdHeadFilerTest {

  private String taxId;
  private FilerType illegalFilerType;
  private ContactInfo contactInfo;
  private double lastYearEarnings;
  private double incomeTaxPaid;
  private SpecialExpense specialExpense;

  private int dependentNum;
  private int minorChildNum;
  private DependentExpense dependentExpense;

  private HouseholdHeadFiler householdHeadFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    illegalFilerType = FilerType.EMPLOYEE;
    contactInfo = new ContactInfo(new Name("Xihao", "Liu"), "225 Terry Ave N", "6173732462",
        "liu.xiha@northeastern.edu");
    lastYearEarnings = 155000;
    incomeTaxPaid = 5000;
    specialExpense = new SpecialExpense(1200, 26000, 0, 15000, 15000, 0);

    dependentNum = 2;
    minorChildNum = 3;
    dependentExpense = new DependentExpense(55000, 3000);
  }

  @Test
  void testConstructorException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      householdHeadFiler = new HouseholdHeadFiler(taxId, illegalFilerType, contactInfo,
          lastYearEarnings, incomeTaxPaid, specialExpense, dependentNum, minorChildNum,
          dependentExpense);
    });
    String expectedMessage = "Individual Filer Type Wrong! It should be HEAD_OF_THE_HOUSEHOLD.";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void testToString() {
    HouseholdHeadFiler householdHeadFiler1 = new HouseholdHeadFiler(taxId,
        FilerType.HEAD_OF_THE_HOUSEHOLD, contactInfo, lastYearEarnings, incomeTaxPaid,
        specialExpense, dependentNum, minorChildNum, dependentExpense);
    String exceptedString = "HouseholdHeadFiler{specialExpense=SpecialExpense{"
        + "mortgageInterestPaid=1200.0, propertyTaxPaid=26000.0, "
        + "studLoanTuitionPaid=0.0, retireSavingContr=15000.0, healthSavingContr=15000.0, "
        + "charitableExpenses=0.0}, incomeTaxPaid=5000.0, lastYearEarnings=155000.0, "
        + "filerType=HEAD_OF_THE_HOUSEHOLD, taxId='aptx4869', "
        + "contactInfo=ContactInfo{name=Name{firstName='Xihao', lastName='Liu'}, "
        + "address='225 Terry Ave N', phoneNumber='6173732462', "
        + "email='liu.xiha@northeastern.edu'}}";
    assertEquals(exceptedString, householdHeadFiler1.toString());
  }
}