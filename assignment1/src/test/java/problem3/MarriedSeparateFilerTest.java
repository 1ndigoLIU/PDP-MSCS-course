package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarriedSeparateFilerTest {

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

  private MarriedSeparateFiler marriedSeparateFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    filerType = FilerType.MARRIED_FILLING_SEPARATELY;
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
    marriedSeparateFiler = new MarriedSeparateFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    assertNotNull(marriedSeparateFiler);
  }

  @Test
  void testConstructorException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      marriedSeparateFiler = new MarriedSeparateFiler(taxId, illegalFilerType, contactInfo,
          lastYearEarnings, incomeTaxPaid, specialExpense, dependentNum, minorChildNum,
          dependentExpense);
    });
    String expectedMessage = "Individual Filer Type Wrong! It should be MARRIED_FILLING_SEPARATELY.";
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void testEquals() {
    marriedSeparateFiler = new MarriedSeparateFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    assertNotNull(marriedSeparateFiler);
    assertEquals(marriedSeparateFiler, marriedSeparateFiler);
  }

  @Test
  void testHashCode() {
    marriedSeparateFiler = new MarriedSeparateFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    assertNotNull(marriedSeparateFiler);
    assertEquals(marriedSeparateFiler.hashCode(), marriedSeparateFiler.hashCode());
  }

  @Test
  void testToString() {
    marriedSeparateFiler = new MarriedSeparateFiler(taxId, filerType, contactInfo, lastYearEarnings,
        incomeTaxPaid, specialExpense, dependentNum, minorChildNum, dependentExpense);
    assertNotNull(marriedSeparateFiler);
    String exceptedString = "MarriedSeparateFiler{incomeTaxPaid=5000.0, lastYearEarnings=155000.0, "
        + "contactInfo=ContactInfo{name=Name{firstName='Xihao', lastName='Liu'}, "
        + "address='225 Terry Ave N', phoneNumber='6173732462', email='liu.xiha@northeastern.edu'}"
        + ", filerType=MARRIED_FILLING_SEPARATELY, taxId='aptx4869', "
        + "specialExpense=SpecialExpense{mortgageInterestPaid=1200.0, propertyTaxPaid=26000.0, "
        + "studLoanTuitionPaid=0.0, retireSavingContr=15000.0, healthSavingContr=15000.0, "
        + "charitableExpenses=0.0}}";
    assertEquals(exceptedString, marriedSeparateFiler.toString());
  }
}