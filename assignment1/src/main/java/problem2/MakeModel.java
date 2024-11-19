package problem2;

import java.util.Objects;

/**
 * Represent vehicle's make and model
 *
 * @author Xihao Liu
 */
public class MakeModel {

  private String make;
  private String model;

  /**
   * constructor of class
   *
   * @param make  vehicle's make
   * @param model vehicle's model
   */
  public MakeModel(String make, String model) {
    this.make = make;
    this.model = model;
  }

  /**
   * get vehicle's make
   *
   * @return make
   */
  public String getMake() {
    return make;
  }

  /**
   * get vehicle's model
   *
   * @return model
   */
  public String getModel() {
    return model;
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
    MakeModel makeModel = (MakeModel) o;
    return Objects.equals(make, makeModel.make) && Objects.equals(model, makeModel.model);
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(make, model);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "MakeModel{" + "make='" + make + '\'' + ", model='" + model + '\'' + '}';
  }
}
