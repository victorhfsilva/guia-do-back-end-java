# PagingAndSortingRepository

`PagingAndSortingRepository` é uma interface estendida do Spring Data JPA que combina recursos de paginação e ordenação com as operações de CRUD (Create, Read, Update e Delete). Essa interface fornece métodos para recuperar dados de maneira paginada e classificá-los de acordo com os critérios desejados. 

```java
public interface SeuRepositorio extends PagingAndSortingRepository<TipoDaEntidade, TipoDaChavePrimária> {
    // Métodos personalizados podem ser definidos aqui
}
```

- `SeuRepositorio`: Substitua isso pelo nome do seu repositório.
- `TipoDaEntidade`: Substitua isso pelo tipo da entidade com a qual o repositório está associado.
- `TipoDaChavePrimária`: Substitua isso pelo tipo da chave primária da entidade.

Métodos herdados do `PagingAndSortingRepository`:
1. `findAll(Pageable pageable)`: Recupera todos os registros paginados e ordenados com base no `Pageable` fornecido.
2. `findAll(Sort sort)`: Recupera todos os registros e os ordena com base no objeto `Sort`.
3. `findAllById(Iterable<ID> ids)`: Recupera entidades por uma lista de IDs.
4. `findAll()`: Recupera todos os registros sem paginação ou ordenação.
5. `count()`: Retorna o número total de registros no repositório.

Você também pode definir consultas personalizadas usando a nomenclatura de métodos, como `findByNome`, `deleteBy...`, `countBy...`, etc.

Exemplo de uso:

```java
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
    Page<Produto> findByCategoria(String categoria, Pageable pageable);
}
```

Para usar um repositório `PagingAndSortingRepository`, você pode injetá-lo em um serviço ou controlador Spring e usá-lo para interagir com o banco de dados. Você também pode criar objetos `Pageable` ou `Sort` para controlar a paginação e a ordenação dos resultados.

## **Objeto Pageable:**

`Pageable` é usado para controlar a paginação de resultados ao consultar o banco de dados. Ele permite que você defina o número de registros por página e especifique a página que deseja recuperar.

1. **Criando um objeto `Pageable`**:

```java
Pageable pageable = PageRequest.of(pagina, tamanhoDaPagina);
```

- `pagina`: O número da página desejada (começando em 0).
- `tamanhoDaPagina`: O número de registros por página.

2. **Consultando com `Pageable`**:

```java
Page<Entidade> resultado = seuRepositorio.findAll(pageable);
```

3. **Obtendo informações da página**:

```java
int numeroDaPagina = resultado.getNumber(); // Número da página atual
int tamanhoDaPagina = resultado.getSize();  // Tamanho da página
int totalDeRegistros = resultado.getTotalElements(); // Total de registros
int totalDePaginas = resultado.getTotalPages();  // Total de páginas
boolean temProximaPagina = resultado.hasNext(); // Verifica se há uma próxima página
boolean temPaginaAnterior = resultado.hasPrevious(); // Verifica se há uma página anterior
```

## **Objeto Sort:**

`Sort` é usado para controlar a ordenação dos resultados ao consultar o banco de dados. Ele permite que você especifique as propriedades e a direção (ascendente ou descendente) pela qual deseja ordenar os resultados.

1. **Criando um objeto `Sort`**:

```java
Sort sort = Sort.by(Sort.Order.asc("nome").desc("preco"));
```

- Neste exemplo, estamos ordenando os resultados primeiro pelo campo "nome" em ordem ascendente e, em seguida, pelo campo "preco" em ordem descendente.

2. **Consultando com `Sort`**:

```java
List<Entidade> resultados = seuRepositorio.findAll(sort);
```

3. **Ordenação personalizada**:

Você pode criar consultas personalizadas definindo métodos na interface do repositório usando a nomenclatura de métodos do Spring Data JPA, como `findBy...OrderBy...`.

```java
List<Entidade> resultados = seuRepositorio.findByCategoriaOrderByPrecoDesc("Eletrônicos");
```

Isso retornará uma lista de entidades com a categoria "Eletrônicos", ordenadas pelo campo "preço" em ordem descendente.

