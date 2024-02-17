package com.shop.soldphonev2.api.model;


import com.shop.soldphonev2.api.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    private Integer id;
    private String uuid;
    private String model;
    private String description;
    private Brand brand;
    private String operatingSystem;
    private String cellularStorageCapacity;
    private String connectivityTechnology;
    private String color;
    private String screenSize;
    private String wirelessNetworkTechnology;
}
