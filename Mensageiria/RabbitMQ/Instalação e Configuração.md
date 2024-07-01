## Instalação e Configuração do RabbitMQ usando Docker

### 1. Pré-requisitos

Antes de começar, certifique-se de ter o Docker instalado em sua máquina. Você pode baixar e instalar o Docker a partir do [site oficial](https://www.docker.com/products/docker-desktop).

### 2. Iniciando o RabbitMQ com Docker

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

### 3. Configuração do RabbitMQ no Spring Boot

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
   ```

3. **Criar configuração do RabbitMQ:**

   Crie uma classe de configuração para definir a fila, exchange e binding:

   ```java
   @Configuration
   public class RabbitMQConfig {

       public static final String QUEUE_NAME = "example.queue";
       public static final String EXCHANGE_NAME = "example.exchange";

       @Bean
       public Queue queue() {
           return QueueBuilder.nonDurable(QUEUE_NAME).build();
       }

       @Bean
       public TopicExchange exchange() {
           return new TopicExchange(EXCHANGE_NAME);
       }

       @Bean
       public Binding binding(Queue queue, TopicExchange exchange) {
           return BindingBuilder.bind(queue).to(exchange).with("example.routingkey");
       }
   }
   ```

4. **Criar produtor de mensagens:**

   Crie um serviço para enviar mensagens para a fila:

   ```java
   @Service
   public class RabbitMQSender {

       @Autowired
       private RabbitTemplate rabbitTemplate;

       public void send(String message) {
           rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "example.routingkey", message);
       }
   }
   ```

5. **Criar consumidor de mensagens:**

   Crie um listener para consumir mensagens da fila:

   ```java
   @Service
   public class RabbitMQReceiver {

       @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
       public void receive(String message) {
           System.out.println("Received Message: " + message);
       }
   }
   ```

### 4. Testando a Configuração

Crie um controlador para testar o envio de mensagens:

```java
@RestController
public class RabbitMQController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        rabbitMQSender.send(message);
        return "Message sent: " + message;
    }
}
```

Agora, você pode enviar uma mensagem acessando a URL `http://localhost:8080/send?message=HelloWorld` e verificar o console para ver a mensagem recebida.

### Conclusão

Seguindo este guia, você instalou e configurou o RabbitMQ usando Docker e integrou-o com um projeto Spring Boot. Agora você pode explorar mais recursos do RabbitMQ e ajustar a configuração conforme necessário para sua aplicação.