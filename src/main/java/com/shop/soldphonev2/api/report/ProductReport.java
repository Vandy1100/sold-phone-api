package com.shop.soldphonev2.api.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductReport {
     String productName;
     Integer quantity;
     Float pricePerPiece;
     String name;
     String phoneNumber;
}
