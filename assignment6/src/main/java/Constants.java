/**
 * The {@code Constants} class provides a set of predefined static values used for communication
 * between clients and a server, as well as utility constants for console color formatting and
 * helper text messages.
 *
 * <p>This class includes:
 * <ul>
 *   <li>Message type constants that define the nature of messages exchanged between the server and clients.</li>
 *   <li>Color code constants for printing text in various colors to the console.</li>
 *   <li>Help text and welcome message templates to guide users when they connect to the chatroom.</li>
 * </ul>
 *
 * <p>All constants are public and static, making them easily accessible throughout the application.
 * They serve as a centralized point of reference to maintain consistency and reduce the use of hard-coded values.
 *
 * @author Xihao Liu
 */
public class Constants {

  /**
   * The maximum number of people online in the chatroom at the same time.
   */
  public static final int MAX_USERS = 10;
  /**
   * Indicates a message type for a client attempting to connect to the server.
   */
  public static final int CONNECT_MESSAGE = 19;
  /**
   * Indicates a server response message type to a client's connection request.
   */
  public static final int CONNECT_RESPONSE = 20;
  /**
   * Indicates a message type for a client disconnecting from the server.
   */
  public static final int DISCONNECT_MESSAGE = 21;
  /**
   * Indicates a message type requesting a list of all currently connected users.
   */
  public static final int QUERY_CONNECTED_USERS = 22;
  /**
   * Indicates a server response message type containing a list of connected users.
   */
  public static final int QUERY_USER_RESPONSE = 23;
  /**
   * Indicates a message type representing a broadcast message sent to all connected clients.
   */
  public static final int BROADCAST_MESSAGE = 24;
  /**
   * Indicates a message type representing a direct (private) message to a specific user.
   */
  public static final int DIRECT_MESSAGE = 25;
  /**
   * Indicates a message type used by the server to notify clients of an error or failure event.
   */
  public static final int FAILED_MESSAGE = 26;
  /**
   * Indicates a message type used to send a randomly generated insult to a specified user.
   */
  public static final int SEND_INSULT = 27;
  /**
   * Indicates a message type used to receive a randomly generated insult from server.
   */
  public static final int BROADCAST_INSULT = 28;
  /**
   * ANSI escape code for resetting the console color to default.
   */
  public static final String RESET_COLOR = "\033[0m";
  /**
   * ANSI escape code for printing text in red.
   */
  public static final String RED = "\033[31m";
  /**
   * ANSI escape code for printing text in green.
   */
  public static final String GREEN = "\033[32m";
  /**
   * ANSI escape code for printing text in yellow.
   */
  public static final String YELLOW = "\033[33m";
  /**
   * ANSI escape code for printing text in blue.
   */
  public static final String BLUE = "\033[34m";
  /**
   * ANSI escape code for printing text in purple.
   */
  public static final String PURPLE = "\033[35m";
  /**
   * ANSI escape code for printing text in cyan.
   */
  public static final String CYAN = "\033[36m";
  /**
   * ANSI escape code for printing text in white.
   */
  public static final String WHITE = "\033[37m";
  /**
   * A multiline help menu detailing various commands and their functions, displayed to the client.
   */
  public static final String HELP_MENU = """
      ====================================================
      HELP MENU - Available Commands:
      ====================================================
      /who                     - Display the number of users in the lobby and their usernames.
      /help                    - Show this help menu with a list of available commands.
      @username <message>      - Send a private whisper message to the specified user.
      !username                - Generate and send a random insult to the target user in the lobby.
      /logoff                  - Disconnect from the server and close the client.
      [Direct Input]           - Type any message directly to broadcast it to the lobby.
      ====================================================
      """;
  /**
   * A multiline welcome message template displayed when a user successfully connects, indicating
   * the number of currently online users and providing guidance on available commands. The
   * placeholders %s and %d should be replaced with the client's username and the number of users,
   * respectively.
   */
  public static final String WELCOME_MESSAGE = """
      ====================================================
      WELCOME TO THE CHATROOM
      ====================================================
      Hello, %s! We're excited to have you here.
      There are currently %d users online in the chatroom.
      Use /help to see the list of available commands.
      Enjoy your time chatting with others!
      ====================================================
      """;
}
