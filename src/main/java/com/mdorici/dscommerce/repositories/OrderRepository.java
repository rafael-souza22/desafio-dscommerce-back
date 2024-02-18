package com.mdorici.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdorici.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository <Order, Long> {

}
