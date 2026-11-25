package kafka.protocol;

import java.io.DataOutputStream;
import java.io.IOException;

public record ResponseHeader(int correlationId) {

  public void writeTo(DataOutputStream out) throws IOException {
    out.writeInt(0); // message_size
    out.writeInt(correlationId);
  }
}
