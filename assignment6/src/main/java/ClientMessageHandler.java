import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * A message handler implementation for the {@link Client}. This class processes incoming messages
 * from the server and performs appropriate actions such as displaying messages, handling user
 * queries, and managing connection responses.
 *
 * @author Xihao Liu
 */
public class ClientMessageHandler implements MessageHandler {

  /**
   * The client associated with this message handler.
   */
  private final Client client;

  /**
   * Constructs a {@code ClientMessageHandler} with the specified client.
   *
   * @param client the {@link Client} instance this handler is associated with
   */
  public ClientMessageHandler(Client client) {
    this.client = client;
  }

  /**
   * Handles a connect message. Since the client does not process this directly, it delegates to
   * {@link #handleUnknown(int)}.
   *
   * @param username the username attempting to connect
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleConnect(String username) throws IOException {
    handleUnknown(Constants.CONNECT_MESSAGE);
  }

  /**
   * Handles a broadcast message by displaying it in the console.
   *
   * @param sender  the username of the sender
   * @param message the broadcasted message
   */
  @Override
  public void handleBroadcast(String sender, String message) {
    System.out.println(
        Constants.CYAN + "[Lobby]" + sender + ": " + message + Constants.RESET_COLOR);
  }

  /**
   * Handles a direct message by displaying it in the console.
   *
   * @param sender    the username of the sender
   * @param recipient the recipient of the message
   * @param message   the message content
   */
  @Override
  public void handleDirectMessage(String sender, String recipient, String message) {
    System.out.println(
        Constants.PURPLE + "[Whisper]From " + sender + ": " + message + Constants.RESET_COLOR);
  }

  /**
   * Handles a query for connected users. Delegates to {@link #handleUnknown(int)} as the client
   * does not process this directly.
   *
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleQueryUsers() throws IOException {
    handleUnknown(Constants.QUERY_CONNECTED_USERS);
  }

  /**
   * Handles a disconnect request by logging the disconnect event.
   *
   * @param username the username that is requested to disconnect
   */
  @Override
  public void handleDisconnect(String username) {
    System.err.println("Server requested disconnect for user: " + username);
  }

  /**
   * Handles an insult request. Delegates to {@link #handleUnknown(int)} as the client does not
   * process this directly.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleInsultRequest(String sender, String recipient) throws IOException {
    handleUnknown(Constants.SEND_INSULT);
  }

  /**
   * Handles receiving an insult by displaying it in the console.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @param insult    the insult content
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleReceiveInsult(String sender, String recipient, String insult)
      throws IOException {
    System.out.println(Constants.CYAN + "[Lobby]" + sender + " --> " + recipient + ": " + insult
        + Constants.RESET_COLOR);
  }

  /**
   * Handles a connection response by displaying the server's response.
   *
   * @param success whether the connection was successful
   * @param message the server's response message
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleConnectResponse(boolean success, String message) throws IOException {
    if (success) {
      System.out.println(Constants.YELLOW + message + Constants.RESET_COLOR);
    } else {
      System.out.println(
          Constants.YELLOW + "Connection failed: " + message + Constants.RESET_COLOR);
      System.out.println(
          Constants.YELLOW + "Press the 'Enter' key to close the client." + Constants.RESET_COLOR);
      client.disconnect();
    }
  }

  /**
   * Handles a response for a query of connected users by displaying the list.
   *
   * @param users the list of connected users
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleQueryUserResponse(List<String> users) throws IOException {
    System.out.println(
        Constants.YELLOW + "Connected users: " + users.size() + Constants.RESET_COLOR);
    for (String user : users) {
      if (client != null && client.getUsername().equals(user)) {
        System.out.println(Constants.YELLOW + "- " + user + "(Me)" + Constants.RESET_COLOR);
      } else {
        System.out.println(Constants.YELLOW + "- " + user + Constants.RESET_COLOR);
      }
    }
  }

  /**
   * Handles a failed message by logging the error.
   *
   * @param errorMessage the error message
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleFailedMessage(String errorMessage) throws IOException {
    System.out.println(Constants.YELLOW + "Failed: " + errorMessage + Constants.RESET_COLOR);
  }

  /**
   * Handles an unknown message type by logging it.
   *
   * @param messageType the unknown message type
   */
  @Override
  public void handleUnknown(int messageType) {
    System.err.println("Client received unknown message type: " + messageType);
  }

  /**
   * Checks whether this handler is equal to another object.
   *
   * @param o the object to compare
   * @return {@code true} if the objects are equal, {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientMessageHandler that = (ClientMessageHandler) o;
    return Objects.equals(client, that.client);
  }

  /**
   * Returns the hash code of this handler, based on the client.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(client);
  }
}
