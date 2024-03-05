# Mockito nos Testes do Spring

O Mockito é uma biblioteca de teste para Java que permite a criação de objetos simulados (mocks) em testes unitários. Ele é especialmente útil ao testar componentes do Spring, onde você pode precisar simular o comportamento de certas classes ou componentes.

Neste guia, vamos abordar como você pode utilizar o Mockito em testes do Spring para criar mocks de dependências e configurar comportamentos específicos para esses mocks.

## Configuração do Ambiente

Para começar a usar o Mockito nos seus testes do Spring, você precisará adicionar a dependência do Mockito ao seu projeto Maven ou Gradle. Aqui está um exemplo de como adicionar a dependência com o Maven:

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.12.4</version>
    <scope>test</scope>
</dependency>
```

Com a dependência adicionada, você está pronto para começar a escrever testes utilizando o Mockito.

## Criando Mocks

Para criar um mock de uma classe ou interface, você pode usar a anotação `@Mock` ou o método `Mockito.mock()`. Aqui está um exemplo de como usar a anotação `@Mock` em um teste do Spring:

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceTest {

    @Mock
    private MyDependency myDependency;

    // Restante do código do teste...
}
```

Você também pode criar mocks usando o método `Mockito.mock()` dentro do método de configuração do seu teste:

```java
import static org.mockito.Mockito.mock;

public class MyServiceTest {

    private MyDependency myDependency;

    @Before
    public void setUp() {
        myDependency = mock(MyDependency.class);
    }

    // Restante do código do teste...
}
```

## Injetando Mock

Para injetar um mock em um atributo, você pode usar a anotação @InjectMocks em conjunto com a anotação @Mock. Aqui está um exemplo:

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceTest {

    @Autowired
    @InjectMocks
    private MyService myService;

    @Mock
    private MyDependency myDependency;

    // Restante do código do teste...
}
```

## Configurando Comportamentos dos Mocks

Após criar um mock, você pode configurar comportamentos específicos para ele usando o Mockito. Aqui estão alguns exemplos:

### Configurando Retornos

```java
// Dentro do método de configuração do teste...
when(myDependency.someMethod()).thenReturn(expectedResult);
```

### Verificando Chamadas de Métodos

Você também pode verificar se determinados métodos foram chamados nos seus mocks:

```java
// Após executar o método que deveria chamar o método do mock...
verify(myDependency).someMethod();
```

## Integração com o Spring Framework

Para integrar os mocks do Mockito com componentes do Spring, você pode usar as anotações `@RunWith(SpringRunner.class)` e `@MockBean`. Aqui está um exemplo:

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceTest {

    @MockBean
    private MyDependency myDependency;

    // Restante do código do teste...
}
```