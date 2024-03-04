# **CommandLineRunner:**

### **Implementação da Interface:**

- Implemente a interface `CommandLineRunner` em uma classe de configuração Spring Boot.
- O método `run` desta interface será executado automaticamente toda vez que a aplicação Spring Boot for iniciada.

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MinhaCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // Código a ser executado quando a aplicação for iniciada
    }
}
```

### **Logger:**
   
- Use um Logger para registrar informações ao executar tarefas no método `run`.

```java
private static final Logger logger = LoggerFactory.getLogger(MinhaCommandLineRunner.class);

@Override
public void run(String... args) throws Exception {
    logger.info("Minha tarefa CommandLineRunner está sendo executada.");
    // Realize suas tarefas aqui
}
```

### **Passagem de Argumentos:**
- Os argumentos passados à aplicação podem ser acessados no parâmetro `args` do método `run`.

```java
@Override
public void run(String... args) throws Exception {
    if (args.length > 0) {
        logger.info("Argumentos passados à aplicação: " + String.join(", ", args));
    } else {
        logger.info("Nenhum argumento foi passado à aplicação.");
    }
}
```

### **Implementação Utilizando Lambda:**


```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunnerConfig {

   private static final Logger logger = LoggerFactory.getLogger(MinhaCommandLineRunner.class);

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return args -> {
            logger.info("Minha tarefa CommandLineRunner está sendo executada.");
            // Realize suas tarefas aqui
        };
    }
}
```

### **Dicas:**

- CommandLineRunner é útil para executar tarefas específicas no início da aplicação, como inicialização de dados, tarefas agendadas, etc.
- Use CommandLineRunner para tarefas que devem ser executadas uma única vez no início da aplicação.
