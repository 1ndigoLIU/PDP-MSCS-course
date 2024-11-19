package problem3;

/**
 * Represent group filer (inheriting TaxFiler) with its information including number of dependents,
 * number of minor children and group filer's dependent expense
 *
 * @author Xihao Liu
 */
public abstract class GroupFiler extends TaxFiler {

  private int dependentNum;
  private int minorChildNum;
  private DependentExpense dependentExpense;

  /**
   * the boundary for choosing correct multiplication ratio when calculate group filer's taxes
   */
  private static final double GROUP_TAX_BOUNDARY = 90000;
  /**
   * multiplication ratio when calculate taxes under boundary
   */
  private static final double GROUP_TAX_UNDER_RATIO = 0.145;
  /**
   * multiplication ratio when calculate taxes over boundary
   */
  private static final double GROUP_TAX_OVER_RATIO = 0.185;
  /**
   * multiplication ratio when calculate group filer's saving deduction
   */
  private static final double GROUP_SAVING_DEDUCTION_RATIO = 0.65;
  /**
   * up limit when calculate saving deduction
   */
  private static final double GROUP_SAVING_DEDUCTION_UP_LIMIT = 17_500.0;
  /**
   * the up limit of last year earnings when calculate childcare deduction
   */
  private static final double CHILDCARE_DEDUCTION_EARNINGS_LIMIT = 200_000.0;
  /**
   * the down limit of childcare expense when calculate childcare deduction
   */
  private static final double CHILDCARE_DEDUCTION_CHILDCARE_LIMIT = 5000.0;
  /**
   * the value of childcare deduction when match eligibility
   */
  private static final double CHILDCARE_DEDUCTION_VALUE = 1250.0;

  /**
   * constructor of class GroupFiler
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
   */
  public GroupFiler(String taxId, FilerType filerType, ContactInfo contactInfo,
      double lastYearEarnings, double incomeTaxPaid, SpecialExpense specialExpense,
      int dependentNum, int minorChildNum, DependentExpense dependentExpense) {
    super(taxId, filerType, contactInfo, lastYearEarnings, incomeTaxPaid, specialExpense);
    this.dependentNum = dependentNum;
    this.minorChildNum = minorChildNum;
    this.dependentExpense = dependentExpense;
  }

  /**
   * get number of dependents
   *
   * @return number of dependents
   */
  public int getDependentNum() {
    return dependentNum;
  }

  /**
   * get number of minor children
   *
   * @return number of minor children
   */
  public int getMinorChildNum() {
    return minorChildNum;
  }

  /**
   * get dependent expense
   *
   * @return dependent expense
   */
  public DependentExpense getDependentExpense() {
    return dependentExpense;
  }

  /**
   * calculate filer’s taxes
   *
   * @return filer’s taxes
   */
  @Override
  public double calculateTaxes() {
    double taxAmount = calcBasicTaxableIncome() - calcSavingDeduction()
        - calcMortgageInterestAndPropertyDeduction() - calcChildcareDeduction();
    return taxAmount < GROUP_TAX_BOUNDARY ? taxAmount * GROUP_TAX_UNDER_RATIO
        : taxAmount * GROUP_TAX_OVER_RATIO;
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
    deduction = deduction * GROUP_SAVING_DEDUCTION_RATIO;
    if (deduction > GROUP_SAVING_DEDUCTION_UP_LIMIT) {
      deduction = GROUP_SAVING_DEDUCTION_UP_LIMIT;
    }
    return deduction;
  }

  /**
   * calculate childcare deduction
   *
   * @return childcare deduction
   */
  public double calcChildcareDeduction() {
    if (lastYearEarnings < CHILDCARE_DEDUCTION_EARNINGS_LIMIT
        && dependentExpense.getChildcareExpense() > CHILDCARE_DEDUCTION_CHILDCARE_LIMIT) {
      return CHILDCARE_DEDUCTION_VALUE;
    }
    return 0.0;
  }

  /**
   * override equals method the same tax ID is treated as equal Vehicle objects
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
