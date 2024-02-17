package com.shop.soldphonev2.api.payments;

import com.shop.soldphonev2.api.method.Method;
import com.shop.soldphonev2.api.method.MethodProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaymentMapper {
    @InsertProvider(PaymentProvider.class)
    void insertPayment(@Param("payment") Payment payment);
    @SelectProvider(PaymentProvider.class)
    @Results({
            @Result(column = "payment_id",property = "id"),
            @Result(column = "order_id",property = "orderId"),
            @Result(column = "payment_date",property = "paymentDate"),
            @Result(column = "payment_account_names",property = "paymentAccountName"),
            @Result(column = "total_amount",property = "totalAmount"),
            @Result(column = "is_deleted",property = "deleted"),
            @Result(column = "payment_account_numbers",property = "paymentAccountNumber"),
            @Result(column = "noted_numbers",property = "notedNumber"),
//            @Result(column = "method_id",property = "method"),
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "image_reference",property = "imageReference"),
    })
    List<Payment> selectAll();
    @SelectProvider(PaymentProvider.class)
    @Results({
            @Result(column = "payment_id",property = "id"),
            @Result(column = "order_id",property = "orderId"),
            @Result(column = "payment_date",property = "paymentDate"),
            @Result(column = "payment_account_names",property = "paymentAccountName"),
            @Result(column = "total_amount",property = "totalAmount"),
            @Result(column = "is_deleted",property = "deleted"),
            @Result(column = "payment_account_numbers",property = "paymentAccountNumber"),
            @Result(column = "noted_numbers",property = "notedNumber"),
//            @Result(column = "method_id",property = "method"),
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "image_reference",property = "imageReference"),
    })
    Payment selectUuid(String uuid);
    @UpdateProvider(PaymentProvider.class)
    void update(@Param("payment") Payment payment, String uuid);

    @DeleteProvider(PaymentProvider.class)
    void delete(String uuid);
}
