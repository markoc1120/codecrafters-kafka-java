package kafka;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
  final DataInputStream in;
  final DataOutputStream out;
  private final Socket socket;

  public Client(Socket socket) throws IOException {
    this.socket = socket;

    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
  }
}
