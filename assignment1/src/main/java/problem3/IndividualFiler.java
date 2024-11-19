package problem3;

import java.util.Objects;

/**
 * Represent individual filer (inheriting TaxFiler)
 *
 * @author Xihao Liu
 */
public abstract class IndividualFiler extends TaxFiler {

  /**
   * the boundary for choosing correct multiplication ratio when calculate individual filer's taxes
   */
  private static final double IND_TAX_BOUNDARY = 55000.0;
  /**
   * multiplication ratio when calculate taxes under boundary
   */
  private static final double IND_TAX_UNDER_RATIO = 0.15;
  /**
   * multiplication ratio when calculate taxes over boundary
   */
  private static final double IND_TAX_OVER_RATIO = 0.19;
  /**
   * multiplication ratio when calculate individual filer's saving deduction
   */
  private static final double IND_SAVING_DEDUCTION_RATIO = 0.7;

  /**
   * constructor of class IndividualFiler
   *
   * @param taxId            tax ID, a unique tax filer’s identifier
   * @param filerType        filer type
   * @param contactInfo      tax filer’s contact information
   * @param lastYearEarnings tax filer’s last year's earning
   * @param incomeTaxPaid    total income tax already paid
   * @param specialExpense   details of several special expenses
   */
  public IndividualFiler(String taxId, FilerType filerType, ContactInfo contactInfo,
      double lastYearEarnings, double incomeTaxPaid, SpecialExpense specialExpense) {
    super(taxId, filerType, contactInfo, lastYearEarnings, incomeTaxPaid, specialExpense);
  }

  /**
   * calculate filer’s taxes (abstract method)
   *
   * @return filer’s taxes
   */
  @Override
  public double calculateTaxes() {
    double taxAmount = calcBasicTaxableIncome() - calcSavingDeduction()
        - calcMortgageInterestAndPropertyDeduction();
    return taxAmount < IND_TAX_BOUNDARY ? taxAmount * IND_TAX_UNDER_RATIO
        : taxAmount * IND_TAX_OVER_RATIO;
  }

  /**
   * calculate retirement and health savings deduction
   *
   * @return retirement and health savings deduction
   */
  @Override
  public double calcSavingDeduction() {
    double deduction =
        specialExpense.getHealthSavingContr() + specialExpense.getRetireSavingContr();
    if (deduction > calcBasicTaxableIncome()) {
      deduction = calcBasicTaxableIncome();
    }
    deduction = deduction * IND_SAVING_DEDUCTION_RATIO;
    return deduction;
  }

  /**
   * override equals method the same tax ID is treated as equal IndividualFiler objects
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
}
