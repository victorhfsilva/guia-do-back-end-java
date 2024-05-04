# Consumindo serviços com o Feign Client

Consumir serviços através de um Discovery Service utilizando o Feign Client no ecossistema Spring Cloud é uma abordagem poderosa para facilitar a comunicação entre microserviços. O Feign é um declarative web service client que torna a escrita de clientes web mais fácil e mais legível. Aqui está um guia sobre como configurar e consumir um serviço usando o Feign Client integrado ao Eureka Discovery Service.

### Adicionar Dependências

Para começar, você precisa adicionar as dependências necessárias ao seu projeto. Se estiver usando o Maven, adicione as seguintes dependências ao seu arquivo `pom.xml`:

```xml
<dependencies>
    <!-- Spring Cloud Starter Netflix Eureka Client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
    <!-- Feign Client -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>

<dependencyManagement>
<dependencies>
    <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>${spring-cloud.version}</version>
    <type>pom</type>
    <scope>import</scope>
    </dependency>
</dependencies>
</dependencyManagement>
```

Para Gradle, adicione no seu `build.gradle`:

```gradle
dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-dependencies:2023.0.1"
	}
}
```

### Configurar o Application Properties

No arquivo `application.properties` ou `application.yml`, adicione as configurações para o Eureka Client e o Feign:

```properties
spring.application.name=feign-client
server.port=8080

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
eureka.instance.preferIpAddress=true
```

### Habilitar o Feign Client

Em seu aplicativo Spring Boot, habilite o Feign Client usando a anotação `@EnableFeignClients` na classe principal:

```java
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }
}
```

### Criar uma Interface para o Feign Client

Defina uma interface para o seu Feign Client que declara os métodos para acessar o serviço remoto. Utilize anotações do Spring MVC como `@GetMapping` para mapear as chamadas:

```java
@FeignClient(name = "nome-do-servico-alvo")
public interface ServiceClient {

    @GetMapping("/endpoint")
    String getSomeData();
}
```

A anotação `@FeignClient` usa o nome do serviço registrado no Eureka.

###  Usar o Feign Client

Agora, você pode injetar o `ServiceClient` em qualquer componente do Spring e usar para fazer chamadas ao serviço remoto:

```java
@RestController
public class ServiceController {

    @Autowired
    private ServiceClient serviceClient;

    @GetMapping("/consume")
    public String consumeService() {
        return serviceClient.getSomeData();
    }
}
```

### Considerações Finais

Ao usar o Feign com Eureka, você simplifica significativamente a comunicação entre serviços, gerenciando o balanceamento de carga e a resolução de instâncias automaticamente. Certifique-se de lidar com questões como timeouts e fallbacks, talvez usando o Spring Cloud Circuit Breaker ou Hystrix, para aumentar a resiliência do seu cliente.