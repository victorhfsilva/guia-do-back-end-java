# JUnit: Testando Exceções

Testar o comportamento de exceções é uma parte importante da verificação da robustez de um código. O JUnit oferece maneiras de testar se os métodos lançam exceções esperadas.

## AssertThrows

A anotação `@Test` em conjunto com `assertThrows` é usada para verificar se um método lança uma exceção específica ao ser chamado.

```java
import org.junit.jupiter.api.*;

public class CalculatorTest {

    @Test
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        });
    }
}

class Calculator {
    public int divide(int num, int denom) {
        if (denom == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return num / denom;
    }
}
```

Neste exemplo, estamos testando se o método `divide` da classe `Calculator` lança uma exceção do tipo `ArithmeticException` quando ocorre uma divisão por zero.

## AssertDoesNotThrow

A anotação `@Test` em conjunto com `assertDoesNotThrow` é usada para verificar se um método não lança exceções ao ser chamado.

```java
import org.junit.jupiter.api.*;

public class MyTest {

    @Test
    public void test1() {
        String str = "Hello, world!";
        assertDoesNotThrow(() -> {
            str.length();
        });
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3};
        assertDoesNotThrow(() -> {
            arr[2] = 4;
        });
    }
}
```

Neste exemplo, estamos testando se o método `length` da classe `String` e a atribuição de um valor a um elemento do array `arr` não lançam exceções.

Testar exceções e seu comportamento é fundamental para garantir que o código funcione conforme o esperado em diferentes cenários.