package com.bas.paymentservice.Exception.Payload;

import com.bas.paymentservice.Constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class ExceptionMsg {

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.ZONED_DATE_TIME_FORMAT)
    private final ZonedDateTime timestamp;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Throwable throwable;
    private final HttpStatus httpStatus;
    private final String msg;

}