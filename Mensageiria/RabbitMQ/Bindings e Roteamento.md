# Bindings e Roteamento no RabbitMQ

No RabbitMQ, bindings e roteamento são fundamentais para direcionar mensagens de exchanges para filas específicas. Entender como esses mecanismos funcionam é essencial para configurar corretamente o fluxo de mensagens na sua aplicação.

### Bindings

**Bindings** são relações entre uma exchange e uma fila. Eles determinam como as mensagens devem ser roteadas de uma exchange para uma fila. Um binding pode incluir uma binding key, que especifica quais mensagens devem ser roteadas para a fila associada.

**Como funcionam os bindings:**
- **Binding sem chave de roteamento:** Em exchanges do tipo `fanout`, o binding simplesmente conecta a exchange à fila sem considerar nenhuma chave de roteamento.
- **Binding com chave de roteamento:** Em exchanges do tipo `direct` e `topic`, o binding inclui uma binding key que é comparada com a chave de roteamento das mensagens.

**Exemplo de Binding:**

```java
@Bean
public Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("example.key");
}
```

Nesse exemplo, a fila é ligada à exchange `DirectExchange` com a chave de roteamento `example.key`.

#### Roteamento de Mensagens

O roteamento de mensagens no RabbitMQ depende do tipo de exchange usada e das bindings configuradas. Vamos explorar como o roteamento funciona com os diferentes tipos de exchanges:

1. **Direct Exchange:**

   A `Direct Exchange` roteia mensagens para filas com base em uma chave de roteamento exata.

   **Exemplo:**
   - Exchange: `direct_logs`
   - Chave de roteamento da mensagem: "info"
   - Binding key da fila: "info"
   - A mensagem é roteada para a fila que tem a binding key "info".

   ```java
   @Bean
   public DirectExchange directExchange() {
       return new DirectExchange("direct_logs");
   }

   @Bean
   public Binding bindingInfoQueue(Queue infoQueue, DirectExchange directExchange) {
       return BindingBuilder.bind(infoQueue).to(directExchange).with("info");
   }
   ```

2. **Fanout Exchange:**

   A `Fanout Exchange` ignora a chave de roteamento e roteia a mensagem para todas as filas vinculadas a ela.

   **Exemplo:**
   - Exchange: `fanout_logs`
   - Mensagem é enviada para `fanout_logs`
   - Todas as filas vinculadas recebem a mensagem.

   ```java
   @Bean
   public FanoutExchange fanoutExchange() {
       return new FanoutExchange("fanout_logs");
   }

   @Bean
   public Binding bindingFanoutQueue1(Queue queue1, FanoutExchange fanoutExchange) {
       return BindingBuilder.bind(queue1).to(fanoutExchange);
   }

   @Bean
   public Binding bindingFanoutQueue2(Queue queue2, FanoutExchange fanoutExchange) {
       return BindingBuilder.bind(queue2).to(fanoutExchange);
   }
   ```

3. **Topic Exchange:**

   A `Topic Exchange` usa padrões na chave de roteamento para determinar para quais filas as mensagens devem ser roteadas. Os padrões podem incluir `*` (substitui exatamente uma palavra) e `#` (substitui zero ou mais palavras).

   **Exemplo:**
   - Exchange: `topic_logs`
   - Chave de roteamento da mensagem: "log.error.system"
   - Binding key da fila: "log.#"
   - A mensagem é roteada para a fila que tem a binding key "log.#".

   ```java
   @Bean
   public TopicExchange topicExchange() {
       return new TopicExchange("topic_logs");
   }

   @Bean
   public Binding bindingTopicQueue(Queue topicQueue, TopicExchange topicExchange) {
       return BindingBuilder.bind(topicQueue).to(topicExchange).with("log.#");
   }
   ```

4. **Headers Exchange:**

   A `Headers Exchange` usa os cabeçalhos das mensagens para determinar o roteamento, em vez de usar a chave de roteamento.

   **Exemplo:**
   - Exchange: `headers_logs`
   - Cabeçalhos da mensagem: `{"format": "pdf", "type": "report"}`
   - Binding da fila: `{"format": "pdf"}`
   - A mensagem é roteada para a fila que tem o cabeçalho correspondente "format: pdf".

   ```java
   @Bean
   public HeadersExchange headersExchange() {
       return new HeadersExchange("headers_logs");
   }

   @Bean
   public Binding bindingHeadersQueue(Queue headersQueue, HeadersExchange headersExchange) {
       return BindingBuilder.bind(headersQueue).to(headersExchange).where("format").matches("pdf");
   }
   ```

### Exemplos Práticos de Roteamento

Vamos ver alguns exemplos práticos de roteamento utilizando as diferentes exchanges:

**Exemplo 1: Direct Exchange**

```java
// Configuração da exchange
@Bean
public DirectExchange directExchange() {
    return new DirectExchange("direct_logs");
}

// Configuração das filas
@Bean
public Queue infoQueue() {
    return new Queue("infoQueue");
}

@Bean
public Queue errorQueue() {
    return new Queue("errorQueue");
}

// Bindings
@Bean
public Binding bindingInfoQueue(Queue infoQueue, DirectExchange directExchange) {
    return BindingBuilder.bind(infoQueue).to(directExchange).with("info");
}

@Bean
public Binding bindingErrorQueue(Queue errorQueue, DirectExchange directExchange) {
    return BindingBuilder.bind(errorQueue).to(directExchange).with("error");
}
```

**Exemplo 2: Fanout Exchange**

```java
// Configuração da exchange
@Bean
public FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanout_logs");
}

// Configuração das filas
@Bean
public Queue queue1() {
    return new Queue("queue1");
}

@Bean
public Queue queue2() {
    return new Queue("queue2");
}

// Bindings
@Bean
public Binding bindingFanoutQueue1(Queue queue1, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queue1).to(fanoutExchange);
}

@Bean
public Binding bindingFanoutQueue2(Queue queue2, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queue2).to(fanoutExchange);
}
```

**Exemplo 3: Topic Exchange**

```java
// Configuração da exchange
@Bean
public TopicExchange topicExchange() {
    return new TopicExchange("topic_logs");
}

// Configuração da fila
@Bean
public Queue topicQueue() {
    return new Queue("topicQueue");
}

// Binding
@Bean
public Binding bindingTopicQueue(Queue topicQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(topicQueue).to(topicExchange).with("log.#");
}
```

**Exemplo 4: Headers Exchange**

```java
// Configuração da exchange
@Bean
public HeadersExchange headersExchange() {
    return new HeadersExchange("headers_logs");
}

// Configuração da fila
@Bean
public Queue headersQueue() {
    return new Queue("headersQueue");
}

// Binding
@Bean
public Binding bindingHeadersQueue(Queue headersQueue, HeadersExchange headersExchange) {
    return BindingBuilder.bind(headersQueue).to(headersExchange).where("format").matches("pdf");
}
```

### Conclusão

Bindings e roteamento são componentes cruciais no RabbitMQ para direcionar mensagens de exchanges para filas específicas. Compreender como configurar bindings e como diferentes tipos de exchanges roteiam mensagens permite que você projete sistemas de mensagens eficazes e eficientes. Utilize esses conceitos e exemplos para configurar o RabbitMQ de acordo com as necessidades específicas da sua aplicação.