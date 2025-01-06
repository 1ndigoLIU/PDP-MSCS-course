import org.junit.jupiter.api.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

  static class TestClient extends Client {

    public TestClient(String host, int port) {
      super(host, port);
    }

    @Override
    public void disconnect() {
//      super.disconnect();
    }

    public void callSendBroadcastMessage(String message) throws Exception {
      var m = Client.class.getDeclaredMethod("sendBroadcastMessage", String.class);
      m.setAccessible(true);
      m.invoke(this, message);
    }

    public void callSendDirectMessage(String recipient, String msg) throws Exception {
      var m = Client.class.getDeclaredMethod("sendDirectMessage", String.class, String.class);
      m.setAccessible(true);
      m.invoke(this, recipient, msg);
    }

    public void callSendRandomInsult(String recipient) throws Exception {
      var m = Client.class.getDeclaredMethod("sendRandomInsult", String.class);
      m.setAccessible(true);
      m.invoke(this, recipient);
    }

    public void callQueryConnectedUsers() throws Exception {
      var m = Client.class.getDeclaredMethod("queryConnectedUsers");
      m.setAccessible(true);
      m.invoke(this);
    }

    public void printHelpMenu() throws Exception {
      var m = Client.class.getDeclaredMethod("printHelpMenu");
      m.setAccessible(true);
      m.invoke(this);
    }
  }

  private TestClient client;
  private ByteArrayOutputStream outContent;
  private ByteArrayOutputStream errContent;
  private PrintStream originalOut;
  private PrintStream originalErr;
  private InputStream originalIn;

  @BeforeEach
  void setUp() {
    originalOut = System.out;
    originalErr = System.err;
    originalIn = System.in;
    outContent = new ByteArrayOutputStream();
    errContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
    System.setIn(originalIn);
  }

  @Test
  void testEqualsAndHashCode() throws Exception {
    Client c1 = new Client("localhost", 12345);
    Client c2 = new Client("localhost", 12345);
    assertEquals(c1, c1);
    assertNotEquals(c1, null);
    assertNotEquals(c1, "string");
    assertEquals(c1.hashCode(), c1.hashCode());
    assertEquals(c1, c2);
    assertEquals(c1.hashCode(), c2.hashCode());

    try (ServerSocket ss = new ServerSocket(0)) {
      int port = ss.getLocalPort();
      Socket s1 = new Socket("localhost", port);
      Socket s2 = ss.accept();
      Client c3 = new Client("localhost", port);
      Client c4 = new Client("localhost", port);
    }
  }

  @Test
  void testStartAndSendConnectMessage() throws Exception {
    String simulatedInput = "testUser\n/logoff\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    try (ServerSocket serverSocket = new ServerSocket(0)) {
      int port = serverSocket.getLocalPort();
      client = new TestClient("localhost", port);
      Thread serverThread = new Thread(() -> {
        try {
          Socket serverSide = serverSocket.accept();
          serverSide.close();
        } catch (IOException ignored) {
        }
      });
      serverThread.setDaemon(true);
      serverThread.start();
      client.start();
    }
  }

  @Test
  void testHandleUserInputCommandsCoverage() throws Exception {
    String simulatedInput = "/who\n/help\n@someone Hi\n!target\nhello\n/logoff\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    class FakeOutClient extends TestClient {

      public FakeOutClient(String host, int port) {
        super(host, port);
      }

      @Override
      public void start() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        this.out = new DataOutputStream(byteOut);
        this.messageHandler = new ClientMessageHandler(this);
        try {
          this.setUsername("testUser");
        } catch (Exception e) {
        }
        handleUserInput();
      }

      @Override
      public void disconnect() {
        super.disconnect();
      }
    }

    FakeOutClient fakeClient = new FakeOutClient("localhost", 12345);
    fakeClient.start();
    String outStr = outContent.toString();
    assertTrue(outStr.contains("[Whisper]To someone: Hi"), "Should have whispered to someone");
  }

  @Test
  void testSendBroadcastMessageDirectly() throws Exception {
    TestClient fakeClient = new TestClient("localhost", 12345) {
      @Override
      public void start() {
        this.out = new DataOutputStream(new ByteArrayOutputStream());
        this.setUsername("testUser");
      }
    };
    fakeClient.start();
    fakeClient.callSendBroadcastMessage("Hello Lobby");
    assertEquals(fakeClient.getUsername(), "testUser");
  }

  @Test
  void testSendDirectMessageDirectly() throws Exception {
    TestClient fakeClient = new TestClient("localhost", 12345) {
      @Override
      public void start() {
        this.out = new DataOutputStream(new ByteArrayOutputStream());
        this.setUsername("testUser");
      }
    };
    fakeClient.start();
    fakeClient.callSendDirectMessage("recipient", "secretMsg");
  }

  @Test
  void testSendRandomInsultDirectly() throws Exception {
    TestClient fakeClient = new TestClient("localhost", 12345) {
      @Override
      public void start() {
        this.out = new DataOutputStream(new ByteArrayOutputStream());
        this.setUsername("testUser");
      }
    };
    fakeClient.start();
    fakeClient.callSendRandomInsult("victimUser");
  }

  @Test
  void testQueryConnectedUsersDirectly() throws Exception {
    TestClient fakeClient = new TestClient("localhost", 12345) {
      @Override
      public void start() {
        this.out = new DataOutputStream(new ByteArrayOutputStream());
      }
    };
    fakeClient.start();
    fakeClient.callQueryConnectedUsers();
  }

  @Test
  void testPrintHelpMenu() throws Exception {
    TestClient fakeClient = new TestClient("localhost", 12345) {
      @Override
      public void start() {
        // do nothing
      }
    };
    fakeClient.printHelpMenu();
    String outStr = outContent.toString();
    assertTrue(outStr.contains("HELP MENU"), "Help menu should be printed");
  }

  @Test
  void testCallPrivateMethodsViaReflection() throws Exception {
    TestClient fakeClient = new TestClient("localhost", 12345) {
      @Override
      public void start() {
        this.setUsername("testUser");
        this.out = new DataOutputStream(new ByteArrayOutputStream());
      }
    };
    fakeClient.start();

    var m = Client.class.getDeclaredMethod("sendBroadcastMessage", String.class);
    m.setAccessible(true);
    m.invoke(fakeClient, "hello");
  }
}