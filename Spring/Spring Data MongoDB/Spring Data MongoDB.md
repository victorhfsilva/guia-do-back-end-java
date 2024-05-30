# Spring Data MongoDB

### Introdução

Spring Data MongoDB é parte do projeto Spring Data, que oferece suporte integrado ao MongoDB no ecossistema Spring. Ele fornece uma maneira simples e consistente de acessar dados no MongoDB, aproveitando a infraestrutura familiar do Spring. Este guia abrange a configuração inicial, operações CRUD e funcionalidades avançadas.

### Configuração Inicial

#### Dependências

Para usar Spring Data MongoDB, adicione as dependências necessárias ao seu projeto Maven. No arquivo `pom.xml`, inclua:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

Se estiver usando Gradle, adicione ao seu `build.gradle`:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
```

#### Configuração do MongoDB

No arquivo `application.properties` ou `application.yml`, configure a conexão com o MongoDB:

**application.properties**:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=nome_do_banco
```

**application.yml**:

```yaml
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: nome_do_banco
```

### Criação de Entidades

Defina suas entidades MongoDB como classes Java anotadas com `@Document`. Utilize `@Id` para marcar o campo que será o identificador do documento.

**Exemplo**:

```java
@Document(collection = "usuarios")
public class Usuario {
    
    @Id
    private String id;
    private String nome;
    private int idade;
    private String cidade;

    // Getters e Setters
    // ...
}
```

### 3. Repositórios

Crie interfaces de repositório que estendam `MongoRepository` ou `CrudRepository` para realizar operações CRUD.

**Exemplo**:

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findByCidade(String cidade);
}
```

### Operações CRUD

Com o repositório definido, você pode realizar operações CRUD. O Spring Data MongoDB fornece métodos padrão, e você pode adicionar métodos personalizados conforme necessário.

**Exemplo de Uso**:

```java
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar um novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Ler usuários por cidade
    public List<Usuario> obterUsuariosPorCidade(String cidade) {
        return usuarioRepository.findByCidade(cidade);
    }

    // Atualizar um usuário
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Deletar um usuário
    public void deletarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }
}
```