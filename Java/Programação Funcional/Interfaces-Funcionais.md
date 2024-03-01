# Programação Funcional

## Functional Interface

Uma *Functional Interface* é uma interface que contém apenas um método abstrato. Ela é projetada para representar um único comportamento, que pode ser passado como um argumento para funções ou expressões.

Em muitos casos, você pode usar uma *Functional Interface* para simplificar a criação de implementações de interfaces simples, como um comportamento de callback. Isso é especialmente útil quando você só precisa fornecer uma implementação rápida e não quer criar uma classe separada.

As *Functional Interfaces* são frequentemente usadas em combinação com *expressões lambda*. Uma expressão lambda é uma forma concisa de representar uma implementação de um método abstrato de uma *Functional Interface*.

Exemplo:

Suponha que você tenha uma *Functional Interface* chamada `Calculadora`:

```java
@FunctionalInterface
interface Calculadora {
    int somar(int a, int b);
}
```

Você pode usar uma expressão lambda para criar rapidamente uma implementação dessa interface:

```java
public class ExemploFunctionalInterface {
   
   public static void main(String[] args) {
      
      // Crie uma expressão lambda que implementa a interface funcional
      Calculadora calculadora = (a, b) -> a + b;
      
      // Chame o método da interface funcional
      int resultado = calculadora.somar(2, 3);
      System.out.println("Resultado: " + resultado);
   
   }
}
```

As *Functional Interfaces* e as expressões lambda são características poderosas da programação funcional em Java, permitindo que você escreva código mais conciso e legível.