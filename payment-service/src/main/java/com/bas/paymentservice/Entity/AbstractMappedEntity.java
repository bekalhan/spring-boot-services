package com.bas.paymentservice.Entity;

import com.bas.paymentservice.Constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@EntityListeners(AuditingEntityListener.class)
abstract public class AbstractMappedEntity {
    @CreatedDate
    @Column(name = "created_at" , updatable = false)
    //@JsonFormat(pattern = Constants.LOCAL_DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    //@JsonFormat(pattern = Constants.LOCAL_DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;
}
