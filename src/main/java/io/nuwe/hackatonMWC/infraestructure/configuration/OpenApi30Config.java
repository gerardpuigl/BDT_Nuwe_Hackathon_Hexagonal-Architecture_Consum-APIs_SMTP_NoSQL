package io.nuwe.hackatonMWC.infraestructure.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;



@Configuration
@OpenAPIDefinition(info = @Info(title = "API_BDT_Hackathon_MWC", 
	description = "An API that consumes other APIs and extract the necessary information to be served to the front-end. User CRUD, JWT security and email notification are also implemented."))
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
