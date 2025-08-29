package or.butenko.infrastructure.repository.simple;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.butenko.domain.model.customer.Customer;
import or.butenko.domain.repository.CustomerRepository;

@Slf4j
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  private final List<Customer> customers;

  @Override
  public Customer saveCustomer(final Customer customer) {
    customer.setUuid(UUID.randomUUID());
    this.customers.add(customer);
    return customer;
  }

  @Override
  public Customer getCustomer(final UUID uuid) {
    return this.customers.stream()
        .filter(c -> c.getUuid().equals(uuid))
        .findFirst()
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public Customer getCustomer(final String name) {
    final Customer customer = this.customers.stream()
        .filter(c -> c.getName().equals(name))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    log.info("Was returned {}", customer);
    return customer;
  }

  @Override
  public Customer updateCustomer(final Customer customer) {
    final Customer outdatedCustomer = this.customers.stream()
        .filter(c -> c.getUuid().equals(customer.getUuid()))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    this.customers.remove(outdatedCustomer);
    this.customers.add(customer);
    return customer;
  }

  @Override
  public void deleteCustomer(final UUID uuid) {
    final Customer customer = this.customers.stream()
        .filter(c -> c.getUuid().equals(uuid))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    this.customers.remove(customer);
  }
}
