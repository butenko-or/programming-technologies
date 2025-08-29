package or.butenko.domain.model.order;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import or.butenko.domain.model.car.Car;
import or.butenko.domain.model.customer.Customer;

@Data
@AllArgsConstructor
public class Order {

  private UUID uuid;
  private List<Car> products;
  private Customer customer;
}
