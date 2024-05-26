# Circuit Breaker do Resilience4j

O Resilience4j é uma biblioteca de tolerância a falhas leve, focada em aplicações Java, inspirada pelo Netflix Hystrix, mas projetada para o Java 8 e funcionalidades posteriores. Ele oferece vários módulos de resiliência, como Circuit Breaker, Rate Limiter, Retry, Bulkhead e TimeLimiter. 

### Configuração Inicial do Projeto

Para começar, certifique-se de que seu projeto Spring Boot esteja configurado com as dependências necessárias para usar o Resilience4j.

```gradle
ext {
  set('springCloudVersion', "2023.0.1")
}

dependencies {
  implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j'
  'org.springframework.boot:spring-boot-starter-aop'
  testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

dependencyManagement {
  imports {
    mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
  }
}
```

### Configuração do Circuit Breaker

Você pode configurar o Circuit Breaker no arquivo `application.yml` ou `application.properties`. Aqui está um exemplo usando `application.yml`:

```yaml
resilience4j.circuitbreaker:
  instances:
    backendA:
      registerHealthIndicator: true
      slidingWindowSize: 100
      minimumNumberOfCalls: 10
      permittedNumberOfCallsInHalfOpenState: 3
      automaticTransitionFromOpenToHalfOpenEnabled: true
      waitDurationInOpenState: 10000
      failureRateThreshold: 50
      eventConsumerBufferSize: 10
```

**Explicação das Configurações:**
- `registerHealthIndicator`: Registra o indicador de saúde para o Circuit Breaker.
- `slidingWindowSize`: Número de chamadas que são registradas no estado fechado.
- `minimumNumberOfCalls`: Número mínimo de chamadas que precisam ser registradas antes de calcular a taxa de falha.
- `permittedNumberOfCallsInHalfOpenState`: Número de chamadas permitidas quando o estado está meio aberto.
- `automaticTransitionFromOpenToHalfOpenEnabled`: Permite transição automática de aberto para meio aberto após um tempo definido.
- `waitDurationInOpenState`: Tempo em milissegundos que o circuito permanece aberto antes de passar para meio aberto.
- `failureRateThreshold`: Limite de taxa de falha para abrir o circuito.
- `eventConsumerBufferSize`: Tamanho do buffer de eventos de estado.

### Implementando o Circuit Breaker com Spring AOP

Você pode aplicar o Circuit Breaker a métodos específicos usando a anotação `@CircuitBreaker`. Por exemplo, para proteger um método que chama um serviço externo:

```java
public class UserService {

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallbackRetrieveUser")
    public User retrieveUser(String userId) {
        // código para chamar serviço externo
    }

    private User fallbackRetrieveUser(String userId, Throwable t) {
        // método de fallback se o circuit breaker abrir
        return new User("default-user", "default@example.com");
    }
}
```

### Monitoramento e Dashboard

O Resilience4j oferece suporte a endpoints Actuator para monitorar o status e os eventos do Circuit Breaker:

1. Adicione a dependência do Spring Boot Actuator:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

2. Verifique o estado do circuit breaker via `/actuator/health` e `/actuator/circuitbreakerevents`.


### Conclusão

O uso do Circuit Breaker do Resilience4j em aplicações Spring Boot ajuda a melhorar a resiliência e a estabilidade do sistema, permitindo gerenciar e isolar falhas em serviços externos de forma eficiente. Monitorar o status e a atividade do Circuit Breaker é crucial para ajustar suas configurações e melhorar a confiabilidade do serviço.