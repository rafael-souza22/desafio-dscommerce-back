package com.mdorici.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdorici.dscommerce.entities.OrderItem;
import com.mdorici.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository <OrderItem, OrderItemPK> {

}
