package kafka.protocol;

import java.io.DataOutputStream;
import java.io.IOException;

public record ResponseHeader(RequestHeader header) {
  private static final int MAX_SUPPORTED_API_VERSION = 4;

  private static boolean isSupported(short version) {
    return version >= 0 && version <= MAX_SUPPORTED_API_VERSION;
  }

  public void writeTo(DataOutputStream out) throws IOException {
    out.writeInt(0); // message_size
    out.writeInt(header.correlationId()); // correlation_id
    out.writeShort(
        isSupported(header.requestApiVersion())
            ? ErrorCode.NONE.getValue()
            : ErrorCode.UNSUPPORTED_VERSION.getValue()); // error_code
  }
}
