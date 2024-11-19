package problem1;

import java.util.Objects;

/**
 * Represents	transfer information including deposit miles amount, recipient account ID and
 * recipient's name
 *
 * @author Xihao Liu
 */
public class Deposit {

  private int milesAmount;
  private String recipientId;
  private FlyerName recipientName;
  /**
   * down limit of miles amount
   */
  private static final int MILES_DOWN = 1000;
  /**
   * up limit of miles amount
   */
  private static final int MILES_UP = 10_000;

  /**
   * @param milesAmount   deposit miles amount
   * @param recipientId   recipient account ID
   * @param recipientName recipient's name
   * @throws IllegalArgumentException throw when deposit miles amount out of range 1000~10000
   */
  public Deposit(int milesAmount, String recipientId, FlyerName recipientName) {
    if (milesAmount >= MILES_DOWN && milesAmount <= MILES_UP) {
      this.milesAmount = milesAmount;
    } else {
      throw new IllegalArgumentException(
          "Deposit milesAmount must be within range (" + MILES_DOWN + " ~ " + MILES_UP + ")!");
    }
    this.recipientId = recipientId;
    this.recipientName = recipientName;
  }

  /**
   * get deposit miles amount
   *
   * @return miles amount
   */
  public int getMilesAmount() {
    return milesAmount;
  }

  /**
   * get recipient account ID
   *
   * @return recipient account ID
   */
  public String getRecipientId() {
    return recipientId;
  }

  /**
   * get recipient's name
   *
   * @return recipient's name
   */
  public FlyerName getRecipientName() {
    return recipientName;
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
    Deposit deposit = (Deposit) o;
    return milesAmount == deposit.milesAmount && Objects.equals(recipientId, deposit.recipientId)
        && Objects.equals(recipientName, deposit.recipientName);
  }

  /**
   * override hashCode method
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(milesAmount, recipientId, recipientName);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Deposit{" + "milesAmount=" + milesAmount + ", recipientId='" + recipientId + '\''
        + ", recipientName=" + recipientName + '}';
  }
}
