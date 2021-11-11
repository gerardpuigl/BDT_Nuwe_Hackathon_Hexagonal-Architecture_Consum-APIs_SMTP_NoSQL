package io.nuwe.hackatonMWC.infraestructure.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;



@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    flows = @OAuthFlows(authorizationCode = @OAuthFlow(
    			tokenUrl = "/login")),
    scheme = "bearer"
)
public class OpenApi30Config {

}

//@Configuration
//@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
//@SecurityScheme(
//  name = "basicAuth",
//  type = SecuritySchemeType.HTTP,
//  scheme = "basic"
//)
//public class OpenApi30Config {
//
//}