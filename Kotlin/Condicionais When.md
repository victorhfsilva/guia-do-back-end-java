# Condicionais When em Kotlin

## **Introdução**

Em Kotlin, a expressão `when` é uma poderosa ferramenta para lidar com múltiplas condições de forma clara e concisa. Ela substitui a necessidade de encadeamento excessivo de instruções `if...else` em situações em que você precisa avaliar várias condições.

## **Utilizando `when` como Expressão ou Declaração**

A expressão `when` pode ser usada tanto como uma expressão quanto como uma declaração. Quando utilizada como expressão, o valor do primeiro ramo que corresponder à condição é retornado.

### **Exemplo de Expressão `when`**

```kotlin
val day = 2

val result = when (day) {
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    4 -> "Thursday"
    5 -> "Friday"
    6 -> "Saturday"
    7 -> "Sunday"
    else -> "Invalid day."
}
println(result)
```

### **Exemplo de Declaração `when`**

```kotlin
val day = 2

when (day) {
    1 -> println("Monday")
    2 -> println("Tuesday")
    3 -> println("Wednesday")
    4 -> println("Thursday")
    5 -> println("Friday")
    6 -> println("Saturday")
    7 -> println("Sunday")
    else -> println("Invalid day.")
}
```

## **Combinação de Condições em `when`**

É possível combinar múltiplas condições em um único ramo utilizando vírgulas.

### **Exemplo de Combinação de Condições**

```kotlin
val day = 2

when (day) {
    1, 2, 3, 4, 5 -> println("Weekday")
    else -> println("Weekend")
}
```

## **Utilizando Ranges em Condições `when`**

Kotlin permite o uso de ranges para verificar se um valor está dentro de um intervalo específico.

### **Exemplo de Uso de Ranges**

```kotlin
val day = 2

when (day) {
    in 1..5 -> println("Weekday")
    else -> println("Weekend")
}
```

## **Expressões Arbitrárias em Condições `when`**

É possível utilizar expressões arbitrárias como condições em `when`, oferecendo maior flexibilidade.

### **Exemplo de Expressões Arbitrárias**

```kotlin
val x = 20
val y = 10
val z = 10

when (x) {
    (y + z) -> print("y + z = x = $x")
    else -> print("Condition is not satisfied")
}
```

## **Blocos de Código em Condições `when`**

Você pode utilizar blocos de código em cada ramo da expressão `when`, proporcionando maior extensibilidade.

### **Exemplo de Blocos de Código**

```kotlin
val day = 2

when (day) {
    1 -> {
        println("First day of the week")
        println("Monday")
    }
    2 -> {
        println("Second day of the week")
        println("Tuesday")
    }
    3 -> {
        println("Third day of the week")
        println("Wednesday")
    }
    4 -> println("Thursday")
    5 -> println("Friday")
    6 -> println("Saturday")
    7 -> println("Sunday")
    else -> println("Invalid day.")
}
```
