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

## **5. GenerationType.NONE:**
- Nenhuma geração automática é realizada; o aplicativo é responsável por definir o valor da chave primária antes de inserir a entidade no banco de dados.

Exemplo:
```java
@Id
@GeneratedValue(strategy = GenerationType.NONE)
private Long id;
```