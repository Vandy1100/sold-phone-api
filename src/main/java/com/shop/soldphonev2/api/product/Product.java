package com.shop.soldphonev2.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stockQuantity;
    private Integer manufacturerId;
    private Date releaseDate;
    private String imageUrl;
    private String uuid;
    private Boolean isDeleted;
    private Boolean isEnable;
    private Integer brandId;

    private String data;


}
