# Spring Boot

## Configuration Properties

A anotação @ConfigurationProperties é uma funcionalidade do framework Spring que permite vincular propriedades de configuração externas a um objeto Java. Isso ajuda a manter as propriedades de configuração organizadas e centralizadas em um único local.

Com @ConfigurationProperties, é possível mapear as propriedades em um arquivo de configuração para um POJO (Plain Old Java Object). Isso torna mais fácil gerenciar e injetar propriedades de configuração em seu aplicativo.

Aqui está um exemplo:

Suponha que você tenha as seguintes propriedades em seu arquivo application.properties:

```
myapp.username=john
myapp.password=secret
myapp.timeout=5000
```

Você pode criar um POJO para armazenar essas propriedades assim:

```java
@ConfigurationProperties(prefix = "myapp")
public class MyAppProperties {
    private String username;
    private String password;
    private int timeout;
    
    // getters e setters
}
```

Neste exemplo, a anotação @ConfigurationProperties indica que as propriedades com o prefixo myapp devem ser mapeadas para esta classe. O prefixo é removido do nome da propriedade e a sequência restante é usada para corresponder ao nome do campo.

Agora, você pode injetar este POJO em seu aplicativo e usar as propriedades conforme necessário:

```java
@Service
public class MyService {
    private final MyAppProperties myAppProperties;
    
    public MyService(MyAppProperties myAppProperties) {
        this.myAppProperties = myAppProperties;
    }
    
    public void doSomething() {
        // use myAppProperties.getUsername(), myAppProperties.getPassword(), etc.
    }
}
```

Com essa configuração, você pode facilmente modificar as propriedades de configuração no arquivo application.properties e elas serão automaticamente mapeadas para o objeto MyAppProperties.