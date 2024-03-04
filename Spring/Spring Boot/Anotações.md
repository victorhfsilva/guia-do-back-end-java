## Spring Boot: Anotações Fundamentais

O Spring Boot é um framework que facilita o desenvolvimento de aplicativos Java, automatizando muitas tarefas de configuração. Aqui estão algumas anotações essenciais usadas no Spring Boot, juntamente com exemplos mais completos e didáticos:

### @SpringBootApplication

A anotação `@SpringBootApplication` é o ponto de partida de uma aplicação Spring Boot. Ela combina várias outras anotações para habilitar a configuração automática, a varredura de componentes e a inicialização da aplicação. Aqui está um exemplo mais completo:

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### @RestController

A anotação `@RestController` é usada para criar controladores RESTful. Esses controladores respondem às solicitações HTTP com objetos convertidos em JSON.

```java
@RestController
@RequestMapping("/api")
public class UserController {
    
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        // Lógica para buscar o usuário com o ID especificado
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Lógica para criar um novo usuário com os dados fornecidos
    }
}
```

### @Autowired

A anotação `@Autowired` é usada para injetar dependências automaticamente. Isso simplifica a criação e o gerenciamento de objetos que uma classe precisa para funcionar corretamente.

```java
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Métodos da classe UserService
}
```

### @Component, @Service e @Repository

Essas anotações são utilizadas para criar componentes, serviços e repositórios, respectivamente. Elas indicam ao Spring que a classe é gerenciada pelo contêiner e pode ser injetada em outras partes da aplicação.

```java
@Component
public class MyComponent {
    // ...
}

@Service
public class MyService {
    // ...
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // ...
}
```

### @Bean

A anotação `@Bean` é usada para definir métodos que produzem objetos que serão gerenciados pelo Spring. Esses objetos podem ser configurados e personalizados antes de serem adicionados ao contexto da aplicação. Vejamos um exemplo:

```java
@Configuration
public class MyConfiguration {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

Para utilizar estes objetos basta injetá-los:

```java
@Service
public class MyOtherService {

    private final Gson gson;
    private final MyService myService;

    @Autowired
    public MyOtherService(Gson gson, MyService myService) {
        this.gson = gson;
        this.myService = myService;
    }

    // Métodos da classe
}
```

## Conclusão

As anotações do Spring Boot simplificam o desenvolvimento de aplicativos Java, automatizando muitas tarefas de configuração e gerenciamento de dependências. Ao entender e usar essas anotações de forma adequada, você pode criar aplicativos robustos e eficientes de maneira mais rápida e eficaz.