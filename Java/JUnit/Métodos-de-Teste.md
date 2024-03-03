# JUnit: Métodos de Teste

O JUnit é uma estrutura de teste amplamente utilizada para testar o comportamento de classes e métodos em Java. Vamos explorar os métodos de teste básicos e asserções disponíveis no JUnit.

## Anotação `@Test`

A anotação `@Test` é usada para indicar que um método é um método de teste. Os métodos anotados com `@Test` serão executados pelo JUnit como testes individuais.

## Asserções

As asserções são usadas para verificar se o comportamento do código testado é o esperado. O JUnit fornece várias asserções para verificar diferentes condições.

### `assertEquals()`

Verifica se dois valores são iguais.

```java
assertEquals(expected, actual);
```

### `assertTrue()` e `assertFalse()`

Verifica se uma condição é verdadeira ou falsa, respectivamente.

```java
assertTrue(condition);
assertFalse(condition);
```

### `assertArrayEquals()`

Verifica se dois arrays são iguais.

```java
assertArrayEquals(expectedArray, actualArray);
```

### `assertNotEquals()`

Verifica se dois valores não são iguais.

```java
assertNotEquals(expected, actual);
```

### `assertNull()` e `assertNotNull()`

Verifica se um valor é nulo ou não nulo, respectivamente.

```java
assertNull(value);
assertNotNull(value);
```

## Exemplo

Considere a seguinte classe `Calculator` que contém os métodos `add()` e `subtract()`:

```java
public class Calculator {

  public int add(int a, int b) {
    return a + b;
  }

  public int subtract(int a, int b) {
    return a - b;
  }
}
```

Aqui está um exemplo de classe de teste `CalculatorTest` que testa os métodos da classe `Calculator` usando asserções do JUnit:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

  @Test
  public void testAdd() {
    Calculator calculator = new Calculator();
    int result = calculator.add(2, 3);
    assertEquals(5, result);
  }

  @Test
  public void testSubtract() {
    Calculator calculator = new Calculator();
    int result = calculator.subtract(5, 3);
    assertEquals(2, result);
  }
}
```

Lembre-se de adicionar a dependência do JUnit ao seu projeto para executar os testes. Com as asserções e anotações apropriadas, o JUnit ajuda a garantir que seu código funcione conforme o esperado.