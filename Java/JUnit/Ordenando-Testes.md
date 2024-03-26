# JUnit: Ordenando Testes

Às vezes, é importante controlar a ordem em que os testes são executados. O JUnit oferece diferentes maneiras de ordenar a execução dos testes.

## Anotação `@TestMethodOrder`

A anotação `@TestMethodOrder` é usada para especificar como os testes devem ser ordenados. Existem várias estratégias disponíveis para isso.

### `MethodOrderer.OrderAnnotation`

Usando esta estratégia, você pode usar a anotação `@Order` para indicar a ordem dos métodos de teste.

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyTest {

    @Test
    @Order(1)
    public void test1() {
        System.out.println("Running test1");
    }

    @Test
    @Order(3)
    public void test3() {
        System.out.println("Running test3");
    }

    @Test
    @Order(2)
    public void test2() {
        System.out.println("Running test2");
    }
}
```

### `MethodOrderer.MethodName`

Usando esta estratégia, os testes são ordenados pelo nome do método.

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MyTest {

    @Test
    public void test1() {
        System.out.println("Running test1");
    }

    @Test
    public void test3() {
        System.out.println("Running test3");
    }

    @Test
    public void test2() {
        System.out.println("Running test2");
    }
}
```

### `MethodOrderer.Random`

Usando esta estratégia, os testes são executados em ordem aleatória.

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;

@TestMethodOrder(MethodOrderer.Random.class)
public class MyTest {

    @Test
    public void test1() {
        System.out.println("Running test1");
    }

    @Test
    public void test3() {
        System.out.println("Running test3");
    }

    @Test
    public void test2() {
        System.out.println("Running test2");
    }
}
```

### `MethodOrderer.DisplayName`

Usando esta estratégia, os testes são ordenados pelo nome de exibição especificado pela anotação `@DisplayName`.

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class MyTest {

    @DisplayName("Teste 1")
    @Test
    public void test1() {
        System.out.println("Running test1");
    }

    @DisplayName("Teste 2")
    @Test
    public void test3() {
        System.out.println("Running test3");
    }

    @DisplayName("Teste 3")
    @Test
    public void test2() {
        System.out.println("Running test2");
    }
}
```

Escolha a estratégia de ordenação que melhor se adapte às suas necessidades de teste.