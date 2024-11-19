package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupFilerTest {
  private String taxId;
  private FilerType filerType;
  private ContactInfo contactInfo;
  private double lastYearEarnings;
  private double incomeTaxPaid;
  private SpecialExpense specialExpense;

  private int dependentNum;
  private int minorChildNum;
  private DependentExpense dependentExpense;

  private GroupFiler groupFiler;

  @BeforeEach
  void setUp() {
    taxId = "aptx4869";
    filerType = FilerType.HEAD_OF_THE_HOUSEHOLD;
    contactInfo = new ContactInfo(new Name("Xihao", "Liu"), "225 Terry Ave N",
        "6173732462", "liu.xiha@northeastern.edu");
    lastYearEarnings = 155000;
    incomeTaxPaid = 5000;
    specialExpense = new SpecialExpense(1200,26000,0,
        15000, 15000, 0);

    dependentNum = 2;
    minorChildNum = 3;
    dependentExpense = new DependentExpense(55000, 3000);

    groupFiler = new HouseholdHeadFiler(taxId, filerType, contactInfo,
        lastYearEarnings, incomeTaxPaid, specialExpense,
        dependentNum, minorChildNum, dependentExpense);
  }

  @Test
  void getDependentNum() {
    assertEquals(dependentNum, groupFiler.getDependentNum());
  }

  @Test
  void getMinorChildNum() {
    assertEquals(minorChildNum, groupFiler.getMinorChildNum());
  }

  @Test
  void getDependentExpense() {
    assertEquals(dependentExpense, groupFiler.getDependentExpense());
  }

  @Test
  void calculateTaxes() {
    assertEquals(23818.75, groupFiler.calculateTaxes());

    groupFiler = new HouseholdHeadFiler(taxId, filerType, contactInfo,
        15000 , incomeTaxPaid, specialExpense,
        dependentNum, minorChildNum, dependentExpense);
    assertEquals(-36.25, groupFiler.calculateTaxes());
  }

  @Test
  void calcSavingDeduction() {
    assertEquals(17500, groupFiler.calcSavingDeduction());

    groupFiler = new HouseholdHeadFiler(taxId, filerType, contactInfo,
        15000 , incomeTaxPaid, specialExpense,
        dependentNum, minorChildNum, dependentExpense);
    assertEquals(6500, groupFiler.calcSavingDeduction());
  }

  @Test
  void calcChildcareDeduction() {
    assertEquals(1250, groupFiler.calcChildcareDeduction());

    groupFiler = new HouseholdHeadFiler(taxId, filerType, contactInfo,
        200001 , incomeTaxPaid, specialExpense,
        dependentNum, minorChildNum, dependentExpense);
    assertEquals(0, groupFiler.calcChildcareDeduction());

    dependentExpense = new DependentExpense(3000, 3000);
    groupFiler = new HouseholdHeadFiler(taxId, filerType, contactInfo,
        lastYearEarnings , incomeTaxPaid, specialExpense,
        dependentNum, minorChildNum, dependentExpense);
    assertEquals(0, groupFiler.calcChildcareDeduction());
  }

  @Test
  void testEquals() {
    assertEquals(groupFiler, groupFiler);
  }

  @Test
  void testHashCode() {
    assertEquals(groupFiler.hashCode(), groupFiler.hashCode());
  }
}