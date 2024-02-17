package com.shop.soldphonev2.api.model;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Value;

public class ModelProvider {

    public String insertModel(){
        return new SQL(){{
            INSERT_INTO("models");
            VALUES("uuid","#{model.uuid}");
            VALUES("model","#{model.model}");
            VALUES("description","#{model.description}");
            VALUES("brand_id","#{model.brand.id}");
            VALUES("operating_system","#{model.operatingSystem}");
            VALUES("cellular_storage_capacity","#{model.cellularStorageCapacity}");
            VALUES("connectivity_technology","#{model.connectivityTechnology}");
            VALUES("color","#{model.color}");
            VALUES("screen_size","#{model.screenSize}");
            VALUES("wireless_network_technology","#{model.wirelessNetworkTechnology}");
        }}.toString();
    }
    public String selectAllModel(){
        return new SQL(){{
            SELECT("*");
            FROM("models");
        }}.toString();
    }
}
