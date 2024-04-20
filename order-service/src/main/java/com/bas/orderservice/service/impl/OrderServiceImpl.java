package com.bas.orderservice.service.impl;

import com.bas.orderservice.dto.OrderDTO;
import com.bas.orderservice.dto.ProductDTO;
import com.bas.orderservice.entity.Order;
import com.bas.orderservice.entity.Status;
import com.bas.orderservice.exception.ProductNotFound;
import com.bas.orderservice.feign.ProductFeign;
import com.bas.orderservice.helper.OrderMappingHelper;
import com.bas.orderservice.repository.OrderRepository;
import com.bas.orderservice.service.OrderService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    //private final ProductFeign productFeign;

    public Long bookOrder(OrderDTO orderDTO) throws ProductNotFound {
        try {
            ProductFeign productFeign = null;
            ResponseEntity<ProductDTO> productDTOResponseEntity=productFeign.findProductByName(orderDTO.getProductDto().getProductTitle());
            Order order=Order.builder()
                   // .productName(orderDTO.getProductDto().getProductTitle())
                    .status(Status.BOOKED)
                    .build();
            orderRepository.save(order);
            return order.getOrderId();
        }catch (FeignException ex){
            throw new ProductNotFound("Product is not there in the inventory");
        }
    }
    public String cancelOrder(Long orderId){
        Optional<Order> order=orderRepository.findById(orderId);
        order.get().setStatus(Status.CANCELLED);
        orderRepository.save(order.get());
        return "Order cancelled successfully";
    }
    public List<Order> viewOrders(List<Long>orderIds){
        return orderIds.stream().map(val->orderRepository.findById(val).get()).collect(Collectors.toList());
    }
    public List<Order>viewOrderByStatus(List<Long> orderIds, String status){
        return orderIds.stream().map(val->orderRepository.findById(val).get()).filter(val->val.getStatus().toString().equals(status)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findAll() {

        return this.orderRepository.findAll()
                .stream()
                .map(OrderMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public OrderDTO findById(Long orderId) {

        return this.orderRepository.findById(orderId)
                .map(OrderMappingHelper::map)
                .orElseThrow(() -> new ProductNotFound(String
                        .format("Order with id: %d not found", orderId)));
    }

    @Override
    public OrderDTO save(OrderDTO orderDto) {

        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(orderDto)));
    }

    @Override
    public OrderDTO update(OrderDTO orderDto) {

        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(orderDto)));
    }

    @Override
    public OrderDTO update(Long orderId, OrderDTO orderDto) {

        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(this.findById(orderId))));
    }

    @Override
    public void deleteById(Long orderId) {
        this.orderRepository.delete(OrderMappingHelper.map(this.findById(orderId)));
    }

}
