package com.bas.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"category"})
@Data
@Builder
@Entity
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

    private String brand;
    private String color;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
    private Long subCategoryId;
    @ElementCollection
    @CollectionTable(name = "dynamic_fields", joinColumns = @JoinColumn(name = "entity_id"))
    @MapKeyColumn(name = "field_key")
    @Column(name = "field_value")
    private Map<String, String> dynamicFields;

}
