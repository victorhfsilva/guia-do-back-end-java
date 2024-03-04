# Spring Boot

## JPA e ORM

O ORM (Object-Relational Mapping) é um padrão de projeto que ajuda a mapear objetos Java para tabelas de banco de dados relacionais. O JPA (Java Persistence API) é uma especificação de ORM para o Java, que permite o mapeamento de objetos Java para bancos de dados relacionais.

No Spring Boot, o JPA é implementado por meio de um conjunto de bibliotecas, incluindo Hibernate, que é um popular framework de ORM para Java. 

Algumas das anotações importantes que podem ser usadas nas entidades incluem:

* __@Entity:__ define uma classe Java como uma entidade persistente.

* __@Id:__ define uma propriedade da entidade como a chave primária da tabela correspondente.

* __@Column:__ mapeia uma propriedade da entidade para uma coluna da tabela correspondente.

## Exemplo

Suponha que estamos construindo uma aplicação de gerenciamento de usuários, em que cada usuário pode ter várias tarefas associadas a eles. Nesse caso, podemos criar duas classes: User e Task, em que a classe User possui uma lista de tarefas associadas a ele. Aqui está um exemplo de como poderíamos mapear essas classes para tabelas no banco de dados:

```java
@Entity
@Table(name = "users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "name")
    private String name;
 
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
 
    // Constructors
    // Utiliza-se protected para que apenas o JPA possa chamar este construtor
    protected User() {    
    }

    public User(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    // Getters
    // Não é boa prática utilizar setters.
    public Long getId() {
        return id;
    }

    public Long getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}

@Entity
@Table(name = "tasks")
public class Task {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "description")
    private String description;
 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    // Getters

    // Constructors
}
```

Nesse exemplo, a classe User é mapeada para uma tabela chamada "users", e a classe Task é mapeada para uma tabela chamada "tasks". A anotação @Entity indica que a classe é uma entidade que deve ser mapeada para uma tabela no banco de dados. As anotações @Table e @Column são usadas para especificar o nome da tabela e das colunas.

Na classe User, a anotação @OneToMany é usada para indicar que um usuário pode ter várias tarefas associadas a ele. A propriedade mappedBy especifica o nome do campo na classe Task que mapeia a relação entre as duas classes.

Na classe Task, a anotação @ManyToOne é usada para indicar que várias tarefas podem estar associadas a um único usuário. A anotação @JoinColumn é usada para especificar o nome da coluna que armazena a chave estrangeira que se refere ao id do usuário.

O GenerationType é uma enumeração que define a estratégia de geração de valores de chave primária para as entidades mapeadas pelo JPA. No exemplo acima, foi utilizado o GenerationType.IDENTITY para gerar automaticamente valores de chave primária para as classes User e Task.

O GenerationType.IDENTITY indica que a geração do valor de chave primária é delegada para o banco de dados e que o valor será gerado automaticamente através de uma coluna de auto-incremento no banco de dados. Dessa forma, quando uma nova instância de User ou Task é persistida no banco de dados, o valor de chave primária é gerado automaticamente pelo banco de dados e atribuído ao objeto Java correspondente.

O uso do GenerationType.IDENTITY é comum em sistemas em que a geração de valores de chave primária é delegada ao banco de dados, como é o caso do MySQL e do PostgreSQL. No entanto, existem outras estratégias de geração de valores de chave primária, como o GenerationType.SEQUENCE, que é usado em bancos de dados Oracle e pode ser configurado para gerar valores de chave primária em um sequencial específico.

Com essas anotações, o JPA irá criar automaticamente as tabelas necessárias no banco de dados e mapear os objetos Java para as linhas de tabela correspondentes. Podemos então usar a API do JPA para realizar operações CRUD no banco de dados, como buscar um usuário e suas tarefas associadas, adicionar uma nova tarefa para um usuário existente, ou remover uma tarefa.