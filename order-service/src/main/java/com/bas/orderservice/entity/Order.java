package com.bas.orderservice.entity;

import com.bas.orderservice.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true, exclude = {"cart"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false, updatable = false)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private Status status;


    @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
    @Column(name = "order_date")
    @CreatedDate
    private LocalDateTime orderDate;

    @Column(name = "order_desc")
    private String orderDesc;

    @Column(name = "order_fee", columnDefinition = "decimal")
    private Double orderPrice;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "QUANTITY")
    private long quantity;


//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        private long id;
//
//        @Column(name = "PRODUCT_ID")
//        private long productId;
//
//        @Column(name = "QUANTITY")
//        private long quantity;
//
//        @Column(name = "ORDER_DATE")
//        private Instant orderDate;
//
//       @Enumerated(EnumType.STRING)
//        private Status status;
//
//        @Column(name = "TOTAL_AMOUNT")
//        private double totalPrice;

}
