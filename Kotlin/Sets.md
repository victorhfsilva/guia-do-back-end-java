# Kotlin: Conjuntos

## Introdução

Em Kotlin, um conjunto é uma coleção não ordenada de itens. Um conjunto Kotlin pode ser mutável (mutableSetOf) ou somente leitura (setOf). Conjuntos mutáveis ou imutáveis em Kotlin não permitem ter elementos duplicados.

## Criação de Conjuntos em Kotlin

Para criar conjuntos, utilize as funções padrão da biblioteca `setOf()` para conjuntos de leitura e `mutableSetOf()` para conjuntos mutáveis.

```kotlin
val conjuntoLeitura = setOf("um", "dois", "três", "quatro")
println(conjuntoLeitura)

val conjuntoMutavel = mutableSetOf("um", "dois", "três", "quatro")
println(conjuntoMutavel)
```

## Iteração em Conjuntos

Existem várias maneiras de iterar por um conjunto em Kotlin:

### Usando a função toString()

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")
println(conjunto.toString())
```

### Usando um Iterador

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")

val itr = conjunto.iterator()
while (itr.hasNext()) {
    println(itr.next())
}
```

### Usando um loop for

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")

for (elemento in conjunto) {
    println(elemento)
}
```

### Usando forEach

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")

conjunto.forEach { println(it) }
```

## Operações com Conjuntos

### Tamanho do Conjunto

Podemos usar a propriedade `size` para obter o número total de elementos em um conjunto.

```kotlin
val conjunto = setOf("um", "dois", null, "quatro", "cinco")
println("Tamanho do conjunto: ${conjunto.size}")
```

### Operador "in"

O operador `in` pode ser usado para verificar a existência de um elemento em um conjunto.

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")

if ("dois" in conjunto) {
    println(true)
} else {
    println(false)
}
```

### Método `contains()`

O método `contains()` também pode ser usado para verificar a existência de um elemento em um conjunto.

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")

if (conjunto.contains("dois")) {
    println(true)
} else {
    println(false)
}
```

### Método `isEmpty()`

O método `isEmpty()` retorna true se o conjunto estiver vazio e false caso contrário.

```kotlin
val conjunto = setOf("um", "dois", "três", "quatro")

if (conjunto.isEmpty()) {
    println(true)
} else {
    println(false)
}
```

### Método `indexOf()`

O método `indexOf()` não está disponível para conjuntos, pois eles não são indexados. Conjuntos não mantêm uma ordem específica.

## Manipulação de Conjuntos

### União de Conjuntos

Podemos usar o operador `+` para unir dois ou mais conjuntos em um único conjunto. Elementos duplicados serão descartados.

```kotlin
val primeiroConjunto = setOf("um", "dois", "três")
val segundoConjunto = setOf("dois", "três", "quatro")
val conjuntoResultado = primeiroConjunto + segundoConjunto

println(conjuntoResultado)
```

### Diferença entre Conjuntos

Podemos usar o operador `-` para encontrar a diferença entre dois conjuntos. Isso removerá os elementos comuns do primeiro conjunto.

```kotlin
val primeiroConjunto = setOf("um", "dois", "três")
val segundoConjunto = setOf("dois", "quatro", "cinco")
val conjuntoResultado = primeiroConjunto - segundoConjunto

println(conjuntoResultado)
```

### Remoção de Elementos de um Conjunto

Podemos usar o método `remove()` para remover um elemento específico de um conjunto.

```kotlin
val conjunto = mutableSetOf("um", "dois", "três", "quatro")
conjunto.remove("dois")

println(conjunto)
```

### Adição de Elementos a um Conjunto

Podemos usar o método `add()` para adicionar elementos a um conjunto mutável.

```kotlin
val conjunto = mutableSetOf("um", "dois", "três", "quatro")
conjunto.add("cinco")

println(conjunto)
```

### Remoção de Nulos de um Conjunto

Podemos usar o método `filterNotNull()` para remover elementos nulos de um conjunto.

```kotlin
val conjunto = setOf("um", "dois", null, "quatro", "cinco")
val conjuntoResultado = conjunto.filterNotNull()

println(conjuntoResultado)
```

### Ordenação de Elementos

A ordenação de conjuntos não é aplicável diretamente, pois conjuntos são estruturas não ordenadas. Se ordenação for necessária, pode-se converter o conjunto para uma lista e ordenar a lista.

```kotlin
val conjunto = setOf(10, 20, 30, 31, 40, 50, -1, 0)
val listaOrdenada = conjunto.toList().sorted()

println(listaOrdenada)
```

### Filtragem de Elementos

Podemos usar o método `filter()` para filtrar os elementos com base em um predicado.

```kotlin
val conjunto = setOf(10, 20, 30, 31, 40, 50, -1, 0)
val conjuntoFiltrado = conjunto.filter { it > 30 }

println(conjuntoFiltrado)
```

## Operações Avançadas com Conjuntos

### Operações de Conjunto Avançadas

Kotlin fornece métodos como `intersect()`, `union()`, e `subtract()` para realizar operações mais avançadas entre conjuntos.

```kotlin
val primeiroConjunto = setOf(1, 2, 3, 4, 5)
val segundoConjunto = setOf(4, 5, 6, 7, 8)

val intersecao = primeiroConjunto.intersect(segundoConjunto)
val uniao = primeiroConjunto.union(segundoConjunto)
val diferenca = primeiroConjunto.subtract(segundoConjunto)

println("Interseção: $intersecao")
println("União: $uniao")
println("Diferença: $diferenca")
```

### Verificação de Subconjunto

Podemos usar o método `isSubsetOf()` para verificar se um conjunto é um subconjunto de outro.

```kotlin
val primeiroConjunto = setOf(1, 2, 3)
val segundoConjunto = setOf(1, 2, 3, 4, 5)

val isSubconjunto = primeiroConjunto.isSubsetOf(segundoConjunto)

println(isSubconjunto) // true
```

### Verificação de Superconjunto

Podemos usar o método `isSupersetOf()` para verificar se um conjunto é um superconjunto de outro.

```kotlin
val primeiroConjunto = setOf(1, 2, 3, 4, 5)
val segundoConjunto = setOf(1, 2, 3)

val isSuperconjunto = primeiroConjunto.isSupersetOf(segundoConjunto)

println(isSuperconjunto) // true
```

### Verificação de Igualdade de Conjuntos

Podemos usar o método `equals()` para verificar se dois conjuntos são iguais.

```kotlin
val primeiroConjunto = setOf(1, 2, 3)
val segundoConjunto = setOf(3, 1, 2)

val saoIguais = primeiroConjunto == segundoConjunto

println(saoIguais) // true
```
