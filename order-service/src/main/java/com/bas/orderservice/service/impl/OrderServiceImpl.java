package com.bas.orderservice.service.impl;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.exception.OrderNotExist;
import com.bas.orderservice.helper.OrderMappingHelper;
import com.bas.orderservice.repository.OrderRepository;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.OrderResponse;
import com.bas.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    //private final ProductFeign productFeign;
    private final RestTemplate restTemplate;

    public Long bookOrder(OrderRequest orderRequest) throws OrderNotExist {
        return null;
//        try {
//
//            Order order=Order.builder()
//                    .orderPrice(orderRequest.getOrderPrice())
//                    .productId(orderRequest.getProductId())
//                    .status(Status.SUBMITTED)
//                    .quantity(orderRequest.getQuantity())
//                    //.cart(orderRequest.getCartId())
//                    .build();
//            orderRepository.save(order);
//            return order.getOrderId();
//        }catch (FeignException ex){
//            throw new ProductNotExist("Product is not there in the inventory");
//        }
    }
    public String cancelOrder(Long orderId){
        return null;
//        Optional<Order> order=orderRepository.findById(orderId);
//        order.get().setStatus(Status.CANCELLED);
//        orderRepository.save(order.get());
//        return "Order cancelled successfully";
    }
    public List<Order> viewOrders(List<Long>orderIds){
        return null;//orderIds.stream().map(val->orderRepository.findById(val).get()).collect(Collectors.toList());
    }
    public List<Order>viewOrderByStatus(List<Long> orderIds, String status){
        return null;//orderIds.stream().map(val->orderRepository.findById(val).get()).filter(val->val.getStatus().toString().equals(status)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> findAll() {

        return null;
//        this.orderRepository.findAll()
//                .stream()
//                .map(OrderMappingHelper::map)
//                .distinct()
//                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public OrderResponse findById(Long orderId) {
        return null;
//        return this.orderRepository.findById(orderId)
//                .map(OrderMappingHelper::map)
//                .orElseThrow(() -> new OrderNotExist(String
//                        .format("Order with id: %d not found", orderId)));
    }

    @Override
    public OrderRequest save(OrderRequest orderRequest) {
        return null;
//        Order order=Order.builder()
//                .orderPrice(orderRequest.getOrderPrice())
//                .productId(orderRequest.getProductId())
//                .status(Status.SUBMITTED)
//                .quantity(orderRequest.getQuantity())
//                //.cart(orderRequest.getCartId())
//                .build();
//        orderRepository.save(order);
//        return  orderRequest;//??Benden ne dönmemi istiyor ONA GÖRE DEĞİŞECEK!!1
    }

    @Override
    public OrderResponse update(OrderRequest orderRequest) {
return null;
        //return OrderMappingHelper.map(this.orderRepository
               // .save(OrderMappingHelper.map(orderDto)));
    }

    @Override
    public OrderResponse update(Long orderId, OrderRequest orderRequest) {
        return null;
        //return OrderMappingHelper.map(this.orderRepository
              //  .save(OrderMappingHelper.map(this.findById(orderId))));
    }

    @Override
    public void deleteById(Long orderId) {

        //this.orderRepository.delete(OrderMappingHelper.map(this.findById(orderId)));
    }

}
