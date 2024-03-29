### Testes de Integração de Repositórios Spring

Os testes de integração de repositórios Spring são importantes para garantir que as operações de persistência de dados estejam funcionando corretamente em seu aplicativo. Eles envolvem a interação com o banco de dados real, permitindo testar se as consultas, inserções, atualizações e exclusões estão sendo executadas conforme o esperado. Abaixo, segue um guia passo a passo para escrever testes de integração de repositórios Spring:

#### 1. Configuração do Ambiente de Testes

   Antes de começar a escrever os testes, certifique-se de configurar seu ambiente de testes de forma adequada:

   - Configure um banco de dados de teste separado para evitar interferências nos dados de produção.
   - Configure o Spring para usar o banco de dados de teste em vez do banco de dados de produção durante a execução dos testes.
   - Para isto basta incluir as configurações do banco de dados de teste em um arquivo *application-test.properties* e então definir que estará utilizando este profile nos testes:

   ```java
   @DataJpaTest
   @ActiveProfiles("test")
   public class RepositoryTest{
        //Testes
   }
   ```

#### 2. Anotações Spring para Testes de Integração

   Utilize as anotações do Spring Test para configurar e executar seus testes:

   - `@RunWith(SpringRunner.class)`:  É uma anotação usada para executar testes dentro do contexto do Spring, você precisa desta anotação para ativar recursos de inicialização do Spring como @Autowire, @MockBean durante o teste junit.

   - `@SpringBootTest`: Carrega o contexto da aplicação Spring para testes de integração.
   
   - `@DataJpaTest`: Carrega apenas o contexto do Spring necessário para testes de repositórios JPA.

#### 3. Configuração dos Dados de Teste

   Antes de executar os testes, você pode precisar configurar alguns dados de teste no banco de dados de teste. Isso pode ser feito usando as anotações `@Before` ou `@BeforeClass` para inicializar os dados antes da execução dos testes.

#### 4. Escrevendo os Testes

   Agora é hora de escrever os testes reais para os repositórios Spring. Aqui estão algumas práticas recomendadas:

   - **Teste Consultas Básicas**: Verifique se é possível recuperar entidades do banco de dados usando consultas básicas como `findById` e `findAll`.
   - **Teste Consultas Personalizadas**: Teste consultas personalizadas definidas nos repositórios usando a anotação `@Query`.
   - **Teste Operações de Persistência**: Verifique se é possível salvar, atualizar e excluir entidades no banco de dados.
   - **Teste Transações**: Verifique se as operações de transação estão funcionando corretamente, garantindo que as alterações sejam revertidas em caso de falha.

#### 5. Limpando os Dados de Teste

   Após a execução dos testes, é importante limpar quaisquer dados de teste que tenham sido inseridos no banco de dados. Isso pode ser feito usando as anotações `@After` ou `@AfterClass` para limpar os dados após a execução dos testes.

#### Exemplo de Teste de Integração de Repositório Spring

```java
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        // Cria um usuário de teste
        User user = new User("John", "john@example.com");
        userRepository.save(user);

        // Recupera o usuário pelo ID
        Optional<User> foundUserOptional = userRepository.findById(user.getId());
        assertTrue(foundUserOptional.isPresent());
        assertEquals(user.getName(), foundUserOptional.get().getName());

        // Limpa os dados de teste
        userRepository.delete(user);
    }
}
```

Este teste verifica se é possível salvar um usuário no banco de dados e recuperá-lo com sucesso usando o método `findById`.