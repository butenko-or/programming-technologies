package or.butenko.domain.model.customer;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

  private UUID uuid;
  private String name;
  private String phoneNumber;
  private Integer discount;
}
