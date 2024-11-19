package problem3;

import java.util.Objects;

/**
 * Represent tax filer with his/her/their information including tax ID, filer type, contact
 * information, last year's earning, total income tax already paid and his/her/their several special
 * expenses
 *
 * @author Xihao Liu
 */
public abstract class TaxFiler {

  /**
   * tax ID, a unique tax filer’s identifier
   */
  protected String taxId;
  /**
   * filer type
   */
  protected FilerType filerType;
  /**
   * tax filer’s contact information
   */
  protected ContactInfo contactInfo;
  /**
   * tax filer’s last year's earning
   */
  protected double lastYearEarnings;
  /**
   * total income tax already paid
   */
  protected double incomeTaxPaid;
  /**
   * details of several special expenses
   */
  protected SpecialExpense specialExpense;
  /**
   * the up limit of last year earnings when calculate mortgage interest and property deduction
   */
  private static final double MIP_DEDUCTION_EARNINGS_LIMIT = 250_000.0;
  /**
   * the down limit of mortgage interests and property taxes expenses when calculate mortgage
   * interest and property deduction
   */
  private static final double MIP_DEDUCTION_EXPENSE_LIMIT = 12_500.0;
  /**
   * the value of mortgage interest and property deduction when match eligibility
   */
  private static final double CHILDCARE_DEDUCTION_VALUE = 2500.0;

  /**
   * constructor of class TaxFiler
   *
   * @param taxId            tax ID, a unique tax filer’s identifier
   * @param filerType        filer type
   * @param contactInfo      tax filer’s contact information
   * @param lastYearEarnings tax filer’s last year's earning
   * @param incomeTaxPaid    total income tax already paid
   * @param specialExpense   details of several special expenses
   */
  public TaxFiler(String taxId, FilerType filerType, ContactInfo contactInfo,
      double lastYearEarnings, double incomeTaxPaid, SpecialExpense specialExpense) {
    this.taxId = taxId;
    this.filerType = filerType;
    this.contactInfo = contactInfo;
    this.lastYearEarnings = lastYearEarnings;
    this.incomeTaxPaid = incomeTaxPaid;
    this.specialExpense = specialExpense;
  }

  /**
   * get tax ID
   *
   * @return tax ID
   */
  public String getTaxId() {
    return taxId;
  }

  /**
   * get filer type
   *
   * @return filer type
   */
  public FilerType getFilerType() {
    return filerType;
  }

  /**
   * get contact information
   *
   * @return contact information
   */
  public ContactInfo getContactInfo() {
    return contactInfo;
  }

  /**
   * get last year's earning
   *
   * @return last year's earning
   */
  public double getLastYearEarnings() {
    return lastYearEarnings;
  }

  /**
   * get total income tax already paid
   *
   * @return total income tax already paid
   */
  public double getIncomeTaxPaid() {
    return incomeTaxPaid;
  }

  /**
   * get details of several special expenses
   *
   * @return special expenses
   */
  public SpecialExpense getSpecialExpense() {
    return specialExpense;
  }

  /**
   * calculate filer’s taxes (abstract method)
   *
   * @return filer’s taxes
   */
  public abstract double calculateTaxes();

  /**
   * calculate basic taxable income
   *
   * @return basic taxable income
   */
  public double calcBasicTaxableIncome() {
    return lastYearEarnings - incomeTaxPaid;
  }

  /**
   * calculate retirement and health savings deduction
   *
   * @return retirement and health savings deduction
   */
  public abstract double calcSavingDeduction();

  /**
   * calculate mortgage interest and property deduction
   *
   * @return mortgage interest and property deduction
   */
  public double calcMortgageInterestAndPropertyDeduction() {
    if (lastYearEarnings < MIP_DEDUCTION_EARNINGS_LIMIT
        && (specialExpense.getMortgageInterestPaid() + specialExpense.getPropertyTaxPaid())
        > MIP_DEDUCTION_EXPENSE_LIMIT) {
      return CHILDCARE_DEDUCTION_VALUE;
    }
    return 0.0;
  }

  /**
   * override equals method the same tax ID is treated as equal TaxFiler objects
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TaxFiler taxFiler)) {
      return false;
    }
    return Objects.equals(taxId, taxFiler.taxId);
  }

  /**
   * override hashCode method hash code is generated using tax ID
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(taxId);
  }
}
