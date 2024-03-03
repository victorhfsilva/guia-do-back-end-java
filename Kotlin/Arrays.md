# Arrays em Kotlin

## **Introdução**

Arrays em Kotlin são usados para armazenar coleções de elementos. Existem dois tipos principais de arrays em Kotlin: arrays de tipos primitivos e arrays genéricos.

### **Array de Tipo Primitivo**

Arrays de tipos primitivos são especializados para armazenar dados primitivos, como inteiros, caracteres, etc. Eles têm desempenho melhor em comparação com arrays genéricos.

```kotlin
val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
val charArray: CharArray = charArrayOf('a', 'b', 'c')
```

### **Array Genérico**

Arrays genéricos são capazes de armazenar qualquer tipo de dado, incluindo tipos de dados personalizados.

```kotlin
val genericArray: Array<String> = arrayOf("Maçã", "Banana", "Pera")

val fruits = arrayOf<String>("Apple", "Mango", "Banana", "Orange")
```

## **Acessando e Modificando Elementos**

Os elementos de um array podem ser acessados usando a notação de colchetes. O índice começa em 0.

```kotlin
val frutas = arrayOf("Maçã", "Banana", "Pera")
println(frutas[1])  // Saída: Banana

frutas[2] = "Uva"
println(frutas[2])  // Saída: Uva
```

Ou utilizando as funções get() e set():

```kotlin
val fruits = arrayOf<String>("Apple", "Mango", "Banana", "Orange")

println( fruits.get(0))
println( fruits.get(3))

// Set the value at 3rd index
fruits.set(3, "Guava")
println( fruits.get(3)) 
```

## **Propriedade Size**

A propriedade `size` de um array retorna o número de elementos no array.

```kotlin
val frutas = arrayOf("Maçã", "Banana", "Pera")
println(frutas.size)  // Saída: 3
```

## **Loop em Arrays**

Podemos percorrer os elementos de um array usando loops, como o `for`.

```kotlin
val frutas = arrayOf("Maçã", "Banana", "Pera")
for (fruta in frutas) {
    println(fruta)
}
```

## **Operador `in`**

O operador `in` é usado para verificar se um elemento está presente em um array.

```kotlin
val frutas = arrayOf("Maçã", "Banana", "Pera")
if ("Banana" in frutas) {
    println("Banana está na lista!")
}
```

## **Removendo Duplicatas com `distinct`**

A função `distinct` remove elementos duplicados de um array.

```kotlin
val numeros = arrayOf(1, 2, 3, 2, 4, 5, 1)
val numerosSemDuplicatas = numeros.distinct()
println(numerosSemDuplicatas.joinToString())  // Saída: 1, 2, 3, 4, 5
```

## **Funções `drop` e `dropLast`**

As funções `drop` e `dropLast` removem os primeiros ou últimos elementos do array.

```kotlin
val frutas = arrayOf("Maçã", "Banana", "Pera")
val frutasSemPrimeira = frutas.drop(1)
val frutasSemUltima = frutas.dropLast(1)

println(frutasSemPrimeira.joinToString())  // Saída: Banana, Pera
println(frutasSemUltima.joinToString())    // Saída: Maçã, Banana
```

## **Verificando se um Array Está Vazio**

A função `isEmpty` verifica se um array está vazio.

```kotlin
val vazio: Boolean = frutas.isEmpty()
println(vazio)  // Saída: false
```

## **Conclusão**

Os arrays em Kotlin oferecem flexibilidade para lidar com coleções de elementos, independentemente de serem de tipos primitivos ou genéricos. Eles são uma parte essencial do desenvolvimento em Kotlin e são amplamente utilizados para armazenar e manipular dados.