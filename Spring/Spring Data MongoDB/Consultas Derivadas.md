# Consultas Derivadas no Spring Data MongoDB

### Introdução

Consultas derivadas no Spring Data MongoDB são métodos de consulta que são automaticamente implementados pelo Spring com base nos nomes dos métodos definidos no repositório. Esses métodos permitem que você escreva consultas complexas de maneira declarativa e concisa, utilizando convenções de nomenclatura.

### Métodos de Consulta Baseados em Nomes

Os métodos de consulta baseados em nomes seguem uma convenção de nomenclatura específica, onde o nome do método define a consulta a ser executada. O Spring Data MongoDB analisa o nome do método e gera a consulta correspondente.

#### Exemplo Básico

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findByNome(String nome);
}
```

No exemplo acima, o método `findByNome` irá buscar todos os documentos na coleção `usuarios` onde o campo `nome` é igual ao valor fornecido.

### Operadores de Consulta

O Spring Data MongoDB suporta uma variedade de operadores de consulta que podem ser utilizados nos nomes dos métodos. Abaixo estão alguns dos operadores mais comuns.

#### And

Usado para combinar múltiplas condições com uma cláusula AND.

```java
List<Usuario> findByNomeAndIdade(String nome, int idade);
```

#### Or

Usado para combinar múltiplas condições com uma cláusula OR.

```java
List<Usuario> findByNomeOrEmail(String nome, String email);
```

#### Between

Usado para encontrar valores dentro de um intervalo específico.

```java
List<Usuario> findByIdadeBetween(int start, int end);
```

#### LessThan

Usado para encontrar valores menores que um valor específico.

```java
List<Usuario> findByIdadeLessThan(int idade);
```

#### GreaterThan

Usado para encontrar valores maiores que um valor específico.

```java
List<Usuario> findByIdadeGreaterThan(int idade);
```

#### Like

Usado para encontrar valores que correspondem a um padrão específico.

```java
List<Usuario> findByNomeLike(String nomePattern);
```

### Exemplo Completo com Vários Operadores

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findByNome(String nome);
    
    List<Usuario> findByNomeAndIdade(String nome, int idade);
    
    List<Usuario> findByNomeOrEmail(String nome, String email);
    
    List<Usuario> findByIdadeBetween(int start, int end);
    
    List<Usuario> findByIdadeLessThan(int idade);
    
    List<Usuario> findByIdadeGreaterThan(int idade);
    
    List<Usuario> findByNomeLike(String nomePattern);
}
```

### Uso de Consultas Derivadas

Para utilizar as consultas derivadas em um serviço, você pode injetar o repositório e chamar os métodos definidos.

#### Exemplo de Serviço

```java
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public List<Usuario> buscarPorNomeEIdade(String nome, int idade) {
        return usuarioRepository.findByNomeAndIdade(nome, idade);
    }

    public List<Usuario> buscarPorNomeOuEmail(String nome, String email) {
        return usuarioRepository.findByNomeOrEmail(nome, email);
    }

    public List<Usuario> buscarPorIdadeEntre(int start, int end) {
        return usuarioRepository.findByIdadeBetween(start, end);
    }

    public List<Usuario> buscarPorIdadeMenorQue(int idade) {
        return usuarioRepository.findByIdadeLessThan(idade);
    }

    public List<Usuario> buscarPorIdadeMaiorQue(int idade) {
        return usuarioRepository.findByIdadeGreaterThan(idade);
    }

    public List<Usuario> buscarPorNomeLike(String nomePattern) {
        return usuarioRepository.findByNomeLike(nomePattern);
    }
}
```