# Kotlin: Listas

## Introdução

Em Kotlin, uma lista é uma coleção ordenada de itens. Pode ser mutável (mutableListOf) ou somente leitura (listOf). Os elementos da lista podem ser acessados usando índices. Listas em Kotlin, sejam mutáveis ou imutáveis, podem conter elementos duplicados.

## Criação de Listas em Kotlin

Para criar listas, use as funções da biblioteca padrão `listOf()` para listas de leitura e `mutableListOf()` para listas mutáveis.

```kotlin
val listaLeitura = listOf("um", "dois", "três", "quatro")
println(listaLeitura)

val listaMutavel = mutableListOf("um", "dois", "três", "quatro")
println(listaMutavel)
```

## Iteração em Listas

Existem várias maneiras de iterar por uma lista em Kotlin:

### Usando a função toString()

```kotlin
val lista = listOf("um", "dois", "três", "quatro")
println(lista.toString())
```

### Usando um Iterador

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

val itr = lista.listIterator()
while (itr.hasNext()) {
    println(itr.next())
}
```

### Usando um loop for

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

for (i in lista.indices) {
    println(lista[i])
}
```

### Usando forEach

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

lista.forEach { println(it) }
```

## Operações com Listas

### Tamanho da Lista

Podemos usar a propriedade `size` para obter o número total de elementos em uma lista.

```kotlin
val lista = listOf("um", "dois", null, "quatro", "cinco")
println("Tamanho da lista: ${lista.size}")
```

### Operador "in"

O operador `in` pode ser usado para verificar a existência de um elemento em uma lista.

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

if ("dois" in lista) {
    println(true)
} else {
    println(false)
}
```

### Método `contains()`

O método `contains()` também pode ser usado para verificar a existência de um elemento em uma lista.

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

if (lista.contains("dois")) {
    println(true)
} else {
    println(false)
}
```

### Método `isEmpty()`

O método `isEmpty()` retorna true se a lista estiver vazia e false caso contrário.

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

if (lista.isEmpty()) {
    println(true)
} else {
    println(false)
}
```

### Método `indexOf()`

O método `indexOf()` retorna o índice da primeira ocorrência do elemento especificado na lista, ou -1 se o elemento especificado não estiver contido na lista.

```kotlin
val lista = listOf("um", "dois", "três", "quatro")

println("Índice de 'dois': ${lista.indexOf("dois")}")
```

## Manipulação de Listas

### Adição de Listas

Podemos usar o operador `+` para adicionar duas ou mais listas em uma única lista. Elementos duplicados serão adicionados.

```kotlin
val primeiraLista = listOf("um", "dois", "três")
val segundaLista = listOf("quatro", "cinco", "seis")
val listaResultado = primeiraLista + segundaLista

println(listaResultado) //[um, dois, três, quatro, cinco, seis]
```

### Subtração de Listas

Podemos usar o operador `-` para subtrair uma lista de outra. Isso removerá os elementos comuns da primeira lista.

```kotlin
val primeiraLista = listOf("um", "dois", "três")
val segundaLista = listOf("um", "cinco", "seis")
val listaResultado = primeiraLista - segundaLista

println(listaResultado) //[dois, três]
```

### Obtenção de uma Sublista

Podemos usar o método `slice()` para obter uma sublista de uma lista com base em intervalos de índices.

```kotlin
val lista = listOf("um", "dois", "três", "quatro", "cinco")
val listaResultado = lista.slice(2..4)

println(listaResultado)
```

### Remoção de Nulos de uma Lista

Podemos usar o método `filterNotNull()` para remover elementos nulos de uma lista.

```kotlin
val lista = listOf("um", "dois", null, "quatro", "cinco")
val listaResultado = lista.filterNotNull()

println(listaResultado)
```

### Filtragem de Elementos

Podemos usar o método `filter()` para filtrar os elementos com base em um predicado.

```kotlin
val lista = listOf(10, 20, 30, 31, 40, 50, -1, 0)
val listaResultado = lista.filter { it > 30 }

println(listaResultado)
```

### Descarte dos Primeiros N Elementos

Podemos usar o método `drop()` para descartar os primeiros N elementos de uma lista.

```kotlin
val lista = listOf(10, 20, 30, 31, 40, 50, -1, 0)
val listaResultado = lista.drop(3)

println(listaResultado)
```

## Operações Avançadas com Listas

### Agrupamento de Elementos

Podemos usar o método `groupBy()` para agrupar os elementos com base em um predicado.

```kotlin
val lista = listOf(10, 12, 30, 31, 40, 9, -3, 0)
val mapaResultado = lista.groupBy { it % 3 }

println(mapaResultado) //{1=[10, 31, 40], 0=[12, 30, 9, -3, 0]}
```

### Mapeamento de Elementos

Podemos usar o método `map()` para mapear todos os elementos usando a função fornecida.

```kotlin
val lista = listOf(10, 12, 30, 31, 40, 9, -3, 0)
val listaResultado = lista.map { it / 3 }

println(listaResultado) 
```

### Divisão de Elementos em Blocos

Podemos usar o método `chunked()` para criar blocos do tamanho especificado a partir de uma lista.

```kotlin
val lista = listOf(10, 12, 30, 31, 40, 9, -3, 0)
val listaResultado = lista.chunked(3)

println(listaResultado) //[[10, 12, 30], [31, 40, 9], [-3, 0]]
```

### Janelamento de Elementos

Podemos usar o método windowed() para criar uma lista de intervalos de elementos movendo uma janela deslizante de um tamanho específico sobre uma coleção de elementos.

```kotlin
val lista = listOf(10, 12, 30, 31, 40, 9, -3, 0)
val listaResultado = lista.windowed(3)

println(listaResultado) //[[10, 12, 30], [12, 30, 31], [30, 31, 40], [31, 40, 9], [40, 9, -3], [9, -3, 0]]
```

Esta janela desliza um passo de cada vez por padrão, porém o tamanho do passo pode ser customizado como a seguir:

```kotlin
fun main() {
    val theList = listOf(10, 12, 30, 31, 40, 9, -3, 0)
    val resultList = theList.windowed(3, 3)
    
    println(resultList) //[[10, 12, 30], [31, 40, 9]]
}
```