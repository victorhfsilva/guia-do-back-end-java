# Padrão de Projeto Visitor

O padrão Visitor é um padrão de design comportamental que permite adicionar novas operações a uma estrutura de objetos sem modificar essa estrutura. Ele separa algoritmos dos objetos aos quais eles operam, permitindo que você adicione novos comportamentos às classes existentes sem alterar seu código-fonte. Isso é útil quando você tem uma hierarquia de classes estável, mas deseja adicionar novas operações que podem variar frequentemente.

### Vantagens

- Adiciona novas operações às classes existentes sem modificá-las.
- Separa algoritmos dos objetos aos quais eles operam, promovendo o princípio de abertura/fechamento.
- Facilita a adição de novos comportamentos à hierarquia de classes existente.

### Quando usar o padrão Visitor?

- Quando você precisa adicionar novas operações a uma hierarquia de classes existente sem alterar essas classes.
- Quando você tem uma estrutura de objetos estável, mas deseja adicionar novos comportamentos que podem variar frequentemente.
- Quando você deseja evitar a proliferação de métodos de operações em classes existentes.

### Componentes do Padrão







### Exemplo de Implementação

Vamos considerar um exemplo de um sistema de modelagem 3D, onde temos diferentes tipos de formas geométricas (Elementos) e queremos calcular diferentes métricas para essas formas (Visitantes).

- **Visitor (Visitante)**: Define uma interface que declara um método de visita para cada tipo de elemento na hierarquia de classes. Este método de visita aceita um objeto do tipo específico e executa uma operação com base nesse tipo.

```java
// Interface para o Visitor
interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}
```

- **ConcreteVisitor (Visitante Concreto)**: Implementa a interface do Visitante e fornece implementações concretas dos métodos de visita para cada tipo de elemento na hierarquia de classes.
```java
// Visitante Concreto para calcular a área das formas
class AreaCalculator implements Visitor {
    public void visit(Circle circle) {
        // Calcular a área do círculo
    }

    public void visit(Rectangle rectangle) {
        // Calcular a área do retângulo
    }
}
```

- **Element (Elemento)**: Define uma interface que declara um método `accept()` que aceita um objeto do tipo Visitor. Este método de aceitação chama o método de visita apropriado no Visitor com uma referência a si mesmo.

```java
// Elemento Interface
interface Shape {
    void accept(Visitor visitor);
}
```

- **ConcreteElement (Elemento Concreto)**: Implementa a interface do Elemento e fornece uma implementação concreta do método `accept()`, que chama o método de visita apropriado no Visitor com uma referência a si mesmo.

```java
// Elemento Concreto - Círculo
class Circle implements Shape {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Elemento Concreto - Retângulo
class Rectangle implements Shape {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
```

- **Object Structure (Estrutura de Objetos)**: Representa uma estrutura ou coleção de objetos do tipo Element. Fornecerá um método `accept()` para cada tipo de elemento na estrutura de objetos.

```java
// Estrutura de Objetos
class ShapeCollection {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void accept(Visitor visitor) {
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
}
```

```java
// Exemplo de Uso
public class Main {
    public static void main(String[] args) {
        ShapeCollection collection = new ShapeCollection();
        collection.addShape(new Circle());
        collection.addShape(new Rectangle());

        Visitor areaCalculator = new AreaCalculator();
        collection.accept(areaCalculator);
    }
}
```

Neste exemplo, o padrão Visitor permite calcular a área de diferentes formas geométricas sem modificar as próprias formas. O visitante `AreaCalculator` implementa a lógica para calcular a área de cada forma, enquanto as formas geométricas (Elementos) fornecem um método `accept()` que permite que o visitante acesse e execute suas operações. Isso mantém as classes de formas geométricas independentes das operações que podem ser executadas nelas.