package com.sample.app.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.domain.core.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
