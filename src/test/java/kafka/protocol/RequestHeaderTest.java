package kafka.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class RequestHeaderTest {

  @Test
  void readsRequestHeader() throws IOException {
    byte[] header = {
      0x00, 0x00, 0x00, 0x23,
      0x00, 0x12, 0x00, 0x04,
      0x6f, 0x7f, (byte) 0xc6, 0x61,
    };

    ByteArrayInputStream buf = new ByteArrayInputStream(header);
    DataInputStream in = new DataInputStream(buf);

    RequestHeader actual = RequestHeader.parse(in);
    assertEquals(new RequestHeader((short) 0x12, (short) 0x04, 0x6f7fc661), actual);
  }
}
