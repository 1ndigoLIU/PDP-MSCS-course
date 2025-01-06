import hw4.Expression;
import hw4.GrammarExpander;
import hw4.GrammarLoader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

/**
 * Handles incoming messages from clients and performs server-side operations such as broadcasting
 * messages, sending direct messages, and managing user connections.
 *
 * @author Xihao Liu
 */
public class ServerMessageHandler implements MessageHandler {

  /**
   * The server managing this handler.
   */
  private final Server server;
  /**
   * The output stream for sending messages to the client.
   */
  private final DataOutputStream out;
  /**
   * The socket connection to the client.
   */
  private final Socket socket;
  /**
   * A map of currently connected clients and their sockets.
   */
  private final Map<String, Socket> clients;
  /**
   * The username of the currently connected client.
   */
  private String currentUsername;

  /**
   * Constructs a {@code ServerMessageHandler} with the given server, socket, client map, and output
   * stream.
   *
   * @param server  the {@link Server} instance managing this handler
   * @param socket  the socket connection to the client
   * @param clients a map of connected clients and their sockets
   * @param out     the {@link DataOutputStream} for the client
   */
  public ServerMessageHandler(Server server, Socket socket, Map<String, Socket> clients,
      DataOutputStream out) {
    this.server = server;
    this.socket = socket;
    this.clients = clients;
    this.out = out;
  }

  /**
   * Returns the username of the client currently connected to this handler.
   *
   * @return the current username
   */
  public String getCurrentUsername() {
    return currentUsername;
  }

  /**
   * Returns the socket associated with the client.
   *
   * @return the client's socket
   */
  public Socket getSocket() {
    return socket;
  }

  /**
   * Handles a client connection request by validating the username and registering the client.
   *
   * @param username the username of the client attempting to connect
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleConnect(String username) throws IOException {
    synchronized (clients) {
      if (clients.size() >= Constants.MAX_USERS) {
        sendConnectResponse(false, "The chatroom is full. Please try again later.");
        server.disconnectSocket(socket);
        return;
      }

      if (clients.containsKey(username)) {
        sendConnectResponse(false,
            "Username already taken. Please restart client and use a different username.");
      } else {
        clients.put(username, socket);
        server.addClientOutput(username, out);
        this.currentUsername = username;
        System.out.println("User connected: " + username);
        sendConnectResponse(true,
            String.format(Constants.WELCOME_MESSAGE, username, clients.size() - 1));
      }
    }
  }

  /**
   * Handles a broadcast message by sending it to all connected clients.
   *
   * @param sender  the username of the sender
   * @param message the message to broadcast
   */
  @Override
  public void handleBroadcast(String sender, String message) {
    synchronized (clients) {
      for (String user : clients.keySet()) {
        DataOutputStream dout = server.getClientOutputStream(user);
        if (dout != null) {
          try {
            dout.writeInt(Constants.BROADCAST_MESSAGE);
            writeStringField(dout, sender);
            writeStringField(dout, message);
            dout.flush();
          } catch (IOException e) {
            System.err.println("Error broadcasting message: " + e.getMessage());
          }
        }
      }
    }
  }

  /**
   * Handles a direct message by sending it to the specified recipient.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @param message   the message content
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleDirectMessage(String sender, String recipient, String message)
      throws IOException {
    synchronized (clients) {
      if (clients.containsKey(recipient)) {
        DataOutputStream dout = server.getClientOutputStream(recipient);
        if (dout != null) {
          dout.writeInt(Constants.DIRECT_MESSAGE);
          writeStringField(dout, sender);
          writeStringField(dout, recipient);
          writeStringField(dout, message);
          dout.flush();
        }
      } else {
        DataOutputStream senderOut = server.getClientOutputStream(sender);
        if (senderOut != null) {
          sendErrorMessage(senderOut, "Whisper failed. User " + recipient + " is not online.");
        }
      }
    }
  }

  /**
   * Handles a query for connected users by sending the list of usernames to the client.
   *
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleQueryUsers() throws IOException {
    synchronized (clients) {
      out.writeInt(Constants.QUERY_USER_RESPONSE);
      out.writeInt(clients.size());
      for (String user : clients.keySet()) {
        writeStringField(out, user);
      }
      out.flush();
    }
  }

  /**
   * Handles a client disconnect request by removing the client from the server.
   *
   * @param username the username of the disconnecting client
   */
  @Override
  public void handleDisconnect(String username) {
    synchronized (clients) {
      clients.remove(username);
      server.removeClientOutput(username);
      System.out.println("User disconnected: " + username);
    }
    server.disconnectSocket(socket);
  }

  /**
   * Handles an insult request by broadcasting an insult to all connected clients.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleInsultRequest(String sender, String recipient) throws IOException {
    GrammarLoader loader = new GrammarLoader("grammars/insult_grammar.json");
    Map<String, List<Expression>> grammar = loader.loadGrammar();
    GrammarExpander expander = new GrammarExpander(grammar);
    String insult = expander.expand("start");

    synchronized (clients) {
      for (String user : clients.keySet()) {
        DataOutputStream dout = server.getClientOutputStream(user);
        if (dout != null) {
          try {
            dout.writeInt(Constants.BROADCAST_INSULT);
            writeStringField(dout, sender);
            writeStringField(dout, recipient);
            writeStringField(dout, insult);
            dout.flush();
          } catch (IOException e) {
            System.err.println("Error broadcasting insult: " + e.getMessage());
          }
        }
      }
    }
  }

  /**
   * Handles receiving an insult message. Since this is not expected on the server, it is delegated
   * to {@link #handleUnknown(int)}.
   *
   * @param sender    the username of the sender
   * @param recipient the username of the recipient
   * @param insult    the insult content
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleReceiveInsult(String sender, String recipient, String insult)
      throws IOException {
    handleUnknown(Constants.BROADCAST_INSULT);
  }

  /**
   * Handles a connection response. This method is not expected to be called on the server and is
   * delegated to {@link #handleUnknown(int)}.
   *
   * @param success whether the connection was successful
   * @param message the server's response message
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleConnectResponse(boolean success, String message) throws IOException {
    handleUnknown(Constants.CONNECT_RESPONSE);
  }

  /**
   * Handles a query user response. This method is not expected to be called on the server and is
   * delegated to {@link #handleUnknown(int)}.
   *
   * @param users a {@link List} of connected users
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleQueryUserResponse(List<String> users) throws IOException {
    handleUnknown(Constants.QUERY_USER_RESPONSE);
  }

  /**
   * Handles a failed message. This method is not expected to be called on the server and is
   * delegated to {@link #handleUnknown(int)}.
   *
   * @param errorMessage the error message content
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void handleFailedMessage(String errorMessage) throws IOException {
    handleUnknown(Constants.FAILED_MESSAGE);
  }

  /**
   * Handles an unknown message type by logging it to the console.
   *
   * @param messageType the type of the unknown message
   */
  @Override
  public void handleUnknown(int messageType) {
    System.err.println("Unknown message type at server: " + messageType);
  }

  /**
   * Sends a connection response to the client indicating success or failure.
   *
   * @param success {@code true} if the connection was successful, {@code false} otherwise
   * @param message the response message to send
   * @throws IOException if an I/O error occurs
   */
  private void sendConnectResponse(boolean success, String message) throws IOException {
    out.writeInt(Constants.CONNECT_RESPONSE);
    out.writeBoolean(success);
    writeStringField(out, message);
    out.flush();
  }

  /**
   * Sends an error message to the client.
   *
   * @param dout    the {@link DataOutputStream} for the client
   * @param message the error message to send
   * @throws IOException if an I/O error occurs
   */
  private void sendErrorMessage(DataOutputStream dout, String message) throws IOException {
    dout.writeInt(Constants.FAILED_MESSAGE);
    writeStringField(dout, message);
    dout.flush();
  }

  /**
   * Writes a string to the specified output stream. The string is encoded in UTF-8 and prefixed
   * with its length as an integer.
   *
   * @param dout the {@link DataOutputStream} to write to
   * @param str  the string to write
   * @throws IOException if an I/O error occurs
   */
  private void writeStringField(DataOutputStream dout, String str) throws IOException {
    byte[] data = str.getBytes("UTF-8");
    dout.writeInt(data.length);
    dout.write(data);
  }

  /**
   * Compares this message handler to another object for equality.
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
    ServerMessageHandler that = (ServerMessageHandler) o;
    return Objects.equals(server, that.server) && Objects.equals(socket, that.socket);
  }

  /**
   * Computes the hash code for this message handler.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(server, socket);
  }
}
