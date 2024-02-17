package com.shop.soldphonev2.api.reviews;

import com.shop.soldphonev2.api.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Integer id;
    private Integer userId;
    private Product product;
    private Float rating;
    private String comments;
    private Date reviewDate;
    private String uuid;
    private Boolean isDeleted;
}
