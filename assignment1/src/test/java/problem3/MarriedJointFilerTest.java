package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarriedJointFilerTest {

  private String taxId;
  private FilerType filerType;
  private FilerType illegalFilerType;
  private ContactInfo contactInfo;
  private double lastYearEarnings;
  private double incomeTaxPaid;
  private SpecialExpense specialExpense;

  private int dependentNum;
  private int minorChildNum;
  private DependentExpense dependentExpense;

  private MarriedJointFiler marriedJointFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    filerType = FilerType.MARRIED_FILLING_JOINTLY;
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
  void testConstructor() {
    marriedJointFiler = new MarriedJointFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
  }

  @Test
  void testConstructorException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      marriedJointFiler = new MarriedJointFiler(taxId, illegalFilerType, contactInfo,
          lastYearEarnings, incomeTaxPaid, specialExpense, dependentNum, minorChildNum,
          dependentExpense);
    });
    String expectedMessage = "Individual Filer Type Wrong! It should be MARRIED_FILLING_JOINTLY.";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void testEquals() {
    marriedJointFiler = new MarriedJointFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    assertEquals(marriedJointFiler, marriedJointFiler);
  }

  @Test
  void testHashCode() {
    marriedJointFiler = new MarriedJointFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    assertEquals(marriedJointFiler.hashCode(), marriedJointFiler.hashCode());
  }

  @Test
  void testToString() {
    marriedJointFiler = new MarriedJointFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    String exceptedString = "MarriedJointFiler{taxId='aptx4869', "
        + "filerType=MARRIED_FILLING_JOINTLY, contactInfo=ContactInfo{"
        + "name=Name{firstName='Xihao', lastName='Liu'}, address='225 Terry Ave N', "
        + "phoneNumber='6173732462', email='liu.xiha@northeastern.edu'}, "
        + "lastYearEarnings=155000.0, incomeTaxPaid=5000.0, "
        + "specialExpense=SpecialExpense{mortgageInterestPaid=1200.0, "
        + "propertyTaxPaid=26000.0, studLoanTuitionPaid=0.0, retireSavingContr=15000.0, "
        + "healthSavingContr=15000.0, charitableExpenses=0.0}}";
    assertEquals(exceptedString, marriedJointFiler.toString());
  }
}