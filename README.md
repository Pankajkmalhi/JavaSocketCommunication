# JavaSocketCommunication
This GitHub repository contains a Java-based socket communication application with a graphical user interface (GUI). The project consists of a server component (GuiServerApp) and a client component (GuiClientApp) that can exchange messages over a network connection. The server and client GUIs allow users to send and receive messages in real-time.



## Features

- **Server and Client**: The project provides both server and client applications, allowing for bidirectional communication.

- **Graphical User Interface**: The applications feature user-friendly GUIs that enable users to type and send messages easily.

- **Termination**: The server and client can gracefully terminate the connection using the "terminate" command.

## Usage

1. **Server Application**: Run the `GuiServerApp` class to start the server. The server listens on port 12345.

2. **Client Application**: Run the `GuiClientApp` class to start the client. The client can connect to the server using the "localhost" address and port 12345.

3. **Sending Messages**: Type your messages in the text field and click the "Send" button to send messages to the connected party.

4. **Terminating the Connection**: To gracefully terminate the connection, send the "terminate" command. Both the server and client will close their connections.

## Dependencies

This project uses standard Java libraries and does not require any additional dependencies.

