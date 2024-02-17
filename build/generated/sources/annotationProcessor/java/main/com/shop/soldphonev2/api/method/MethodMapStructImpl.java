package com.shop.soldphonev2.api.method;

import com.shop.soldphonev2.api.method.web.MethodDto;
import com.shop.soldphonev2.api.method.web.MethodResponseDto;
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
public class MethodMapStructImpl implements MethodMapStruct {

    @Override
    public Method create(MethodDto methodDto) {
        if ( methodDto == null ) {
            return null;
        }

        Method method = new Method();

        method.setMethodAccountNumber( methodDto.methodAccountNumber() );
        method.setMethodType( methodDto.methodType() );
        method.setMethodName( methodDto.methodName() );
        method.setDescription( methodDto.description() );
        method.setMethodLink( methodDto.methodLink() );

        return method;
    }

    @Override
    public List<MethodResponseDto> selectAll(List<Method> methodList) {
        if ( methodList == null ) {
            return null;
        }

        List<MethodResponseDto> list = new ArrayList<MethodResponseDto>( methodList.size() );
        for ( Method method : methodList ) {
            list.add( selectMethodByUuid( method ) );
        }

        return list;
    }

    @Override
    public MethodResponseDto selectMethodByUuid(Method method) {
        if ( method == null ) {
            return null;
        }

        Integer id = null;
        String uuid = null;
        String methodAccountNumber = null;
        String methodType = null;
        String methodName = null;
        String description = null;
        String methodLink = null;
        Boolean isDeleted = null;

        id = method.getId();
        uuid = method.getUuid();
        methodAccountNumber = method.getMethodAccountNumber();
        methodType = method.getMethodType();
        methodName = method.getMethodName();
        description = method.getDescription();
        methodLink = method.getMethodLink();
        isDeleted = method.getIsDeleted();

        MethodResponseDto methodResponseDto = new MethodResponseDto( id, uuid, methodAccountNumber, methodType, methodName, description, methodLink, isDeleted );

        return methodResponseDto;
    }

    @Override
    public Method update(MethodDto methodDto) {
        if ( methodDto == null ) {
            return null;
        }

        Method method = new Method();

        method.setMethodAccountNumber( methodDto.methodAccountNumber() );
        method.setMethodType( methodDto.methodType() );
        method.setMethodName( methodDto.methodName() );
        method.setDescription( methodDto.description() );
        method.setMethodLink( methodDto.methodLink() );

        return method;
    }
}
