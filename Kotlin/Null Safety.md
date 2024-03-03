# Null Safety em Kotlin

## Introdução

Em Kotlin, o conceito de null safety (segurança contra nulos) é fundamental para lidar com valores que podem ser nulos de maneira mais segura e eficiente. A linguagem Kotlin incorpora recursos projetados para eliminar ou minimizar erros relacionados a valores nulos, promovendo uma codificação mais robusta.

## Tipos Nullable

Em Kotlin, a anotação `?` pode ser usada para criar tipos que aceitam valores nulos. Por exemplo, `Int?` é um tipo que pode armazenar um valor inteiro ou ser nulo.

```kotlin
val numero: Int? = null
```

## Operador de Chamada Segura `?.`

O operador de chamada segura (`?.`) é uma ferramenta poderosa que permite chamar métodos ou acessar propriedades em um objeto apenas se esse objeto não for nulo.

```kotlin
val comprimento: Int? = nome?.length
```

Neste exemplo, o comprimento da variável `nome` é obtido apenas se `nome` não for nulo.

## Operador Elvis `?:`

O operador Elvis (`?:`) é utilizado para fornecer um valor padrão quando a expressão à esquerda é nula.

```kotlin
val nomeComprimento: Int = nome?.length ?: 0
```

Se `nome` for nulo, `nomeComprimento` receberá o valor padrão 0.

## Operador `!!`

O operador de assertiva (`!!`) é utilizado para indicar que você tem certeza de que um valor não é nulo. Cautela deve ser exercida ao usar este operador, pois pode levar a exceções de `NullPointerException` se aplicado incorretamente.

```kotlin
val comprimento: Int = nome!!.length
```

## Safe Cast (`as?`)

O operador de conversão segura (`as?`) é usado para realizar uma conversão de tipo, retornando `null` se a conversão não for possível.

```kotlin
val numero: Int? = texto as? Int
```

Se `texto` não puder ser convertido para `Int`, `numero` será nulo.

## Checagem de Nulo com `is`

A checagem de nulo pode ser realizada usando o operador `is`.

```kotlin
if (valor is String) {
    // Código a ser executado se valor for do tipo String
} else {
    // Código a ser executado se valor não for do tipo String
}
```
