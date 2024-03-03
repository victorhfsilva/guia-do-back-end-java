# Padrão de Projeto Memento

O padrão Memento é um padrão de design comportamental que permite capturar e armazenar o estado interno de um objeto em um momento específico, de modo que o objeto possa ser restaurado para esse estado posteriormente. Ele é útil quando você precisa implementar a funcionalidade de desfazer (undo) ou restaurar o estado de um objeto para um estado anterior.

### Vantagens

- Controle de histórico: permite salvar e gerenciar o histórico de estados de um objeto.
- Desfazer (undo): facilita a implementação de funcionalidades de desfazer ações realizadas em um objeto.
- Restauração de estado: permite restaurar o estado de um objeto para um estado anterior.

### Quando usar o padrão Memento?

- Quando você precisa implementar funcionalidades de desfazer (undo) em um aplicativo.
- Quando você precisa armazenar e restaurar o estado de um objeto em diferentes pontos no tempo.
- Quando você deseja encapsular a lógica de armazenamento e restauração de estado em um objeto separado.

### Memento

- Mantém o estado interno de um objeto em um momento específico.
- Pode armazenar diferentes instantâneos do estado do objeto.
- Fornece métodos para obter e definir o estado do objeto.

**Exemplo**: Interface para o objeto Memento.

```java
// Memento
interface Memento {
    String getState();
}
```
### Memento Concreto (Concrete Memento)

- Implementa a interface Memento para armazenar o estado interno do Originator.
- Pode conter informações sobre o estado do Originator em um momento específico.

**Exemplo**: Implementação concreta do objeto Memento.

```java
// Memento Concreto
class ConcreteMemento implements Memento {
    private String state;

    public ConcreteMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
```

### Originador (Originator)

- Cria objetos Memento para capturar o estado interno.
- Usa objetos Memento para restaurar o estado interno.

**Exemplo**: Interface para o objeto Originator.

```java
// Originator
interface Originator {
    Memento saveState();
    void restoreState(Memento memento);
}
```

### Originador Concreto (Concrete Originator)

- Mantém o estado interno do objeto.
- Cria objetos Memento para capturar o estado interno.
- Usa objetos Memento para restaurar o estado interno.

**Exemplo**: Implementação concreta do objeto Originator.

```java
// Originator Concreto
class ConcreteOriginator implements Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveState() {
        return new ConcreteMemento(state);
    }

    public void restoreState(Memento memento) {
        this.state = ((ConcreteMemento) memento).getState();
    }
}
```

### Cuidador (Caretaker)

- Responsável por armazenar e gerenciar objetos Memento.
- Não deve modificar ou interpretar o estado armazenado no Memento.

**Exemplo**: Classe Caretaker.

```java
// Cuidador
class Caretaker {
    private List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria um Originator
        ConcreteOriginator originator = new ConcreteOriginator();

        // Cria um Caretaker para armazenar os Mementos
        Caretaker caretaker = new Caretaker();

        // Define o estado do Originator
        originator.setState("State1");
        // Salva o estado atual
        caretaker.addMemento(originator.saveState());

        // Altera o estado do Originator
        originator.setState("State2");
        // Salva o estado atual
        caretaker.addMemento(originator.saveState());

        // Restaura o estado anterior do Originator
        originator.restoreState(caretaker.getMemento(0));
    }
}
```

Neste exemplo, o padrão Memento é utilizado para capturar e armazenar o estado interno de um objeto (Originator) em diferentes momentos no tempo. O objeto Caretaker é responsável por gerenciar os objetos Memento e permitir a restauração do estado do Originator para um estado anterior, se necessário. Isso facilita a implementação de funcionalidades de desfazer (undo) e restauração de estado em um aplicativo.