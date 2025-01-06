import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract base class for handling communication endpoints. This class provides the basic setup and
 * utilities for managing a socket connection, sending and receiving messages, and handling specific
 * message types through a {@link MessageHandler}.
 *
 * @author Xihao Liu
 */
public abstract class AbstractEndpoint {

  /**
   * The socket used for communication.
   */
  protected Socket socket;
  /**
   * Input stream for reading data from the socket.
   */
  protected DataInputStream in;
  /**
   * Output stream for writing data to the socket.
   */
  protected DataOutputStream out;
  /**
   * Handler for processing various message types.
   */
  protected MessageHandler messageHandler;

  /**
   * Constructs an AbstractEndpoint with the specified message handler.
   *
   * @param handler the {@link MessageHandler} for processing messages
   */
  public AbstractEndpoint(MessageHandler handler) {
    this.messageHandler = handler;
  }

  /**
   * Initializes the input and output streams for the provided socket.
   *
   * @param socket the {@link Socket} to initialize I/O streams
   * @throws IOException if an I/O error occurs during initialization
   */
  protected void setupIO(Socket socket) throws IOException {
    this.socket = socket;
    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
  }

  /**
   * Starts the endpoint. This method must be implemented by subclasses to define the specific
   * behavior of the endpoint.
   */
  public abstract void start();

  /**
   * Disconnects the endpoint by closing the socket if it is open.
   */
  public void disconnect() {
    try {
      if (socket != null && !socket.isClosed()) {
        socket.close();
      }
    } catch (IOException ignored) {
    }
  }

  /**
   * Continuously listens for and handles incoming messages. Processes each message type using the
   * {@link MessageHandler}.
   */
  protected void handleIncomingMessages() {
    try {
      while (true) {
        int messageType = in.readInt();
        switch (messageType) {
          case Constants.CONNECT_MESSAGE -> {
            String username = readStringField();
            messageHandler.handleConnect(username);
          }
          case Constants.BROADCAST_MESSAGE -> {
            String sender = readStringField();
            String message = readStringField();
            messageHandler.handleBroadcast(sender, message);
          }
          case Constants.DIRECT_MESSAGE -> {
            String sender = readStringField();
            String recipient = readStringField();
            String msg = readStringField();
            messageHandler.handleDirectMessage(sender, recipient, msg);
          }
          case Constants.QUERY_CONNECTED_USERS -> messageHandler.handleQueryUsers();
          case Constants.DISCONNECT_MESSAGE -> {
            String username = readStringField();
            messageHandler.handleDisconnect(username);
            return;
          }
          case Constants.SEND_INSULT -> {
            String sender = readStringField();
            String recipient = readStringField();
            messageHandler.handleInsultRequest(sender, recipient);
          }
          case Constants.BROADCAST_INSULT -> {
            String sender = readStringField();
            String recipient = readStringField();
            String insult = readStringField();
            messageHandler.handleReceiveInsult(sender, recipient, insult);
          }
          case Constants.CONNECT_RESPONSE -> {
            boolean success = in.readBoolean();
            String respMsg = readStringField();
            messageHandler.handleConnectResponse(success, respMsg);
          }
          case Constants.QUERY_USER_RESPONSE -> {
            int userCount = in.readInt();
            List<String> users = new ArrayList<>();
            for (int i = 0; i < userCount; i++) {
              users.add(readStringField());
            }
            messageHandler.handleQueryUserResponse(users);
          }
          case Constants.FAILED_MESSAGE -> {
            String errorMsg = readStringField();
            messageHandler.handleFailedMessage(errorMsg);
          }

          default -> messageHandler.handleUnknown(messageType);
        }
      }
    } catch (IOException e) {
      System.err.println("Connection closed or error: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error handling message: " + e.getMessage());
    }
  }

  /**
   * Reads a UTF-8 encoded string from the input stream. The string is prefixed by its length as an
   * integer.
   *
   * @return the decoded string
   * @throws IOException if an I/O error occurs while reading
   */
  protected String readStringField() throws IOException {
    int length = in.readInt();
    byte[] buf = new byte[length];
    in.readFully(buf);
    return new String(buf, "UTF-8");
  }

  /**
   * Writes a UTF-8 encoded string to the output stream. The string is prefixed by its length as an
   * integer.
   *
   * @param dout the {@link DataOutputStream} to write to
   * @param str  the string to write
   * @throws IOException if an I/O error occurs while writing
   */
  protected void writeStringField(DataOutputStream dout, String str) throws IOException {
    byte[] data = str.getBytes("UTF-8");
    dout.writeInt(data.length);
    dout.write(data);
  }

  /**
   * Compares this endpoint to the specified object for equality. Two are considered equal if and
   * only if they have the same socket reference. This method adheres to the general contract of
   * {@link Object#equals(Object)}.
   *
   * @param o the object to be compared for equality with this endpoint
   * @return {@code true} if the specified object is equal to this endpoint, {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractEndpoint that = (AbstractEndpoint) o;
    return Objects.equals(socket, that.socket);
  }

  /**
   * Returns the hash code value for this endpoint. This method returns the hash code of the
   * underlying socket. The returned hash code is consistent with the definition of
   * {@link Object#hashCode()} and is compatible with the {@link #equals(Object)} method in that two
   * endpoints considered equal will have the same hash code.
   *
   * @return the hash code value for this endpoint
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(socket);
  }
}
