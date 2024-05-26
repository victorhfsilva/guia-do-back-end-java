# Configurando Rotas no Spring Cloud Gateway

Configurar rotas em uma API Gateway usando o Spring Cloud Gateway através de arquivos `application.properties` ou `application.yml` é uma abordagem flexível e centralizada para definir como as requisições devem ser roteadas para diferentes microserviços. Vou mostrar como você pode fazer isso detalhadamente.

### Configuração de Rotas no `application.yml`

Usando o `application.yml` (ou `application.properties`), você pode definir rotas de forma declarativa. Aqui está como você pode configurar o arquivo `application.yml` para definir rotas:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: service1_route
          uri: lb://SERVICE1
          predicates:
            - Path=/service1/**
          filters:
            - StripPrefix=1

        - id: service2_route
          uri: lb://SERVICE2
          predicates:
            - Path=/service2/**
            - Method=GET
          filters:
            - AddRequestHeader=X-Request-Service2, Value2
```

**Explicação:**

- `id`: Identificador único para a rota. Útil para fins de logging e rastreamento.
- `uri`: O URI do serviço de destino. O prefixo `lb://` indica que o Spring Cloud Gateway usará o LoadBalancer para descobrir a instância do serviço.
- `predicates`: Condições que determinam quando esta rota deve ser usada. `Path=/service1/**` significa que qualquer requisição com o caminho iniciando por `/service1/` será roteada para `SERVICE1`.
- `filters`: Modificações ou verificações adicionais na requisição ou resposta. `StripPrefix=1` remove o primeiro segmento do caminho da requisição antes de encaminhá-la ao serviço de destino.

### Configuração de Rotas no `application.properties`

Se preferir usar o `application.properties`, a configuração será um pouco diferente devido à natureza linear do arquivo:

```properties
spring.cloud.gateway.routes[0].id=service1_route
spring.cloud.gateway.routes[0].uri=lb://SERVICE1
spring.cloud.gateway.routes[0].predicates[0]=Path=/service1/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[1].id=service2_route
spring.cloud.gateway.routes[1].uri=lb://SERVICE2
spring.cloud.gateway.routes[1].predicates[0]=Path=/service2/**
spring.cloud.gateway.routes[1].predicates[1]=Method=GET
spring.cloud.gateway.routes[1].filters[0]=AddRequestHeader=X-Request-Service2, Value2
```

### Configuração de Rotas utilizando Java Puro

Você vai criar uma classe de configuração no Spring que define as rotas do Gateway. Esta classe usará o `RouteLocatorBuilder` para construir as rotas:

```java
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("route_id1", r -> r.path("/service1/**")
                                      .filters(f -> f.addRequestHeader("Header-Name", "Header-Value"))
                                      .uri("http://localhost:8081"))
            .route("route_id2", r -> r.path("/service2/**")
                                      .filters(f -> f.stripPrefix(1))
                                      .uri("lb://SERVICE2"))
            .build();
    }
}
```

**Detalhes do código:**

- **@Configuration**: Anotação que define a classe como uma fonte de definições de beans do Spring.
- **@Bean**: Anotação que marca o método `customRouteLocator` para criação de um bean gerenciado pelo Spring.
- **RouteLocatorBuilder**: Utilizado para construir as definições de rotas.
- **path**: Define o padrão de caminho da URL que ativa a rota.
- **filters**: Aplica filtros à rota; por exemplo, `addRequestHeader` adiciona um cabeçalho à requisição.
- **uri**: Especifica o URI de destino; o prefixo `lb://` indica que o Spring Cloud LoadBalancer será usado para descoberta de serviço.

