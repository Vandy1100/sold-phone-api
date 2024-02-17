package com.shop.soldphonev2.api.payments;

import com.shop.soldphonev2.api.method.web.MethodDto;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
import com.shop.soldphonev2.api.payments.web.PaymentDto;
import com.shop.soldphonev2.api.payments.web.PaymentResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;

import java.util.List;

public interface PaymentService {
    BaseResponseMessage create(PaymentDto paymentDto);

    BaseResponseMessage<List<PaymentResponseDto>> selectAll();
    BaseResponseMessage<PaymentResponseDto> selectUuid(String uuid);
    BaseResponseMessage update(PaymentDto paymentDto,String uuid);
    BaseResponseMessage delete(String uuid);
}
