package hello.toy.todoapp.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    public OpenAPI openAPI() {
        Info info = new Info()
            .title("todo list side project API Document")
            .version("v0.0.1")
            .description("todo list side project의 API 명세서입니다.");
        return new OpenAPI()
            .components(new Components())
            .info(info);
    }
}
