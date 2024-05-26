# Retry

O módulo de Retry do Resilience4j permite a implementação de uma política de repetição de chamadas em caso de falhas, o que é crucial em ambientes distribuídos onde componentes e serviços podem ser temporariamente instáveis. Neste guia, mostrarei como configurar e utilizar o Retry do Resilience4j em uma aplicação Spring Boot para melhorar a resiliência de suas operações.

### Adicionar Dependências

Primeiro, certifique-se de que seu projeto Spring Boot tem as dependências necessárias para usar o Retry do Resilience4j. Se estiver usando Maven, adicione o seguinte ao seu `pom.xml`:

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-retry</artifactId>
    <version>1.7.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

Para Gradle, adicione isso ao seu `build.gradle`:

```gradle
implementation 'io.github.resilience4j:resilience4j-retry:1.7.0'
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

### Configurar o Retry

Você pode configurar as políticas de retry no arquivo `application.yml` ou `application.properties`. Aqui está um exemplo usando `application.yml`:

```yaml
resilience4j.retry:
  instances:
    retryService:
      maxRetryAttempts: 5
      waitDuration: 2000
      retryExceptions:
        - org.springframework.web.client.HttpServerErrorException
        - java.io.IOException
      ignoreExceptions:
        - org.springframework.web.client.HttpClientErrorException
```

**Explicação das Configurações:**
- `maxRetryAttempts`: Número máximo de tentativas de retry antes de desistir.
- `waitDuration`: Tempo de espera entre as tentativas, em milissegundos.
- `retryExceptions`: Lista de exceções que devem acionar uma tentativa de retry.
- `ignoreExceptions`: Lista de exceções que devem ser ignoradas, ou seja, não acionarão uma tentativa de retry.

### Implementar o Retry

Para aplicar o Retry a métodos específicos em seus serviços, você pode usar a anotação `@Retry`. Por exemplo, para proteger um método que faz uma chamada de rede:

```java
public class NetworkService {

    @Retry(name = "retryService")
    public String fetchData() {
        // código para chamar um serviço externo
        return "Data";
    }
}
```

### Método de Fallback

Para lidar com falhas após esgotadas todas as tentativas de retry, você pode definir um método de fallback:

```java
public class NetworkService {

    @Retry(name = "retryService", fallbackMethod = "fallbackMethod")
    public String fetchData() {
        // código que pode falhar
        return "Data";
    }

    private String fallbackMethod(Exception ex) {
        // método de fallback após falhas repetidas
        return "Fallback Data";
    }
}
```

### Monitoramento e Dashboard

Adicione suporte ao Actuator para monitorar os retries:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Você pode visualizar eventos de retry e verificar o estado usando endpoints do Actuator, como `/actuator/retryevents` e `/actuator/health`.

### Conclusão

Implementar o Retry do Resilience4j em sua aplicação Spring Boot ajuda a aumentar a resiliência frente a falhas intermitentes ou temporárias em serviços externos. Monitorar e ajustar as políticas de retry é crucial para manter uma operação estável e eficiente em ambientes de produção.