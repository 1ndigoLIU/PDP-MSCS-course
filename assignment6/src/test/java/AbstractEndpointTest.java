import org.junit.jupiter.api.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractEndpointTest {

  static class TestHandler implements MessageHandler {

    String lastMessage;
    boolean receivedUnknown = false;

    @Override
    public void handleConnect(String username) throws IOException {
      lastMessage = "handleConnect:" + username;
    }

    @Override
    public void handleBroadcast(String sender, String message) throws IOException {
      lastMessage = "handleBroadcast:" + sender + ":" + message;
    }

    @Override
    public void handleDirectMessage(String sender, String recipient, String message)
        throws IOException {
      lastMessage = "handleDirectMessage:" + sender + ":" + recipient + ":" + message;
    }

    @Override
    public void handleQueryUsers() throws IOException {
      lastMessage = "handleQueryUsers";
    }

    @Override
    public void handleDisconnect(String username) throws IOException {
      lastMessage = "handleDisconnect:" + username;
    }

    @Override
    public void handleInsultRequest(String sender, String recipient) throws IOException {
      lastMessage = "handleInsultRequest:" + sender + ":" + recipient;
    }

    @Override
    public void handleReceiveInsult(String sender, String recipient, String insult)
        throws IOException {
      lastMessage = "handleReceiveInsult:" + sender + ":" + recipient + ":" + insult;
    }

    @Override
    public void handleConnectResponse(boolean success, String message) throws IOException {
      lastMessage = "handleConnectResponse:" + success + ":" + message;
    }

    @Override
    public void handleQueryUserResponse(List<String> users) throws IOException {
      lastMessage = "handleQueryUserResponse:" + users.size();
    }

    @Override
    public void handleFailedMessage(String errorMessage) throws IOException {
      lastMessage = "handleFailedMessage:" + errorMessage;
    }

    @Override
    public void handleUnknown(int messageType) {
      receivedUnknown = true;
    }
  }

  static class TestEndpoint extends AbstractEndpoint {

    public TestEndpoint(MessageHandler handler) {
      super(handler);
    }

    @Override
    public void start() {

    }

    public void callHandleIncomingMessages() {
      handleIncomingMessages();
    }

    public String callReadStringField() throws IOException {
      return readStringField();
    }

    public void callWriteStringField(String s) throws IOException {
      writeStringField(out, s);
    }

    public void setStreams(InputStream inStream, OutputStream outStream) throws Exception {
      this.in = new DataInputStream(inStream);
      this.out = new DataOutputStream(outStream);
    }
  }

  private TestEndpoint endpoint;
  private TestHandler handler;

  @BeforeEach
  void setUp() {
    handler = new TestHandler();
    endpoint = new TestEndpoint(handler);
  }

  @Test
  void testSetupIOAndDisconnect() throws Exception {
    try (ServerSocket serverSocket = new ServerSocket(0)) {
      Socket client = new Socket("localhost", serverSocket.getLocalPort());
      Socket serverSide = serverSocket.accept();
      endpoint.setupIO(serverSide);
      assertNotNull(endpoint.in);
      assertNotNull(endpoint.out);

      endpoint.disconnect();
      assertTrue(serverSide.isClosed());
    }
    endpoint.disconnect();
  }

  @Test
  void testReadWriteStringField() throws Exception {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    endpoint.out = new DataOutputStream(byteOut);
    endpoint.callWriteStringField("Hello");
    DataInputStream din = new DataInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
    endpoint.in = din;
    String s = endpoint.callReadStringField();
    assertEquals("Hello", s);
    byteOut.reset();
    endpoint.callWriteStringField("");
    din = new DataInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
    endpoint.in = din;
    s = endpoint.callReadStringField();
    assertEquals("", s);
  }

  @Test
  void testHandleIncomingMessages() throws Exception {
    ByteArrayOutputStream inputData = new ByteArrayOutputStream();
    DataOutputStream dout = new DataOutputStream(inputData);
    dout.writeInt(Constants.CONNECT_MESSAGE);
    writeString(dout, "UserA");
    dout.writeInt(Constants.BROADCAST_MESSAGE);
    writeString(dout, "Alice");
    writeString(dout, "Hi all");
    dout.writeInt(Constants.DIRECT_MESSAGE);
    writeString(dout, "Bob");
    writeString(dout, "Charlie");
    writeString(dout, "Secret");
    dout.writeInt(Constants.QUERY_CONNECTED_USERS);
    dout.writeInt(Constants.DISCONNECT_MESSAGE);
    writeString(dout, "UserB");
    ByteArrayInputStream bin = new ByteArrayInputStream(inputData.toByteArray());
    endpoint.in = new DataInputStream(bin);
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    endpoint.out = new DataOutputStream(byteOut);
    endpoint.callWriteStringField("TestString");
    endpoint.callHandleIncomingMessages();
    assertEquals("handleDisconnect:UserB", handler.lastMessage);
  }

  @Test
  void testAdditionalMessageTypesCoverage() throws Exception {
    ByteArrayOutputStream inputData = new ByteArrayOutputStream();
    DataOutputStream dout = new DataOutputStream(inputData);
    dout.writeInt(Constants.SEND_INSULT);
    writeString(dout, "SenderUser");
    writeString(dout, "VictimUser");
    dout.writeInt(Constants.BROADCAST_INSULT);
    writeString(dout, "InsultSender");
    writeString(dout, "InsultRecipient");
    writeString(dout, "You are funny!");
    dout.writeInt(Constants.CONNECT_RESPONSE);
    dout.writeBoolean(true);
    writeString(dout, "Welcome, you are in!");
    dout.writeInt(Constants.QUERY_USER_RESPONSE);
    dout.writeInt(2);
    writeString(dout, "UserX");
    writeString(dout, "UserY");
    dout.writeInt(Constants.FAILED_MESSAGE);
    writeString(dout, "Some error occurred.");
    dout.writeInt(9999);
    ByteArrayInputStream bin = new ByteArrayInputStream(inputData.toByteArray());
    endpoint.in = new DataInputStream(bin);
    endpoint.callHandleIncomingMessages();
    assertTrue(handler.receivedUnknown, "Unknown message type should be handled by handleUnknown");
    assertEquals("handleFailedMessage:Some error occurred.", handler.lastMessage);
  }

  @Test
  void testHandleIncomingMessagesWithIOException() throws Exception {
    endpoint.in = new DataInputStream(new ByteArrayInputStream(new byte[0]));
    endpoint.callHandleIncomingMessages();
  }

  @Test
  void testHandleIncomingMessagesWithException() throws Exception {
    MessageHandler badHandler = new MessageHandler() {
      @Override
      public void handleConnect(String username) throws IOException {
        throw new RuntimeException("TestException");
      }

      public void handleBroadcast(String s, String m) {
      }

      public void handleDirectMessage(String s, String r, String m) {
      }

      public void handleQueryUsers() {
      }

      public void handleDisconnect(String u) {
      }

      public void handleInsultRequest(String s, String r) {
      }

      public void handleReceiveInsult(String s, String r, String i) {
      }

      public void handleConnectResponse(boolean b, String msg) {
      }

      public void handleQueryUserResponse(List<String> us) {
      }

      public void handleFailedMessage(String err) {
      }
    };

    TestEndpoint ep2 = new TestEndpoint(badHandler);
    ByteArrayOutputStream inputData = new ByteArrayOutputStream();
    DataOutputStream dout = new DataOutputStream(inputData);
    dout.writeInt(Constants.CONNECT_MESSAGE);
    writeString(dout, "CrashUser");
    ep2.in = new DataInputStream(new ByteArrayInputStream(inputData.toByteArray()));
    ep2.callHandleIncomingMessages();
  }

  @Test
  void testEqualsAndHashCode() throws Exception {
    assertEquals(endpoint, endpoint);
    assertNotEquals(endpoint, null);
    assertNotEquals(endpoint, "string");

    TestEndpoint ep2 = new TestEndpoint(handler);
    assertEquals(endpoint.hashCode(), ep2.hashCode());
    assertEquals(endpoint, ep2);

    try (ServerSocket ss = new ServerSocket(0); Socket s1 = new Socket("localhost",
        ss.getLocalPort()); Socket s2 = ss.accept()) {
      endpoint.setupIO(s2);
      TestEndpoint ep3 = new TestEndpoint(handler);
      ep3.setupIO(s2);
      assertEquals(endpoint, ep3);
      assertEquals(endpoint.hashCode(), ep3.hashCode());
      try (ServerSocket ss2 = new ServerSocket(0); Socket s3 = new Socket("localhost",
          ss2.getLocalPort()); Socket s4 = ss2.accept()) {
        TestEndpoint ep4 = new TestEndpoint(handler);
        ep4.setupIO(s4);
        assertNotEquals(endpoint, ep4);
      }
    }
  }

  private void writeString(DataOutputStream dout, String str) throws IOException {
    byte[] data = str.getBytes("UTF-8");
    dout.writeInt(data.length);
    dout.write(data);
  }
}
