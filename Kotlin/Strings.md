# Strings em Kotlin

## **Introdução**

O tipo de dado String em Kotlin é usado para armazenar uma sequência de caracteres. Os valores de string devem estar entre aspas duplas (" ") ou aspas triplas (""" """).

Kotlin possui dois tipos de strings: a string escapada e a string bruta.

### String Escapada

A string escapada é declarada entre aspas duplas e pode conter caracteres de escape como '\n', '\t', '\b', etc.

```kotlin
val escapedString: String = "Eu sou uma String escapada!\n"
```

### String Bruta

A string bruta é declarada entre três aspas duplas e pode conter várias linhas de texto sem caracteres de escape.

```kotlin
var rawString: String = """Esta será uma
   string de várias linhas e não terá
   nenhum caractere de escape"""
```

## **Declarando Strings em Kotlin**

A especificação do tipo para uma String é opcional, pois o Kotlin consegue inferir o tipo de variável devido às aspas duplas ou triplas.

```kotlin
val nome: String = "Maria"
val sobrenome = "Silva"
```

## **Templates de String em Kotlin**

Os templates de string são trechos de código avaliados e cujos resultados são interpolados na string. Iniciam-se com um cifrão ($) e podem consistir em um nome ou uma expressão.

```kotlin
val nome: String = "Maria"
println("Nome: $nome")
println("Nome: ${nome.length}")
```

## **Índices e Tamanho de Strings em Kotlin**

As strings em Kotlin podem ser tratadas como sequências de caracteres. Os índices começam do 0, e o tamanho de uma string pode ser obtido com a propriedade `length`.

```kotlin
val nome: String = "Zara Ali"
println(nome[3])            // Saída: a
println(nome[5])            // Saída: A
println("Tamanho: ${nome.length}")  // Saída: Tamanho: 8
```

## **Mudando o Case de Strings**

Kotlin fornece as funções `toUpperCase()` e `toLowerCase()` para converter uma string para maiúsculas e minúsculas, respectivamente.

```kotlin
val nome: String = "Zara Ali"
println("Maiúsculas: ${nome.toUpperCase()}")
println("Minúsculas: ${nome.toLowerCase()}")
```

## **Concatenação de Strings em Kotlin**

A concatenação de strings pode ser feita usando o operador `+` ou a função `plus()`.

```kotlin
var nome: String = "Zara "
var sobrenome: String = "Ali"
println("Nome Completo: " + nome + sobrenome)
println("Nome Completo: ${nome.plus(sobrenome)}")
```

## **Removendo Caracteres de uma String**

Podemos remover os primeiros ou últimos caracteres de uma string usando as funções `drop()` e `dropLast()`.

```kotlin
var nome: String = "Zara Ali"
println("Remover os dois primeiros caracteres: ${nome.drop(2)}")
println("Remover os dois últimos caracteres: ${nome.dropLast(2)}")
```

## **Localizando uma String Dentro de Outra**

Kotlin fornece a função `indexOf()` para encontrar a posição da primeira ocorrência de um texto em uma string.

```kotlin
var str: String = "Meditação e Yoga são sinônimos da Índia"
println("Índice de 'Yoga' na string: ${str.indexOf("Yoga")}")
```

## **Comparando Duas Strings**

A função `compareTo()` compara duas strings. Retorna 0 se forem iguais, caso contrário, retorna 1.

```kotlin
var str1: String = "Maçã"
var str2: String = "Maçã"
println(str1.compareTo(str2))  // Saída: 0
```

## **Função getOrNull() em Kotlin**

A função `getOrNull()` retorna um caractere no índice especificado ou nulo se estiver fora dos limites.

```kotlin
var nome: String = "Zara"
println(nome.getOrNull(0))    // Saída: Z
println(nome.getOrNull(2))    // Saída: r
println(nome.getOrNull(100))  // Saída: null
```
