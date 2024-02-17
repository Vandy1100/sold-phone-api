package com.shop.soldphonev2.api.payments.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record PaymentResponseDto(
        Integer id,
        LocalDateTime paymentDate,
        String uuid,
        Integer orderId,
        String paymentAccountName,
        Float totalAmount,
        String paymentAccountNumber,
        Integer notedNumber,
        Integer methodId,
        String phoneNumber,
        String imageReference,
        Boolean deleted
) {
}
