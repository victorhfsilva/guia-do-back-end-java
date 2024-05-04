# Open Feign

OpenFeign é uma solução elegante para criar clientes HTTP declarativos em aplicações Java, facilitando a integração entre microserviços. Utilizar o OpenFeign no ecossistema Spring Cloud permite que você escreva interfaces que automaticamente se transformam em clientes HTTP. 

### Configuração Inicial do Projeto

Certifique-se de que seu projeto Spring Boot está configurado com as dependências necessárias para usar o OpenFeign. 

```gradle
ext {
  set('springCloudVersion', "2023.0.1")
}

dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}

dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-dependencies:2023.0.1"
	}
}
```
### Habilitar o Feign Clients

Adicione a anotação `@EnableFeignClients` na sua classe principal do Spring Boot para habilitar o processamento de interfaces Feign:

```java
@SpringBootApplication
@EnableFeignClients
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### Criar uma Interface Feign

Defina uma interface Feign para abstrair as chamadas HTTP para um serviço externo. Use anotações do Spring MVC, como `@GetMapping` ou `@PostMapping`, para mapear essas chamadas. Por exemplo, para consumir um serviço de usuários:

```java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);
}
```

- **@FeignClient**: Especifica o nome do serviço que será chamado e o URL base. O nome é usado para outras configurações e, se estiver usando Eureka ou outro discovery service, pode ser o nome registrado do serviço.
- **@GetMapping**: Mapeia a função para uma chamada GET em um endpoint específico.

### Usar o Cliente Feign em Seus Componentes

Você pode injetar o cliente Feign criado em qualquer componente do Spring, como controllers ou services, e usá-lo para fazer chamadas HTTP:

```java
@RestController
public class UserController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/findUser/{id}")
    public User findUser(@PathVariable Long id) {
        return userClient.getUserById(id);
    }
}
```

### Conclusão

OpenFeign é uma ferramenta poderosa para simplificar a comunicação entre serviços em arquiteturas de microserviços. Com sua abordagem declarativa, você pode focar mais na lógica de negócios e menos nos detalhes técnicos das chamadas HTTP.