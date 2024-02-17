package com.shop.soldphonev2.api.product.web;

import java.util.Date;

public record ProductDto (
         String name,
         String description,
         Float price,
         Integer stockQuantity,
         Integer manufacturerId,
         String imageUrl,
         Integer brandId,
         //set image phone list
         String frontImage,
         String backImage,
         String bodyImage,
         String cameraImage,
         String interfaceImage,
                 //set material phone
         String model,
         String operatingSystem,
         String cellularStorageCapacity,
         String connectivityTechnology,
         String color,
         String screenSize,
         String wirelessNetworkTechnology
){
}
