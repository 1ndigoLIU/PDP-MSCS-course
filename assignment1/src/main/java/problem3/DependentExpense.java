package problem3;

import java.util.Objects;

/**
 * Represent the dependent expense of tax filer including childcare expense and dependent care
 * expense
 *
 * @author Xihao Liu
 */
public class DependentExpense {

  private double childcareExpense;
  private double dependentCareExpense;

  /**
   * constructor of class DependentExpense
   *
   * @param childcareExpense     tax filer's childcare expenses
   * @param dependentCareExpense tax filer's dependent-care expenses
   */
  public DependentExpense(double childcareExpense, double dependentCareExpense) {
    this.childcareExpense = childcareExpense;
    this.dependentCareExpense = dependentCareExpense;
  }

  /**
   * get tax filer's childcare expenses
   *
   * @return childcare expenses
   */
  public double getChildcareExpense() {
    return childcareExpense;
  }

  /**
   * get tax filer's dependent-care expenses
   *
   * @return dependent-care expenses
   */
  public double getDependentCareExpense() {
    return dependentCareExpense;
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
    DependentExpense that = (DependentExpense) o;
    return Double.compare(childcareExpense, that.childcareExpense) == 0
        && Double.compare(dependentCareExpense, that.dependentCareExpense) == 0;
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(childcareExpense, dependentCareExpense);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "DependentExpense{" + "childcareExpense=" + childcareExpense + ", dependentCareExpense="
        + dependentCareExpense + '}';
  }
}
