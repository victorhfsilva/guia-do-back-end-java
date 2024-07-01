# SpringQueryMap

## Introdução

O Spring QueryMap é uma funcionalidade oferecida pelo Spring Framework, especialmente útil em projetos que utilizam o Spring Web ou Spring Cloud OpenFeign. Ele permite mapear parâmetros de uma requisição HTTP diretamente para um objeto, facilitando o manuseio de requisições complexas com múltiplos parâmetros.

## Configuração Inicial

1. **Dependências do Maven**: Certifique-se de incluir as dependências necessárias no seu `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

2. **Habilitar Feign Clients**: Adicione a anotação `@EnableFeignClients` na classe principal da sua aplicação Spring Boot.

```java
@SpringBootApplication
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## Criação de um Feign Client

1. **Definindo a Interface**: Crie uma interface que representará o cliente Feign. Utilize a anotação `@FeignClient` para especificar o serviço.

```java
@FeignClient(name = "exampleClient", url = "http://example.com")
public interface ExampleClient {
    @GetMapping("/search")
    ResponseEntity<String> search(@SpringQueryMap SearchParams searchParams);
}
```

2. **Definindo o Objeto de Parâmetros**: Crie uma classe que representará os parâmetros da consulta. Utilize as anotações do Lombok (`@Data`, `@Getter`, `@Setter`, etc.) para facilitar a criação dos métodos getters e setters, se desejar.

```java
@Data
public class SearchParams {
    private String query;
    private int page;
    private int size;
    private String sort;
}
```

## Utilizando o Feign Client

Agora que você configurou o Feign Client e definiu o objeto de parâmetros, pode utilizá-lo em seus serviços Spring.

```java
@Service
public class ExampleService {

    private final ExampleClient exampleClient;

    @Autowired
    public ExampleService(ExampleClient exampleClient) {
        this.exampleClient = exampleClient;
    }

    public String performSearch(String query, int page, int size, String sort) {
        SearchParams searchParams = new SearchParams();
        searchParams.setQuery(query);
        searchParams.setPage(page);
        searchParams.setSize(size);
        searchParams.setSort(sort);

        ResponseEntity<String> response = exampleClient.search(searchParams);
        return response.getBody();
    }
}
```