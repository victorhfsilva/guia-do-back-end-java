# Filas e Mensagens no RabbitMQ com Java

O RabbitMQ oferece uma variedade de recursos para criar e gerenciar filas e mensagens.

### Criando e Gerenciando Filas

As filas no RabbitMQ são criadas e gerenciadas através de comandos AMQP ou usando bibliotecas como a Spring AMQP no Java.

**Exemplo de criação de fila:**

```java
@Bean
public Queue exampleQueue() {
    return new Queue("exampleQueue");
}
```

### Persistência de Mensagens

Mensagens persistentes são armazenadas no disco, garantindo que não sejam perdidas em caso de falha do servidor RabbitMQ.

**Configuração de persistência:**

```java
@Bean
public Queue persistentQueue() {
    return new Queue("persistentQueue", true);
}
```

### TTL (Time to Live)

TTL define o tempo que uma mensagem pode permanecer na fila antes de ser descartada.

**Configuração de TTL:**

```java
@Bean
public Queue ttlQueue() {
    return QueueBuilder.durable("ttlQueue")
            .withArgument("x-message-ttl", 60000) // TTL de 60 segundos
            .build();
}
```

### Auto Expire

Define o tempo de vida de uma fila antes de ser automaticamente excluída.

**Configuração de Auto Expire:**

```java
@Bean
public Queue autoExpireQueue() {
    return QueueBuilder.durable("autoExpireQueue")
            .withArgument("x-expires", 300000) // 5 minutos
            .build();
}
```

### Overflow Behaviour

Define o comportamento da fila quando seu limite de tamanho é atingido.

**Configuração de Overflow Behaviour:**

```java
@Bean
public Queue overflowQueue() {
    return QueueBuilder.durable("overflowQueue")
            .withArgument("x-max-length", 1000) // Limite de 1000 mensagens
            .withArgument("x-overflow", "reject-publish")
            .build();
}
```

### Single Active Consumer

Garante que apenas um consumidor esteja ativo por vez, mesmo que vários consumidores estejam ouvindo na fila.

**Configuração de Single Active Consumer:**

```java
@Bean
public Queue singleActiveConsumerQueue() {
    return QueueBuilder.durable("singleActiveConsumerQueue")
            .withArgument("x-single-active-consumer", true)
            .build();
}
```

### Dead Letter Exchange e Dead Letter Routing Key

- Especifica a exchange e a chave de roteamento para mensagens mortas.
- Dead Letter Queues (DLQ) são filas para onde as mensagens são redirecionadas quando não podem ser entregues corretamente (por exemplo, após múltiplas tentativas de entrega).

**Configuração:**

```java
@Bean
public Queue mainQueueWithDLX() {
    return QueueBuilder.durable("mainQueueWithDLX")
            .withArgument("x-dead-letter-exchange", "deadLetterExchange")
            .withArgument("x-dead-letter-routing-key", "deadLetterKey")
            .build();
}

@Bean
public Queue deadLetterQueue() {
    return new Queue("deadLetterQueue");
}

@Bean
public DirectExchange deadLetterExchange() {
    return new DirectExchange("deadLetterExchange");
}
```

### Max Length e Max Length Bytes

Define o número máximo de mensagens ou o tamanho máximo total das mensagens na fila.

**Configuração de Max Length:**

```java
@Bean
public Queue maxLengthQueue() {
    return QueueBuilder.durable("maxLengthQueue")
            .withArgument("x-max-length", 500)
            .build();
}
```

**Configuração de Max Length Bytes:**

```java
@Bean
public Queue maxLengthBytesQueue() {
    return QueueBuilder.durable("maxLengthBytesQueue")
            .withArgument("x-max-length-bytes", 10485760) // 10 MB
            .build();
}
```

### Maximum Priority

Define a prioridade máxima das mensagens na fila.

**Configuração de Maximum Priority:**

```java
@Bean
public Queue priorityQueue() {
    return QueueBuilder.durable("priorityQueue")
            .withArgument("x-max-priority", 10)
            .build();
}
```

### Lazy Mode

As filas em modo lazy armazenam as mensagens no disco para economizar memória.

**Configuração de Lazy Mode:**

```java
@Bean
public Queue lazyQueue() {
    return QueueBuilder.durable("lazyQueue")
            .withArgument("x-queue-mode", "lazy")
            .build();
}
```

### Version

Especifica a versão da configuração da fila, geralmente usada para migrações e atualizações.

**Configuração de Version:**

```java
@Bean
public Queue versionedQueue() {
    return QueueBuilder.durable("versionedQueue")
            .withArgument("x-queue-version", 1)
            .build();
}
```

### Conclusão

O RabbitMQ oferece uma ampla gama de funcionalidades para configurar e gerenciar filas e mensagens, permitindo criar sistemas de mensagens altamente escaláveis e resilientes. Compreender e utilizar essas funcionalidades avançadas permite que você adapte o RabbitMQ às necessidades específicas da sua aplicação, garantindo eficiência e confiabilidade no processamento de mensagens.