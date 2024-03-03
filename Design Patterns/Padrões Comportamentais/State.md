# Padrão de Projeto State

O padrão State é um padrão de design comportamental que permite que um objeto altere seu comportamento quando seu estado interno muda. Ele encapsula estados diferentes em classes separadas e permite que o objeto altere de estado durante o tempo de execução, sem modificar sua estrutura. Isso é útil quando o comportamento de um objeto é altamente dependente de seu estado interno e quando as transições entre os estados precisam ser tratadas de forma limpa e organizada.

### Vantagens

- **Encapsulamento de estados**: cada estado é encapsulado em sua própria classe, facilitando a manutenção e a adição de novos estados.
- **Desacoplamento**: o objeto e seus estados ficam desacoplados, o que facilita a adição de novos estados ou a alteração do comportamento existente.
- **Facilidade de adição de novos estados**: é fácil adicionar novos estados sem alterar o código existente.

### Quando usar o padrão State?

- Quando o comportamento de um objeto depende fortemente de seu estado interno e precisa mudar dinamicamente durante o tempo de execução.
- Quando há muitos estados diferentes e a lógica condicional relacionada a eles está se tornando complexa e difícil de gerenciar.
- Quando você deseja evitar a poluição de uma classe com muitos métodos relacionados a diferentes estados.

![State](./images/state.png)

### Estado (State)

- Define uma interface para encapsular o comportamento associado a um estado particular do contexto.
- Implementa métodos que representam ações possíveis enquanto está nesse estado.

**Exemplo**: Interface para o estado do objeto.

```java
// Estado
interface State {
    void handle();
}
```

### Estado Concreto (Concrete State)

- Implementa a interface de Estado para fornecer comportamento específico para um estado particular.
- Pode acessar e modificar o contexto conforme necessário.

**Exemplo**: Implementação concreta de um estado do objeto.

```java
// Estado Concreto
class ConcreteStateA implements State {
    private Context context;

    public ConcreteStateA(Context context) {
        this.context = context;
    }

    public void handle() {
        // Implementa a lógica para o estado A.
        // Pode modificar o estado do contexto, se necessário.
        context.setState(new ConcreteStateB(context));
    }
}
```

### Contexto (Context)

- Define a interface para clientes interagirem com o objeto cujo estado pode mudar.
- Mantém uma referência para uma instância de um estado concreto.
- Encaminha as solicitações do cliente para o objeto de estado atual.

**Exemplo**: Interface para o contexto do objeto.

```java
// Contexto
interface Context {
    void request();
    void setState(State state);
}
```

### Contexto Concreto (Concrete Context)

- Implementa a interface do contexto e mantém uma referência para um estado concreto.
- Encaminha as solicitações do cliente para o estado atual.

**Exemplo:** Implementação concreta do contexto do objeto.

```java
// Contexto Concreto
class ConcreteContext implements Context {
    private State state;

    public void request() {
        state.handle();
    }

    public void setState(State state) {
        this.state = state;
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria o contexto
        Context context = new ConcreteContext();

        // Define o estado inicial
        context.setState(new ConcreteStateA(context));

        // Executa a ação no contexto
        context.request();
    }
}
```

No exemplo acima, o padrão State é utilizado para encapsular estados diferentes em classes separadas e permitir que um objeto mude seu comportamento dinamicamente durante o tempo de execução. O contexto mantém uma referência para uma instância de estado concreto e encaminha solicitações do cliente para o objeto de estado atual. Isso ajuda a manter o código limpo, organizado e desacoplado, facilitando a adição de novos estados e a alteração do comportamento existente.