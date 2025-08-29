package or.butenko.domain.repository;

import java.util.UUID;
import or.butenko.domain.model.customer.Customer;
import or.butenko.domain.model.order.Order;

public interface OrderRepository {

  Order saveOrder(Order order);

  Order getOrder(UUID uuid);

  Order getOrder(Customer customer);

  Order updateOrder(Order order);

  void deleteOrder(UUID uuid);
}
