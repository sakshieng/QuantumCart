package com.orderservice.orderservice.repository;

import com.orderservice.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositorty extends JpaRepository<Order,Long> {
}
