# Operadores em Kotlin

## **Operadores Aritméticos**

Os operadores aritméticos em Kotlin realizam operações matemáticas básicas.

```kotlin
val soma = 5 + 3
val subtracao = 7 - 2
val multiplicacao = 4 * 6
val divisao = 10 / 2
val resto = 15 % 4
```

## **Operadores Relacionais**

Operadores relacionais comparam valores e retornam resultados booleanos.

```kotlin
val igualdade = (5 == 5)
val desigualdade = (3 != 7)
val maiorQue = (8 > 3)
val menorQue = (4 < 9)
val maiorOuIgual = (6 >= 6)
val menorOuIgual = (2 <= 5)
```

## **Operadores de Atribuição**

Operadores de atribuição são usados para atribuir valores a variáveis.

```kotlin
var numero = 10
numero += 5  // Equivalente a: numero = numero + 5
numero -= 3  // Equivalente a: numero = numero - 3
numero *= 2  // Equivalente a: numero = numero * 2
numero /= 4  // Equivalente a: numero = numero / 4
numero %= 3  // Equivalente a: numero = numero % 3
```

## **Operadores Unários**

Operadores unários realizam operações em um único operando.

```kotlin
var positivo = +7
var negativo = -5

var incremento = 3
incremento++

var decremento = 8
decremento--
```

## **Operadores Lógicos**

Operadores lógicos realizam operações booleanas.

```kotlin
val and = true && false
val or = true || false
val not = !true
```

### Utilizando as Funções and() e or()

```kotlin
val resultadoAnd = true.and(false)  // Equivalente a: true && false
val resultadoOr = true.or(false)    // Equivalente a: true || false
```
Ao utilizar essas funções, você pode ter um código mais legível em situações que envolvem avaliação condicional mais elaborada.

## **Operações Bit a Bit**

Operações bitwise manipulam bits individuais de valores.

```kotlin
val andBitwise = 0b1010 and 0b1100
val orBitwise = 0b1010 or 0b1100
val xorBitwise = 0b1010 xor 0b1100
val deslocamentoEsquerda = 0b1010 shl 2
val deslocamentoDireita = 0b1010 shr 1
```
