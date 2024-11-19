package problem3;

/**
 * Represent tax filer which type is employee (inheriting IndividualFiler)
 *
 * @author Xihao Liu
 */
public class EmployeeFiler extends IndividualFiler {

  /**
   * constructor of class EmployeeFiler
   *
   * @param taxId            tax ID, a unique tax filer’s identifier
   * @param filerType        filer type
   * @param contactInfo      tax filer’s contact information
   * @param lastYearEarnings tax filer’s last year's earning
   * @param incomeTaxPaid    total income tax already paid
   * @param specialExpense   details of several special expenses
   * @throws IllegalArgumentException throw when value of filer type is illegal, it should be
   *                                  EMPLOYEE
   */
  public EmployeeFiler(String taxId, FilerType filerType, ContactInfo contactInfo,
      double lastYearEarnings, double incomeTaxPaid, SpecialExpense specialExpense) {
    super(taxId, filerType, contactInfo, lastYearEarnings, incomeTaxPaid, specialExpense);
    if (filerType != FilerType.EMPLOYEE) {
      throw new IllegalArgumentException("Individual Filer Type Wrong! It should be EMPLOYEE.");
    }
  }

  /**
   * override equals method the same tax ID is treated as equal EmployeeFiler objects
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
    return "EmployeeFiler{" + "taxId='" + taxId + '\'' + ", filerType=" + filerType
        + ", contactInfo=" + contactInfo + ", lastYearEarnings=" + lastYearEarnings
        + ", incomeTaxPaid=" + incomeTaxPaid + ", specialExpense=" + specialExpense + '}';
  }
}
