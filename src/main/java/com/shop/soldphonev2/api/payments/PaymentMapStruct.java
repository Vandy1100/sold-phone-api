package com.shop.soldphonev2.api.payments;

import com.shop.soldphonev2.api.method.Method;
import com.shop.soldphonev2.api.method.web.MethodDto;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
import com.shop.soldphonev2.api.payments.web.PaymentDto;
import com.shop.soldphonev2.api.payments.web.PaymentResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapStruct {
    Payment create(PaymentDto paymentDto);
    List<PaymentResponseDto> selectAll(List<Payment> paymentList);
    PaymentResponseDto selectUuid(Payment payment);
    Payment update(PaymentDto paymentDto);
}
