# GeneratedValue

O `@GeneratedValue` é uma anotação do Java Persistence API (JPA) usada para especificar como os valores das chaves primárias das entidades são gerados automaticamente no banco de dados. Existem diferentes estratégias que você pode escolher, dependendo dos requisitos do seu aplicativo.

## **1. GenerationType.AUTO:**
- Esta estratégia permite que o provedor JPA escolha a estratégia mais apropriada com base no banco de dados subjacente.
- Pode usar sequências, identidades ou tabelas, dependendo do banco de dados.

Exemplo:
```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

## **2. GenerationType.IDENTITY:**
- Utiliza colunas de autoincremento (geração automática) específicas do banco de dados para gerar valores da chave primária.
- Comum em bancos de dados como MySQL e PostgreSQL.

Exemplo:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

## **3. GenerationType.SEQUENCE:**
- Usa sequências de banco de dados para gerar valores da chave primária.
- O banco de dados deve suportar sequências.
- Comum no Oracle Database.

Exemplo:
```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "minhaSequencia")
@SequenceGenerator(name = "minhaSequencia", sequenceName = "seq_entidade")
private Long id;
```

```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
@GenericGenerator(
    name = "sequence-generator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
)
private Long id;
```

## **4. GenerationType.TABLE:**
- Usa uma tabela de banco de dados específica para armazenar valores gerados para as chaves primárias.
- Mais portátil do que `GenerationType.IDENTITY`, mas menos eficiente.

Exemplo:
```java
@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "minhaTabela")
@TableGenerator(name = "minhaTabela", table = "chaves_primarias", allocationSize = 1)
private Long id;
```

```java
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
      generator = "table-generator")
    @TableGenerator(name = "table-generator", 
            table = "ids", 
            pkColumnName = "seq_id", 
            valueColumnName = "seq_value")
    private Long id;
```

## **5. GenerationType.NONE:**
- Nenhuma geração automática é realizada; o aplicativo é responsável por definir o valor da chave primária antes de inserir a entidade no banco de dados.

Exemplo:
```java
@Id
@GeneratedValue(strategy = GenerationType.NONE)
private Long id;
```


## **6. GenerationType.UUID:**

- Utiliza UUIDs (Universal Unique Identifiers) para gerar valores da chave primária.
- Garante a unicidade dos identificadores, mesmo em ambientes distribuídos.
- Não depende do banco de dados subjacente e pode ser implementado em qualquer banco de dados.

Exemplo:
```java
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
private UUID id;
```

Essa estratégia é especialmente útil em cenários onde a geração de chaves primárias deve ser independente do banco de dados e garantir a unicidade dos identificadores. É amplamente adotada em sistemas distribuídos e em ambientes de microsserviços devido à sua robustez e portabilidade.