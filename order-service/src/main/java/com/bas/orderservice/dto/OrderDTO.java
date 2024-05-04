package com.bas.orderservice.dto;

import com.bas.orderservice.constant.AppConstant;
import com.bas.orderservice.entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long orderId;


    @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime orderDate;

    private String orderDesc;
    private Double orderPrice;

    private Integer productId;
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonProperty("product")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductDTO productDto;

    @JsonProperty("cart")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CartDto cartDto;
}
