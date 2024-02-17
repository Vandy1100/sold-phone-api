package com.shop.soldphonev2.api.payments;

import com.shop.soldphonev2.api.method.Method;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
import com.shop.soldphonev2.api.payments.web.PaymentDto;
import com.shop.soldphonev2.api.payments.web.PaymentResponseDto;
import com.shop.soldphonev2.base.BaseResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService{
    private final PaymentMapStruct paymentMapStruct;
    private final PaymentMapper paymentMapper;
    @Override
    public BaseResponseMessage create(PaymentDto paymentDto) {
        try {
            String uuid = UUID.randomUUID().toString();
            LocalDateTime date = LocalDateTime.now();
            Payment payment = paymentMapStruct.create(paymentDto);
            payment.setUuid(uuid);
            payment.setPaymentDate(date);
            payment.setMethod(new Method().setId(paymentDto.methodId()));
            paymentMapper.insertPayment(payment);
            return new BaseResponseMessage()
                    .setMessage("Payment has been created successfully.")
                    .setData(paymentDto)
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponseMessage()
                    .setMessage("Exception occurred!!")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage<List<PaymentResponseDto>> selectAll() {
        try {
            List<Payment> paymentList = paymentMapper.selectAll();
            List<PaymentResponseDto> paymentResponseDtoList = paymentMapStruct.selectAll(paymentList);
            if(!ObjectUtils.isEmpty(paymentResponseDtoList)){
                return new BaseResponseMessage<List<PaymentResponseDto>>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(paymentResponseDtoList)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved all payments")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<List<PaymentResponseDto>>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select payment!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponseMessage<List<PaymentResponseDto>>().setMessage("Exception occurred!!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);

        }
    }

    @Override
    public BaseResponseMessage<PaymentResponseDto> selectUuid(String uuid) {
        try {
            Payment payment = paymentMapper.selectUuid(uuid);
            PaymentResponseDto paymentResponseDto = paymentMapStruct.selectUuid(payment);
            if(!ObjectUtils.isEmpty(paymentResponseDto)){
                return new BaseResponseMessage<PaymentResponseDto>().setCode(String.valueOf(HttpStatus.OK.value()))
                        .setData(paymentResponseDto)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Successfully retrieved payment")
                        .setStatus(true);
            }else {
                return new BaseResponseMessage<PaymentResponseDto>().setStatus(false)
                        .setTimestamp(LocalDateTime.now())
                        .setMessage("Failed to select payment!!!!")
                        .setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            return new BaseResponseMessage<PaymentResponseDto>().setMessage("Exception occurred!!")
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setTimestamp(LocalDateTime.now())
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage update(PaymentDto paymentDto, String uuid) {
        try {
            Payment payment = paymentMapStruct.update(paymentDto);
            paymentMapper.update(payment,uuid);
            return new BaseResponseMessage()
                    .setMessage("Payment has been updated successfully.")
                    .setData(paymentDto)
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponseMessage()
                    .setMessage("Exception occurred!!")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false);
        }
    }

    @Override
    public BaseResponseMessage delete(String uuid) {
        try {
            paymentMapper.delete(uuid);
            return new BaseResponseMessage()
                    .setMessage("Payment has been deleted successfully.")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.OK.value()))
                    .setStatus(true);
        } catch (Exception e) {
            return new BaseResponseMessage()
                    .setMessage("Exception occurred!!")
                    .setTimestamp(LocalDateTime.now())
                    .setCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()))
                    .setStatus(false);
        }
    }
}
