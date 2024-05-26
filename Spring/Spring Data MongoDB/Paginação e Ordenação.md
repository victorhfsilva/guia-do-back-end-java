## Paginação e Ordenação no Spring Data MongoDB

### Introdução

Paginação e ordenação são funcionalidades cruciais para aplicações que lidam com grandes volumes de dados. O Spring Data MongoDB fornece interfaces e classes para facilitar a implementação dessas funcionalidades de forma eficiente e intuitiva.

### Interface Pageable

A interface `Pageable` é usada para representar informações de paginação, como número da página e tamanho da página. Ela é comumente utilizada em métodos de repositórios para solicitar uma página específica de dados.

### Classe PageRequest

A classe `PageRequest` é uma implementação da interface `Pageable` e é usada para criar objetos de paginação.

#### Criação de um PageRequest

```java
Pageable pageable = PageRequest.of(0, 10); // Primeira página, 10 elementos por página
```

### Interface Sort

A interface `Sort` é usada para especificar a ordenação dos resultados. Você pode definir a ordenação por um ou mais campos, bem como a direção da ordenação (ascendente ou descendente).

#### Criação de um Sort

```java
Sort sort = Sort.by(Sort.Direction.ASC, "nome"); // Ordenação ascendente pelo campo "nome"
```

### Usando Pageable e Sort nos Repositórios

Você pode combinar paginação e ordenação em métodos de repositórios para retornar dados paginados e ordenados.

#### Exemplo de Repositório

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Page<Usuario> findByIdadeGreaterThan(int idade, Pageable pageable);
}
```
### Utilizando Pageable e Sort

Para utilizar as funcionalidades de paginação e ordenação, você precisa criar instâncias de `Pageable` e `Sort`, e passá-las como argumentos aos métodos do repositório.

#### Exemplo de Paginação e Ordenação

```java
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listarUsuariosComPaginacaoEOrdenacao(int page, int size, String sortBy, String direction) {
        
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        return usuarioRepository.findByIdadeGreaterThan(18, pageable);
    }
}
```