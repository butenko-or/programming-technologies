package or.butenko.infrastructure.repository.simple;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.butenko.domain.model.customer.Customer;
import or.butenko.domain.model.order.Order;
import or.butenko.domain.repository.OrderRepository;

@Slf4j
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

  private final List<Order> orders;

  @Override
  public Order saveOrder(final Order order) {
    order.setUuid(UUID.randomUUID());
    this.orders.add(order);
    return order;
  }

  @Override
  public Order getOrder(final UUID uuid) {
    return this.orders.stream()
        .filter(o -> o.getUuid().equals(uuid))
        .findFirst()
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public Order getOrder(final Customer customer) {
    final Order order = this.orders.stream()
        .filter(o -> o.getCustomer().equals(customer))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    log.info("Was returned {}", order);
    return order;
  }

  @Override
  public Order updateOrder(final Order order) {
    final Order outdatedOrder = this.orders.stream()
        .filter(c -> c.getUuid().equals(order.getUuid()))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    this.orders.remove(outdatedOrder);
    this.orders.add(order);
    return order;
  }

  @Override
  public void deleteOrder(final UUID uuid) {
    final Order order = this.orders.stream()
        .filter(c -> c.getUuid().equals(uuid))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    this.orders.remove(order);
  }
}
