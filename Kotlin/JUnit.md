# JUnit em Kotlin

## Introdução ao JUnit em Kotlin

O JUnit é um framework de teste amplamente utilizado na linguagem Java, e sua integração com Kotlin proporciona uma experiência eficaz para realizar testes de unidade. Kotlin se beneficia das capacidades do JUnit para garantir a qualidade do código.

## Anotações Básicas do JUnit em Kotlin

O uso de anotações é essencial no JUnit para marcar os métodos que representam testes e suas configurações.

- `@Test`: Indica que o método é um teste.
- `@Before`: Executado antes de cada método de teste.
- `@After`: Executado após cada método de teste.
- `@BeforeClass`: Executado uma vez antes de todos os testes da classe.
- `@AfterClass`: Executado uma vez após todos os testes da classe.

## Asserts no JUnit em Kotlin

Assim como em Java, Kotlin utiliza métodos `assert` para verificar se o resultado esperado é igual ao resultado real.

- `assertEquals(expected, actual)`: Verifica se dois valores são iguais.
- `assertTrue(condition)`: Verifica se uma condição é verdadeira.
- `assertFalse(condition)`: Verifica se uma condição é falsa.
- `assertNull(object)`: Verifica se um objeto é nulo.
- `assertNotNull(object)`: Verifica se um objeto não é nulo.

## Testes de Exceção em Kotlin com JUnit

O JUnit em Kotlin permite testar se um método lança uma exceção esperada.

```kotlin
@Test(expected = MinhaExcecao::class)
fun testMetodoQueDeveLancarExcecao() {
    // Código que deve lançar MinhaExcecao
}
```

## Testes Parametrizados em Kotlin com JUnit

- Testes parametrizados permitem a execução do mesmo teste com diferentes conjuntos de dados.

- A anotação `@ParameterizedTest` indica que o método deve ser executado várias vezes com diferentes parâmetros. Em conjunto, é comum utilizar anotações como `@ValueSource` para fornecer os valores que serão utilizados nos testes.

```kotlin
class MinhaClasse {

    fun calcularDobro(numero: Int): Int {
        return numero * 2
    }
}

@ExtendWith(value = [ParameterizedTest::class])
@ValueSource(ints = [1, 2, 3])
fun testCalcularDobro(numero: Int) {
    // Arrange
    val minhaClasse = MinhaClasse()

    // Act
    val resultado = minhaClasse.calcularDobro(numero)

    // Assert
    assertEquals(numero * 2, resultado)
}

```

## Mocking em Kotlin com JUnit

O Mockito é uma biblioteca amplamente utilizada para criar mocks (objetos simulados) em testes. Ele permite simular o comportamento de objetos reais, especialmente útil quando você deseja isolar o código que está sendo testado de suas dependências.

Suponha que temos uma classe MinhaClasse com um método metodo que queremos testar. Utilizaremos o Mockito para criar um mock dessa classe e configurar seu comportamento.

```kotlin
class MinhaClasse {
    fun metodo(): String {
        // Lógica real que queremos testar
        return "Método Real"
    }
}
```

```kotlin
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class MinhaClasseTest {

    @Test
    fun testMetodoComMockito() {

        val minhaClasseMock = mock(MinhaClasse::class.java)

        // Configuração de comportamento do mock
        `when`(minhaClasseMock.metodo()).thenReturn("Mocked")

        // Act
        val resultado = minhaClasseMock.metodo()

        // Assert
        assertEquals("Mocked", resultado)
    }
}
```