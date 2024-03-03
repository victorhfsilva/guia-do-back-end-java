# For Loops em Kotlin

## **Introdução**

Os loops `for` em Kotlin oferecem uma maneira eficaz de iterar sobre elementos em uma coleção ou para realizar um conjunto de instruções um número específico de vezes.

## **For Loops com Ranges**

Os loops `for` em Kotlin são frequentemente utilizados com ranges para iterar sobre uma sequência de valores.

```kotlin
for (i in 1..5) {
    println("Número: $i")
}
```

## **Iterando sobre Coleções**

Você pode usar loops `for` para percorrer elementos em coleções, como listas, conjuntos ou arrays.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)

for (number in numbers) {
    println("Número: $number")
}
```

## **Iteração com Índices**

Caso precise acessar índices durante a iteração, você pode utilizar o método `indices` em uma coleção.

```kotlin
val colors = listOf("Vermelho", "Verde", "Azul")

for (index in colors.indices) {
    println("Cor: ${colors[index]}, Índice: $index")
}
```

## **Loops For Decrescentes**

Os loops `for` podem ser utilizados para iterações decrescentes especificando o operador `downTo`.

```kotlin
for (i in 5 downTo 1) {
    println("Número decrescente: $i")
}
```

## **Saltos com Step**

A instrução `step` pode ser utilizada para pular um número específico de elementos durante a iteração.

```kotlin
for (i in 1..10 step 2) {
    println("Número com passo 2: $i")
}
```

## **Loops com Iteradores Personalizados**

Além de ranges e coleções, você pode utilizar loops `for` com iteradores personalizados.

```kotlin
val iterator = customIterable.iterator()

for (element in iterator) {
    println("Elemento do iterador: $element")
}
```


