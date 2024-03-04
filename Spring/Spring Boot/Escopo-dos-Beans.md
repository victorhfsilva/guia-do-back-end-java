#  Escopos de Beans

No ecossistema do Spring Boot, o conceito de escopo desempenha um papel importante ao determinar o ciclo de vida e a visibilidade dos objetos gerenciados pelo contêiner de inversão de controle (IoC). Existem vários escopos disponíveis, cada um servindo a diferentes necessidades. Vamos explorar os principais escopos e seus usos:

### Singleton

O escopo `singleton` é o padrão e garante que apenas uma instância de um bean seja criada e compartilhada em todo o contexto do aplicativo. Isso significa que todas as injeções do mesmo bean referenciarão a mesma instância.

```java
@Component
public class SingletonBean {
    // ...
}
```

### Prototype

No escopo `prototype`, uma nova instância de bean é criada toda vez que é solicitada pelo contêiner Spring. Isso é útil quando você deseja ter uma nova instância isolada a cada injeção.

```java
@Component
@Scope("prototype")
public class PrototypeBean {
    // ...
}
```

### Request

O escopo `request` é usado para beans associados a uma única solicitação HTTP. Cada solicitação terá uma nova instância do bean.

```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {
    // ...
}
```

### Session

O escopo `session` cria um bean separado para cada sessão do usuário. Cada vez que um usuário acessa a aplicação, um novo bean é criado e associado à sessão.

```java
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {
    // ...
}
```

### Application

O escopo `application` cria um único bean para todo o contexto da aplicação. Ele mantém o mesmo bean compartilhado entre todas as solicitações e sessões.

```java
@Component
@Scope(value = "application", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationScopedBean {
    // ...
}
```

### Websocket

O escopo `websocket` é usado em aplicativos WebSocket para criar um bean por sessão WebSocket.

```java
@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WebsocketScopedBean {
    // ...
}
```

