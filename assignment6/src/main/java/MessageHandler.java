import java.io.IOException;
import java.util.List;

/**
 * Defines the contract for handling various types of messages in a client-server architecture.
 * Implementations of this interface must define how to process specific message types.
 *
 * @author Xihao Liu
 */
public interface MessageHandler {

  /**
   * Handles a connect message from the server.
   *
   * @param username the username attempting to connect
   * @throws IOException if an I/O error occurs
   */
  void handleConnect(String username) throws IOException;

  /**
   * Handles a broadcast message sent to all connected clients.
   *
   * @param sender  the username of the sender
   * @param message the message content
   * @throws IOException if an I/O error occurs
   */
  void handleBroadcast(String sender, String message) throws IOException;

  /**
   * Handles a direct message sent to a specific recipient.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @param message   the message content
   * @throws IOException if an I/O error occurs
   */
  void handleDirectMessage(String sender, String recipient, String message) throws IOException;

  /**
   * Handles a query for the list of connected users.
   *
   * @throws IOException if an I/O error occurs
   */
  void handleQueryUsers() throws IOException;

  /**
   * Handles a disconnect message indicating a user is disconnecting.
   *
   * @param username the username of the user disconnecting
   * @throws IOException if an I/O error occurs
   */
  void handleDisconnect(String username) throws IOException;

  /**
   * Handles an insult request, where one user sends an insult to another.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @throws IOException if an I/O error occurs
   */
  void handleInsultRequest(String sender, String recipient) throws IOException;

  /**
   * Handles the receipt of an insult message.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @param insult    the insult content
   * @throws IOException if an I/O error occurs
   */
  void handleReceiveInsult(String sender, String recipient, String insult) throws IOException;

  /**
   * Handles a response to a connection attempt, indicating success or failure.
   *
   * @param success {@code true} if the connection was successful, {@code false} otherwise
   * @param message the server's response message
   * @throws IOException if an I/O error occurs
   */
  void handleConnectResponse(boolean success, String message) throws IOException;

  /**
   * Handles a response containing a list of connected users.
   *
   * @param users a {@link List} of usernames representing the connected users
   * @throws IOException if an I/O error occurs
   */
  void handleQueryUserResponse(List<String> users) throws IOException;

  /**
   * Handles a failed message, typically containing an error description.
   *
   * @param errorMessage the error message content
   * @throws IOException if an I/O error occurs
   */
  void handleFailedMessage(String errorMessage) throws IOException;

  /**
   * Handles an unknown message type. Provides a default implementation that logs the message type.
   *
   * @param messageType the type of the unknown message
   */
  default void handleUnknown(int messageType) {
    System.err.println("Unknown message type: " + messageType);
  }
}
