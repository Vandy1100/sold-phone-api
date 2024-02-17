package com.shop.soldphonev2.api.model;

import com.shop.soldphonev2.api.brand.Brand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ModelMapper {
    @InsertProvider(type = ModelProvider.class, method = "insertModel")
    void insertModel(@Param("model") Model model);
    @SelectProvider(type = ModelProvider.class,method = "selectAllModel")
    @Results({
            @Result(column = "brand_id",property = "brand",one = @One(select = "selectBrandById"))
    })
    List<Model> selectAllModel();
//    @SelectProvider(type = ModelProvider.class,method = "selectModelUuid")
//    Brand selectModelUuid(String uuid);
//    @UpdateProvider(type = ModelProvider.class,method = "updateModel")
//    void updateModel(@Param("model") Model model, String uuid);
//
//    @DeleteProvider(type = ModelProvider.class,method = "deleteModel")
//    void deleteModel(String uuid);
    @Select("SELECT * FROM brands WHERE id = #{id}")
    Brand selectBrandById(int id);
}
