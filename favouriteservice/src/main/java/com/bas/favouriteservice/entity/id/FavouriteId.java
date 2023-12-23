package com.bas.favouriteservice.entity.id;

import com.bas.favouriteservice.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavouriteId implements Serializable {
     @Serial
     private static final long serialVersionUID = 1L;
     @NotNull
     private Integer userId;

     @NotNull
     private Integer productId;

     @NotNull
     @JsonSerialize(using = LocalDateTimeSerializer.class)
     @JsonDeserialize(using = LocalDateTimeDeserializer.class)
     @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
     @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
     private LocalDateTime likeDate;

 }

