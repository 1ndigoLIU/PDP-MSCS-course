import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientMessageHandlerTest {

  static class FakeClient extends Client {

    private boolean disconnected = false;
    private String username;

    public FakeClient(String host, int port, String username) {
      super(host, port);
      this.username = username;
    }

    @Override
    public String getUsername() {
      return username;
    }

    @Override
    public void disconnect() {
      disconnected = true;
    }

    @Override
    public void start() {
    }
  }

  private ByteArrayOutputStream outContent;
  private ByteArrayOutputStream errContent;
  private PrintStream originalOut;
  private PrintStream originalErr;
  private ClientMessageHandler handler;
  private FakeClient fakeClient;

  @BeforeEach
  void setUp() {
    originalOut = System.out;
    originalErr = System.err;
    outContent = new ByteArrayOutputStream();
    errContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));

    fakeClient = new FakeClient("localhost", 12345, "testUser");
    handler = new ClientMessageHandler(fakeClient);
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  void testHandleConnect() throws IOException {
    handler.handleConnect("someone");
    String errOutput = errContent.toString();
    assertTrue(errOutput.contains("unknown message type: " + Constants.CONNECT_MESSAGE));
  }

  @Test
  void testHandleBroadcast() {
    handler.handleBroadcast("Alice", "Hello world");
    String output = outContent.toString();
    assertTrue(output.contains("[Lobby]Alice: Hello world"));
  }

  @Test
  void testHandleDirectMessage() {
    handler.handleDirectMessage("Bob", "testUser", "secretMsg");
    String output = outContent.toString();
    assertTrue(output.contains("[Whisper]From Bob: secretMsg"));
  }

  @Test
  void testHandleQueryUsers() throws IOException {
    handler.handleQueryUsers();
    String errOutput = errContent.toString();
    assertTrue(errOutput.contains("unknown message type: " + Constants.QUERY_CONNECTED_USERS));
  }

  @Test
  void testHandleDisconnect() {
    handler.handleDisconnect("someone");
    String errOutput = errContent.toString();
    assertTrue(errOutput.contains("Server requested disconnect for user: someone"));
  }

  @Test
  void testHandleInsultRequest() throws IOException {
    handler.handleInsultRequest("Alice", "Bob");
    String errOutput = errContent.toString();
    assertTrue(errOutput.contains("unknown message type: " + Constants.SEND_INSULT));
  }

  @Test
  void testHandleReceiveInsult() throws IOException {
    handler.handleReceiveInsult("Charlie", "testUser", "You are silly!");
    String output = outContent.toString();
    assertTrue(output.contains("[Lobby]Charlie --> testUser: You are silly!"));
  }

  @Test
  void testHandleConnectResponseSuccess() throws IOException {
    outContent.reset();
    handler.handleConnectResponse(true, "Welcome message");
    String output = outContent.toString();
    assertTrue(output.contains("Welcome message"));
    assertFalse(fakeClient.disconnected, "Client should not be disconnected on success");
  }

  @Test
  void testHandleConnectResponseFail() throws IOException {
    outContent.reset();
    handler.handleConnectResponse(false, "Failed reason");
    String output = outContent.toString();
    assertTrue(output.contains("Connection failed: Failed reason"));
    assertTrue(fakeClient.disconnected, "Client should be disconnected on failure");
  }

  @Test
  void testHandleQueryUserResponseWithCurrentUser() throws IOException {
    outContent.reset();
    List<String> users = Arrays.asList("testUser", "Alice", "Bob");
    handler.handleQueryUserResponse(users);
    String output = outContent.toString();
    assertTrue(output.contains("Connected users: 3"));
    assertTrue(output.contains("- testUser(Me)"));
    assertTrue(output.contains("- Alice"));
    assertTrue(output.contains("- Bob"));
  }

  @Test
  void testHandleQueryUserResponseWithoutCurrentUser() throws IOException {
    outContent.reset();
    List<String> users = Arrays.asList("Alice", "Bob");
    handler.handleQueryUserResponse(users);
    String output = outContent.toString();
    assertTrue(output.contains("Connected users: 2"));
    assertTrue(output.contains("- Alice"));
    assertTrue(output.contains("- Bob"));
    assertFalse(output.contains("(Me)"));
  }

  @Test
  void testHandleFailedMessage() throws IOException {
    outContent.reset();
    handler.handleFailedMessage("Some error occurred");
    String outOutput = outContent.toString();
    assertTrue(outOutput.contains("Failed: Some error occurred"));
  }

  @Test
  void testHandleUnknown() {
    errContent.reset();
    handler.handleUnknown(999);
    String errOutput = errContent.toString();
    assertTrue(errOutput.contains("unknown message type: 999"));
  }

  @Test
  void testEqualsAndHashCode() {
    ClientMessageHandler h2 = new ClientMessageHandler(fakeClient);
    assertEquals(handler, handler);
    assertEquals(handler.hashCode(), handler.hashCode());
    assertEquals(handler, h2);
    assertEquals(handler.hashCode(), h2.hashCode());

    FakeClient otherClient = new FakeClient("localhost", 12345, "otherUser");
    ClientMessageHandler h3 = new ClientMessageHandler(otherClient);
    assertNotEquals(handler, null);
    assertNotEquals(handler, "string");
  }
}
