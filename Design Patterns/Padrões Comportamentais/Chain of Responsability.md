# Padrão de Projeto Chain of Responsibility

O padrão Chain of Responsibility é um padrão de design comportamental que cria uma cadeia de objetos receptores para a solicitação e passa a solicitação ao longo da cadeia até que um objeto a processe. Isso permite que você desacople o remetente da solicitação dos destinatários e forneça uma maneira flexível de lidar com solicitações de maneira encapsulada.

### Vantagens

- Desacoplamento: o remetente da solicitação não precisa conhecer os destinatários específicos na cadeia, resultando em um código mais flexível e fácil de manter.
- Adiciona/remoção dinâmica de manipuladores: você pode adicionar ou remover facilmente novos manipuladores de solicitação à cadeia sem alterar o código do remetente.
- Encapsulamento: cada manipulador na cadeia é responsável apenas por processar a solicitação que pode manipular, isolando a lógica de processamento de solicitações.

### Quando usar o padrão Chain of Responsibility?

- Quando você tem múltiplos objetos que podem processar uma solicitação e deseja evitar acoplamento rígido entre o remetente e os destinatários.
- Quando o conjunto de objetos que podem processar a solicitação e a ordem na qual eles devem ser considerados podem variar dinamicamente durante a execução.
- Quando você deseja encapsular a lógica de processamento de solicitações em objetos separados e evitar que o remetente tenha conhecimento dos detalhes da implementação.

### Handler (Manipulador)

- Define uma interface para manipuladores de solicitação.

**Exemplo**: Interface para manipuladores de solicitação.

```java
// Manipulador
interface Handler {
    void handleRequest(Request request);
}
```

### ConcreteHandler (Manipulador Concreto)

- Implementa a interface Handler para processar solicitações específicas.
- Mantém uma referência opcional para o próximo manipulador na cadeia.
- Decide se pode processar a solicitação e a processa, ou a passa para o próximo manipulador na cadeia.

**Exemplo**: Implementação de um manipulador concreto na cadeia de responsabilidade.

```java
// Manipulador Concreto
class ConcreteHandler implements Handler {
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Request request) {
        if (canHandle(request)) {
            // Processa a solicitação.
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    private boolean canHandle(Request request) {
        // Verifica se este manipulador pode lidar com a solicitação.
    }
}
```

### Solicitação

- Define o formato das solicitações passadas ao longo da cadeia.
- Pode conter informações sobre a solicitação que ajudam os manipuladores a decidir se podem processá-la.

**Exemplo**: Classe de solicitação que contém informações sobre a solicitação.

```java
// Solicitação
class Request {
    // Atributos da solicitação.
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria os manipuladores da cadeia.
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        Handler handler3 = new ConcreteHandler();

        // Configura a ordem da cadeia.
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        // Envia uma solicitação pela cadeia.
        Request request = new Request();
        handler1.handleRequest(request);
    }
}
```

Neste exemplo, o padrão Chain of Responsibility é usado para processar uma solicitação ao longo de uma cadeia de manipuladores. O remetente da solicitação não precisa conhecer os detalhes dos manipuladores na cadeia, pois a solicitação é passada de um manipulador para o próximo até que seja processada. Cada manipulador decide se pode processar a solicitação ou a passa para o próximo manipulador na cadeia, resultando em um código flexível e desacoplado.

