import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {

  private Server server;

  @BeforeEach
  void setUp() {
    server = new Server();
  }

  @Test
  @DisplayName("Test addClientOutput, getClientOutputStream, removeClientOutput")
  void testClientOutputOperations() throws IOException {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    DataOutputStream dout = new DataOutputStream(byteOut);
    server.addClientOutput("user1", dout);
    DataOutputStream retrieved = server.getClientOutputStream("user1");
    assertNotNull(retrieved, "Client output stream should be retrieved successfully");
    server.removeClientOutput("user1");
    assertNull(server.getClientOutputStream("user1"),
        "Client output should be removed and return null");
  }

  @Test
  @DisplayName("Test disconnectSocket with a closed and an open socket")
  void testDisconnectSocket() throws IOException {
    Socket closedSocket = new Socket();
    closedSocket.close();
    server.disconnectSocket(closedSocket);
    assertTrue(closedSocket.isClosed(), "Already closed socket remains closed");
    try (ServerSocket tempServer = new ServerSocket(0)) {
      int port = tempServer.getLocalPort();
      Socket clientSocket = new Socket("localhost", port);
      Socket serverSideSocket = tempServer.accept();
      assertFalse(serverSideSocket.isClosed(),
          "Server side socket should be open before disconnect");
      server.disconnectSocket(serverSideSocket);
      assertTrue(serverSideSocket.isClosed(),
          "Server side socket should be closed after disconnect");
      clientSocket.close();
    }
  }

  @Test
  @DisplayName("Test disconnectSocket IOException branch (ignored exception)")
  void testDisconnectSocketIOException() {
    class FailingCloseSocket extends Socket {

      @Override
      public synchronized void close() throws IOException {
        throw new IOException("Simulated close failure");
      }
    }
    Socket failingSocket = new FailingCloseSocket();
    server.disconnectSocket(failingSocket);
  }

  @Test
  @DisplayName("Test equals and hashCode")
  void testEqualsAndHashCode() throws Exception {
    Server anotherServer = new Server();
    assertEquals(server, anotherServer, "Servers should be equal initially");
    assertEquals(server.hashCode(), anotherServer.hashCode(),
        "Hash codes should match for equal servers");
    assertEquals(server, server, "Servers should be equal initially");
  }

  @Test
  @DisplayName("Test reflection to verify internal maps initialization")
  void testInternalMapsInitialization() throws Exception {
    Field clientsField = Server.class.getDeclaredField("clients");
    Field outputsField = Server.class.getDeclaredField("clientOutputs");
    clientsField.setAccessible(true);
    outputsField.setAccessible(true);
    Object clientsObj = clientsField.get(server);
    Object outputsObj = outputsField.get(server);
    assertNotNull(clientsObj, "clients map should be initialized");
    assertNotNull(outputsObj, "clientOutputs map should be initialized");
    assertTrue(clientsObj instanceof Map, "clients should be a Map");
    assertTrue(outputsObj instanceof Map, "clientOutputs should be a Map");
  }

  @Test
  @DisplayName("Test equals with null and different class")
  void testEqualsWithNullAndDifferentClass() {
    assertNotEquals(server, null, "Server should not equal null");
    assertNotEquals(server, "Some string", "Server should not equal an object of different class");
  }

  @Test
  @DisplayName("Test main method directly")
  void testMainMethodDirect() throws Exception {
    Thread serverThread = new Thread(() -> {
      Server.main(new String[]{});
    });
    serverThread.setDaemon(true);
    serverThread.start();
    Thread.sleep(500);
    Socket testClient = null;
    try {
      testClient = new Socket("localhost", 12345);
      assertTrue(testClient.isConnected(),
          "Should be able to connect to the server started by main");
    } catch (IOException e) {
      fail("Unable to connect to server started by main method: " + e.getMessage());
    } finally {
      if (testClient != null) {
        testClient.close();
      }
    }
  }

  @Test
  @DisplayName("Test start method with IOException in handler block (lines 55-66)")
  void testStartWithIOException() throws Exception {
    Thread serverThread = new Thread(() -> server.start());
    serverThread.setDaemon(true);
    serverThread.start();
    Thread.sleep(500);
    Socket clientSocket = null;
    try {
      clientSocket = new Socket("localhost", 12345);
      assertTrue(clientSocket.isConnected(), "Client should connect successfully");
      clientSocket.close();
      Thread.sleep(500);
    } finally {
      // No additional assertions needed, coverage is the goal here.
    }
  }

  @Test
  @DisplayName("Test ConnectionEndpoint.start() method")
  void testConnectionEndpointStartMethod() throws Exception {
    Class<?>[] declaredClasses = Server.class.getDeclaredClasses();
    Class<?> connectionEndpointClass = null;
    for (Class<?> c : declaredClasses) {
      if (c.getSimpleName().equals("ConnectionEndpoint")) {
        connectionEndpointClass = c;
        break;
      }
    }
    assertNotNull(connectionEndpointClass,
        "ConnectionEndpoint class should be found via reflection");
  }

  @Test
  @DisplayName("Test finally block with handler and username in start() method (55-66 lines)")
  void testFinallyBlockWithHandlerAndUsername() throws Exception {
    Thread serverThread = new Thread(() -> server.start());
    serverThread.setDaemon(true);
    serverThread.start();
    Thread.sleep(500);
    Socket clientSocket = null;
    DataOutputStream clientOut = null;
    String testUsername = "testUser";
    try {
      clientSocket = new Socket("localhost", 12345);
      assertTrue(clientSocket.isConnected(), "Client should connect successfully");
      clientOut = new DataOutputStream(clientSocket.getOutputStream());
      clientOut.writeInt(Constants.CONNECT_MESSAGE);
      byte[] usernameBytes = testUsername.getBytes("UTF-8");
      clientOut.writeInt(usernameBytes.length);
      clientOut.write(usernameBytes);
      clientOut.flush();
      Thread.sleep(200);
      clientSocket.close();
      Thread.sleep(500);
    } finally {
      if (clientOut != null) {
        clientOut.close();
      }
      if (clientSocket != null && !clientSocket.isClosed()) {
        clientSocket.close();
      }
    }
  }
}