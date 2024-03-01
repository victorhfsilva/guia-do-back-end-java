# **Abstract Factory em Java**

O padrão Abstract Factory é um dos padrões de projeto criacionais mais utilizados em Java. Ele fornece uma maneira de encapsular a criação de famílias de objetos relacionados sem especificar suas classes concretas. Neste guia, vamos explorar o Abstract Factory em detalhes, incluindo sua definição, estrutura, implementação e exemplos de uso em Java.

### Definição

O padrão Abstract Factory é projetado para resolver o problema de criar conjuntos de objetos relacionados sem especificar suas classes concretas. Ele define uma interface para criar famílias de objetos relacionados, mas deixa a implementação dessas interfaces para subclasses específicas. Isso promove o princípio do design por contrato, onde o código cliente depende apenas de interfaces abstratas em vez de implementações concretas.

![Abstratc Factory](./images/abstract-factory.png)

### Implementação em Java

Vamos dar uma olhada em como implementar o padrão Abstract Factory em Java:

1. **Defina as interfaces para os produtos abstratos (Abstract Products)**: Define uma interface para cada tipo de produto na família de objetos relacionados. Cada interface representa um tipo de produto abstrato que pode ser criado pela fábrica.

   ```java
   interface Car {
       void drive();
   }

   interface Motorcycle {
       void ride();
   }
   ```

2. **Implemente classes concretas que implementam essas interfaces (Concrete Products)**:  Implementa a interface do produto abstrato para criar produtos concretos. Cada produto concreto corresponde a um tipo específico de produto na família.

   ```java
   class SportsCar implements Car {
       public void drive() {
           System.out.println("Driving sports car...");
       }
   }

   class Cruiser implements Motorcycle {
       public void ride() {
           System.out.println("Riding cruiser motorcycle...");
       }
   }
   ```

3. **Defina a interface para a fábrica abstrata (Abstract Factory)**: Define uma interface para criar famílias de objetos relacionados. Esta interface contém métodos para criar cada tipo de objeto na família.

   ```java
   interface VehicleFactory {
       Car createCar();
       Motorcycle createMotorcycle();
   }
   ```

4. **Implemente classes concretas que implementam a interface da fábrica abstrata (Concrete Factories)**: Implementa a interface Abstract Factory para criar objetos concretos dentro de uma família específica. Cada fábrica concreta é responsável por criar uma família completa de objetos relacionados.

   ```java
   class LuxuryVehicleFactory implements VehicleFactory {
       public Car createCar() {
           return new SportsCar();
       }
       public Motorcycle createMotorcycle() {
           return new Cruiser();
       }
   }
   ```

### Cliente

Vamos usar o Abstract Factory para criar veículos de luxo:

```java
VehicleFactory factory = new LuxuryVehicleFactory();
Car car = factory.createCar();
Motorcycle motorcycle = factory.createMotorcycle();

car.drive(); // Saída: "Driving sports car..."
motorcycle.ride(); // Saída: "Riding cruiser motorcycle..."
```

### Exemplo

![Exemplo de Abstratc Factory](./images/abstract-factory-example.png)

```java
// A interface da fábrica abstrata declara um conjunto de métodos que
// retornam diferentes produtos abstratos. Esses produtos são chamados
// de uma família e estão relacionados por um tema ou conceito de alto nível.
// Produtos de uma família geralmente são capazes de colaborar entre si.
// Uma família de produtos pode ter várias variantes,
// mas os produtos de uma variante são incompatíveis com
// os produtos de outra variante.
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// As fábricas concretas produzem uma família de produtos que pertencem
// a uma única variante. A fábrica garante que os
// produtos resultantes sejam compatíveis. As assinaturas dos métodos da fábrica concreta
// retornam um produto abstrato, enquanto dentro
// do método um produto concreto é instanciado.
class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

// Cada fábrica concreta tem uma variante de produto correspondente.
class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Cada produto distinto de uma família de produtos deve ter uma interface base.
// Todas as variantes do produto devem implementar esta interface.
interface Button {
    void paint();
}

// Os produtos concretos são criados pelas fábricas concretas correspondentes.
class WinButton implements Button {
    public void paint() {
        // Renderiza um botão no estilo Windows.
    }
}

class MacButton implements Button {
    public void paint() {
        // Renderiza um botão no estilo macOS.
    }
}

// Aqui está a interface base de outro produto. Todos os produtos
// podem interagir entre si, mas a interação adequada é
// possível apenas entre produtos da mesma variante concreta.
interface Checkbox {
    void paint();
}

class WinCheckbox implements Checkbox {
    public void paint() {
        // Renderiza uma caixa de seleção no estilo Windows.
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        // Renderiza uma caixa de seleção no estilo macOS.
    }
}

// O código do cliente trabalha com fábricas e produtos apenas
// através de tipos abstratos: GUIFactory, Button e Checkbox. Isso
// permite passar qualquer subclasse de fábrica ou produto para o código do cliente
// sem quebrá-lo.
class Application {
    private GUIFactory factory;
    private Button button;

    public Application(GUIFactory factory) {
        this.factory = factory;
    }

    public void createUI() {
        this.button = factory.createButton();
    }

    public void paint() {
        button.paint();
    }
}

// O aplicativo escolhe o tipo de fábrica dependendo da
// configuração atual ou configurações de ambiente e a cria
// em tempo de execução (geralmente na etapa de inicialização).
class ApplicationConfigurator {
    public static void main(String[] args) {
        Config config = readApplicationConfigFile();

        GUIFactory factory;
        if (config.getOS().equals("Windows")) {
            factory = new WinFactory();
        } else if (config.getOS().equals("Mac")) {
            factory = new MacFactory();
        } else {
            throw new IllegalArgumentException("Erro! Sistema operacional desconhecido.");
        }

        Application app = new Application(factory);
    }

    private static Config readApplicationConfigFile() {
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

### Conclusão

O padrão Abstract Factory é uma ferramenta poderosa para criar famílias de objetos relacionados de forma flexível e desacoplada em Java. Ele promove o princípio do design por contrato e facilita a adição de novos tipos de produtos sem modificar o código cliente existente. Ao entender e aplicar corretamente o padrão Abstract Factory em seus projetos Java, você pode criar sistemas mais flexíveis, modulares e fáceis de manter.