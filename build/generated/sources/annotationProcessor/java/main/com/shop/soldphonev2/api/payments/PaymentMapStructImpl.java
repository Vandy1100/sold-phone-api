package com.shop.soldphonev2.api.payments;

import com.shop.soldphonev2.api.payments.web.PaymentDto;
import com.shop.soldphonev2.api.payments.web.PaymentResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-03T08:49:22+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class PaymentMapStructImpl implements PaymentMapStruct {

    @Override
    public Payment create(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setOrderId( paymentDto.orderId() );
        payment.setPaymentAccountName( paymentDto.paymentAccountName() );
        payment.setTotalAmount( paymentDto.totalAmount() );
        payment.setPaymentAccountNumber( paymentDto.paymentAccountNumber() );
        payment.setNotedNumber( paymentDto.notedNumber() );
        payment.setPhoneNumber( paymentDto.phoneNumber() );
        payment.setImageReference( paymentDto.imageReference() );

        return payment;
    }

    @Override
    public List<PaymentResponseDto> selectAll(List<Payment> paymentList) {
        if ( paymentList == null ) {
            return null;
        }

        List<PaymentResponseDto> list = new ArrayList<PaymentResponseDto>( paymentList.size() );
        for ( Payment payment : paymentList ) {
            list.add( selectUuid( payment ) );
        }

        return list;
    }

    @Override
    public PaymentResponseDto selectUuid(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        Integer id = null;
        LocalDateTime paymentDate = null;
        String uuid = null;
        Integer orderId = null;
        String paymentAccountName = null;
        Float totalAmount = null;
        String paymentAccountNumber = null;
        Integer notedNumber = null;
        String phoneNumber = null;
        String imageReference = null;
        Boolean deleted = null;

        id = payment.getId();
        paymentDate = payment.getPaymentDate();
        uuid = payment.getUuid();
        orderId = payment.getOrderId();
        paymentAccountName = payment.getPaymentAccountName();
        totalAmount = payment.getTotalAmount();
        paymentAccountNumber = payment.getPaymentAccountNumber();
        notedNumber = payment.getNotedNumber();
        phoneNumber = payment.getPhoneNumber();
        imageReference = payment.getImageReference();
        deleted = payment.getDeleted();

        Integer methodId = null;

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto( id, paymentDate, uuid, orderId, paymentAccountName, totalAmount, paymentAccountNumber, notedNumber, methodId, phoneNumber, imageReference, deleted );

        return paymentResponseDto;
    }

    @Override
    public Payment update(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setOrderId( paymentDto.orderId() );
        payment.setPaymentAccountName( paymentDto.paymentAccountName() );
        payment.setTotalAmount( paymentDto.totalAmount() );
        payment.setPaymentAccountNumber( paymentDto.paymentAccountNumber() );
        payment.setNotedNumber( paymentDto.notedNumber() );
        payment.setPhoneNumber( paymentDto.phoneNumber() );
        payment.setImageReference( paymentDto.imageReference() );

        return payment;
    }
}
