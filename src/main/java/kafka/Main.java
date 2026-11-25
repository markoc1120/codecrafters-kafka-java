package kafka;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import kafka.protocol.RequestHeader;
import kafka.protocol.ResponseHeader;

public class Main {
  private static final int PORT = 9092;

  static void main(String[] args) throws IOException {
    System.err.println("Logs from your program will appear here!");
    try (ServerSocket serverSocket = createServerSocket(PORT)) {
      serve(serverSocket);
    }
  }

  static void serve(ServerSocket serverSocket) throws IOException {
    try (Socket clientSocket = serverSocket.accept()) {
      Client client = new Client(clientSocket);
      RequestHeader header = RequestHeader.parse(client.in);
      new ResponseHeader(header.correlationId()).writeTo(client.out);
    }
  }

  static ServerSocket createServerSocket(int port) throws IOException {
    ServerSocket serverSocket = new ServerSocket();
    serverSocket.setReuseAddress(true);
    serverSocket.bind(new InetSocketAddress(port));
    return serverSocket;
  }
}
