import java.io.IOException;
import java.io.DataOutputStream;
import java.net.*;
import java.util.*;

/**
 * The {@code Server} class represents a multi-client server in a client-server architecture. It
 * listens for incoming client connections, manages connected clients, and handles communication
 * using a {@link ServerMessageHandler}.
 *
 * @author Xihao Liu
 */
public class Server extends AbstractEndpoint {

  /**
   * The port number the server listens on.
   */
  private static final int PORT = 12345;
  /**
   * A thread-safe map of connected clients and their sockets.
   */
  private Map<String, Socket> clients = Collections.synchronizedMap(new HashMap<>());
  /**
   * A thread-safe map of clients and their output streams for sending messages.
   */
  private Map<String, DataOutputStream> clientOutputs = Collections.synchronizedMap(
      new HashMap<>());

  /**
   * Constructs a {@code Server} instance.
   */
  public Server() {
    super(null);
  }

  /**
   * Starts the server. Listens for incoming client connections and spawns a new thread to handle
   * each client.
   */
  @Override
  public void start() {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Server started. Listening on port: " + PORT);
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
        Thread t = new Thread(() -> {
          ServerMessageHandler handler = null;

          try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            handler = new ServerMessageHandler(this, clientSocket, clients, out);
            ConnectionEndpoint endpoint = new ConnectionEndpoint(clientSocket, handler);
            endpoint.handleIncomingMessages();
          } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
          } finally {
            if (handler != null) {
              String username = handler.getCurrentUsername();
              if (username != null && !username.isEmpty()) {
                synchronized (clients) {
                  clients.remove(username);
                  clientOutputs.remove(username);
                  System.out.println("User disconnected unexpectedly: " + username);
                }
              }
              disconnectSocket(handler.getSocket());
            }
          }
        });
        t.start();
      }
    } catch (IOException e) {
      System.err.println("Server error: " + e.getMessage());
    }
  }

  /**
   * Disconnects a client by closing their socket.
   *
   * @param socket the socket to disconnect
   */
  public void disconnectSocket(Socket socket) {
    try {
      if (socket != null && !socket.isClosed()) {
        socket.close();
      }
    } catch (IOException ignored) {
    }
  }

  /**
   * Adds a client's output stream to the map of client outputs.
   *
   * @param username the username of the client
   * @param dout     the {@link DataOutputStream} for the client
   */
  public void addClientOutput(String username, DataOutputStream dout) {
    clientOutputs.put(username, dout);
  }

  /**
   * Retrieves the output stream for a specific client.
   *
   * @param username the username of the client
   * @return the {@link DataOutputStream} for the client, or {@code null} if not found
   */
  public DataOutputStream getClientOutputStream(String username) {
    return clientOutputs.get(username);
  }

  /**
   * Removes a client's output stream from the map of client outputs.
   *
   * @param username the username of the client to remove
   */
  public void removeClientOutput(String username) {
    clientOutputs.remove(username);
  }

  /**
   * Compares this server to another object for equality.
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
    Server server = (Server) o;
    return Objects.equals(clients, server.clients);
  }

  /**
   * Computes the hash code for this server.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(clients);
  }

  /**
   * The main entry point of the server application. Starts the server.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    new Server().start();
  }

  /**
   * Represents a connection endpoint for a single client. This inner class handles the I/O streams
   * and message handling for a specific client.
   */
  private class ConnectionEndpoint extends AbstractEndpoint {

    /**
     * Constructs a {@code ConnectionEndpoint} for a specific client.
     *
     * @param socket  the socket for the client connection
     * @param handler the {@link MessageHandler} to process messages
     * @throws IOException if an I/O error occurs during initialization
     */
    public ConnectionEndpoint(Socket socket, MessageHandler handler) throws IOException {
      super(handler);
      setupIO(socket);
    }

    /**
     * Starts the endpoint. This implementation is a no-op since handling is managed externally.
     */
    @Override
    public void start() {
    }
  }
}
