package com.mehmetozkan.readingisgood.repository;

import com.mehmetozkan.readingisgood.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Customer getCustomerByOrderListId(Long id);

    Customer getCustomerByEmail(String email);
}
