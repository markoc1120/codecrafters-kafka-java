package kafka.protocol;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ResponseHeaderTest {

  @Test
  void writesResponseHeaderWithCorrelationIdSeven() throws IOException {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);

    int correlationId = 7;
    new ResponseHeader(correlationId).writeTo(out);
    byte[] expected = {
      0, 0, 0, 0, // message_size (any value for this stage)
      0, 0, 0, 7, // correlation_id: 7
    };

    assertArrayEquals(expected, buf.toByteArray());
  }
}
