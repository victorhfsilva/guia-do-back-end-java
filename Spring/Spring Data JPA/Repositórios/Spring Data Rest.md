# Guia Prático do Spring Data REST

O Spring Data REST é uma extensão do Spring Data que simplifica a criação de APIs RESTful a partir dos repositórios Spring Data. Ele elimina a necessidade de escrever código manual para controladores REST, permitindo que você se concentre mais na lógica de negócios e menos na configuração da camada de controle.

Neste guia, vamos explorar os conceitos básicos do Spring Data REST e como usá-lo para criar rapidamente uma API RESTful.

## Configuração Básica

Para começar, você precisa configurar sua aplicação Spring Boot para usar o Spring Data REST. Isso geralmente envolve adicionar algumas dependências ao seu arquivo `pom.xml` (se você estiver usando Maven) ou `build.gradle` (se estiver usando Gradle):

### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

### Gradle

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-data-rest'
```

Com essas dependências no lugar, o Spring Boot detectará automaticamente seus repositórios Spring Data e os disponibilizará como endpoints RESTful.

## Expor Repositórios como Endpoints RESTful

Vamos supor que temos uma entidade `Produto` e um repositório correspondente:

```java
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    // Getters e Setters
}
```

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

O Spring Data REST irá expor automaticamente esse repositório como um endpoint RESTful em `/products`. Isso significa que você pode realizar operações CRUD em seus produtos usando os métodos HTTP padrão.

## Personalização de Endpoints


### Alterando o Path Base

Por padrão, os endpoints RESTful serão baseados no nome do seu repositório (por exemplo, */products* para *ProductRepository*). Você pode personalizar isso usando a anotação *@RepositoryRestResource*:

```java
@RepositoryRestResource(path = "itens")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

Isso alterará o caminho base para `/itens`.

### Personalização de Consultas

Você pode personalizar consultas adicionando métodos ao seu repositório:

```java
@RepositoryRestResource(path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String keyword);
}
```

Agora, você pode pesquisar produtos por nome usando `/products/search/findByNameContaining?keyword=produto`.

## Paginação, Ordenação e Filtragem

O Spring Data REST oferece suporte à paginação, ordenação e filtragem diretamente nos endpoints RESTful.

### Paginação

Basta adicionar `?page=0&size=10` à sua solicitação para obter os primeiros 10 resultados. O Spring Data REST irá retornar os resultados paginados.

### Ordenação

Adicione `?sort=price,desc` à sua solicitação para classificar os resultados por preço em ordem decrescente.

### Filtragem

Use `?name=produto` para filtrar os resultados por nome igual a "produto".

## Conclusão

O Spring Data REST simplifica significativamente o processo de criação de uma API RESTful a partir de seus repositórios Spring Data. Com algumas configurações simples e personalizações básicas, você pode expor facilmente seus dados como endpoints RESTful, permitindo que você crie rapidamente uma API robusta e poderosa para sua aplicação.