# JPQL

A JPQL (Java Persistence Query Language) é uma linguagem de consulta que é usada para consultar entidades no contexto do Java Persistence API (JPA). Ela permite que você escreva consultas em um formato semelhante ao SQL, mas que opera em objetos de entidade Java, em vez de tabelas de banco de dados. 

### **Sintaxe Básica:**

- Consulta JPQL é escrita como uma string em Java, geralmente dentro de uma anotação `@NamedQuery` ou diretamente em seu código.

```java
String jpql = "SELECT e FROM Entidade e WHERE e.propriedade = :valor";
```

- `SELECT` especifica as propriedades da entidade que você deseja recuperar.
- `FROM` especifica a entidade que você está consultando.
- `WHERE` estabelece as condições de filtro.

### **Parâmetros:**

- Você pode usar parâmetros nomeados na JPQL para tornar a consulta dinâmica.

```java
Query query = entityManager.createQuery("SELECT e FROM Entidade e WHERE e.nome = :nome");
query.setParameter("nome", "NomeDesejado");
```

### **Operadores de Comparação:**

- Você pode usar operadores de comparação, como `=`, `<`, `>`, `<=`, `>=`, `<>` (diferente), etc.

### **Ordenação:**

- Use a cláusula `ORDER BY` para classificar os resultados.

```java
String jpql = "SELECT e FROM Entidade e ORDER BY e.nome ASC";
```

- `ASC` para ordenação ascendente e `DESC` para ordenação descendente.

### **Funções Agregadas:**

- Funções como `COUNT`, `SUM`, `AVG`, `MAX` e `MIN` podem ser usadas para realizar cálculos nos resultados.

```java
String jpql = "SELECT COUNT(e) FROM Entidade e";
```

### **Operadores Lógicos:**

- Use operadores lógicos como `AND`, `OR`, `NOT` para criar condições complexas.

```java
String jpql = "SELECT e FROM Entidade e WHERE e.id > 10 AND e.nome LIKE '%Victor%'";
```

Neste exemplo, estamos buscando por entidades onde o campo nome contenha a sequência "Victor" em qualquer posição da string.

### **Consultando valores de tabelas relacionadas:**

```java
String jpql = "SELECT s FROM Student s WHERE s.address.street LIKE %Major%";
```
Neste exemplo, s.address.street se refere ao campo de rua do endereço associado ao estudante. Estamos buscando por estudantes onde a rua do endereço contenha o padrão especificado em %Major%.

### **Joins:**

- Você pode usar `JOIN` para acessar associações entre entidades.

```java
String jpql = "SELECT a FROM Autor a JOIN a.livros l WHERE l.titulo = 'Meu Livro'";
```

### **Subconsultas:**

- Você pode aninhar subconsultas dentro de consultas principais.

```java
String jpql = "SELECT e FROM Entidade e WHERE e.id IN (SELECT e2.id FROM Entidade2 e2 WHERE e2.status = 'ativo')";
```

### **Consultas Nomeadas:**

- Você pode criar consultas nomeadas para reutilização.

```java
@NamedQuery(name = "NomeDaConsulta", query = "SELECT e FROM Entidade e WHERE e.propriedade = :valor")
```

- Em seguida, você pode chamar essa consulta por nome.

```java
Query query = entityManager.createNamedQuery("NomeDaConsulta");
```

### **Execução de Consultas:**

- Use um `EntityManager` para criar e executar consultas JPQL.

```java
Query query = entityManager.createQuery(jpql);
List<Entidade> resultados = query.getResultList();
```

- Ou para recuperar um único resultado:

```java
Query query = entityManager.createQuery(jpql);
Entidade resultado = query.getSingleResult();
```
