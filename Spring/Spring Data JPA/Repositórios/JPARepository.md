# Spring Boot
## JPA Repositories

JPA (Java Persistence API) é uma especificação Java que define um padrão para o mapeamento objeto-relacional (ORM) em aplicativos Java. Ele permite que os desenvolvedores mapeiem classes Java para tabelas de banco de dados e fornece uma maneira de acessar e manipular os dados armazenados nessas tabelas.

JPA Repository é uma interface fornecida pelo framework Spring Data JPA que fornece um conjunto de métodos para realizar operações CRUD (Create, Read, Update, Delete) no banco de dados. Ele estende a interface CRUDRepository com alguns métodos adicionais.


### Anotação @Repository: 

Esta anotação é usada para indicar que a interface é um repositório. É usada em conjunto com o recurso de varredura de componentes do Spring Framework para descobrir e registrar automaticamente a implementação do repositório.

Exemplo:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // métodos do repositório
}
```

### JpaRepository: 

Esta é a interface base para todos os repositórios JPA. Ele fornece um conjunto de métodos comuns para realizar operações CRUD, como save(), findById(), findAll(), deleteById() e outros.

Exemplo:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    List<User> findAll();
    void deleteById(long id);
}
```


### Criação de consulta:

A interface JpaRepository permite criar consultas com base nos nomes dos métodos, seguindo uma convenção específica.

Exemplo:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
```

 Abaixo está uma tabela que mostra algumas palavras-chave comuns, exemplos de métodos correspondentes e como eles se traduzem em snippets de JPQL (Java Persistence Query Language):

| Palavra-chave    | Exemplo de Método                             | Snippet JPQL                                        |
|------------------|-----------------------------------------------|-----------------------------------------------------|
| And              | findByLastnameAndFirstname                   | … where x.lastname = ?1 and x.firstname = ?2        |
| Or               | findByLastnameOrFirstname                    | … where x.lastname = ?1 or x.firstname = ?2         |
| Is, Equals       | findByFirstname, findByFirstnameIs, findByFirstnameEquals | … where x.firstname = ?1                 |
| Between          | findByStartDateBetween                        | … where x.startDate between ?1 and ?2               |
| LessThan         | findByAgeLessThan                             | … where x.age < ?1                                  |
| LessThanEqual    | findByAgeLessThanEqual                        | … where x.age <= ?1                                 |
| GreaterThan      | findByAgeGreaterThan                          | … where x.age > ?1                                  |
| GreaterThanEqual | findByAgeGreaterThanEqual                     | … where x.age >= ?1                                 |
| After            | findByStartDateAfter                          | … where x.startDate > ?1                            |
| Before           | findByStartDateBefore                         | … where x.startDate < ?1                            |
| IsNull           | findByAgeIsNull                               | … where x.age is null                               |
| IsNotNull, NotNull | findByAge(Is)NotNull                         | … where x.age not null                              |
| Like             | findByFirstnameLike                           | … where x.firstname like ?1                        |
| NotLike          | findByFirstnameNotLike                        | … where x.firstname not like ?1                    |
| StartingWith     | findByFirstnameStartingWith                   | … where x.firstname like ?1 (parameter bound with appended %) |
| EndingWith       | findByFirstnameEndingWith                     | … where x.firstname like ?1 (parameter bound with prepended %) |
| Containing       | findByFirstnameContaining                     | … where x.firstname like ?1 (parameter bound wrapped in %)  |
| OrderBy          | findByAgeOrderByLastnameDesc                  | … where x.age = ?1 order by x.lastname desc         |
| Not              | findByLastnameNot                             | … where x.lastname <> ?1                           |
| In               | findByAgeIn(Collection<Age> ages)             | … where x.age in ?1                                |
| NotIn            | findByAgeNotIn(Collection<Age> age)           | … where x.age not in ?1                            |
| True             | findByActiveTrue()                            | … where x.active = true                            |
| False            | findByActiveFalse()                           | … where x.active = false                           |
| IgnoreCase       | findByFirstnameIgnoreCase                    | … where UPPER(x.firstame) = UPPER(?1)              |

Esses métodos são traduzidos pelo Spring Data JPA em consultas JPQL correspondentes, permitindo a execução de consultas personalizadas sem a necessidade de escrever SQL diretamente.

### Anotação @Query: 

Esta anotação é usada para especificar uma consulta personalizada para um método de repositório. Pode ser usado para definir consultas complexas que não podem ser geradas pelo Spring Data JPA.

Exemplo:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findByAgeGreaterThan(@Param("age") int age);
}
```

### Anotação @Param: 

Esta anotação é usada para especificar os parâmetros de uma consulta personalizada. É usada em conjunto com a anotação @Query.

Exemplo:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    List<User> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
```


### Paginação e ordenação: 

O JPA Repository também oferece suporte para paginação e ordenação. A paginação permite recuperar um subconjunto de resultados, enquanto a ordenação permite ordenar os resultados com base em um ou mais campos.

Exemplo:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAgeGreaterThan(int age, Pageable pageable);
    List<User> findByAgeGreaterThan(int age, Sort sort);
}
```