# **Mockito**

O Mockito é uma popular estrutura de simulação em Java usada para fins de teste. Ele permite que você crie objetos simulados (mocks) e verifique as interações com esses objetos durante os testes. Aqui está um guia rápido para começar com o Mockito:


### **Adicionando o Mockito ao Seu Projeto**

Adicione a dependência do Mockito ao seu arquivo de compilação (por exemplo, Gradle ou Maven):


#### Gradle:

```gradle
testImplementation 'org.mockito:mockito-core:3.11.2'
```


#### Maven:

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.11.2</version>
    <scope>test</scope>
</dependency>
```


### **Criando Objetos Simulados (Mocks)**

Você pode criar objetos simulados para suas interfaces ou classes.

```java
SuaClasse seuMock = Mockito.mock(SuaClasse.class);
```

Ou usando a anotação `@Mock`:

```java
@Mock
SuaClasse seuMock;
```


### **Anotações**

Você pode usar anotações para mocks em testes JUnit.

```java
@Mock
SuaClasse seuMock;

@InjectMocks
SuaClasseTestada classeTestada;
```


### **MockitoJUnitRunner**

Para utilizar estas anotações você deve utilizar o `MockitoJUnitRunner` para simplificar a configuração.

```java
@RunWith(MockitoJUnitRunner.class)
public class SeuTeste {
    @Mock
    SuaClasse seuMock;

    @InjectMocks
    SuaClasseTestada classeTestada;
}
```

Caso `MockitoJUnitRunner` não fosse utilizado o teste ficaria:

```java
public class SeuTeste {
    @BeforeAll
    void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    SuaClasse seuMock = Mockito.mock(SuaClasse.class);

    SuaClasseTestada classeTestada = new SuaClasseTestada();
}
```


### **Configurando Métodos Simulados**

Use `when` para especificar o comportamento de seus mocks.

```java
when(seuMock.algumMetodo()).thenReturn(umValor);
```


### **Matchers de Argumentos**

Você pode usar matchers de argumentos para tornar suas verificações mais flexíveis.

```java
when(seuMock.metodoComArgumentos(anyString(), eq(42))).thenReturn(umValor);
```


### **Simulando Exceções**

Você pode especificar que um método deve lançar uma exceção.

```java
when(seuMock.algumMetodo()).thenThrow(new AlgumaExcecao());
```


### **Verificando Chamadas de Métodos**

Use `verify` para verificar se um método foi chamado.

```java
verify(seuMock).algumMetodo();
```


### **Verificando o Número de Chamadas**

Você pode verificar quantas vezes um método foi chamado.

```java
verify(seuMock, times(2)).algumMetodo();
```


### **Redefinindo Mocks**

Se você desejar redefinir o comportamento de um mock, use `reset`.

```java
reset(seuMock);
```

### **Extensões do Mockito**

O Mockito possui extensões como o `Mockito-Kotlin` para melhor suporte ao Kotlin.

Para cenários mais avançados e documentação detalhada, consulte a documentação oficial do Mockito: [Documentação do Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/index.html).
