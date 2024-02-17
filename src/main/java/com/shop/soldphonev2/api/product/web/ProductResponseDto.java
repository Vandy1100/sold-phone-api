package com.shop.soldphonev2.api.product.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public record ProductResponseDto(
        Integer id,
        String name,
        String description,
        Float price,
        Integer stockQuantity,
        Integer manufacturerId,
        String imageUrl,
        String uuid,
        Date releaseDate,
        Boolean isEnable,
        Boolean isDeleted,
        Integer brandId,
        String data
) {

    public JsonNode getData() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;

        try {
            rootNode = mapper.readTree(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootNode;
    }
}
