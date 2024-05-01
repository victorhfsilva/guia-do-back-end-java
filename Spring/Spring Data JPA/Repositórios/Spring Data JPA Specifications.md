# Guia sobre Filtragem de Dados Utilizando Spring Data JPA Specifications

Spring Data JPA Specifications é uma maneira poderosa de construir consultas de banco de dados de forma programática. Este recurso é particularmente útil quando você precisa de filtragem dinâmica baseada em critérios complexos que podem variar em tempo de execução. 

# Modelo de Domínio

Vamos começar definindo uma entidade simples. Por exemplo, uma entidade `User` com campos `name` e `email`:

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Getters e setters
}
```

## Criando a Specification

Para utilizar Specifications, você precisará de uma interface `JpaSpecificationExecutor` no seu repositório. Primeiro, crie uma classe `UserSpecification` que implementa a interface `Specification<User>` para encapsular os critérios de consulta:

```java
public class UserSpecification implements Specification<User> {

    private SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                    root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
```

Neste exemplo, `SearchCriteria` é uma classe simples que armazena os detalhes do critério de pesquisa, como chave, valor e operação.

## Repositório

Estenda `JpaSpecificationExecutor` em seu repositório para poder usar o `Specification`:

```java
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
```

## Controlador

No controlador, você pode aceitar critérios de pesquisa como parâmetros e construir uma Specification para passar ao repositório:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findUsers(@RequestParam(value = "search", required = false) String search) {
        UserSpecification spec = new UserSpecification(new SearchCriteria("name", ":", search));
        return userRepository.findAll(spec);
    }
}
```

Também é possível utilizar mais de um specification na query.

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findUsers(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "email", required = false) String email,
    ) {
        UserSpecification specName = new UserSpecification(new SearchCriteria("name", ":", name));
        UserSpecification specEmail = new UserSpecification(new SearchCriteria("email", ":", email));
        
        return userRepository.findAll(Specification.where(specName).and(specEmail));
    }
}
```

## Passo 6: Testando a API

Com tudo configurado, você pode testar sua API utilizando uma ferramenta como Postman ou cURL. Faça uma requisição GET para:

```
http://localhost:8080/users?search=John
```

Isso deverá retornar uma lista de usuários cujos nomes contêm "John".

## Conclusão

Utilizar Spring Data JPA Specifications permite uma abordagem robusta e flexível para construir consultas dinâmicas baseadas em critérios que variam. Para mais informações consulte [REST Query Language with Spring Data JPA Specifications](https://www.baeldung.com/rest-api-search-language-spring-data-specifications)