package com.bas.orderservice.service.impl;

import com.bas.orderservice.dto.OrderDto;
import com.bas.orderservice.dto.OrderResponse;
import com.bas.orderservice.entity.Order;
import com.bas.orderservice.exception.OrderNotFoundException;
import com.bas.orderservice.helper.OrderMappingHelper;
import com.bas.orderservice.repository.OrderRepository;
import com.bas.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;




    @Override
    public OrderDto findById(Integer orderId) {

        return this.orderRepository.findById(orderId)
                .map(OrderMappingHelper::map)
                .orElseThrow(() -> new OrderNotFoundException(String
                        .format("Order with id: %d not found", orderId)));
    }

    @Override
    public List<OrderDto> findAll() {




       return this.orderRepository.findAll()
                .stream()
                .map(OrderMappingHelper::map)
                //.map(a -> OrderMappingHelper.map(a))
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }
    public OrderResponse getAllProductsWithPageableAndSorting(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, sort);

        Page<Order> orders = orderRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        // get content for page object
        List<Order> listOfPosts = orders.getContent();

        List<OrderDto> content= listOfPosts.stream().map(OrderMappingHelper::map).collect(Collectors.toList());

        return OrderMappingHelper.mapProductResponse(content,orders);

    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(orderDto)));
    }
    @Override
    public OrderDto update(final OrderDto orderDto) {

        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(orderDto)));
    }

    @Override
    public OrderDto update(final Integer orderId,final OrderDto orderDto) {
        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(this.findById(orderDto.getOrderId()))));
    }

    @Override
    public void deleteById(Integer orderId) {
        this.orderRepository.delete(OrderMappingHelper.map(this.findById(orderId)));
    }

}
