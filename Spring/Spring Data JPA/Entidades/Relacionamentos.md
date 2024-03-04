# Relacionamentos

## **@OneToOne:**

- Utilizado para estabelecer um relacionamento um-para-um entre duas entidades.
- Normalmente usado quando uma entidade tem uma referência direta para outra.

```java
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //optional = true - permite que um elemento não seja mapeado 0..1
    //optional = false - obriga que todos elementos sejam mapeados 1..1
    @OneToOne(optional=false) 
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    // Outros campos e métodos
}

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;

    // Outros campos e métodos
}
```

**@OneToMany:**

- Utilizado para estabelecer um relacionamento um-para-muitos entre duas entidades.
- Normalmente usado quando uma entidade contém uma coleção de outras entidades.

```java
@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //OneToMany define um relacionamento 1-N
    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;
    // Outros campos e métodos
}

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //ManyToOne define um relacionamento N-1
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
    // Outros campos e métodos
}
```

**@ManyToOne:**

- Utilizado para estabelecer um relacionamento muitos-para-um entre duas entidades.
- Normalmente usado quando várias entidades se relacionam com uma única entidade.

```java
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    // Outros campos e métodos
}

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
    // Outros campos e métodos
}
```

**@ManyToMany:**

- Utilizado para estabelecer um relacionamento muitos-para-muitos entre duas entidades.
- Normalmente usado quando entidades têm uma associação bidirecional de muitos para muitos.

```java
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany
    @JoinTable(name = "LivrosAutores",
               joinColumns = @JoinColumn(name = "livro_id"),
               inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores;
    // Outros campos e métodos
}

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros;
    // Outros campos e métodos
}
```

## **@JoinColumn:**

   - A anotação `@JoinColumn` é usada para personalizar a coluna na tabela do banco de dados que será usada para manter o relacionamento.
   - É frequentemente usada em associações `@OneToOne` e `@ManyToOne` para especificar a coluna que representa a chave estrangeira no lado proprietário do relacionamento.
   - Ela permite que você especifique o nome da coluna, bem como outras propriedades, como `nullable`, `unique`, etc.

   Exemplo:
   ```java
   @OneToOne
   @JoinColumn(name = "endereco_id")
   private Endereco endereco;
   ```

## **mappedBy:**
   - A anotação `mappedBy` é usada em associações bidirecionais, como em relacionamentos `@OneToMany` e `@ManyToMany`.
   - Ela indica qual é o lado proprietário do relacionamento. O lado "não-proprietário" será o lado que usa o `mappedBy` e não conterá a coluna da chave estrangeira no banco de dados.
   - Isso evita a duplicação de informações no banco de dados e estabelece um relacionamento bidirecional.

   Exemplo:
   ```java
   @OneToMany(mappedBy = "turma")
   private List<Aluno> alunos;
   ```

## **joinColumns:**
   - A anotação `joinColumns` é usada em associações `@ManyToMany` e `@JoinTable` para personalizar as colunas que representam as chaves estrangeiras na tabela de junção (tabela intermediária) que liga as entidades.
   - Cada `@JoinColumn` dentro de `joinColumns` especifica a coluna que se relaciona com a entidade atual.

   Exemplo:
   ```java
   @ManyToMany
   @JoinTable(name = "LivrosAutores",
              joinColumns = @JoinColumn(name = "livro_id"),
              inverseJoinColumns = @JoinColumn(name = "autor_id"))
   private Set<Autor> autores;
   ```

## **inverseJoinColumns:**
   - A anotação `inverseJoinColumns` é usada junto com `joinColumns` em associações `@ManyToMany` para especificar as colunas que representam as chaves estrangeiras da outra entidade na tabela de junção.
   - Ela permite definir a coluna que representa a outra entidade no relacionamento `@ManyToMany`.

   Exemplo:
   ```java
   @ManyToMany
   @JoinTable(name = "LivrosAutores",
              joinColumns = @JoinColumn(name = "livro_id"),
              inverseJoinColumns = @JoinColumn(name = "autor_id"))
   private Set<Autor> autores;
   ```

