package com.shop.soldphonev2.api.report;

import com.shop.soldphonev2.api.report.web.ProductDto;
import com.shop.soldphonev2.api.report.web.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T15:42:44+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class GeneratePdfMapStructImpl implements GeneratePdfMapStruct {

    @Override
    public List<ProductReport> create(List<ProductDto> productDtos) {
        if ( productDtos == null ) {
            return null;
        }

        List<ProductReport> list = new ArrayList<ProductReport>( productDtos.size() );
        for ( ProductDto productDto : productDtos ) {
            list.add( productDtoToProductReport( productDto ) );
        }

        return list;
    }

    @Override
    public ProductReport user(UserDto userDtos) {
        if ( userDtos == null ) {
            return null;
        }

        ProductReport productReport = new ProductReport();

        productReport.setName( userDtos.name() );
        productReport.setPhoneNumber( userDtos.phoneNumber() );

        return productReport;
    }

    protected ProductReport productDtoToProductReport(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductReport productReport = new ProductReport();

        productReport.setProductName( productDto.productName() );
        productReport.setQuantity( productDto.quantity() );
        productReport.setPricePerPiece( productDto.pricePerPiece() );

        return productReport;
    }
}
