package com.shop.soldphonev2.api.brand;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BrandMapper {
    @InsertProvider(type = BrandProvider.class, method = "insertBrand")
    void insertBrand(@Param("brand") Brand brand);
    @SelectProvider(type = BrandProvider.class,method = "selectAllBrand")
    List<Brand> selectAllBrand();
    @SelectProvider(type = BrandProvider.class,method = "selectBrandUuid")
    Brand selectBrandUuid(String uuid);
    @UpdateProvider(type = BrandProvider.class,method = "updateBrand")
    void updateBrand(@Param("brand") Brand brand, String uuid);

    @DeleteProvider(type = BrandProvider.class,method = "deleteBrand")
    void deleteBrand(String uuid);
}
