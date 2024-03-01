# Cheatsheet: Expressões Lambda em Java

## Introdução

As expressões lambda são uma característica poderosa do Java que permitem criar funções anônimas de forma concisa. Elas são frequentemente usadas em programação funcional para passar comportamentos como argumentos de métodos ou para criar instâncias de interfaces funcionais.

## Sintaxe

Uma expressão lambda em Java possui a seguinte sintaxe geral:

```
(parametros) -> expressao
```

- Parâmetros: Lista de parâmetros separados por vírgulas, representando os argumentos da função.
- Setas (`->`): Separam os parâmetros da expressão.
- Expressão: Código a ser executado pela lambda, que pode ser uma única instrução ou um bloco de código.

## Exemplos

### Expressões Lambda Simples

```java
// Lambda que recebe dois inteiros e retorna a soma
(int a, int b) -> a + b

// Lambda que verifica se um número é par
(int n) -> n % 2 == 0

// Lambda que imprime uma mensagem
() -> System.out.println("Hello, Lambda!")
```

### Utilizando Expressões Lambda

```java
// Utilizando uma lambda em um método
int resultado = operacao((a, b) -> a + b);

// Utilizando uma lambda em um stream para imprimir números positivos
lista.stream().filter(n -> n > 0).forEach(System.out::println);
```

## Tipos de Expressões

### Expressões com Parâmetros

```java
// Lambda com parâmetros e retorno
(int a, int b) -> a + b
```

### Expressões sem Parâmetros

```java
// Lambda sem parâmetros e retorno
() -> "Hello, Lambda!"
```

### Expressões com Bloco de Código

```java
// Lambda com bloco de código e retorno
(int a, int b) -> {
    int soma = a + b;
    return soma;
}
```

## Interfaces Funcionais

As expressões lambda são frequentemente usadas com interfaces funcionais, que são interfaces que possuem apenas um método abstrato.

Exemplo:

```java
// Interface funcional para comparação
interface OperacaoMatematica {
    int calcular(int a, int b);
}

// Uso da interface funcional com lambda
OperacaoMatematica subtrair = (a, b) -> a - b;

resultado = subtrair.calcular(4,2));
```

## Method References

Os *Method References* permitem fazer referência a métodos existentes utilizando uma expressão lambda mais curta.

```java
// Referência a um método estático
Math::max

// Referência a um método de instância
String::length

// Referência a um construtor
ArrayList::new
```

## Utilizando Method References

```java
lista.stream().forEach(System.out::println);
```

## Considerações Finais

As expressões lambda são uma ferramenta poderosa para tornar o código mais conciso e legível. Elas são frequentemente usadas em programação funcional, streams e interfaces funcionais do Java. Ao usar expressões lambda, lembre-se de manter o código claro e evitar expressões excessivamente complexas.

## Exemplos

### Exemplo 1: Ordenação de uma lista de strings

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExamples {

    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();
        nomes.add("Alice");
        nomes.add("Bob");
        nomes.add("Charlie");
        
        // Ordenação utilizando lambda
        Collections.sort(nomes, (nome1, nome2) -> nome1.compareTo(nome2));
        
        System.out.println(nomes);
    }
}
```

### Exemplo 2: Filtrando números pares

```java
import java.util.ArrayList;
import java.util.List;

public class LambdaExamples {

    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        
        // Filtragem utilizando lambda
        List<Integer> pares = new ArrayList<>();
        numeros.forEach(num -> {
            if (num % 2 == 0) {
                pares.add(num);
            }
        });
        
        System.out.println(pares);
    }
}
```

### Exemplo 3: Interface Funcional e Lambda

```java
interface OperacaoMatematica {
    int calcular(int a, int b);
}

public class LambdaExamples {

    public static void main(String[] args) {
        // Uso de interface funcional com lambda
        OperacaoMatematica soma = (a, b) -> a + b;
        OperacaoMatematica subtracao = (a, b) -> a - b;
        
        int resultadoSoma = soma.calcular(5, 3);
        int resultadoSubtracao = subtracao.calcular(10, 7);
        
        System.out.println("Soma: " + resultadoSoma);
        System.out.println("Subtração: " + resultadoSubtracao);
    }
}
```

### Exemplo 4: Utilizando Method Reference

```java
import java.util.Arrays;

public class LambdaExamples {

    public static void main(String[] args) {
        String[] nomes = {"Alice", "Bob", "Charlie"};
        
        // Utilizando Method Reference para ordenar
        Arrays.sort(nomes, String::compareTo);
        
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }
}
```

### Exemplo 5: Interface Runnable com Lambda

```java
public class LambdaExamples {

    public static void main(String[] args) {
        // Uso de interface Runnable com lambda
        Runnable tarefa = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Tarefa executando...");
            }
        };
        
        Thread thread = new Thread(tarefa);
        thread.start();
    }
}
```

Esses exemplos mostram como as expressões lambda, interfaces funcionais e method references podem ser utilizados em diferentes situações para tornar o código mais conciso e legível.