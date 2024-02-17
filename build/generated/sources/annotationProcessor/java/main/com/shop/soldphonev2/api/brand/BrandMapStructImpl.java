package com.shop.soldphonev2.api.brand;

import com.shop.soldphonev2.api.brand.web.BrandDto;
import com.shop.soldphonev2.api.brand.web.BrandResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T11:39:46+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class BrandMapStructImpl implements BrandMapStruct {

    @Override
    public Brand create(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setBrand( brandDto.brand() );
        brand.setDescription( brandDto.description() );

        return brand;
    }

    @Override
    public List<BrandResponseDto> selectAll(List<Brand> brand) {
        if ( brand == null ) {
            return null;
        }

        List<BrandResponseDto> list = new ArrayList<BrandResponseDto>( brand.size() );
        for ( Brand brand1 : brand ) {
            list.add( selectBrandByUuid( brand1 ) );
        }

        return list;
    }

    @Override
    public BrandResponseDto selectBrandByUuid(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        int id = 0;
        String uuid = null;
        String brand1 = null;
        String description = null;

        id = brand.getId();
        uuid = brand.getUuid();
        brand1 = brand.getBrand();
        description = brand.getDescription();

        BrandResponseDto brandResponseDto = new BrandResponseDto( id, uuid, brand1, description );

        return brandResponseDto;
    }

    @Override
    public Brand update(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setBrand( brandDto.brand() );
        brand.setDescription( brandDto.description() );

        return brand;
    }
}
