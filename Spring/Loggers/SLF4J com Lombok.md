# Logging com Lombok e SLF4J

### Introdução
Lombok é uma biblioteca Java que ajuda a reduzir o código boilerplate. Uma das funcionalidades oferecidas pelo Lombok é a geração automática de loggers, incluindo loggers para a API SLF4J. Isso simplifica ainda mais o processo de logging em sua aplicação.

### Configuração do Projeto

#### Adicionando Dependências
Adicione as dependências do Lombok e SLF4J no seu arquivo `pom.xml` (para projetos Maven) ou `build.gradle` (para projetos Gradle).

**Gradle:**

```groovy
dependencies {
    // Lombok
    compileOnly 'org.projectlombok:lombok:1.18.22'
    annotationProcessor 'org.projectlombok:lombok:1.18.22'

    // SLF4J API
    implementation 'org.slf4j:slf4j-api:1.7.30'

    // Logback Classic
    implementation 'ch.qos.logback:logback-classic:1.2.3'
}
```

###  Usando Lombok para Logging com SLF4J

#### Anotação `@Slf4j`
Lombok fornece várias anotações para diferentes frameworks de logging. Para SLF4J, usamos a anotação `@Slf4j`.

```java
@Slf4j
public class MyClass {
    public void doSomething() {
        log.info("Doing something");
    }
}
```

#### Níveis de Log
A API de logging SLF4J suporta diferentes níveis de log. Com Lombok, você pode usar esses níveis diretamente através do logger gerado.

```java
@Slf4j
public class LoggingExample {
    public void performTask() {
        log.trace("This is a trace message");
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");
    }
}
```

#### Mensagens de Log com Variáveis
SLF4J permite incorporar variáveis nas mensagens de log usando placeholders `{}`.

```java
@Slf4j
public class UserService {
    public void createUser(String username) {
        log.info("Creating user with username: {}", username);
    }
}
```

#### Logando Exceções
Você pode registrar exceções juntamente com uma mensagem de log.

```java
@Slf4j
public class Calculator {
    public int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            log.error("An error occurred while dividing {} by {}", a, b, e);
            return 0;
        }
    }
}
```