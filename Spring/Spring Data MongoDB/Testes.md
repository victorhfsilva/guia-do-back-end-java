## Testes no Spring Data MongoDB

### Introdução

Testar sua aplicação é crucial para garantir que ela funcione corretamente e para identificar problemas antes que eles cheguem ao ambiente de produção. No Spring Data MongoDB, você pode usar testes unitários e de integração para validar o comportamento dos seus repositórios e serviços. 

### Mocking de Repositórios

Para testar serviços que dependem de repositórios, você pode usar frameworks de mocking como Mockito para criar mocks dos repositórios e definir o comportamento esperado.

#### Exemplo de Mocking com Mockito

```java
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testBuscarPorEmail() {
        Usuario usuario = new Usuario("Jane", "jane@example.com", 25);
        when(usuarioRepository.findByEmail("jane@example.com")).thenReturn(usuario);

        Usuario result = usuarioService.buscarPorEmail("jane@example.com");
        assertNotNull(result);
        assertEquals("Jane", result.getNome());
    }
}
```

### Testes de Integração com MongoDB Embutido (Embedded MongoDB)

Para testes de integração, você pode usar um MongoDB embutido que permite criar um banco de dados MongoDB na memória durante a execução dos testes. Isso é útil para validar o comportamento da aplicação em um ambiente que simula o mais próximo possível o ambiente de produção.

#### Dependência Maven para Embedded MongoDB

Adicione a seguinte dependência no seu `pom.xml`:

```xml
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
    <scope>test</scope>
</dependency>
```

#### Exemplo de Teste de Integração com Embedded MongoDB

A anotação `@DataMongoTest` é usada para configurar um ambiente de teste focado nos repositórios MongoDB. Ela desabilita a configuração completa da aplicação e carrega apenas os componentes relacionados ao Spring Data MongoDB.

```java
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.0.12")
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindByIdadeGreaterThan() {
        Usuario usuario1 = new Usuario("Alice", "alice@example.com", 35);
        Usuario usuario2 = new Usuario("Bob", "bob@example.com", 40);
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);

        List<Usuario> usuarios = usuarioRepository.findByIdadeGreaterThan(30);
        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        assertEquals("Alice", usuarios.get(0).getNome());
        assertEquals("Bob", usuarios.get(1).getNome());
    }
}
```