package or.butenko.infrastructure.utils.localizer;

import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalizerConstant {

  public static final String APPLICATION_STARTED = "The application was started";
  public static final String SPRING_STARTED = "Spring was started";
  public static final String CREATED_SPRING_BEANS = "Created Spring beans: {}\n";

  public static final Map<String, Message> LOCALIZE_MAP = Map.ofEntries(
      Map.entry(APPLICATION_STARTED, Message.of("Приложение запущено")),
      Map.entry(SPRING_STARTED, Message.of("Spring запущен")),
      Map.entry(CREATED_SPRING_BEANS, Message.of("Созданы следующие Spring beans {}"))
  );
}
