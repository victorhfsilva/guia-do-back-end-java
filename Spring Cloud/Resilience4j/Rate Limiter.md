# Rate Limiter

O Rate Limiter do Resilience4j é uma ferramenta eficaz para controlar a taxa de chamadas a recursos que podem ser limitados ou caros, como APIs externas ou microserviços. Este guia irá explicar como implementar o Rate Limiter do Resilience4j em uma aplicação Spring Boot, permitindo gerenciar melhor a carga em seus sistemas e evitar a sobrecarga.

### Configuração Inicial do Projeto

Certifique-se de que seu projeto Spring Boot está configurado com as dependências necessárias para usar o Resilience4j, incluindo o módulo específico para o Rate Limiter. Se estiver usando Maven, adicione o seguinte ao seu `pom.xml`:

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-ratelimiter</artifactId>
    <version>1.7.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

Para Gradle, adicione isso ao seu `build.gradle`:

```gradle
implementation 'io.github.resilience4j:resilience4j-ratelimiter:1.7.0'
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

### Configuração do Rate Limiter

Você pode configurar o Rate Limiter no arquivo `application.yml` ou `application.properties`. Aqui está um exemplo usando `application.yml`:

```yaml
resilience4j.ratelimiter:
  instances:
    rateLimiterA:
      limitForPeriod: 10
      limitRefreshPeriod: 1s
      timeoutDuration: 500ms
```

**Explicação das Configurações:**
- `limitForPeriod`: O número máximo de chamadas permitidas dentro do período de atualização.
- `limitRefreshPeriod`: O período de tempo durante o qual o limite de chamadas é calculado.
- `timeoutDuration`: O tempo máximo que uma chamada ao Rate Limiter irá esperar por permissão para proceder.

### Implementando o Rate Limiter com Spring AOP

Para aplicar o Rate Limiter a métodos específicos em seus serviços, você pode usar a anotação `@RateLimiter`. Por exemplo, para proteger um método que chama um serviço externo:

```java
public class ExternalService {

    @RateLimiter(name = "rateLimiterA")
    public String callExternalService() {
        // código para chamar serviço externo
        return "Success";
    }
}
```

### Tratamento de Exceções e Fallback

Quando uma chamada excede o limite definido, o Rate Limiter lançará uma exceção do tipo `RequestNotPermitted`. Você pode tratar essa exceção ou definir um método de fallback:

```java
public class ExternalService {

    @RateLimiter(name = "rateLimiterA", fallbackMethod = "fallbackMethod")
    public String callExternalService() {
        // código para chamar serviço externo
        return "Success";
    }

    private String fallbackMethod(RequestNotPermitted ex) {
        return "Rate limit exceeded";
    }
}
```

### Monitoramento e Dashboard

Integre com o Actuator do Spring Boot para monitorar o estado do seu Rate Limiter:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Você pode verificar o estado do Rate Limiter via endpoints do Actuator, como `/actuator/health` e `/actuator/ratelimiter-events`.


### Conclusão

Implementar o Rate Limiter do Resilience4j em sua aplicação Spring Boot ajuda a gerenciar a carga em serviços críticos, evitando sobrecargas e melhorando a estabilidade geral do sistema. Configurar, monitorar e ajustar suas políticas de rate limiting são passos essenciais para manter uma boa qualidade de serviço e resposta em ambientes de produção.

