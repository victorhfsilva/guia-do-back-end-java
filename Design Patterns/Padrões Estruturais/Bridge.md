# Padrão de Projeto Bridge

O padrão de projeto Bridge é um dos padrões estruturais mais úteis na engenharia de software. Ele permite que você separe abstrações de suas implementações, permitindo que ambas variem independentemente. Isso é alcançado através da composição de objetos em vez de herança, o que resulta em um sistema mais flexível e extensível.

### Problema

Imagine que você esteja desenvolvendo um sistema de desenho que precisa manipular formas geométricas. As formas podem ter diferentes cores e, além disso, você quer ser capaz de desenhar essas formas em diferentes plataformas, como Windows ou Linux. O desafio aqui é como lidar com a combinação de diferentes formas, cores e plataformas de forma flexível e extensível.

### Solução

O padrão Bridge resolve esse problema separando as abstrações das implementações, permitindo que ambas possam variar independentemente umas das outras. Ele faz isso criando uma hierarquia de classes para a abstração e outra hierarquia de classes para a implementação. A abstração então contém uma referência para um objeto da implementação, permitindo que as duas hierarquias sejam alteradas independentemente.

### Exemplo 1

Vamos considerar um exemplo de um sistema de desenho que precisa desenhar diferentes formas em diferentes plataformas. Vamos usar o padrão Bridge para separar as formas geométricas de suas implementações de desenho em diferentes plataformas.

**Implementor**: Define a interface para a parte de implementação do objeto.

```java
// Implementor
interface DrawingAPI {
    void drawCircle(double x, double y, double radius);
    void drawRectangle(double x, double y, double width, double height);
}
```

**Concrete Implementor**: Fornece implementações concretas da interface Implementor.

```java
// Concrete Implementor 1: Desenha na plataforma A (por exemplo, Windows)
class DrawingAPIA implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.println("Drawing Circle [API A] at (" + x + ", " + y + ") with radius " + radius);
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        System.out.println("Drawing Rectangle [API A] at (" + x + ", " + y + ") with width " + width + " and height " + height);
    }
}

// Concrete Implementor 2: Desenha na plataforma B (por exemplo, Linux)
class DrawingAPIB implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.println("Drawing Circle [API B] at (" + x + ", " + y + ") with radius " + radius);
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        System.out.println("Drawing Rectangle [API B] at (" + x + ", " + y + ") with width " + width + " and height " + height);
    }
}
```

**Abstraction**: Define a interface para a parte abstrata do objeto e mantém uma referência para um objeto da hierarquia de implementação.

```java
// Abstraction
abstract class Shape {
    protected DrawingAPI drawingAPI;

    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw();
}
```

**Refined Abstraction**: Estende a interface definida pela Abstraction.

```java
// Refined Abstraction 1: Círculo
class Circle extends Shape {
    private double x, y, radius;

    public Circle(double x, double y, double radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }
}

// Refined Abstraction 2: Retângulo
class Rectangle extends Shape {
    private double x, y, width, height;

    public Rectangle(double x, double y, double width, double height, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        drawingAPI.drawRectangle(x, y, width, height);
    }
}
```

```java
// Exemplo de Uso
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(1, 2, 3, new DrawingAPIA());
        circle.draw();

        Shape rectangle = new Rectangle(2, 3, 4, 5, new DrawingAPIB());
        rectangle.draw();
    }
}
```

Neste exemplo, `DrawingAPI` é a interface Implementor que define as operações para desenhar formas geométricas em diferentes plataformas. `DrawingAPIA` e `DrawingAPIB` são as implementações concretas da interface `DrawingAPI`. `Shape` é a classe Abstraction que mantém uma referência para um objeto da hierarquia de implementação e fornece uma interface para desenhar formas geométricas. Circle e Rectangle são classes refinadas de abstração que estendem a classe Shape e implementam o método draw() de acordo com suas necessidades específicas.

### Exemplo 2 

![Exemplo de Bridge](./images/bridge-example.png)

```java
// A "abstração" define a interface para a parte de "controle"
// das duas hierarquias de classes. Ele mantém uma referência
// para um objeto da hierarquia de "implementação" e delega
// todo o trabalho real para esse objeto.
class RemoteControl {
    protected Device device;
    
    public RemoteControl(Device device) {
        this.device = device;
    }
    
    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }
    
    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }
    
    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }
    
    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }
    
    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}


// Você pode estender classes da hierarquia de abstração
// independentemente das classes de dispositivo.
class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }
    
    public void mute() {
        device.setVolume(0);
    }
}


// A interface "implementação" declara métodos comuns a todas
// as classes de implementação concretas. Não é necessário que corresponda
// a interface da abstração. Na verdade, as duas interfaces podem ser
// completamente diferentes. Tipicamente, a interface de implementação
// fornece apenas operações primitivas, enquanto a abstração
// define operações de nível mais alto baseadas nessas primitivas.
interface Device {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
}


// Todos os dispositivos seguem a mesma interface.
class Tv implements Device {
    // Implementação dos métodos da interface
}

class Radio implements Device {
    // Implementação dos métodos da interface
}


// Em algum lugar do código do cliente.
Tv tv = new Tv();
RemoteControl remote = new RemoteControl(tv);
remote.togglePower();

Radio radio = new Radio();
remote = new AdvancedRemoteControl(radio);
```