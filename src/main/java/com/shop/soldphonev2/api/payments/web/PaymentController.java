package com.shop.soldphonev2.api.payments.web;

import com.shop.soldphonev2.api.payments.PaymentService;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("")
    BaseResponseMessage<?> create(@RequestBody PaymentDto paymentDto){
        return paymentService.create(paymentDto);
    }
    @GetMapping("")
    BaseResponseMessage<?> selectAll(){
        return paymentService.selectAll();
    }
    @GetMapping("/{uuid}")
    BaseResponseMessage<?> selectUuid(@PathVariable String uuid){
        return paymentService.selectUuid(uuid);
    }
    @PutMapping("/{uuid}")
    BaseResponseMessage<?> update(@RequestBody PaymentDto paymentDto, @PathVariable String uuid){
        return paymentService.update(paymentDto,uuid);
    }
    @DeleteMapping("/{uuid}")
    BaseResponseMessage<?> delete(@PathVariable String uuid){
        return paymentService.delete(uuid);
    }
}
