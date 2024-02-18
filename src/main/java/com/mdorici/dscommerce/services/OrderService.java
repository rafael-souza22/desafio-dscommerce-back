package com.mdorici.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdorici.dscommerce.dto.OrderDTO;
import com.mdorici.dscommerce.dto.OrderItemDTO;
import com.mdorici.dscommerce.entities.Order;
import com.mdorici.dscommerce.entities.OrderItem;
import com.mdorici.dscommerce.entities.OrderStatus;
import com.mdorici.dscommerce.entities.Product;
import com.mdorici.dscommerce.entities.User;
import com.mdorici.dscommerce.repositories.OrderItemRepository;
import com.mdorici.dscommerce.repositories.OrderRepository;
import com.mdorici.dscommerce.repositories.ProductRepository;
import com.mdorici.dscommerce.services.exceptions.ResourceNotFindException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFindException("Recurso não encontrado!"));
		authService.validateSelfOrAdmin(order.getClient().getId());
		return new OrderDTO(order);
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.authenticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}
}
