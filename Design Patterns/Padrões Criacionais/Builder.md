# **Builder em Java**

O padrão de projeto Builder é um padrão criacional que visa simplificar a criação de objetos complexos. Ele permite a construção de objetos passo a passo, facilitando a criação de objetos com múltiplos parâmetros ou configurações opcionais.

**Quando usar o padrão de projeto Builder?**

O padrão de projeto Builder é especialmente útil quando:

1. Você precisa criar objetos complexos com muitos parâmetros opcionais.
2. Você quer evitar construtores com muitos parâmetros ou métodos com muitos argumentos.
3. Você deseja fornecer uma interface fluente para criar objetos, tornando o código mais legível e fácil de entender.
4. Você quer garantir que os objetos criados estejam em um estado consistente.

## **Exemplo de Implementação:**

Vamos criar um exemplo de um builder para construir objetos de um carro.

**Product (Produto)**: Representa o objeto complexo que está sendo construído.
```java
// Produto
class Car {
    private String make;
    private String model;
    private int year;
    // outros atributos opcionais

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
    // getters e setters
}
```

**Builder (Construtor)**: Define uma interface para criar partes do produto.
```java
// Interface Builder
interface CarBuilder {
    CarBuilder setMake(String make);
    CarBuilder setModel(String model);
    CarBuilder setYear(int year);
    Car build();
}
```

**ConcreteBuilder (Construtor Concreto)**: Implementa a interface Builder para construir e reunir as partes do produto.
```java
// Concrete Builder
class CarBuilderImpl implements CarBuilder {
    private String make;
    private String model;
    private int year;

    @Override
    public CarBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    @Override
    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    @Override
    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    @Override
    public Car build() {
        return new Car(make, model, year);
    }
}
```

**Director (Diretor)**: Coordena o processo de construção usando o construtor para construir o produto final.

```java
// Director (opcional)
class CarDirector {
    private CarBuilder builder;

    public CarDirector(CarBuilder builder) {
        this.builder = builder;
    }

    public Car construct() {
        return builder.setMake("Toyota")
                      .setModel("Corolla")
                      .setYear(2022)
                      .build();
    }
}
```

```java
// Exemplo de Uso
public class Main {
    public static void main(String[] args) {
        CarBuilder builder = new CarBuilderImpl();
        Car car = builder.setMake("Ford")
                         .setModel("Mustang")
                         .setYear(2021)
                         .build();

        System.out.println(car);
    }
}
```

Neste exemplo, temos a classe `Car` que é o produto que queremos construir. O `CarBuilder` define a interface para construir partes do carro, e `CarBuilderImpl` é a implementação concreta desse builder. O `CarDirector` é opcional e pode ser usado para coordenar a construção do carro usando o builder.

### Exemplo

```java
// Usar o padrão Builder faz sentido apenas quando seus produtos
// são bastante complexos e requerem uma extensa configuração.
// Os dois produtos a seguir estão relacionados, embora não tenham
// uma interface comum.
class Carro {
    // Um carro pode ter um GPS, computador de bordo e algum número
    // de assentos. Modelos diferentes de carros (carro esportivo,
    // SUV, conversível) podem ter diferentes recursos instalados
    // ou ativados.
}

class Manual {
    // Cada carro deve ter um manual do usuário que corresponde à
    // configuração do carro e descreve todos os seus recursos.
}

// A interface Builder especifica métodos para criar as diferentes
// partes dos objetos de produto.
interface Builder {
    void reset();
    void setSeats(int seats);
    void setEngine(String engine);
    void setTripComputer(boolean tripComputer);
    void setGPS(boolean gps);
}

// As classes Concrete Builder seguem a interface Builder e fornecem
// implementações específicas das etapas de construção. Seu programa
// pode ter várias variações de builders, cada uma implementada de
// maneira diferente.
class CarBuilder implements Builder {
    private Carro carro;

    public CarBuilder() {
        this.reset();
    }

    public void reset() {
        this.carro = new Carro();
    }

    public void setSeats(int seats) {
        // Definir o número de assentos no carro.
    }

    public void setEngine(String engine) {
        // Instalar um determinado motor.
    }

    public void setTripComputer(boolean tripComputer) {
        // Instalar um computador de bordo.
    }

    public void setGPS(boolean gps) {
        // Instalar um sistema de posicionamento global.
    }

    public Carro getProduct() {
        Carro product = this.carro;
        this.reset();
        return product;
    }
}

// Ao contrário de outros padrões de criação, o Builder permite que
// você construa produtos que não seguem a interface comum.
class CarManualBuilder implements Builder {
    private Manual manual;

    public CarManualBuilder() {
        this.reset();
    }

    public void reset() {
        this.manual = new Manual();
    }

    public void setSeats(int seats) {
        // Documentar recursos do assento do carro.
    }

    public void setEngine(String engine) {
        // Adicionar instruções do motor.
    }

    public void setTripComputer(boolean tripComputer) {
        // Adicionar instruções do computador de bordo.
    }

    public void setGPS(boolean gps) {
        // Adicionar instruções do GPS.
    }

    public Manual getProduct() {
        Manual product = this.manual;
        this.reset();
        return product;
    }
}

// O diretor é apenas responsável por executar as etapas de
// construção em uma sequência específica. É útil quando se
// produz produtos de acordo com uma ordem ou configuração
// específica. Estritamente falando, a classe Director é opcional,
// já que o cliente pode controlar os builders diretamente.
class Director {
    public void constructSportsCar(Builder builder) {
        builder.reset();
        builder.setSeats(2);
        builder.setEngine("Motor esportivo");
        builder.setTripComputer(true);
        builder.setGPS(true);
    }

    public void constructSUV(Builder builder) {
        // ...
    }
}

// O cliente cria um objeto builder, passa-o para o diretor e
// inicia o processo de construção. O resultado final é obtido
// do objeto builder.
public class Aplicacao {
    public void makeCar() {
        Director director = new Director();

        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);
        Carro carro = builder.getProduct();

        CarManualBuilder builderManual = new CarManualBuilder();
        director.constructSportsCar(builderManual);
        Manual manual = builderManual.getProduct();
    }
}
```

**Benefícios do Padrão Builder:**

1. Facilita a criação de objetos complexos.
2. Permite a criação de objetos com múltiplos parâmetros ou configurações opcionais de forma fácil e legível.
3. Ajuda a garantir que os objetos criados estejam em um estado consistente.
