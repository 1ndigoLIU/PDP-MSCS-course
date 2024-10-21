package problem1;

/**
 * Custom exception that is thrown when a product is out of stock.
 *
 * @author Xihao Liu
 */
public class OutOfStockException extends Exception {

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and
   * may subsequently be initialized by a call to initCause.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *                getMessage() method.
   */
  public OutOfStockException(String message) {
    super(message);
  }
}