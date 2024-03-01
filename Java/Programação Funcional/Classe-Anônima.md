# Programação Funcional

## Classes Anônimas

Uma classe anônima é uma maneira de criar uma classe que não possui um nome próprio. Ela é definida e instanciada ao mesmo tempo, geralmente como uma forma mais concisa de implementar uma interface ou classe abstrata.

Imagine que você precisa criar uma classe que implemente uma interface ou estenda uma classe abstrata, mas você só vai usar essa classe uma vez. Em vez de criar uma classe separada com um nome, você pode usar uma classe anônima para criar uma implementação rápida e única.

Exemplo:

Suponha que você tenha a seguinte interface `Greeter`:

```java
public interface Greeter {
    void sayHello();
}
```

Em vez de criar uma classe separada para implementar essa interface, você pode usar uma classe anônima diretamente:

```java
public class ExemploClasseAnonima {
    public static void main(String[] args) {

        // Crie uma instância de uma classe anônima que implementa a interface Greeter
        Greeter greeter = new Greeter() {
            @Override
            public void sayHello() {
                System.out.println("Olá da classe anônima!");
            }
        };

        // Chame o método da instância da classe anônima
        greeter.sayHello();
    }
}
```

As classes anônimas podem ser úteis para implementações rápidas e temporárias de interfaces ou classes abstratas, evitando a criação de uma classe separada com nome.