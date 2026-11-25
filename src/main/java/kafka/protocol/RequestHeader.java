package kafka.protocol;

import java.io.DataInputStream;
import java.io.IOException;

public record RequestHeader(short requestApiKey, short requestApiVersion, int correlationId) {

  public static RequestHeader parse(DataInputStream in) throws IOException {
    in.readInt(); // messageSize
    return new RequestHeader(in.readShort(), in.readShort(), in.readInt());
  }
}
