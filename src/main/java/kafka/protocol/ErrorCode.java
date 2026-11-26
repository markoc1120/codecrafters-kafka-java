package kafka.protocol;

import lombok.Getter;

@Getter
public enum ErrorCode {
  NONE(0),
  UNSUPPORTED_VERSION(35);

  private final short value;

  ErrorCode(int value) {
    this.value = (short) value;
  }
}
