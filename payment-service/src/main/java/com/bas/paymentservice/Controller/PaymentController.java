package com.bas.paymentservice.Controller;

import com.bas.paymentservice.Dto.PaymentDto;
import com.bas.paymentservice.Dto.response.DtoResponse;
import com.bas.paymentservice.Service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Slf4j
@RequiredArgsConstructor
@EnableJpaAuditing
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<DtoResponse<PaymentDto>> findAll() {
        return ResponseEntity.ok(new DtoResponse<>(this.paymentService.findAll()));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> findById(
            @PathVariable("paymentId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String paymentId) {
        return ResponseEntity.ok(this.paymentService.findById(Integer.parseInt(paymentId)));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final PaymentDto paymentDto) {
        return ResponseEntity.ok(this.paymentService.save(paymentDto));
    }

    @PutMapping
    public ResponseEntity<PaymentDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final PaymentDto paymentDto) {
        return ResponseEntity.ok(this.paymentService.update(paymentDto));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("paymentId") final String paymentId) {
        this.paymentService.deleteById(Integer.parseInt(paymentId));
        return ResponseEntity.ok(true);
    }
}
