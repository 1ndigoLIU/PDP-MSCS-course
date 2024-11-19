package problem3;

/**
 * Represent tax filer which type is head of the household (inheriting GroupFiler)
 *
 * @author Xihao Liu
 */
public class HouseholdHeadFiler extends GroupFiler {

  /**
   * constructor of class HouseholdHeadFiler
   *
   * @param taxId            tax ID, a unique tax filer’s identifier
   * @param filerType        filer type
   * @param contactInfo      tax filer’s contact information
   * @param lastYearEarnings tax filer’s last year's earning
   * @param incomeTaxPaid    total income tax already paid
   * @param specialExpense   details of several special expenses
   * @param dependentNum     number of dependents
   * @param minorChildNum    number of minor children
   * @param dependentExpense dependent expense
   * @throws IllegalArgumentException throw when value of filer type is illegal, it should be
   *                                  HEAD_OF_THE_HOUSEHOLD
   */
  public HouseholdHeadFiler(String taxId, FilerType filerType, ContactInfo contactInfo,
      double lastYearEarnings, double incomeTaxPaid, SpecialExpense specialExpense,
      int dependentNum, int minorChildNum, DependentExpense dependentExpense) {
    super(taxId, filerType, contactInfo, lastYearEarnings, incomeTaxPaid, specialExpense,
        dependentNum, minorChildNum, dependentExpense);
    if (filerType != FilerType.HEAD_OF_THE_HOUSEHOLD) {
      throw new IllegalArgumentException(
          "Individual Filer Type Wrong! It should be HEAD_OF_THE_HOUSEHOLD.");
    }
  }

  /**
   * override equals method the same tax ID is treated as equal HouseholdHeadFiler objects
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  /**
   * override hashCode method hash code is generated using tax ID
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "HouseholdHeadFiler{" + "specialExpense=" + specialExpense + ", incomeTaxPaid="
        + incomeTaxPaid + ", lastYearEarnings=" + lastYearEarnings + ", filerType=" + filerType
        + ", taxId='" + taxId + '\'' + ", contactInfo=" + contactInfo + '}';
  }
}