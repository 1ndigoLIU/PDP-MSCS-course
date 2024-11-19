package problem3;

import java.util.Objects;

/**
 * Represent tax filer's details of several special expenses including mortgage interest paid,
 * property taxes paid, student loan and tuition paid, contributions made to a retirement savings
 * account, contributions made to a health savings account and charitable donations and
 * contributions
 *
 * @author Xihao Liu
 */
public class SpecialExpense {

  private double mortgageInterestPaid;
  private double propertyTaxPaid;
  private double studLoanTuitionPaid;
  private double retireSavingContr;
  private double healthSavingContr;
  private double charitableExpenses;

  /**
   * constructor of class SpecialExpense
   *
   * @param mortgageInterestPaid mortgage interest paid
   * @param propertyTaxPaid      property taxes paid
   * @param studLoanTuitionPaid  student loan and tuition paid
   * @param retireSavingContr    contributions made to a retirement savings account
   * @param healthSavingContr    contributions made to a health savings account
   * @param charitableExpenses   charitable donations and contributions
   */
  public SpecialExpense(double mortgageInterestPaid, double propertyTaxPaid,
      double studLoanTuitionPaid, double retireSavingContr, double healthSavingContr,
      double charitableExpenses) {
    this.mortgageInterestPaid = mortgageInterestPaid;
    this.propertyTaxPaid = propertyTaxPaid;
    this.studLoanTuitionPaid = studLoanTuitionPaid;
    this.retireSavingContr = retireSavingContr;
    this.healthSavingContr = healthSavingContr;
    this.charitableExpenses = charitableExpenses;
  }

  /**
   * @return mortgage interest paid
   */
  public double getMortgageInterestPaid() {
    return mortgageInterestPaid;
  }

  /**
   * @return property taxes paid
   */
  public double getPropertyTaxPaid() {
    return propertyTaxPaid;
  }

  /**
   * @return student loan and tuition paid
   */
  public double getStudLoanTuitionPaid() {
    return studLoanTuitionPaid;
  }

  /**
   * @return contributions made to a retirement savings account
   */
  public double getRetireSavingContr() {
    return retireSavingContr;
  }

  /**
   * @return contributions made to a health savings account
   */
  public double getHealthSavingContr() {
    return healthSavingContr;
  }

  /**
   * @return charitable donations and contributions
   */
  public double getCharitableExpenses() {
    return charitableExpenses;
  }

  /**
   * override equals method
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpecialExpense that = (SpecialExpense) o;
    return Double.compare(mortgageInterestPaid, that.mortgageInterestPaid) == 0
        && Double.compare(propertyTaxPaid, that.propertyTaxPaid) == 0
        && Double.compare(studLoanTuitionPaid, that.studLoanTuitionPaid) == 0
        && Double.compare(retireSavingContr, that.retireSavingContr) == 0
        && Double.compare(healthSavingContr, that.healthSavingContr) == 0
        && Double.compare(charitableExpenses, that.charitableExpenses) == 0;
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(mortgageInterestPaid, propertyTaxPaid, studLoanTuitionPaid,
        retireSavingContr, healthSavingContr, charitableExpenses);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "SpecialExpense{" + "mortgageInterestPaid=" + mortgageInterestPaid + ", propertyTaxPaid="
        + propertyTaxPaid + ", studLoanTuitionPaid=" + studLoanTuitionPaid + ", retireSavingContr="
        + retireSavingContr + ", healthSavingContr=" + healthSavingContr + ", charitableExpenses="
        + charitableExpenses + '}';
  }
}
