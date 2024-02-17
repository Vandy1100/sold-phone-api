package com.shop.soldphonev2.api.payments;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class PaymentProvider implements ProviderMethodResolver {
    public String insertPayment(){
        return new SQL(){{
            INSERT_INTO("payments");
            VALUES("order_id","#{payment.orderId}");
            VALUES("payment_date","#{payment.paymentDate}");
            VALUES("payment_account_names","#{payment.paymentAccountName}");
            VALUES("total_amount","#{payment.totalAmount}");
            VALUES("uuid","#{payment.uuid}");
            VALUES("payment_account_numbers","#{payment.paymentAccountNumber}");
            VALUES("noted_numbers","#{payment.notedNumber}");
            VALUES("method_id","#{payment.method.id}");
            VALUES("phone_number","#{payment.phoneNumber}");
            VALUES("image_reference","#{payment.imageReference}");
        }}.toString();
    }
    public String selectAll(){
        return new SQL(){{
            SELECT("*");
            FROM("payments");
        }}.toString();
    }
    public String selectUuid(){
        return new SQL(){{
            SELECT("*");
            FROM("payments");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String update(){
        return new SQL(){{
            UPDATE("payments");
            SET("payment_account_names = #{payment.paymentAccountName}");
            SET("payment_account_numbers = #{payment.paymentAccountNumber}");
            SET("total_amount = #{payment.totalAmount}");
            SET("noted_numbers = #{payment.notedNumber}");
            SET("phone_number = #{payment.phoneNumber}");
            SET("image_reference = #{payment.imageReference}");
        }}.toString();
    }
    public String delete(){
        return new SQL(){{
            DELETE_FROM("payments");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
}
