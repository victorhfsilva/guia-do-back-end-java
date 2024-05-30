# WebSocket com Spring

## Introdução ao WebSocket

WebSocket é um protocolo de comunicação que oferece canais de comunicação full-duplex sobre uma única conexão TCP. Ele é projetado para ser implementado em navegadores e servidores, permitindo comunicação em tempo real entre clientes e servidores.

## Configuração do Projeto

Primeiro, você precisará configurar seu projeto Spring Boot. Certifique-se de ter o Maven ou Gradle instalado. Vamos usar Maven neste exemplo.

### a. Criando um Novo Projeto Spring Boot

Você pode criar um novo projeto Spring Boot usando o [Spring Initializr](https://start.spring.io/).

- Selecione "Spring Web" e "Spring WebSocket" como dependências.
- Baixe o projeto gerado e extraia-o.

### b. Adicionando Dependências

Se você não usou o Spring Initializr, adicione as seguintes dependências ao seu arquivo `pom.xml`:

```xml
<dependencies>
    <!-- Dependência para o Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Dependência para o Spring WebSocket -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
</dependencies>
```

## Configuração do WebSocket

### a. Configuração Básica

Crie uma classe de configuração para configurar o WebSocket.

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-endpoint").setAllowedOriginPatterns("*");
        registry.addEndpoint("/websocket-endpoint").setAllowedOriginPatterns("*").withSockJS();
    }
}
```

Na configuração do WebSocket com Spring, usamos três métodos principais para configurar o comportamento do servidor WebSocket. Vamos entender cada um deles brevemente e de forma didática:

#### 1. `enableSimpleBroker("/topic")`

- **O que faz?** Configura um "broker" simples, que é como um entregador de mensagens. Ele sabe para onde as mensagens devem ir e entrega essas mensagens para os clientes corretos.
- **Como funciona?** Qualquer mensagem que for enviada para um destino que começa com `/topic` será tratada por esse broker e enviada para todos os clientes que estiverem inscritos nesse destino.

#### 2. `setApplicationDestinationPrefixes("/app")`

- **O que faz?** Define um prefixo para os destinos das mensagens enviadas pelos clientes.
- **Como funciona?** Quando um cliente envia uma mensagem para o servidor, ele usará uma URL que começa com `/app`. O servidor então saberá que essa mensagem deve ser processada por um método de controle antes de ser enviada para o broker ou outro destino.

#### 3. `addEndpoint("/websocket-endpoint")`

- **O que faz?** Registra o endpoint WebSocket que o cliente usará para se conectar ao servidor.
- **Como funciona?** Os clientes (como navegadores) se conectam a este endereço (`/websocket-endpoint`) para iniciar a comunicação WebSocket. Esse endpoint pode ser acessado diretamente ou através de um fallback como SockJS, que é útil para navegadores que não suportam WebSocket nativamente.


### b. Controlador de WebSocket

Crie um controlador para lidar com mensagens WebSocket.

```java
@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulando um processo demorado
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
```

Aqui:
- `@MessageMapping("/hello")` mapeia a mensagem recebida para o método `greeting`.
- `@SendTo("/topic/greetings")` envia a mensagem retornada para todos os assinantes do tópico.

### c. Modelos de Mensagens

Crie classes para as mensagens que serão trocadas.

```java
public class HelloMessage {
    private String name;

    public HelloMessage() {}

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Greeting {
    private String content;

    public Greeting() {}

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
```

## URLs e Seus Propósitos

Neste exemplo de implementação de WebSocket com Spring, diferentes URLs são usadas para configurar e interagir com o WebSocket. Abaixo, uma explicação detalhada do que cada URL faz:

### 1. `/websocket-endpoint`

#### Descrição:
Esta URL registra o endpoint WebSocket que os clientes usarão para se conectar ao servidor. Ela pode ser acessada diretamente por clientes WebSocket.

#### Configuração:
```java
@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket-endpoint").setAllowedOriginPatterns("*");
    registry.addEndpoint("/websocket-endpoint").setAllowedOriginPatterns("*").withSockJS();
}
```

#### Propósito:
- **Conexão Direta:** Clientes WebSocket podem se conectar diretamente a este endpoint.
- **Fallback para SockJS:** Se o navegador não suportar WebSocket, o SockJS fornecerá um fallback usando técnicas como long polling.

### 2. `/app/hello`

#### Descrição:
Esta URL é usada pelos clientes para enviar mensagens ao servidor. No contexto do Spring WebSocket, essa URL é mapeada para um método no controlador que trata as mensagens recebidas.

#### Configuração:
```java
@MessageMapping("/hello")
@SendTo("/topic/greetings")
public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulando um processo demorado
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
}
```

#### Propósito:
- **Envio de Mensagens:** Clientes enviam mensagens para o servidor através desta URL.
- **Processamento:** O servidor processa a mensagem recebida e gera uma resposta.

### 3. `/topic/greetings`

#### Descrição:
Esta URL é usada pelo servidor para enviar mensagens aos clientes. Qualquer cliente que esteja inscrito neste tópico receberá as mensagens enviadas pelo servidor.

#### Configuração:
```java
@SendTo("/topic/greetings")
public Greeting greeting(HelloMessage message) throws Exception {
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
}
```
#### Propósito:
- **Broadcasting:** O servidor envia mensagens para todos os clientes que estão inscritos neste tópico.
