package com.bas.favouriteservice.dto;

import com.bas.favouriteservice.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteDto {
    @NotNull(message = "Field must not be NULL")
    private Integer userId;

    @NotNull(message = "Field must not be NULL")
    private Integer productId;

    @NotNull(message = "Field must not be NULL")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime likeDate;

    @JsonProperty("user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto userDto;

    @JsonProperty("product")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductDto productDto;
}