package com.bas.productservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"category"})
@Data
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, updatable = false)
    private Long productId;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private Integer quantity;
    //features object
// renk marka hafiza gibi ozellliklerin eklenmesi gerekiyo
    //ekran boyutu
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
}
