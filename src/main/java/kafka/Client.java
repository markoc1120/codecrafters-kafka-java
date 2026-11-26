package kafka;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import kafka.protocol.RequestHeader;
import kafka.protocol.ResponseHeader;

public class Client {
  private final DataInputStream in;
  private final DataOutputStream out;
  private final Socket socket;

  public Client(Socket socket) throws IOException {
    this.socket = socket;

    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
  }

  public RequestHeader readRequest() throws IOException {
    return RequestHeader.parse(in);
  }

  public void writeResponse(RequestHeader r) throws IOException {
    new ResponseHeader(r).writeTo(out);
  }
}
