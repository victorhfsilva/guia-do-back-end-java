# Tipos de Interfaces Funcionais em Java

## Introdução

As **Functional Interfaces** são interfaces que possuem exatamente um método abstrato, conhecido como **método funcional**. Elas são a base para a implementação de lambdas e expressões lambda em Java. A partir do Java 8, as functional interfaces são amplamente usadas para programação funcional, permitindo passar comportamentos como parâmetros para funções ou métodos.

### `Consumer<T>`

- Representa uma operação que aceita um argumento do tipo `T` e não retorna nenhum resultado.
- É usada quando se deseja executar uma ação em um objeto do tipo `T` sem retornar um valor.

```java
Consumer<String> printUpperCase = (str) -> System.out.println(str.toUpperCase());
printUpperCase.accept("hello"); // Imprime "HELLO"
```

### `Supplier<T>`

- Representa uma função que não recebe argumentos, mas retorna um resultado do tipo `T`.
- É usada quando se deseja gerar ou prover um valor.

```java
Supplier<Integer> randomNumber = () -> (int) (Math.random() * 100);
int num = randomNumber.get(); // Retorna um número aleatório entre 0 e 100
```

### `Function<T, R>`

- Representa uma função que recebe um argumento do tipo `T` e retorna um resultado do tipo `R`.
- É usada quando se deseja mapear um valor de um tipo para outro.

```java
Function<Integer, String> intToString = (num) -> "O número é: " + num;
String result = intToString.apply(42); // Retorna "O número é: 42"
```

### `Predicate<T>` 

- Representa uma função que recebe um argumento do tipo `T` e retorna um valor booleano.
- É usada para testar uma condição sobre o objeto do tipo `T`.

```java
Predicate<Integer> isEven = (num) -> num % 2 == 0;
boolean result = isEven.test(4); // Retorna true
```

### `BinaryOperator<T>`

- Representa uma função que recebe dois argumentos do tipo `T` e retorna um resultado do mesmo tipo `T`.
- É usada para operações binárias, como adição, multiplicação, etc.

```java
BinaryOperator<Integer> add = (a, b) -> a + b;
int result = add.apply(5, 3); // Retorna 8
```

### `BiFunction<T, U, R>`

A interface **BiFunction** representa uma função que aceita dois argumentos de tipos diferentes e retorna um resultado de outro tipo. Ela possui o método abstrato `apply(T t, U u)` para realizar a operação.

```java
BiFunction<Integer, String, String> biFunction = (num, str) -> num + ": " + str;
String result = biFunction.apply(42, "answer");
```

### `BiConsumer<T, U>` 

A interface **BiConsumer** representa um consumidor que aceita dois argumentos, mas não retorna um resultado. Ela possui o método abstrato `accept(T t, U u)` para realizar a operação.

```java
BiConsumer<String, Integer> biConsumer = (str, num) -> System.out.println(str + num);
biConsumer.accept("The answer is: ", 42);
```
