# Generics em Kotlin

## Introdução

Os generics em Kotlin proporcionam flexibilidade ao criar classes, interfaces e funções que podem operar em diferentes tipos sem perder a segurança de tipo. Eles permitem escrever código mais genérico e reutilizável.

## Tipos Genéricos

Os tipos genéricos são representados por parâmetros de tipo dentro de colchetes angulares (`<T>`). Esses parâmetros de tipo podem ser substituídos por tipos reais quando você usa a classe, função ou interface.

```kotlin
class Caixa<T>(var conteudo: T)
```

No exemplo acima, `T` é um parâmetro de tipo que pode ser substituído por qualquer tipo real quando você cria uma instância da classe `Caixa`.

## Classes Genéricas

Você pode criar classes genéricas usando parâmetros de tipo.

```kotlin
class Lista<T> {
    private val elementos = mutableListOf<T>()

    fun adicionar(elemento: T) {
        elementos.add(elemento)
    }

    fun obter(index: Int): T {
        return elementos[index]
    }
}
```

A classe `Lista` acima é genérica e pode ser usada para armazenar e recuperar elementos de qualquer tipo.

## Funções Genéricas

Funções também podem ser genéricas, permitindo operações em tipos desconhecidos.

```kotlin
fun <T> imprimirLista(lista: List<T>) {
    for (item in lista) {
        println(item)
    }
}
```

Neste exemplo, a função `imprimirLista` aceita uma lista de qualquer tipo (`List<T>`) e imprime cada item.

## Restrições em Tipos Genéricos

Você pode impor restrições nos tipos que podem ser usados como parâmetros genéricos. Por exemplo, limitar o tipo a subtipos de uma determinada classe.

```kotlin
fun <T : Number> somar(a: T, b: T): Double {
    return a.toDouble() + b.toDouble()
}
```

A função `somar` aceita apenas tipos que são subtipos de `Number`, garantindo operações numéricas válidas.

