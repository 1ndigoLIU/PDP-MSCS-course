import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageHandlerTest {

  static class TestMessageHandler implements MessageHandler {

    String lastInfo;
    String lastError;
    boolean successFlag;
    String messageParam;
    List<String> userList;

    @Override
    public void handleConnect(String username) throws IOException {
      lastInfo = "handleConnect:" + username;
    }

    @Override
    public void handleBroadcast(String sender, String message) throws IOException {
      lastInfo = "handleBroadcast:" + sender + ":" + message;
    }

    @Override
    public void handleDirectMessage(String sender, String recipient, String message)
        throws IOException {
      lastInfo = "handleDirectMessage:" + sender + ":" + recipient + ":" + message;
    }

    @Override
    public void handleQueryUsers() throws IOException {
      lastInfo = "handleQueryUsers";
    }

    @Override
    public void handleDisconnect(String username) throws IOException {
      lastInfo = "handleDisconnect:" + username;
    }

    @Override
    public void handleInsultRequest(String sender, String recipient) throws IOException {
      lastInfo = "handleInsultRequest:" + sender + ":" + recipient;
    }

    @Override
    public void handleReceiveInsult(String sender, String recipient, String insult)
        throws IOException {
      lastInfo = "handleReceiveInsult:" + sender + ":" + recipient + ":" + insult;
    }

    @Override
    public void handleConnectResponse(boolean success, String message) throws IOException {
      successFlag = success;
      messageParam = message;
      lastInfo = "handleConnectResponse:" + success + ":" + message;
    }

    @Override
    public void handleQueryUserResponse(List<String> users) throws IOException {
      userList = users;
      lastInfo = "handleQueryUserResponse:" + users.size();
    }

    @Override
    public void handleFailedMessage(String errorMessage) throws IOException {
      lastError = errorMessage;
      lastInfo = "handleFailedMessage:" + errorMessage;
    }
  }

  private ByteArrayOutputStream outContent;
  private ByteArrayOutputStream errContent;
  private PrintStream originalOut;
  private PrintStream originalErr;
  private TestMessageHandler handler;

  @BeforeEach
  void setUp() {
    originalOut = System.out;
    originalErr = System.err;
    outContent = new ByteArrayOutputStream();
    errContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));

    handler = new TestMessageHandler();
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  void testHandleConnect() throws IOException {
    handler.handleConnect("userA");
    assertEquals("handleConnect:userA", handler.lastInfo);
  }

  @Test
  void testHandleBroadcast() throws IOException {
    handler.handleBroadcast("Alice", "Hi all");
    assertEquals("handleBroadcast:Alice:Hi all", handler.lastInfo);
  }

  @Test
  void testHandleDirectMessage() throws IOException {
    handler.handleDirectMessage("Bob", "Charlie", "secret");
    assertEquals("handleDirectMessage:Bob:Charlie:secret", handler.lastInfo);
  }

  @Test
  void testHandleQueryUsers() throws IOException {
    handler.handleQueryUsers();
    assertEquals("handleQueryUsers", handler.lastInfo);
  }

  @Test
  void testHandleDisconnect() throws IOException {
    handler.handleDisconnect("userB");
    assertEquals("handleDisconnect:userB", handler.lastInfo);
  }

  @Test
  void testHandleInsultRequest() throws IOException {
    handler.handleInsultRequest("Dave", "Eve");
    assertEquals("handleInsultRequest:Dave:Eve", handler.lastInfo);
  }

  @Test
  void testHandleReceiveInsult() throws IOException {
    handler.handleReceiveInsult("Frank", "George", "You smell!");
    assertEquals("handleReceiveInsult:Frank:George:You smell!", handler.lastInfo);
  }

  @Test
  void testHandleConnectResponse() throws IOException {
    handler.handleConnectResponse(true, "Welcome!");
    assertTrue(handler.successFlag);
    assertEquals("Welcome!", handler.messageParam);
    assertEquals("handleConnectResponse:true:Welcome!", handler.lastInfo);

    handler.handleConnectResponse(false, "No entry");
    assertFalse(handler.successFlag);
    assertEquals("No entry", handler.messageParam);
    assertEquals("handleConnectResponse:false:No entry", handler.lastInfo);
  }

  @Test
  void testHandleQueryUserResponse() throws IOException {
    List<String> users = Arrays.asList("user1", "user2");
    handler.handleQueryUserResponse(users);
    assertEquals(users, handler.userList);
    assertEquals("handleQueryUserResponse:2", handler.lastInfo);
  }

  @Test
  void testHandleFailedMessage() throws IOException {
    handler.handleFailedMessage("Some error");
    assertEquals("Some error", handler.lastError);
    assertEquals("handleFailedMessage:Some error", handler.lastInfo);
  }

  @Test
  void testHandleUnknown() {
    handler.handleUnknown(999);
    String errOutput = errContent.toString();
    assertTrue(errOutput.contains("Unknown message type: 999"));
  }
}
