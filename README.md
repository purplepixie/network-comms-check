# network-comms-check
Check network communication (such as in our labs) with very simple UDP network clients.

# Unicast UDP

## Python

Start server with port i.e. ```python server.py 5010``` for port 5010.

Run client with remote IP and port i.e. ```python client.py 127.0.0.1 5010``` for remote host 127.0.0.1 port 5010.

## Java

Compile the Java code in the java directory: ```javac *.java```.

Run the server with an optional port (defaults to 5000 if not provided): ```java Server 5010``` to start on port 5010.

Run the client with the remoteip and remoteport: ```java Client 127.0.0.1 5010``` to send a message to 127.0.0.1 on 5010.

# Multicast UDP 

## Java

Compile the Java code in the java-multicast directory: ```javac *.java```.

Run the receiver (with an optional port and optional multicast address - defaults to 4446 and 230.0.0.0): ```java Receiver```.

Run the sender (with an optional port and optional multicast address - defaults as above): ```java Sender```.
