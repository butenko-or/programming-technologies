package or.butenko.domain.repository;

import java.util.UUID;
import or.butenko.domain.model.customer.Customer;

public interface CustomerRepository {

  Customer saveCustomer(Customer customer);

  Customer getCustomer(UUID uuid);

  Customer getCustomer(String name);

  Customer updateCustomer(Customer customer);

  void deleteCustomer(UUID uuid);
}
