# Crud Repository

Um "CrudRepository" é uma interface fornecida pelo Spring Data JPA, que facilita a criação de repositórios de dados para operações CRUD (Create, Read, Update e Delete) em entidades de domínio. 

```java
public interface NomeDoRepositorio extends CrudRepository<TipoDaEntidade, TipoDaChavePrimária> {
    // Métodos personalizados podem ser definidos aqui
}
```

- `NomeDoRepositorio`: Substitua isso pelo nome do seu repositório.
- `TipoDaEntidade`: Substitua isso pelo tipo da entidade com a qual o repositório está associado.
- `TipoDaChavePrimária`: Substitua isso pelo tipo da chave primária da entidade.

Métodos herdados do CrudRepository:
1. `save(S entity)`: Salva uma entidade no banco de dados. Se a entidade já existe, ela é atualizada; caso contrário, uma nova é criada.
2. `findById(ID id)`: Recupera uma entidade com base na chave primária.
3. `findAll()`: Recupera todas as entidades no repositório.
4. `count()`: Retorna o número de entidades no repositório.
5. `deleteById(ID id)`: Exclui uma entidade com base na chave primária.
6. `deleteAll()`: Exclui todas as entidades no repositório.

Além desses métodos, o CrudRepository permite definir consultas personalizadas usando a nomenclatura de métodos, como `findByNome`, `deleteBy...`, `countBy...`, etc.

Exemplo de uso:

```java
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    List<Produto> findByNome(String nome);
    List<Produto> findByPrecoGreaterThan(double preco);
    void deleteByCategoria(String categoria);
}
```

Para usar um repositório CrudRepository, você deve injetá-lo em um serviço ou controlador Spring e usá-lo para interagir com o banco de dados.
