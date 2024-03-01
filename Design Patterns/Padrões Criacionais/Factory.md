# **Padrão de Projeto Factory**

O padrão de projeto Factory é um dos padrões criacionais mais comuns em Java. Ele fornece uma maneira de criar objetos sem expor a lógica de instanciação diretamente ao cliente. Em vez disso, o cliente chama um método de fábrica para obter uma instância do objeto desejado.

### Objetivo do Padrão Factory

O objetivo principal do padrão Factory é delegar a criação de objetos para uma classe separada, conhecida como fábrica. Isso permite que o código do cliente seja desacoplado das classes concretas que estão sendo instanciadas.

### Implementação do Padrão Factory

1. **Product (Produto)**: A interface ou classe abstrata que define o tipo de objetos que a fábrica pode criar.

```java
// 1. Product
interface Vehicle {
    void start();
}
```

2. **ConcreteProduct (Produto Concreto)**: As classes concretas que implementam ou estendem a interface do produto.

```java
// 2. Concrete Products
class Car implements Vehicle {
    public void start() {
        System.out.println("Car started.");
    }
}

class Motorcycle implements Vehicle {
    public void start() {
        System.out.println("Motorcycle started.");
    }
}
```


3. **Factory (Fábrica)**: A interface ou classe abstrata que declara o método de fábrica para criar objetos do tipo Product.

```java
// 3. Factory
interface VehicleFactory {
    Vehicle createVehicle();
}
```

4. **ConcreteFactory (Fábrica Concreta)**: As classes concretas que implementam o método de fábrica para criar instâncias específicas do ConcreteProduct.

```java
// 4. Concrete Factory
class CarFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Car();
    }
}

class MotorcycleFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Motorcycle();
    }
}
```

5. **Client (Cliente)**: A classe que utiliza a fábrica para obter instâncias do produto.

```java
// 5. Client
public class Client {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        car.start(); // Output: Car started.

        VehicleFactory motorcycleFactory = new MotorcycleFactory();
        Vehicle motorcycle = motorcycleFactory.createVehicle();
        motorcycle.start(); // Output: Motorcycle started.
    }
}
```

### Exemplo

![Exemplo de Factory](./images/factory-example.png)

```java
// A classe criadora declara o método de fábrica que deve
// retornar um objeto de uma classe de produto. As subclasses do criador
// geralmente fornecem a implementação deste método.
abstract class Dialog {
    // O criador também pode fornecer alguma implementação padrão
    // do método de fábrica.
    abstract Button createButton();

    // Observe que, apesar de seu nome, a responsabilidade principal do
    // criador não é criar produtos. Ele geralmente
    // contém alguma lógica de negócios principal que depende de objetos de produto
    // retornados pelo método de fábrica. As subclasses podem
    // mudar indiretamente essa lógica de negócios, substituindo o
    // método de fábrica e retornando um tipo diferente de produto
    // dele.
    void render() {
        // Chama o método de fábrica para criar um objeto de produto.
        Button okButton = createButton();
        // Agora, usa o produto.
        okButton.onClick(this::closeDialog);
        okButton.render();
    }

    private void closeDialog() {
        // Implementação para fechar o diálogo
    }
}

// Os criadores concretos substituem o método de fábrica para mudar
// o tipo do produto resultante.
class WindowsDialog extends Dialog {
    Button createButton() {
        return new WindowsButton();
    }
}

class WebDialog extends Dialog {
    Button createButton() {
        return new HTMLButton();
    }
}

// A interface do produto declara as operações que todos
// os produtos concretos devem implementar.
interface Button {
    void render();
    void onClick(ActionListener f);
}

// Os produtos concretos fornecem várias implementações do
// interface do produto.
class WindowsButton implements Button {
    public void render() {
        // Renderiza um botão no estilo Windows.
    }

    public void onClick(ActionListener f) {
        // Vincula um evento de clique nativo do SO.
    }
}

class HTMLButton implements Button {
    public void render() {
        // Retorna uma representação HTML de um botão.
    }

    public void onClick(ActionListener f) {
        // Vincula um evento de clique do navegador da Web.
    }
}

class Application {
    private Dialog dialog;

    // O aplicativo seleciona o tipo de criador dependendo do
    // configuração atual ou configurações de ambiente.
    void initialize() {
        String os = readApplicationConfigFile().getOS();

        if (os.equals("Windows")) {
            dialog = new WindowsDialog();
        } else if (os.equals("Web")) {
            dialog = new WebDialog();
        } else {
            throw new IllegalArgumentException("Erro! Sistema operacional desconhecido.");
        }
    }

    // O código cliente trabalha com uma instância de um criador concreto,
    // embora através de sua interface base. Enquanto
    // o cliente continuar trabalhando com o criador via o
    // interface base, você pode passar qualquer subclasse do criador para ele.
    void main() {
        initialize();
        dialog.render();
    }

    private Config readApplicationConfigFile() {
        // Implementação para ler as configurações do aplicativo
        return new Config();
    }
}

class Config {
    private String OS;

    public String getOS() {
        return OS;
    }

    // Implementação para obter o sistema operacional
}
```

### Quando usar o Padrão Factory

- Quando o código cliente precisa criar objetos, mas não deve se preocupar com as classes concretas desses objetos.
- Quando você deseja centralizar a lógica de criação de objetos em uma única classe ou método.
- Quando você quer desacoplar o código cliente das classes concretas, facilitando a manutenção e a extensão do código.

### Vantagens

- Reduz acoplamento, pois o código cliente não precisa conhecer as classes concretas dos objetos que está criando.
- Permite a adição de novos tipos de objetos sem modificar o código cliente.
