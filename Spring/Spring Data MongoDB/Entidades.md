## Entidades no Spring Data MongoDB

### Introdução

Entidades no Spring Data MongoDB são classes que representam documentos armazenados no MongoDB. O mapeamento dessas classes para documentos do MongoDB é feito por meio de várias anotações fornecidas pelo Spring Data MongoDB.

### Anotações Principais

#### @Document

A anotação `@Document` é usada para indicar que uma classe Java é um documento do MongoDB. Você pode especificar o nome da coleção onde os documentos dessa classe serão armazenados.

```java
@Document(collection = "usuarios")
public class Usuario {
    // campos e métodos
}
```

#### @Id

A anotação `@Id` é usada para marcar o campo que será usado como identificador único do documento. Esse campo será automaticamente gerenciado pelo MongoDB.

```java
@Document(collection = "usuarios")
public class Usuario {
    
    @Id
    private String id;
    
    // outros campos e métodos
}
```

#### @Field

A anotação `@Field` é usada para especificar o nome do campo no documento MongoDB que será mapeado para o campo da entidade. Isso é útil quando o nome do campo na classe Java é diferente do nome do campo no documento MongoDB.

```java
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    
    @Field("nome_completo")
    private String nome;
    
    // outros campos e métodos
}
```

#### @Indexed

A anotação `@Indexed` é usada para criar um índice no campo especificado, melhorando o desempenho das consultas que filtram por esse campo.

```java
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    
    @Field("nome_completo")
    private String nome;
    
    @Indexed(unique = true)
    private String email;
    
    // outros campos e métodos
}
```

#### @CompoundIndex

A anotação `@CompoundIndex` é usada para criar um índice composto em múltiplos campos, o que pode melhorar o desempenho de consultas complexas que envolvem mais de um campo.

```java
@Document(collection = "usuarios")
@CompoundIndexes({
    @CompoundIndex(name = "nome_idade_idx", def = "{'nome': 1, 'idade': -1}")
})
public class Usuario {

    @Id
    private String id;
    
    @Field("nome_completo")
    private String nome;
    
    private int idade;
    
    @Indexed(unique = true)
    private String email;
    
    // outros campos e métodos
}
```

#### @Transient

A anotação `@Transient` é usada para indicar que um campo não deve ser persistido no banco de dados. Esses campos são ignorados pelo MongoDB.

```java
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    
    @Field("nome_completo")
    private String nome;
    
    private int idade;
    
    @Indexed(unique = true)
    private String email;
    
    @Transient
    private String statusTemporario;
    
    // outros campos e métodos
}
```