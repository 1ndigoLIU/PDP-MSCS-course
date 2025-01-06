# **HW6: Build a Chatroom**

## Overview

This project implements a simple multi-user chatroom application, consisting of a server and a client. Users can connect to the server using the client, communicate with other users in the chatroom, send private messages, query the list of connected users, and even send a randomly generated "insult" message based on a predefined grammar.

Additionally, the chatroom has a limit of 10 simultaneous users. If the chatroom is full, any new client attempting to connect will receive a message indicating that the room is full, and their connection will be refused.

## Entry Points

- **Server Entry Point**: `Server` class, `main(String[] args)` method
- **Client Entry Point**: `Client` class, `main(String[] args)` method

## How to Run the Program from IntelliJ IDEA

1. Open IntelliJ IDEA.

2. Import or open the project. Ensure that all classes (`Server.java`, `Client.java`, `Constants.java`, as well as classes under `hw4` like `Expression`, `GrammarExpander`, `GrammarLoader`) are placed in the correct packages.

3. Locate `Server.java` in the Project panel. Right-click and select **Run 'Server.main()'** to start the server.

   - The server listens on the default port `12345` for incoming client connections.

4. Similarly, run `Client.java`:

   - Make sure the server is already running.
   - Run **Client.main()**, then enter the server IP address (`localhost` if running on the same machine) and port (`12345` by default).
   - Enter a username when prompted. Once connected, you can start sending messages.

5. **Note for Running Multiple Clients in IntelliJ**:

   If you want to run multiple clients at the same time from IntelliJ:

   1. Go to **Run** > **Edit Configurations...**
   2. Select the **Client** run configuration.
   3. Click on **Modify options** (the gear icon) and ensure **Allow multiple instances** is checked.

## Key Classes and Methods

**Server Class**:

- `main()` launches the server, which listens for client connections.
- `start()` initializes a `ServerSocket` and enters a loop, accepting new clients and spawning threads to handle them.
- Internally uses a `ServerMessageHandler` (via `ConnectionEndpoint` objects) to process messages from connected clients.
- Implements a user limit of 10 concurrent connections. When a new client attempts to connect and the limit is reached, the server sends a `CONNECT_RESPONSE` with a failure message indicating that the chatroom is full, and then disconnects the new client.

**Client Class**:

- `main()` is the entry point. It prompts for the server IP and port, attempts to connect, and then prompts for a username.
- `connect()` sends a connection request to the server and starts a thread to listen for server messages.
- `handleUserInput()` waits for user commands. Supported commands include:
  - `/help` to view the help menu
  - `/who` to query connected users
  - `@username <message>` to send a private message
  - `!username` to send a random insult
  - `/logoff` to disconnect
  - Any other input is broadcasted to all users

**Constants Class**: Defines message type codes, ANSI color codes for console output, and templates for the help menu and welcome message.

**MessageHandler Interface & Implementations (e.g., ServerMessageHandler, ClientMessageHandler)**:

- Abstracts the logic of handling different message types (connect, broadcast, direct message, query users, insult, etc.).
- `ServerMessageHandler` checks user count during `handleConnect`, updates user maps, and sends appropriate responses.

**GrammarLoader and GrammarExpander (under hw4)**: Used to load a JSON-based grammar and generate a random "insult" message. They demonstrate extensibility and a more interesting user experience beyond simple chat messages.

## Assumptions

- The server and client run on machines capable of establishing a TCP connection.
- The chosen port (default `12345`) is open and not blocked by firewalls.
- Usernames are considered unique. If a username is already taken, the client must restart with a different one.

## Steps to Ensure Correctness

- The code includes error handling for I/O operations and prints error messages if exceptions occur.
- Each message type has a dedicated handler method to keep responsibilities clear.
- The server uses synchronized access to shared data structures (e.g., `Map<String, Socket> clients`) to ensure thread safety.
- Simple console logs are included to track server activities (user connections, disconnections, message handling).

## Example Output

**Server side:**

```
Server started. Listening on port: 12345
New client connected: /127.0.0.1:53273
User connected: Alice
User disconnected: Alice
```

**Client side:**

```
Enter server IP (localhost for local server): localhost
Enter server port: 12345
Connected to server at localhost:12345
Enter your username: Alice
====================================================
WELCOME TO THE CHATROOM
====================================================
Hello, Alice! We're excited to have you here.
There are currently 0 users online in the chatroom.
Use /help to see the list of available commands.
Enjoy your time chatting with others!
====================================================

/who
Connected users: 1
- Alice
@Alice hello
[Whisper]From Alice: hello
[Whisper]To Alice: hello
/logoff
```