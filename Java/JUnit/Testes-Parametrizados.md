# **Testes Parametrizados em Java**

### **Introdução**
Testes parametrizados são uma abordagem eficaz para verificar o comportamento de um método ou função com diferentes conjuntos de dados de entrada. O uso do `@MethodSource` e `@CsvSource` no JUnit permite criar testes parametrizados de forma limpa e organizada.


### **Escrevendo o Método de Teste Parametrizado**

O método de teste parametrizado é anotado com `@ParameterizedTest`, e o método de origem dos dados é fornecido como argumento. Este método de origem é responsável por fornecer os diferentes conjuntos de dados para o teste.

```java
@ParameterizedTest
@MethodSource("nomedometodo")
void meuTeste(int parametro1, String parametro2, boolean parametro3) {
    // Lógica do teste
}
```

### ** Escrevendo o Método de Origem dos Dados**

O método de origem dos dados é estático e retorna um fluxo ou matriz de argumentos para o método de teste parametrizado.

```java
private static Stream<Arguments> nomedometodo() {
    return Stream.of(
        Arguments.of(1, "abc", true),
        Arguments.of(2, "def", false),
        // Adicione mais conjuntos de dados conforme necessário
    );
}
```

### Utilizando o @CsvSource

Além do @MethodSource, o JUnit oferece a opção de usar o @CsvSource para ler os dados de um arquivo CSV.

```java
@ParameterizedTest
@CsvSource({
    "1, 'abc', true",
    "2, 'def', false",
    // Adicione mais linhas conforme necessário
})
void meuTeste(int param1, String param2, boolean param3) {
    // Lógica do teste
}
```

### **Exemplo**

Aqui está um exemplo completo de como testar um método simples que verifica se um número é par:

```java
public class ParidadeTest {

    @ParameterizedTest
    @MethodSource("valoresParaTeste")
    void testVerificarParidade(int numero, boolean esperado) {
        assertEquals(esperado, verificarParidade(numero));
    }

    private static Stream<Arguments> valoresParaTeste() {
        return Stream.of(
            Arguments.of(2, true),
            Arguments.of(3, false),
            Arguments.of(10, true),
            Arguments.of(15, false)
        );
    }

    private boolean verificarParidade(int numero) {
        return numero % 2 == 0;
    }
}
```