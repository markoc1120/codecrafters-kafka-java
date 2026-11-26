package kafka.protocol;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResponseHeaderTest {

  static Stream<Arguments> cases() {
    return Stream.of(Arguments.of((short) 0, 0, 0), Arguments.of((short) 999, 42, 35));
  }

  @ParameterizedTest(name = "api version {0}")
  @MethodSource("cases")
  void writesCorrelationIdAndVersionDependentErrorCode(
      short requestApiVersion, int correlationId, int errorCode) throws IOException {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);
    RequestHeader header = new RequestHeader((short) 0, requestApiVersion, correlationId);
    new ResponseHeader(header).writeTo(out);

    byte[] expected = new byte[] {0, 0, 0, 0, 0, 0, 0, (byte) correlationId, 0, (byte) errorCode};

    assertArrayEquals(expected, buf.toByteArray());
  }

  @Test
  void writesMultiByteCorrelationId() throws IOException {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);
    RequestHeader header = new RequestHeader((short) 0, (short) 0, 1333056139);
    new ResponseHeader(header).writeTo(out);

    byte[] expected = new byte[] {0, 0, 0, 0, 0x4f, 0x74, (byte) 0xd2, (byte) 0x8b, 0, 0};

    assertArrayEquals(expected, buf.toByteArray());
  }
}
