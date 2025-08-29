package or.butenko.infrastructure.locale.impl;

import lombok.NoArgsConstructor;
import or.butenko.infrastructure.locale.MessageLocalizer;

@NoArgsConstructor
public class EnglishMessageLocalizerImpl implements MessageLocalizer {

  @Override
  public String returnLocalizedMessage(final String message) {
    return message;
  }
}
