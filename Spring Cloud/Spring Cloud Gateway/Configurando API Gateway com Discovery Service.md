# Configurando uma API Gateway com Discovery Service

Criar um gateway integrado ao Eureka Discovery Service utilizando o Spring Cloud Gateway oferece uma maneira eficiente de rotear solicitações para vários microserviços com descoberta de serviços dinâmica. Aqui está um guia para configurar um Spring Cloud Gateway integrado ao Eureka.

### Adicionar Dependências

Para começar, você precisa adicionar as dependências necessárias ao seu projeto. Se estiver usando o Maven, adicione as seguintes dependências ao seu arquivo `build.gradle`:

```groovy
dependencies {
	implementation 'org.springframework.cloud:spring-cloud-starter-gateway'
	implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
}
dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
	}
}
```

### Configurar o `application.properties` ou `application.yml`

No arquivo `application.properties` ou `application.yml` do seu projeto gateway, adicione as seguintes propriedades para integrar com o Eureka e configurar o gateway:

```yaml
server.port=8080

spring.application.name=api-gateway
eureka.client.serviceUrl.defaultZone=http://localhost:8081/eureka/
eureka.instance.preferIpAddress=true

spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lowerCaseServiceId=true
```

Essas configurações habilitam a integração do Gateway com o Eureka e ativam o roteamento dinâmico baseado nos serviços registrados no Eureka.

### Habilitar o cliente Eureka no Spring Boot Application

Na sua classe principal do Spring Boot, certifique-se de que as anotações `@SpringBootApplication` e `@EnableEurekaClient` estão presentes para habilitar a funcionalidade de cliente Eureka:

```java
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

### Configurar Rotas Estáticas

Com a descoberta de serviços ativada, o Gateway pode rotear automaticamente para serviços registrados no Eureka. Se necessário, você pode configurar rotas estáticas ou adicionais no arquivo de configuração ou diretamente no código:

```java
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("service1", r -> r.path("/service1/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://SERVICE-NAME1"))
				.route("service2", r -> r.path("/service2/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://SERVICE-NAME2"))
				.build();
}
```

- **Identificador da Rota**: `"service1"` e `"service2"` são identificadores únicos para cada rota, facilitando a referência e o gerenciamento das rotas individualmente.
- **Função do `.path("/service1/**")` e `.path("/service2/**")`**: Define que estas rotas capturam quaisquer requisições cujos caminhos iniciem com `/service1/` e `/service2/`, respectivamente. O padrão `**` indica que qualquer caminho adicional é aceito após o prefixo.
- **Função do `.filters(f -> f.stripPrefix(1))`**: Este filtro modifica a requisição antes de encaminhá-la ao serviço de destino, removendo o primeiro segmento do caminho da URL. Por exemplo, uma requisição para `/service1/example` seria transformada em `/example` antes de ser encaminhada.
- **Função do `.uri("lb://SERVICE-NAME1")` e `.uri("lb://SERVICE-NAME2")`**: Indica o URI de destino para onde as requisições devem ser encaminhadas. O prefixo `lb://` sugere que o Spring Cloud Gateway utilizará o Spring Cloud LoadBalancer para resolver o nome do serviço e balancear a carga entre instâncias disponíveis do serviço. `SERVICE-NAME1` e `SERVICE-NAME2` devem corresponder aos nomes dos serviços registrados no Eureka.
- `SERVICE-NAME1` é o ID do seu serviço no servidor eureka. Por padrão, ele é o nome deste microsserviço em letras maiúsculas. Graças aos recursos de descoberta do EUREKA, o servidor gateway reconhecerá o host desse serviço com base no nome.

### Considerações Finais

Esse setup permite que o Spring Cloud Gateway funcione como um gateway dinâmico que se integra com o Eureka Discovery Service, facilitando o roteamento para microserviços de forma eficiente e flexível. Com essa configuração, você pode expandir facilmente a sua arquitetura de microserviços e manter a comunicação entre serviços simplificada e centralizada.
