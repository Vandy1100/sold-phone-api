package com.shop.soldphonev2.api.brand;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Brand {
    private int id;
    private String uuid;
    private String brand;
    private String description;
    private Boolean isDeleted;
}
