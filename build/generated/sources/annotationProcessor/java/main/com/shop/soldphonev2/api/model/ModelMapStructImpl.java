package com.shop.soldphonev2.api.model;

import com.shop.soldphonev2.api.brand.Brand;
import com.shop.soldphonev2.api.model.web.ModelDto;
import com.shop.soldphonev2.api.model.web.ModelResponseDto;
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
public class ModelMapStructImpl implements ModelMapStruct {

    @Override
    public Model created(ModelDto modelDto) {
        if ( modelDto == null ) {
            return null;
        }

        Model model = new Model();

        model.setModel( modelDto.model() );
        model.setDescription( modelDto.description() );
        model.setOperatingSystem( modelDto.operatingSystem() );
        model.setCellularStorageCapacity( modelDto.cellularStorageCapacity() );
        model.setConnectivityTechnology( modelDto.connectivityTechnology() );
        model.setColor( modelDto.color() );
        model.setScreenSize( modelDto.screenSize() );
        model.setWirelessNetworkTechnology( modelDto.wirelessNetworkTechnology() );

        return model;
    }

    @Override
    public List<ModelResponseDto> selects(List<Model> models) {
        if ( models == null ) {
            return null;
        }

        List<ModelResponseDto> list = new ArrayList<ModelResponseDto>( models.size() );
        for ( Model model : models ) {
            list.add( modelToModelResponseDto( model ) );
        }

        return list;
    }

    protected ModelResponseDto modelToModelResponseDto(Model model) {
        if ( model == null ) {
            return null;
        }

        int id = 0;
        String uuid = null;
        String model1 = null;
        String description = null;
        Brand brand = null;
        String operatingSystem = null;
        String cellularStorageCapacity = null;
        String connectivityTechnology = null;
        String color = null;
        String screenSize = null;
        String wirelessNetworkTechnology = null;

        if ( model.getId() != null ) {
            id = model.getId();
        }
        uuid = model.getUuid();
        model1 = model.getModel();
        description = model.getDescription();
        brand = model.getBrand();
        operatingSystem = model.getOperatingSystem();
        cellularStorageCapacity = model.getCellularStorageCapacity();
        connectivityTechnology = model.getConnectivityTechnology();
        color = model.getColor();
        screenSize = model.getScreenSize();
        wirelessNetworkTechnology = model.getWirelessNetworkTechnology();

        ModelResponseDto modelResponseDto = new ModelResponseDto( id, uuid, model1, description, brand, operatingSystem, cellularStorageCapacity, connectivityTechnology, color, screenSize, wirelessNetworkTechnology );

        return modelResponseDto;
    }
}
