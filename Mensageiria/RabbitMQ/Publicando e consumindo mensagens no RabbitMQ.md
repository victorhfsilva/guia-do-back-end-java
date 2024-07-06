# Publicando e consumindo mensagens no RabbitMQ usando Docker e Java

### Pré-requisitos

Antes de começar, certifique-se de ter o Docker instalado em sua máquina. Você pode baixar e instalar o Docker a partir do [site oficial](https://www.docker.com/products/docker-desktop).

### Iniciando o RabbitMQ com Docker

Vamos usar uma imagem oficial do RabbitMQ disponível no Docker Hub.

1. **Puxar a imagem do RabbitMQ:**

   Execute o seguinte comando no terminal para baixar a imagem do RabbitMQ:

   ```sh
   docker pull rabbitmq:3-management
   ```

2. **Rodar o contêiner do RabbitMQ:**

   Execute o seguinte comando para iniciar um contêiner do RabbitMQ com a interface de gerenciamento habilitada:

   ```sh
   docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
   ```

   Esse comando faz o seguinte:
   - `-d` executa o contêiner em segundo plano.
   - `--name rabbitmq` dá um nome ao contêiner.
   - `-p 5672:5672` mapeia a porta 5672 do contêiner (porta padrão do RabbitMQ) para a porta 5672 da sua máquina.
   - `-p 15672:15672` mapeia a porta 15672 do contêiner (porta do console de gerenciamento) para a porta 15672 da sua máquina.

    Para iniciar um container definindo o usuário e senha padrão:

    ```sh
    $ docker run -d --name some-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
    ```
3. **Verificar se o RabbitMQ está rodando:**

   Você pode acessar o console de gerenciamento do RabbitMQ através do navegador em `http://localhost:15672`. Use as credenciais definidas previamente ou as credenciais padrão:
   - Username: `guest`
   - Password: `guest`

### Configuração do RabbitMQ no Spring Boot

Com o RabbitMQ rodando, agora vamos configurar um projeto Spring Boot para se comunicar com o RabbitMQ.

1. **Adicionar dependências:**

   No seu arquivo `pom.xml`, adicione as seguintes dependências:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-amqp</artifactId>
   </dependency>
   ```

2. **Configurar propriedades:**

   No arquivo `application.properties` ou `application.yml`, adicione as configurações para conectar ao RabbitMQ:

   ```properties
   spring.rabbitmq.host=localhost
   spring.rabbitmq.port=5672
   spring.rabbitmq.username=guest
   spring.rabbitmq.password=guest

   spring.rabbitmq.listener.simple.retry.enabled=true
   spring.rabbitmq.listener.simple.retry.max-attempts=4
   spring.rabbitmq.listener.simple.retry.initial-interval=5000
   spring.rabbitmq.listener.simple.retry.multiplier=2
   spring.rabbitmq.listener.simple.retry.max-interval=300000
   ```

   As últimas linhas definem se ele deve repetir a tentativa em caso de falha e a quantidade máxima de tentativas. Além disso elas definem o intervalo de tempo entre tentativas.

3. **Criar configuração do produtor de mensagens  do RabbitMQ:**

   Crie uma classe de configuração para definir a exchange:

   ```java
   @Configuration
   public class RabbitMQConfig {

        public static final String EXCHANGE_NAME = "example.exchange";

        @Bean
        public RabbitAdmin createRabbitAdmin(ConnectionFactory connection) {
            return new RabbitAdmin(connection);
        }

        @Bean
        public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin){
            return event -> rabbitAdmin.initialize();
        }

        @Bean
        public Jackson2JsonMessageConverter messageConverter(){
            return new Jackson2JsonMessageConverter();
        }

        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                            Jackson2JsonMessageConverter messageConverter){
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(messageConverter);
            return rabbitTemplate;
        }

        @Bean
        public TopicExchange exchange() {
            return new TopicExchange(EXCHANGE_NAME);
        }

   }
   ```

4. **Criar produtor de mensagens:**

   Crie um POJO Greeting:

   ```java
    @AllArgsConstructor
    @Builder
    @Getter
    public class Greeting {
        private String subject;
        private LocalDateTime timestamp;
    }
   ```

   Crie um serviço para enviar mensagens para a fila:

   ```java
   @Service
   public class RabbitMQSender {

       @Autowired
       private RabbitTemplate rabbitTemplate;

       public void send() {
            Greeting greeting = Greeting.builder()
                .subject("Victor")
                .timestamp(LocalDateTime.now())
                .build();
           rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "example.routingkey", greeting);
       }
   }
   ```

5. **Criar configuração de consumidor de mensagens  do RabbitMQ:**

   Crie uma classe de configuração para definir a fila, exchange e binding. As exchanges devem possuir o mesmo nome das declaradas na configuração do produtor.

   ```java
   @Configuration
   public class RabbitMQConfig {

        public static final String QUEUE_NAME = "example.queue";
        public static final String EXCHANGE_NAME = "example.exchange";

        @Bean
        public RabbitAdmin createRabbitAdmin(ConnectionFactory connection) {
            return new RabbitAdmin(connection);
        }

        @Bean
        public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin){
            return event -> rabbitAdmin.initialize();
        }

        @Bean
        public Jackson2JsonMessageConverter messageConverter(){
            return new Jackson2JsonMessageConverter();
        }

        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                            Jackson2JsonMessageConverter messageConverter){
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(messageConverter);
            return rabbitTemplate;
        }

        @Bean
        public Queue queue() {
            return QueueBuilder.nonDurable(QUEUE_NAME).build();
        }

        @Bean
        public TopicExchange exchange() {
            return new TopicExchange(EXCHANGE_NAME);
        }

        @Bean
        public Binding binding(TopicExchange exchange, Queue queue) {
            return BindingBuilder.bind(queue).to(exchange).with("example.#");
        }
   }
   ```

    * A chave de roteamento do binding `example.#` aceita todas as mensagens com chave de roteamento que comece com a palavra `example`.

6. **Criar consumidor de mensagens:**

   Crie um listener para consumir mensagens da fila:

   ```java
   @Service
   public class RabbitMQReceiver {

       @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
       public void receive(@Payload Greeting greeting) {
        String message = "Hello " + greeting.getSubject() + ". It is " + greeting.getTimestamp() + ".";
           System.out.println(message);
       }
   }
   ```

### Testando a Configuração

Crie um controlador para testar o envio de mensagens:

```java
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @GetMapping("/send")
    public void sendMessage() {
        rabbitMQSender.send();
    }
}
```

Agora, você pode enviar uma mensagem acessando a URL `http://localhost:8080/send` e verificar o console para ver a mensagem recebida.

### Conclusão

Seguindo este guia, você instalou e configurou o RabbitMQ usando Docker e integrou-o com um projeto Spring Boot. Agora você pode explorar mais recursos do RabbitMQ e ajustar a configuração conforme necessário para sua aplicação.