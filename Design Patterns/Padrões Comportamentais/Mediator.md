# Padrão de Projeto Mediator

O padrão Mediator é um padrão de design comportamental que define um objeto que encapsula como um conjunto de objetos interage. Ele promove o acoplamento fraco entre os componentes de um sistema, permitindo que eles se comuniquem de forma independente através do objeto Mediator, em vez de se comunicarem diretamente uns com os outros. Isso ajuda a reduzir o acoplamento entre os componentes e a simplificar a comunicação entre eles.

### Vantagens

- Desacoplamento: o padrão Mediator promove o desacoplamento entre os componentes de um sistema, permitindo que eles se comuniquem de forma indireta através de um objeto Mediator.
- Centralização: centraliza a lógica de comunicação entre os componentes em um único objeto Mediator, facilitando a manutenção e a evolução do sistema.
- Reutilização: os componentes podem ser reutilizados em diferentes contextos, pois sua lógica de comunicação é encapsulada pelo objeto Mediator.

### Quando usar o padrão Mediator?

- Quando um sistema tem um grande número de objetos que precisam se comunicar entre si de maneira complexa.
- Quando você quer promover o desacoplamento entre os componentes de um sistema.
- Quando você deseja centralizar a lógica de comunicação entre os componentes em um único objeto.

### Mediator

- Define uma interface para comunicação entre objetos.
- Encapsula a lógica de comunicação entre os objetos em um único local.
- Mantém referências para todos os objetos participantes.

**Exemplo**: Interface para o objeto Mediator.

```java
// Mediator
interface Mediator {
    void sendMessage(String message, Colleague colleague);
}
```

### Colega (Colleague)

- Define uma interface para interagir com o objeto Mediator.
- Mantém uma referência ao objeto Mediator.
- Comunica-se com outros colegas por meio do objeto Mediator.

**Exemplo**: Interface para os colegas que interagem com o objeto Mediator.

```java
// Colega
interface Colleague {
    void send(String message);
    void receive(String message);
}
```

### Mediator Concreto

- Implementa a interface Mediator.
- Mantém uma lista de colegas participantes.
- Encaminha mensagens entre os colegas.

**Exemplo**: Implementação do objeto Mediator.

```java
// Mediator Concreto
class ConcreteMediator implements Mediator {
    private List<Colleague> colleagues = new ArrayList<>();

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    public void sendMessage(String message, Colleague colleague) {
        for (Colleague c : colleagues) {
            if (c != colleague) {
                c.receive(message);
            }
        }
    }
}
```

### Colega Concreto (ConcreteColleague)

- Implementa a interface Colleague.
- Comunica-se com outros colegas por meio do objeto Mediator.

**Exemplo**: Implementação de um colega que interage com o objeto Mediator.

```java
// Colega Concreto
class ConcreteColleague implements Colleague {
    private Mediator mediator;

    public ConcreteColleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria um objeto Mediator.
        Mediator mediator = new ConcreteMediator();

        // Cria colegas e os registra no Mediator.
        Colleague colleague1 = new ConcreteColleague(mediator);
        Colleague colleague2 = new ConcreteColleague(mediator);
        mediator.addColleague(colleague1);
        mediator.addColleague(colleague2);

        // Envio de mensagens entre colegas através do Mediator.
        colleague1.send("Olá, como você está?");
        colleague2.send("Estou bem, obrigado!");
    }
}
```

Neste exemplo, o padrão Mediator é utilizado para facilitar a comunicação entre colegas de maneira indireta através de um objeto Mediator. Os colegas se comunicam enviando mensagens para o Mediator, que as encaminha para os outros colegas. Isso promove o desacoplamento entre os colegas e centraliza a lógica de comunicação em um único objeto, facilitando a manutenção e a evolução do sistema.