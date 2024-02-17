package com.shop.soldphonev2.api.payments.web;

public record PaymentDto(
        Integer orderId,
        String paymentAccountName,
        Float totalAmount,
        String paymentAccountNumber,
        Integer notedNumber,
        Integer methodId,
        String phoneNumber,
        String imageReference

) {
}
