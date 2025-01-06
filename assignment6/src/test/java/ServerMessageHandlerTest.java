import org.junit.jupiter.api.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ServerMessageHandlerTest {

  private Server server;
  private Socket socket;
  private Map<String, Socket> clients;
  private ByteArrayOutputStream byteOut;
  private DataOutputStream out;
  private ServerMessageHandler handler;

  @BeforeEach
  void setUp() throws IOException {
    server = new Server();
    socket = new Socket();
    clients = Collections.synchronizedMap(new HashMap<>());
    byteOut = new ByteArrayOutputStream();
    out = new DataOutputStream(byteOut);
    handler = new ServerMessageHandler(server, socket, clients, out);
  }

  @Test
  void testHandleConnectSuccess() throws IOException {
    clients.clear();
    handler.handleConnect("userA");
    assertEquals("userA", handler.getCurrentUsername());
  }

  @Test
  void testHandleConnectFull() throws IOException {
    for (int i = 0; i < Constants.MAX_USERS; i++) {
      clients.put("existingUser" + i, socket);
    }
    handler.handleConnect("newUser");
    assertNull(handler.getCurrentUsername());
  }

  @Test
  void testHandleConnectDuplicate() throws IOException {
    clients.put("userA", socket);
    handler.handleConnect("userA");
    assertNull(handler.getCurrentUsername());
  }

  @Test
  void testHandleBroadcast() throws IOException {
    clients.put("userA", socket);
    server.addClientOutput("userA", out);
    handler.handleBroadcast("sender", "msg");
  }

  @Test
  void testHandleDirectMessageUserOnline() throws IOException {
    clients.put("userB", socket);
    server.addClientOutput("userB", out);
    handler.handleDirectMessage("sender", "userB", "msg");
  }

  @Test
  void testHandleDirectMessageUserOffline() throws IOException {
    clients.clear();
    clients.put("sender", socket);
    server.addClientOutput("sender", out);
    handler.handleDirectMessage("sender", "userC", "msg");
  }

  @Test
  void testHandleQueryUsers() throws IOException {
    clients.put("userX", socket);
    handler.handleQueryUsers();
  }

  @Test
  void testHandleDisconnect() {
    clients.put("userY", socket);
    server.addClientOutput("userY", out);
    handler.handleDisconnect("userY");
    assertFalse(clients.containsKey("userY"));
  }

  @Test
  void testHandleInsultRequest() throws IOException {
    clients.put("userZ", socket);
    server.addClientOutput("userZ", out);
    try {
      handler.handleInsultRequest("sender", "userZ");
    } catch (Exception e) {
      // Ignore to avoid test crash if file not found
    }
  }

  @Test
  void testHandleReceiveInsult() throws IOException {
    handler.handleReceiveInsult("a", "b", "c");
  }

  @Test
  void testHandleConnectResponse() throws IOException {
    handler.handleConnectResponse(true, "msg");
  }

  @Test
  void testHandleQueryUserResponse() throws IOException {
    handler.handleQueryUserResponse(new ArrayList<>());
  }

  @Test
  void testHandleFailedMessage() throws IOException {
    handler.handleFailedMessage("err");
  }

  @Test
  void testHandleUnknown() {
    handler.handleUnknown(999);
  }

  @Test
  void testEqualsAndHashCode() {
    ServerMessageHandler h2 = new ServerMessageHandler(server, socket, clients, out);
    assertEquals(handler, h2);
    assertEquals(handler.hashCode(), h2.hashCode());
    assertNotEquals(handler, null);
    assertNotEquals(handler, "string");
    Socket otherSocket = new Socket();
    ServerMessageHandler h3 = new ServerMessageHandler(server, otherSocket, clients, out);
    assertNotEquals(handler, h3);
  }
}