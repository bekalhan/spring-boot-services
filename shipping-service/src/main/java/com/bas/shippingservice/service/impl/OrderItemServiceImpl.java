package com.bas.shippingservice.service.impl;

import com.bas.shippingservice.constant.AppConstant;
import com.bas.shippingservice.dto.OrderDto;
import com.bas.shippingservice.dto.OrderItemDto;
import com.bas.shippingservice.dto.ProductDto;
import com.bas.shippingservice.entity.id.OrderItemId;
import com.bas.shippingservice.exception.wrapper.OrderItemNotFoundException;
import com.bas.shippingservice.helper.OrderItemMappingHelper;
import com.bas.shippingservice.repository.OrderItemRepository;
import com.bas.shippingservice.service.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<OrderItemDto> findAll() {
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemMappingHelper::map)
                .map(o -> {
                    o.setProductDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .PRODUCT_SERVICE_API_URL + "/" + o.getProductDto().getProductId(), ProductDto.class));
                    o.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + o.getOrderDto().getOrderId(), OrderDto.class));
                    return o;
                })
                .distinct()
                .toList();
    }

    @Override
    public OrderItemDto findById(final OrderItemId orderItemId) {
        return this.orderItemRepository.findById(orderItemId)
                .map(OrderItemMappingHelper::map)
                .map(o -> {
                    o.setProductDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .PRODUCT_SERVICE_API_URL + "/" + o.getProductDto().getProductId(), ProductDto.class));
                    o.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + o.getOrderDto().getOrderId(), OrderDto.class));
                    return o;
                })
                .orElseThrow(() -> new OrderItemNotFoundException(String.format("OrderItem with id: %s not found", orderItemId)));
    }

    @Override
    public OrderItemDto save(final OrderItemDto orderItemDto) {
        return OrderItemMappingHelper.map(this.orderItemRepository
                .save(OrderItemMappingHelper.map(orderItemDto)));
    }

    @Override
    public OrderItemDto update(final OrderItemDto orderItemDto) {
        return OrderItemMappingHelper.map(this.orderItemRepository
                .save(OrderItemMappingHelper.map(orderItemDto)));
    }

    @Override
    public void deleteById(final OrderItemId orderItemId) {
        this.orderItemRepository.deleteById(orderItemId);
    }
}
