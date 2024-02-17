package com.shop.soldphonev2.api.auth;

import com.shop.soldphonev2.api.auth.web.RegisterDto;
import com.shop.soldphonev2.api.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T11:39:46+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class AuthMapStructImpl implements AuthMapStruct {

    @Override
    public User created(RegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( registerDto.username() );
        user.setPassword( registerDto.password() );
        user.setEmail( registerDto.email() );

        return user;
    }
}
