# JUnit Conditionals

O JUnit oferece recursos de condicionais que permitem controlar quais testes serão executados com base em diferentes condições, como sistema operacional, versão do JRE, expressões condicionais ou variáveis de ambiente.

## OnOS e OnJRE

A anotação `@EnabledOnOs` permite a execução condicional de testes com base no sistema operacional do computador.

A anotação `@DisabledOnJre` permite desativar a execução de testes com base na versão do JRE.

### Exemplo:

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

public class MyTest {

    @Test
    @EnabledOnOs(OS.MAC)
    public void test1() {
        System.out.println("Running test1 on macOS");
        // Se o SO for macOS, o teste será executado
    }

    @Test
    @EnabledOnOs({ OS.WINDOWS, OS.LINUX })
    public void test2() {
        System.out.println("Running test2 on Windows or Linux");
        // Se o SO for Windows ou Linux, o teste será executado
    }

    @Test
    @DisabledOnJre(JRE.JAVA_16)
    public void test3() {
        System.out.println("Running test3 on a JRE version other than 16");
        // Se a versão do JRE não for 16, o teste será executado
    }
}
```

## If

A anotação `@EnabledIf` permite que você execute testes com base em expressões condicionais.

### Exemplo:

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

public class MyTest {

    @Test
    @EnabledIf("Math.random() > 0.5")
    public void test1() {
        System.out.println("Running test1");
        // O teste será executado se a expressão for verdadeira
    }

    @Test
    @DisabledIf("System.getProperty(\"os.arch\").equals(\"arm64\")")
    public void test2() {
        System.out.println("Running test2");
        // O teste será desativado se a expressão for verdadeira
    }
}
```

## IfEnvironmentVariable

A anotação `@EnabledIfEnvironmentVariable` permite que você execute testes com base no valor de uma variável de ambiente.

### Exemplo:

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

public class MyTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "prod")
    public void test1() {
        System.out.println("Running test1 in production environment");
        // O teste será executado se a variável de ambiente ENVIRONMENT for igual a "prod"
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "DISABLED", matches = "true")
    public void test2() {
        System.out.println("Running test2 unless DISABLED environment variable is set to true");
        // O teste será desativado se a variável de ambiente DISABLED for igual a "true"
    }
}
```

Esses recursos de condicionais fornecem uma maneira poderosa de controlar a execução dos testes com base em diferentes condições e ambientes.