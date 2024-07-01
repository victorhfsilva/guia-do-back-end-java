# Testcontainers

## Introdução

Testcontainers é uma biblioteca Java que suporta testes baseados em contêineres. Ele oferece a capacidade de executar instâncias efêmeras de bancos de dados, navegadores web, ou outros serviços em contêineres Docker, facilitando a configuração e a execução de testes de integração. Este guia mostrará como configurar e utilizar Testcontainers com Spring e PostgreSQL.

## Configuração Inicial

1. **Dependências do Maven/Gradle**: Certifique-se de incluir as dependências necessárias para Testcontainers no seu `build.gradle`.

```groovy
dependencies {
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.boot:spring-boot-testcontainers'
    testImplementation 'org.testcontainers:junit-jupiter'
    testImplementation 'org.testcontainers:postgresql'
}
```

2. **Configuração do Banco de Dados para Testes**: Utilize Testcontainers para criar instâncias efêmeras do banco de dados PostgreSQL durante os testes.

## Configuração do `application.properties` para Testes

Crie um arquivo `application-test.properties` em `src/test/resources` para definir a configuração do banco de dados para os testes.

```properties
spring.datasource.url=jdbc:tc:postgresql:14.1:///testdb
spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver
spring.datasource.username=test
spring.datasource.password=test
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Escrevendo os Testes de Integração

1. **Configuração do Ambiente de Testes**: Crie uma classe base de teste para configurar o ambiente de testes.

```java
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14.1")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    static {
        postgresContainer.start();
    }
}
```

Para testes de controladores pode ser útil configurar a porta dos testes como aleatória.

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14.1")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    static {
        postgresContainer.start();
    }
}
```

2. **Escrevendo Testes de Integração para os Serviços**:

```java
@SpringBootTest
public class CursoServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CursoService cursoService;


    // Testes de integração...
}
```

2. **Escrevendo Testes de Integração para os Controladores**:

````java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursoControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    // Testes de integração
}
```

## Considerações Finais

- **Perfis de Teste**: Utilize perfis específicos para testes (`test`) para isolar a configuração de teste da configuração de produção.
- **Testcontainers**: Utilize Testcontainers para garantir que o banco de dados usado nos testes seja semelhante ao ambiente de produção.
