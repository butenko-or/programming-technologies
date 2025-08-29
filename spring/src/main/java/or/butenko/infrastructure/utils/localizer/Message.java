package or.butenko.infrastructure.utils.localizer;

import lombok.Getter;

@Getter
public final class Message {

  private final String russian;

  private Message(final String russian) {
    this.russian = russian;
  }

  public static Message of(final String russianMessage) {
    return new Message(russianMessage);
  }
}
