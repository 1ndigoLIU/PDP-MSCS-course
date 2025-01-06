import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@code Client} class represents a client in a client-server architecture. It manages the
 * connection to the server, user input, and sending/receiving messages.
 *
 * @author Xihao Liu
 */
public class Client extends AbstractEndpoint {

  /**
   * The server host to connect to.
   */
  private String host;
  /**
   * The server port to connect to.
   */
  private int port;
  /**
   * The username of the client.
   */
  private String username;
  /**
   * A {@link Scanner} for reading user input from the console.
   */
  private final Scanner scanner = new Scanner(System.in);

  /**
   * Constructs a {@code Client} instance with the specified server host and port.
   *
   * @param host the server host
   * @param port the server port
   */
  public Client(String host, int port) {
    super(null);
    this.host = host;
    this.port = port;
  }

  /**
   * Returns the username of the client.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Set the username of the client.
   *
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Starts the client by connecting to the server, initializing I/O streams, and handling user
   * input.
   */
  @Override
  public void start() {
    try {
      this.socket = new Socket(host, port);
      setupIO(this.socket);
      this.messageHandler = new ClientMessageHandler(this);

      System.out.println("Connected to server at " + host + ":" + port);
      System.out.print("Enter your username: ");
      this.username = scanner.nextLine();
      sendConnectMessage();

      Thread receiveThread = new Thread(this::handleIncomingMessages);
      receiveThread.start();

      handleUserInput();
    } catch (IOException e) {
      System.err.println("Error connecting to server: " + e.getMessage());
      disconnect();
    }
  }

  /**
   * Sends a connect message to the server with the client's username.
   *
   * @throws IOException if an I/O error occurs
   */
  private void sendConnectMessage() throws IOException {
    out.writeInt(Constants.CONNECT_MESSAGE);
    writeStringField(out, username);
    out.flush();
  }

  /**
   * Sends a broadcast message to the server.
   *
   * @param message the message to broadcast
   * @throws IOException if an I/O error occurs
   */
  private void sendBroadcastMessage(String message) throws IOException {
    out.writeInt(Constants.BROADCAST_MESSAGE);
    writeStringField(out, username);
    writeStringField(out, message);
    out.flush();
  }

  /**
   * Sends a direct message to a specific recipient.
   *
   * @param recipient the recipient's username
   * @param message   the message to send
   * @throws IOException if an I/O error occurs
   */
  private void sendDirectMessage(String recipient, String message) throws IOException {
    out.writeInt(Constants.DIRECT_MESSAGE);
    writeStringField(out, username);
    writeStringField(out, recipient);
    writeStringField(out, message);
    out.flush();
    System.out.println(
        Constants.PURPLE + "[Whisper]To " + recipient + ": " + message + Constants.RESET_COLOR);
    System.out.flush();
  }

  /**
   * Sends a random insult to a specific recipient.
   *
   * @param recipient the recipient's username
   * @throws IOException if an I/O error occurs
   */
  private void sendRandomInsult(String recipient) throws IOException {
    out.writeInt(Constants.SEND_INSULT);
    writeStringField(out, username);
    writeStringField(out, recipient);
    out.flush();
  }

  /**
   * Queries the server for a list of connected users.
   *
   * @throws IOException if an I/O error occurs
   */
  private void queryConnectedUsers() throws IOException {
    out.writeInt(Constants.QUERY_CONNECTED_USERS);
    out.flush();
  }

  /**
   * Sends a disconnect message to the server and terminates the connection.
   *
   * @throws IOException if an I/O error occurs
   */
  private void sendDisconnect() throws IOException {
    out.writeInt(Constants.DISCONNECT_MESSAGE);
    writeStringField(out, username);
    out.flush();
    disconnect();
  }

  /**
   * Handles user input from the console and processes commands.
   */
  public void handleUserInput() {
    try {
      while (true) {
        if (scanner.hasNextLine()) {
          String input = scanner.nextLine();
          if (input.equalsIgnoreCase("/logoff")) {
            sendDisconnect();
            break;
          } else if (input.equalsIgnoreCase("/help")) {
            printHelpMenu();
          } else if (input.equalsIgnoreCase("/who")) {
            queryConnectedUsers();
          } else if (input.startsWith("@")) {
            String[] parts = input.split(" ", 2);
            if (parts.length == 2) {
              sendDirectMessage(parts[0].substring(1), parts[1]);
            } else {
              System.out.println("Invalid direct message format. Use '@username <message>'.");
            }
          } else if (input.startsWith("!")) {
            sendRandomInsult(input.substring(1));
          } else {
            sendBroadcastMessage(input);
          }
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage() + ". Please restart Client.");
    }
  }

  /**
   * Prints the help menu to the console.
   */
  private void printHelpMenu() {
    System.out.println(Constants.YELLOW + Constants.HELP_MENU + Constants.RESET_COLOR);
  }

  /**
   * Disconnects the client by closing the socket and scanner, and exits the application.
   */
  public void disconnect() {
    super.disconnect();
    if (scanner != null) {
      scanner.close();
    }
    System.out.flush();
    System.err.flush();
    System.exit(0);
  }

  /**
   * Compares this client to the specified object for equality. Two clients are considered equal if
   * and only if they have the same socket reference. This method adheres to the general contract of
   * {@link Object#equals(Object)}.
   *
   * @param o the object to be compared for equality with this client
   * @return {@code true} if the specified object is equal to this client, {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return super.equals(o);
  }

  /**
   * Returns the hash code value for this client. This method returns the hash code of the
   * underlying socket. The returned hash code is consistent with the definition of
   * {@link Object#hashCode()} and is compatible with the {@link #equals(Object)} method in that two
   * clients considered equal will have the same hash code.
   *
   * @return the hash code value for this client
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  /**
   * The main method to start the {@code Client}. Prompts the user for the server IP and port,
   * creates a client, and then connects to the server.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter server IP (localhost for local server): ");
    String host = scanner.nextLine();
    System.out.print("Enter server port: ");
    int port = Integer.parseInt(scanner.nextLine());

    Client client = new Client(host, port);
    client.start();
  }
}
