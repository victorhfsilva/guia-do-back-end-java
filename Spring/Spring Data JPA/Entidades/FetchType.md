# Tipos de Carregamento

Vamos explicar o que são carregamento preguiçoso (lazy loading) e carregamento imediato (eager loading):

**Carregamento Preguiçoso (Lazy Loading):**
- Carregamento preguiçoso é um padrão de design no qual a inicialização de dados é adiada até que seja estritamente necessário.
- Quando o carregamento preguiçoso é habilitado, os dados relacionados não são carregados automaticamente quando a entidade principal é carregada, mas apenas quando são explicitamente acessados no código.
- Padrão para 1-N e N-N.

**Exemplo:**

```java
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "name")
    private String name;

    // Definindo a relação entre Author e Book com carregamento tardio
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    // Construtores, Getters e Setters
}
```

```java
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title")
    private String title;

    // Definindo a relação com Author, também com carregamento tardio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    // Construtores, Getters e Setters
}
```

Aqui, `UserLazy` possui uma coleção `orderDetail` que é carregada de forma preguiçosa. Isso significa que os detalhes do pedido não são carregados automaticamente quando um usuário é carregado, economizando memória.

**Carregamento Imediato (Eager Loading):**
- Carregamento imediato é um padrão de design em que a inicialização de dados ocorre imediatamente junto com a entidade principal.
- Quando o carregamento imediato é habilitado, os dados relacionados são carregados automaticamente quando a entidade principal é carregada, ocupando espaço na memória.
- Padrão para 1-1 e N-1.

**Exemplo:**


```java
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "name")
    private String name;

    // Definindo a relação entre Author e Book com carregamento tardio
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    // Construtores, Getters e Setters
}
```

```java
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title")
    private String title;

    // Definindo a relação com Author, também com carregamento tardio
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    // Construtores, Getters e Setters
}
```

**Resumo:**

- Carregamento preguiçoso é útil quando você deseja evitar o carregamento desnecessário de dados relacionados.
- Carregamento imediato é útil quando você sabe que sempre precisará dos dados relacionados junto com a entidade principal.
- O Hibernate, um provedor JPA, oferece suporte a essas estratégias de carregamento para personalizar o comportamento de suas consultas e otimizar o desempenho.