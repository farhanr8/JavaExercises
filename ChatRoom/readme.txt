General Assignment Requirements

The purpose of this assignment is to use Java Socket Programming to build a simple chat room application. You
are free to use whatever classes and methods from the Java 8 library you wish. You may not use non-standard
library features.

In this assignment, you are asked to implement a simple chat room application with both Server and Client. You
should implement necessary UI in JavaFX, and use Java Socket for network communication. Also, design your
application using OOP principles.

Server: The server is responsible for receiving messages from clients and dispatching messages to appropriate
clients. You just need one server; call the main class of the server ServerMain.java. Make sure that
ServerMain.java has a main() method.

Client: You should support both the chatting scenarios below. They should work with at least three clients.
Make sure that you have a ClientMain.java file with a main() method in it.
- One-to-one chat: Client A can chat with Client B and Client C individually at the same time. You can just use
one window to show both chats. Using separate windows to represent different conversations is also acceptable.
- Chatting Group: Client A can create a chat group, and send message to B and C in the same chat group, and
all members in the chat group can send/receive messages to/from the group.

You are encouraged to design the UI based on your experience of using chat room applications. You are
encouraged to add more interesting functions to the chat room, such as registering with a login and password,
retrieving chat history, sending friend requests, only allowing friends to chat with each other, and providing a
UI to change password. These extra features can be worth up to 10 extra points based on their complexity.
Please discuss your ideas with the TAs before implementation.

For full credit, bundle your Server code and your Client code into executable jar files, that we can execute by
double-clicking. There should be one jar file to start up the server, and one to start up the client.
