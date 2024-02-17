package com.shop.soldphonev2.api.method;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Method {
    private Integer id;
    private String uuid;
    private String methodAccountNumber;
    private String methodType;
    private String methodName;
    private String description;
    private Boolean isDeleted;
    private String methodLink;
}
