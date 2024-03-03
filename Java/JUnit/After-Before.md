# JUnit

## After e Before

O JUnit fornece anotações que permitem a execução de métodos antes e depois dos testes. Essas anotações são úteis para preparar o ambiente de teste, como inicialização de recursos, e também para limpar o ambiente após os testes.

### AfterAll e BeforeAll

A anotação @BeforeAll indica que este método deve ser executado antes dos testes.

Já a anotação @AfterAll indica que o método deve ser executado depois dos testes.

Exemplo:

```java
import org.junit.jupiter.api.*;

public class MyTest {

    @BeforeAll
    public static void setup() {
        System.out.println("Running setup before all tests");
        // Any setup code you want to run before all tests go here
    }

    @AfterAll
    public static void teardown() {
        System.out.println("Running teardown after all tests");
        // Any teardown code you want to run after all tests go here
    }

    @Test
    public void test1() {
        System.out.println("Running test1");
        // Your test code goes here
    }

    @Test
    public void test2() {
        System.out.println("Running test2");
        // Your test code goes here
    }
}
```

## AfterEach e BeforeEach

São executados antes ou depois de cada teste.

```java
import org.junit.jupiter.api.*;

public class MyTest {

    @BeforeEach
    public void setup() {
        System.out.println("Running setup before each test");
        // Any setup code you want to run before each test goes here
    }

    @AfterEach
    public void teardown() {
        System.out.println("Running teardown after each test");
        // Any teardown code you want to run after each test goes here
    }

    @Test
    public void test1() {
        System.out.println("Running test1");
        // Your test code goes here
    }

    @Test
    public void test2() {
        System.out.println("Running test2");
        // Your test code goes here
    }
}
```



