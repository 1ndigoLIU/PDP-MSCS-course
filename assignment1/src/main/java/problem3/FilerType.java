package problem3;

/**
 * Represent the filer types of tax filer
 *
 * @author Xihao Liu
 */
public enum FilerType {
  /**
   * Employee (Individual filer)
   */
  EMPLOYEE,
  /**
   * Married, filling jointly (Group filer)
   */
  MARRIED_FILLING_JOINTLY,
  /**
   * Married, filling separately (Group filer)
   */
  MARRIED_FILLING_SEPARATELY,
  /**
   * Head of the household (Group filer)
   */
  HEAD_OF_THE_HOUSEHOLD
}
