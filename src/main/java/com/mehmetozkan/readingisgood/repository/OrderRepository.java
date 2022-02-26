package com.mehmetozkan.readingisgood.repository;

import com.mehmetozkan.readingisgood.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByDateCreatedBetween(Date startDate, Date endDate);

    List<Order> findByCustomerId(Long id, Pageable pageable);

    List<Order> findByCustomerId(Long id);
}
