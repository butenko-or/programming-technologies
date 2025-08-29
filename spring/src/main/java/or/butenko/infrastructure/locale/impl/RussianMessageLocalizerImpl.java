package or.butenko.infrastructure.locale.impl;

import or.butenko.infrastructure.locale.MessageLocalizer;
import or.butenko.infrastructure.utils.localizer.LocalizerConstant;

public class RussianMessageLocalizerImpl implements MessageLocalizer {

  @Override
  public String returnLocalizedMessage(final String message) {
    return LocalizerConstant.LOCALIZE_MAP.get(message).getRussian();
  }
}
