package com.shop.soldphonev2.api.payments;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.soldphonev2.api.method.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    private Integer id;
    private Integer orderId;
    private LocalDateTime paymentDate;
    private String paymentAccountName;
    private Float totalAmount;
    private String uuid;
    private Boolean deleted;
    private String paymentAccountNumber;
    private Integer notedNumber;
    private Method method;
    private String phoneNumber;
    private String imageReference;
}
