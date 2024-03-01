# Cheatsheet: Optional\<T\> em Java

## Introdução

O **Optional\<T\>** é uma classe introduzida no Java 8 que visa lidar de forma mais segura e elegante com valores que podem ser nulos (null). Ele encapsula um valor de um tipo específico **T** ou indica que o valor está ausente. O uso de **Optional\<T\>** ajuda a evitar erros de NullPointerException e a escrever um código mais legível.

## Criação de Optional

### Criar um Optional com um Valor Presente

```java
Optional<String> optionalValue = Optional.of("Hello, Optional!");
```

### Criar um Optional com Valor Possivelmente Nulo

```java
String nullableValue = null;
Optional<String> optionalNullableValue = Optional.ofNullable(nullableValue);
```

### Criar um Optional Vazio

```java
Optional<String> emptyOptional = Optional.empty();
```

## Métodos Principais

### isPresent()

Verifica se o Optional contém um valor.

```java
Optional<String> optionalValue = Optional.of("Hello");
boolean isPresent = optionalValue.isPresent(); // Retorna true
```

### get()

Recupera o valor contido no Optional. Cuidado, pode lançar NoSuchElementException se o valor estiver ausente.

```java
Optional<String> optionalValue = Optional.of("Hello");
String value = optionalValue.get(); // Retorna "Hello"
```

### orElse(T defaultValue)

Recupera o valor contido no Optional, ou retorna um valor padrão se o valor estiver ausente.

```java
Optional<String> optionalValue = Optional.empty();
String result = optionalValue.orElse("Default Value"); // Retorna "Default Value"
```

### orElseGet(Supplier<? extends T> supplier)

Recupera o valor contido no Optional, ou obtém um valor através de um Supplier se o valor estiver ausente.

```java
Optional<String> optionalValue = Optional.empty();
String result = optionalValue.orElseGet(() -> "Generated Value"); // Retorna "Generated Value"
```

### orElseThrow(Supplier<? extends X> exceptionSupplier)

Recupera o valor contido no Optional, ou lança uma exceção personalizada se o valor estiver ausente.

```java
Optional<String> optionalValue = Optional.empty();
optionalValue.orElseThrow(() -> new NoSuchElementException("Value not found"));
```

## Encadeamento de Métodos

Os métodos do **Optional** podem ser encadeados para realizar operações sequenciais em valores presentes.

```java
Optional<String> optionalValue = Optional.of("Hello");
optionalValue.map(String::toUpperCase).ifPresent(System.out::println); // Imprime "HELLO"
```

## Considerações Finais

O uso de **Optional\<T\>** pode melhorar significativamente a robustez e legibilidade do seu código, evitando exceções de **NullPointerException** e indicando explicitamente a presença ou ausência de valores. No entanto, é importante utilizá-lo com moderação e em situações apropriadas, uma vez que seu uso excessivo pode resultar em código mais complexo do que necessário.