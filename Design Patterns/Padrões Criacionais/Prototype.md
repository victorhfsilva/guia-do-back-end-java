# **Padrão de Projeto Prototype**

### Introdução
O padrão de projeto Prototype é usado para criar novos objetos duplicando um objeto existente, conhecido como protótipo, usando um método de clonagem. Isso elimina a necessidade de instanciar novos objetos usando a palavra-chave `new`, o que pode ser útil quando a criação de objetos é complexa ou requer muitos recursos.

![Prototype](./images/prototype.png)

### Implementação
Vamos dar uma olhada em como implementar o padrão Prototype em Java:

1. **Crie a Interface Prototype**: Define uma interface para clonar objetos.

```java
public interface Prototype {
    Prototype clone();
}
```

2. **Implemente as Classes Concretas do Protótipo**: Implementa a interface Prototype para fornecer uma implementação de clonagem.

```java
public class ConcretePrototype1 implements Prototype {
    public ConcretePrototype1(ConcretePrototype1 source) {
        // ...
    }
    
    @Override
    public Prototype clone() {
        return new ConcretePrototype1(this);
    }
}

public class ConcretePrototype2 implements Prototype {
    public ConcretePrototype2(ConcretePrototype2 source) {
        // ...
    }
    
    @Override
    public Prototype clone() {
        return new ConcretePrototype2(this);
    }
}
```

3. **Cliente usa os Protótipos**: Utiliza o protótipo para criar novos objetos clonados.

```java
public class Client {
    public static void main(String[] args) {
        Prototype prototype1 = new ConcretePrototype1();
        Prototype clonedPrototype1 = prototype1.clone();
        
        Prototype prototype2 = new ConcretePrototype2();
        Prototype clonedPrototype2 = prototype2.clone();
    }
}
```

### Exemplo

```java
import java.util.ArrayList;
import java.util.List;

// Base prototype.
abstract class Shape implements Cloneable {
    protected int X;
    protected int Y;
    protected String color;

    // A regular constructor.
    public Shape() {
        // ...
    }

    // The prototype constructor. A fresh object is initialized
    // with values from the existing object.
    public Shape(Shape source) {
        this();
        this.X = source.X;
        this.Y = source.Y;
        this.color = source.color;
    }

    // The clone operation returns one of the Shape subclasses.
    abstract public Shape clone();
}


// Concrete prototype. The cloning method creates a new object
// in one go by calling the constructor of the current class and
// passing the current object as the constructor's argument.
// Performing all the actual copying in the constructor helps to
// keep the result consistent: the constructor will not return a
// result until the new object is fully built; thus, no object
// can have a reference to a partially-built clone.
class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(Rectangle source) {
        super(source);
        this.width = source.width;
        this.height = source.height;
    }

    public Shape clone() {
        return new Rectangle(this);
    }
}

class Circle extends Shape {
    private int radius;

    public Circle(Circle source) {
        super(source);
        this.radius = source.radius;
    }

    public Shape clone() {
        return new Circle(this);
    }
}

// Somewhere in the client code.
public class Application {
    private List<Shape> shapes = new ArrayList<>();

    public Application() {
        Circle circle = new Circle(null);
        circle.X = 10;
        circle.Y = 10;
        circle.radius = 20;
        shapes.add(circle);

        Circle anotherCircle = (Circle) circle.clone();
        shapes.add(anotherCircle);
        // The `anotherCircle` variable contains an exact copy
        // of the `circle` object.

        Rectangle rectangle = new Rectangle(null);
        rectangle.width = 10;
        rectangle.height = 20;
        shapes.add(rectangle);
    }

    public void businessLogic() {
        // Prototype rocks because it lets you produce a copy of
        // an object without knowing anything about its type.
        List<Shape> shapesCopy = new ArrayList<>();

        // For instance, we don't know the exact elements in the
        // shapes array. All we know is that they are all
        // shapes. But thanks to polymorphism, when we call the
        // `clone` method on a shape the program checks its real
        // class and runs the appropriate clone method defined
        // in that class. That's why we get proper clones
        // instead of a set of simple Shape objects.
        for (Shape s : shapes) {
            shapesCopy.add(s.clone());
        }

        // The `shapesCopy` array contains exact copies of the
        // `shape` array's children.
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.businessLogic();
    }
}
```

### Considerações Importantes
- O padrão Prototype pode ser útil quando a criação de objetos é cara ou complexa, e a instância de novos objetos através de clonagem é mais eficiente.
- Certifique-se de que as classes concretas implementam corretamente o método `clone()` para garantir que o objeto seja clonado corretamente.

### Vantagens do Padrão Prototype
- Reduz o custo de criação de objetos complexos.
- Proporciona uma alternativa para a criação de objetos usando `new`.
- Permite a adição fácil de novos tipos de protótipos.

### Conclusão
O padrão Prototype é uma ferramenta poderosa para criar novos objetos através da clonagem de protótipos existentes. Ele oferece uma abordagem flexível e eficiente para a criação de objetos em situações onde a instância direta pode ser impraticável ou ineficiente. Ao entender e aplicar corretamente o padrão Prototype, os desenvolvedores podem melhorar a flexibilidade e o desempenho de seus sistemas.