# Repositórios no Spring Data MongoDB

### Introdução

O Spring Data MongoDB fornece várias interfaces que facilitam a implementação de repositórios para realizar operações CRUD (Create, Read, Update, Delete) e operações de paginação e ordenação. As principais interfaces são `MongoRepository`, `CrudRepository` e `PagingAndSortingRepository`.

### Interface MongoRepository

A interface `MongoRepository` estende a `PagingAndSortingRepository` e a `CrudRepository`, fornecendo uma implementação completa para manipulação de dados no MongoDB. Ela oferece métodos básicos de CRUD, bem como funcionalidades adicionais para paginação e ordenação.

#### Exemplo de Repositório com MongoRepository

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Métodos de consulta personalizados podem ser adicionados aqui
    Usuario findByEmail(String email);
}
```

### Interface CrudRepository

A interface `CrudRepository` fornece métodos CRUD básicos para operações em entidades. Ela é adequada quando você precisa apenas de operações CRUD básicas sem a necessidade de paginação ou ordenação.

#### Métodos Principais da CrudRepository

- `save(S entity)`: Salva uma entidade.
- `findById(ID id)`: Busca uma entidade pelo seu ID.
- `findAll()`: Busca todas as entidades.
- `count()`: Conta o número total de entidades.
- `deleteById(ID id)`: Deleta uma entidade pelo seu ID.
- `delete(S entity)`: Deleta uma entidade.

#### Exemplo de Repositório com CrudRepository

```java
public interface ProdutoRepository extends CrudRepository<Produto, String> {
    // Métodos de consulta personalizados podem ser adicionados aqui
    List<Produto> findByNome(String nome);
}
```

### Interface PagingAndSortingRepository

A interface `PagingAndSortingRepository` estende a `CrudRepository` e adiciona funcionalidades de paginação e ordenação. Ela é útil quando você precisa lidar com grandes conjuntos de dados e quer implementar paginação ou ordenação nos resultados.

#### Métodos Principais da PagingAndSortingRepository

- `findAll(Pageable pageable)`: Retorna todas as entidades em uma página específica.
- `findAll(Sort sort)`: Retorna todas as entidades ordenadas de acordo com o parâmetro `Sort`.
- `findAll(Pageable pageable, Sort sort)`: Retorna todas as entidades em uma página específica e ordenadas de acordo com o parâmetro `Sort`.

#### Exemplo de Repositório com PagingAndSortingRepository

```java
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, String> {
    // Métodos de consulta personalizados podem ser adicionados aqui
    Page<Pedido> findByClienteId(String clienteId, Pageable pageable);
}
```

### Usando os Repositórios

#### Exemplo de Serviço utilizando MongoRepository

```java
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void deletarPorId(String id) {
        usuarioRepository.deleteById(id);
    }
}
```

#### Exemplo de Serviço utilizando PagingAndSortingRepository

```java
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Page<Pedido> listarPedidosPorCliente(String clienteId, int page, int size) {
        return pedidoRepository.findByClienteId(clienteId, PageRequest.of(page, size));
    }
}
```