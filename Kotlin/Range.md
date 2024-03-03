# Ranges em Kotlin

## **Introdução**

Ranges em Kotlin são uma forma poderosa e expressiva de representar intervalos de valores. Elas são frequentemente utilizadas em loops, estruturas condicionais e operações matemáticas.

### **Operador `..` e rangeTo**

O operador `..` e a função `rangeTo` são utilizados para criar ranges em Kotlin.

```kotlin
val range1 = 1..5    // Representação usando operador `..`
val range2 = 1.rangeTo(5)  // Representação usando rangeTo
```

## **downTo e step**

Os operadores `downTo` e `step` são usados para criar ranges decrescentes e especificar o passo entre os valores, respectivamente.

```kotlin
val decrescente = 5 downTo 1
val passo = 1..10 step 2
```

## **reversed**

A função `reversed` inverte a ordem dos elementos em um range.

```kotlin
val range = 1..5
val reversedRange = range.reversed()
```

## **until**

O operador `until` cria um range excluindo o último valor.

```kotlin
val exclusiveRange = 1 until 5  // Representa [1, 2, 3, 4]
```

## **Operações em Ranges**

### **Propriedades `first`, `last` e `step`**

As propriedades `first`, `last` e `step` fornecem o primeiro elemento, último elemento e o passo de um range, respectivamente.

```kotlin
val range = 1..10
val primeiro = range.first
val ultimo = range.last
val passo = range.step
```

### **Funções Utilitárias em Ranges**

- **`count`**: Retorna a contagem de elementos no range.
- **`min`**: Retorna o menor valor no range.
- **`max`**: Retorna o maior valor no range.
- **`sum`**: Retorna a soma de todos os elementos no range.
- **`average`**: Retorna a média dos elementos no range.

```kotlin
val range = 1..10
val contagem = range.count()
val menorValor = range.min()
val maiorValor = range.max()
val soma = range.sum()
val media = range.average()
```

## **Filtragem e Distinção em Ranges**

### **Filtrar com `filter`**

A função `filter` pode ser usada para criar um subconjunto de um range com base em uma condição.

```kotlin
val range = 1..10
val numerosPares = range.filter { T -> T % 2 == 0 }
```

### **Remover Duplicatas com `distinct`**

A função `distinct` remove elementos duplicados de um range.

```kotlin
val numerosDuplicados = (1..5) + (3..7)
val numerosDistintos = numerosDuplicados.distinct()
```

## **Conclusão**

Ranges em Kotlin fornecem uma maneira eficiente e concisa de representar conjuntos de valores. Elas são flexíveis e oferecem uma variedade de operações que simplificam o trabalho com intervalos de números. Ao entender as operações e funções disponíveis, você pode usar ranges de forma eficaz em seus programas Kotlin.