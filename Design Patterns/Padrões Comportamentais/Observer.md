# Padrão de Projeto Observer

O padrão Observer é um padrão de design comportamental que permite que um objeto, chamado de "sujeito" (subject), mantenha uma lista de seus dependentes, chamados de "observadores" (observers), que são notificados automaticamente sobre quaisquer mudanças no estado do sujeito. Ele é útil quando você precisa criar uma relação de um para muitos entre objetos, onde a alteração em um objeto leva à atualização automática de outros objetos dependentes.

### Vantagens

- Desacoplamento: permite que o sujeito e os observadores evitem acoplamento direto, tornando-os independentes um do outro.
- Notificação automática: os observadores são notificados automaticamente quando ocorrem mudanças no estado do sujeito, eliminando a necessidade de verificações manuais.
- Suporte a broadcasting: um sujeito pode notificar vários observadores simultaneamente sobre uma mudança de estado.

### Quando usar o padrão Observer?

- Quando você precisa estabelecer uma relação de dependência um para muitos entre objetos.
- Quando você quer garantir que os observadores sejam automaticamente notificados sobre mudanças no estado do sujeito.
- Quando você precisa manter o desacoplamento entre o sujeito e os observadores.

### Sujeito (Subject)

- Mantém uma lista de observadores.
- Fornecer métodos para adicionar, remover e notificar observadores.
- Notifica os observadores sobre mudanças em seu estado.

**Exemplo**: Interface para o objeto Sujeito.

```java
// Sujeito
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
```

### Observador (Observer)

- Define um método de atualização que é chamado pelo sujeito quando seu estado muda.
- Registrado com o sujeito para receber notificações.

**Exemplo**: Interface para o objeto Observador.

```java
// Observador
interface Observer {
    void update();
}
```

### Observador Concreto (Concrete Observer)

- Implementa a interface Observer para receber notificações do sujeito.
- Mantém uma referência ao sujeito, se necessário.

**Exemplo**: Implementação concreta do objeto Observador.

```java
// Observador Concreto
class ConcreteObserver implements Observer {
    private String state;
    private Subject subject;

    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        this.state = subject.getState();
        // Realiza ações com base na mudança de estado.
    }
}
```

### Sujeito Concreto (Concrete Subject)

- Implementa a interface Subject para manter a lista de observadores e notificá-los sobre mudanças de estado.
- Mantém o estado interno que pode ser observado pelos observadores.

**Exemplo**: Implementação concreta do objeto Sujeito.

```java
// Sujeito Concreto
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    public String getState() {
        return state;
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria um sujeito
        ConcreteSubject subject = new ConcreteSubject();

        // Cria observadores e os anexa ao sujeito
        ConcreteObserver observer1 = new ConcreteObserver(subject);
        ConcreteObserver observer2 = new ConcreteObserver(subject);

        // Define o estado do sujeito
        subject.setState("Novo Estado");
    }
}
```

### Saída Esperada

Nenhuma saída é gerada, pois este é um exemplo de implementação do padrão Observer, que ilustra a relação entre o sujeito e os observadores. Quando o estado do sujeito muda, os observadores são automaticamente notificados e atualizam seu estado de acordo com a mudança.

Neste exemplo, o padrão Observer é utilizado para estabelecer uma relação de dependência entre um sujeito e seus observadores. Quando o estado do sujeito muda, todos os observadores registrados são automaticamente notificados e atualizam seu estado com base na mudança, sem a necessidade de uma comunicação direta entre o sujeito e os observadores. Isso ajuda a manter o desacoplamento entre os objetos, permitindo que eles evoluam independentemente uns dos outros.