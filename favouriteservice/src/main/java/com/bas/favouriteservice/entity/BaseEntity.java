package com.bas.favouriteservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public class BaseEntity {
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at")
    private Instant updatedAt;
}
