# Padrão de Projeto Decorator

O padrão Decorator é um padrão de design estrutural que permite adicionar comportamento a objetos individuais de forma dinâmica e transparente, sem afetar outros objetos da mesma classe. Ele é útil quando você deseja adicionar funcionalidades a um objeto de forma flexível e modular, evitando a criação de subclasses excessivas para cada combinação de funcionalidades.

### Vantagens

- Permite estender o comportamento de objetos individuais de maneira flexível e modular.
- Evita a explosão de subclasses ao adicionar várias funcionalidades a objetos.
- Promove o princípio da responsabilidade única, mantendo cada classe focada em uma única tarefa.

### Quando usar o padrão Decorator?

- Quando você precisa adicionar responsabilidades adicionais a objetos de forma dinâmica e flexível.
- Quando você quer evitar a criação de subclasses excessivas para cada combinação de funcionalidades.
- Quando você precisa de uma alternativa à herança para estender o comportamento de objetos.

### Componente

- Define a interface para objetos que podem ter responsabilidades adicionais anexadas a eles.
- Pode ser uma interface ou uma classe abstrata que implementa operações comuns.

**Exemplo**: Interface para componentes gráficos que podem ser decorados com bordas.

```java
// Componente
interface Component {
    void draw();
}
```

### Componentes Concretos

- Implementa o comportamento básico do componente.
- Pode ser decorado com responsabilidades adicionais por meio de decoradores.

**Exemplo**: Implementação concreta de um componente, como um botão.

```java
// Componente Concreto
class Button implements Component {
    public void draw() {
        System.out.println("Drawing a button");
    }
}
```

### Decorador

- Mantém uma referência para um objeto do tipo Component.
- Implementa a mesma interface do componente base.
- Encapsula o componente base e adiciona responsabilidades adicionais.
- Pode ser estendido por subclasses para adicionar funcionalidades específicas.

**Exemplo**: Decorador genérico para adicionar bordas a um componente.

```java
// Decorador
abstract class BorderDecorator implements Component {
    protected Component component;

    public BorderDecorator(Component component) {
        this.component = component;
    }

    public void draw() {
        component.draw();
        drawBorder();
    }

    protected abstract void drawBorder();
}
```

### Decoradores Concretos

- Estende o decorador para adicionar responsabilidades específicas.
- Implementa a funcionalidade adicional específica no método `drawBorder()`.

**Exemplo**: Decorador concreto para adicionar uma borda vermelha ao componente.

```java
// Decorador Concreto
class RedBorderDecorator extends BorderDecorator {
    public RedBorderDecorator(Component component) {
        super(component);
    }

    protected void drawBorder() {
        System.out.println("Drawing a red border");
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Componente original
        Component button = new Button();

        // Decorando o componente original com uma borda vermelha
        Component decoratedButton = new RedBorderDecorator(button);

        // Desenhando o componente decorado
        decoratedButton.draw();
    }
}
```

### Saída Esperada

```
Drawing a button
Drawing a red border
```

Neste exemplo, o padrão Decorator é usado para adicionar uma borda vermelha a um componente de botão existente. O botão original é encapsulado dentro do decorador, permitindo que a borda seja adicionada dinamicamente durante o tempo de execução. Isso evita a necessidade de criar subclasses separadas para cada variação de botão com borda.