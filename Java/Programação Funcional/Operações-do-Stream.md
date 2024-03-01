# Operações do Stream em Java

## Introdução

Os **Streams** em Java oferecem uma maneira eficiente e funcional de processar coleções de dados. Eles permitem executar operações em sequência ou paralelamente, tornando o código mais conciso e legível.

## Operações do Stream

### Operações Intermediárias

As operações de streams que podem se conectar entre si são chamadas de operações intermediárias. Elas podem se conectar porque a saída retornada é de tipo Stream. 

#### Filtragem 

- **filter(Predicate\<T\> predicate):** Toma um predicado (java.util.function.Predicate) como argumento e retorna uma stream incluindo todos os elementos que coincidem com o predicado indicado.
- **distinct():**  Retorna uma stream com elementos únicos (dependendo da implementação de equals para um elemento da stream).
- **limit(n):** Retorna uma stream cuja longitude máxima é n.
- **skip(n):** Retorna uma stream em que se descartaram os primeiros n números. 


#### Optional

Optional<T> (java.util .Optional) é uma classe invólucro que representa a existência ou ausência de um valor. Este método contém diversos métodos para comprovar a existência de um elemento.

- **ifPresent(Consumer\<T\> action):** Executa uma operação caso exista um valor no Optional. 

#### Mapeamento

- **map(Function\<T, R\> mapper):** Transforma elementos aplicando uma função.

- **mapToInt(Function\<T, R\> mapper):** Transforma os elementos aplicando uma função. Retorna uma Stream Primitiva de Inteiros.

- **mapToDouble(Function\<T, R\> mapper):** Transforma os elementos aplicando uma função. Retorna uma Stream Primitiva de Double.

- **mapToLong(Function\<T, R\> mapper):** Transforma os elementos aplicando uma função. Retorna uma Stream Primitiva de Long.

- **flatMap(Function\<T, Stream\<R\>\> mapper):** Transforma elementos em um novo **Stream** e, em seguida, os achatam.

#### Peek

- **peek(Consumer\<T\> action):** Permite executar uma ação em cada elemento do **Stream**, sem alterá-los.

#### Sort 

- **sorted():** Ordena os elementos.

### Operações Terminais

As operações que encerram um processo de stream são chamadas de operações terminais. Só é permitido utilizar uma operação terminal por stream. A partir de um processo, elas produzem um resultado de tipo List, Integer ou até void.

 As operações intermediárias não realizam tarefas de processamento até uma operação terminal ser chamada no processo de stream; são "preguiçosas". Isso é porque, frequentemente, a operação terminal pode "fundir" e processar diversas operações intermediárias em uma única ação.

#### Pesquisa e identificação de coincidências

- **anyMatch(Predicate\<T\> predicate):** Retorna verdadeiro se algum dos elementos satisfaz o predicato.

- **allMatch(Predicate\<T\> predicate):** Retorna verdadeiro se todos os elementos satisfazem o predicato.

- **noneMatch(Predicate\<T\> predicate):** Retorna verdadeiro se nenhum dos elementos satisfaz o predicato.

#### Find

- **findFirst():** Retorna um Optional que contém o primeiro elemento do stream caso este exista.

- **findAny():** Retorna um Optional que contém qualquer elemento do stream, caso estes existam.

#### forEach
- **forEach(Consumer\<T\> action):** Executa uma ação para cada elemento do **Stream**.

#### toArray

- **toArray():** Converte o **Stream** em um array.


#### Reduce

- **reduce(BinaryOperator\<T\> accumulator):** Reduz os elementos a um único valor aplicando um acumulador.

Exemplo:
```java
int sum = numbers.stream().reduce(0, (a, b) -> a + b); 
```

Caso o valor inicial da redução seja zero, está pode ser omitida.

#### Collect

- **collect(Collector\<T, A, R\> collector):** Coleta os elementos em uma estrutura de dados definida pelo coletor.

Exemplo:

```java
List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
```

```java
String listToString = productList.stream().map(Product::getName)
  .collect(Collectors.joining(", ", "[", "]"));
```

#### Math

- **min(Comparator\<T\> comparator):** Encontra o menor elemento com base no comparador.

- **max(Comparator\<T\> comparator):** Encontra o maior elemento com base no comparador.

- **count():** Conta o número de elementos no **Stream**.

- **sum():** Soma todos elementos. Só pode ser executado em Streams Primitivas como String de Double, Int e Long.

### Operações em Paralelo

- **parallel():** Transforma o **Stream** em um **Stream** paralelo.

- **sequential():** Transforma o **Stream** em um **Stream** sequencial.

### Gerando Streams

- **IntStream.range(x0, x1):** Gera uma stream de Inteiros entre o intervalo exclusivo entre x0 e x1. 

- **IntStream.rangeClosed(x0, x1):** Gera uma stream de Inteiros entre o intervalo inclusivo entre x0 e x1.

- **DoubleStream.range(x0, x1):** Gera uma stream de Double entre o intervalo exclusivo entre x0 e x1.

- **DoubleStream.rangeClosed(x0, x1):** Gera uma stream de Double entre o intervalo inclusivo entre x0 e x1.

- **LongStream.range(x0, x1):** Gera uma stream de Long entre o intervalo exclusivo entre x0 e x1.

- **LongStream.rangeClosed(x0, x1):** Gera uma stream de Long entre o intervalo inclusivo entre x0 e x1.

- **Stream.of(...values):** Gera uma stream com os valores inseridos como parâmetros.

- **Arrays.stream(array):** Gera uma stream do array inserido como parâmetro.

- **Files.lines(Paths.get(“yourFile.txt”), Charset.defaultCharset()):** Converte um arquivo em uma stream de linhas.

- **Pattern.compile(",").splitAsStream(string):** Divide uma string com base no padrão inserido como parâmetro em compile e gera um Stream de Strings.

- **Stream.iterate(x0, Supplier<T> supplier):** Gera uma stream infinita tomando x0 como valor inicial e aplicando a função inserida como parâmetro.

Exemplo:

```java
Stream<Integer> numbers = Stream.iterate(0, n -> n + 10);
```

## Exemplos

### Filtrando Números Pares e Dobrando-os

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evenDoubled = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .map(n -> n * 2)
                                   .collect(Collectors.toList());
```

### Encontrando o Maior Nome

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Optional<String> longestName = names.stream()
                                   .max(Comparator.comparing(String::length));
```

### Verificando se Todos os Números São Positivos

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
boolean allPositive = numbers.stream()
                            .allMatch(n -> n > 0);
```

## Considerações Finais

As operações de **Stream** em Java oferecem uma maneira poderosa e concisa de manipular e processar coleções de dados. Ao escolher as operações apropriadas para sua tarefa, você pode escrever código mais legível, eficiente e expressivo. Lembre-se de que algumas operações podem exigir uma compreensão mais profunda do funcionamento dos **Streams**, especialmente ao lidar com operações paralelas.