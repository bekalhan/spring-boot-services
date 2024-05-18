package com.bas.paymentservice.controller;

import com.bas.paymentservice.request.PaymentRequest;
import com.bas.paymentservice.response.PaymentResponse;
import com.bas.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment/")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<List<PaymentResponse>> getAllPayments(){
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("getPayment/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("paymentId") Long paymentId){
        return new ResponseEntity<>(paymentService.findById(paymentId), HttpStatus.OK);
    }

    @GetMapping("getPaymentByUserId/{userId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(paymentService.findPaymentByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("createPayment")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.save(paymentRequest), HttpStatus.CREATED);
    }

    @PutMapping("updatePayment/{paymentId}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable("paymentId") Long paymentId, @RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.update(paymentRequest, paymentId), HttpStatus.OK);
    }
    @DeleteMapping("deletePayment/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable("paymentId") Long paymentId){
        paymentService.deleteById(paymentId);
        return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
    }

}
