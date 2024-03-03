# Mapas em Kotlin

## Introdução

Em Kotlin, um mapa é uma coleção de pares chave/valor, onde cada chave é única e pode estar associada a apenas um valor. O mesmo valor pode estar associado a várias chaves. Podemos declarar chaves e valores de qualquer tipo; não há restrições.

Mapas em Kotlin podem ser mutáveis (`mutableMapOf`) ou somente leitura (`mapOf`). Em outras linguagens de programação, mapas também são conhecidos como dicionários ou arrays associativos.

## Criação de Mapas em Kotlin

Para criar mapas, utilize as funções da biblioteca padrão `mapOf()` para mapas de leitura e `mutableMapOf()` para mapas mutáveis.

```kotlin
val mapaLeitura = mapOf("um" to 1, "dois" to 2, "três" to 3)
println(mapaLeitura)

val mapaMutavel = mutableMapOf("um" to 1, "dois" to 2, "três" to 3)
println(mapaMutavel)
```

## Criação de Mapa usando HashMap

Um mapa em Kotlin pode ser criado a partir de um `HashMap` do Java.

```kotlin
fun main() {
    val oMapa = HashMap<String, Int>()
    
    oMapa["um"] = 1
    oMapa["dois"] = 2
    oMapa["três"] = 3
    
    println(oMapa)
}
```

## Utilizando Pair na Criação de Mapas

Podemos utilizar o método `Pair()` para criar pares chave/valor ao construir um mapa.

```kotlin
val oMapa = mapOf(Pair("um", 1), Pair("dois", 2), Pair("três", 3))
println(oMapa)
```

## Propriedades dos Mapas em Kotlin

Mapas em Kotlin possuem propriedades para obter todas as entradas, chaves e valores do mapa.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)
println("Entradas: ${oMapa.entries}")
println("Chaves: ${oMapa.keys}")
println("Valores: ${oMapa.values}")
```

## Iteração em Mapas

Existem várias maneiras de iterar através de mapas em Kotlin:

### Usando `toString()`

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)
println(oMapa.toString())
```

### Usando Iterador

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)

val iterador = oMapa.keys.iterator()
while (iterador.hasNext()) {
    val chave = iterador.next()
    val valor = oMapa[chave]
    println("$chave = $valor")
}
```

### Usando Loop For

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)

for ((chave, valor) in oMapa) {
    println("$chave = $valor")
}
```

### Usando forEach

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)

oMapa.forEach { chave, valor ->
    println("Chave = $chave, Valor = $valor")
}
```

## Tamanho do Mapa em Kotlin

Podemos usar a propriedade `size` ou o método `count()` para obter o número total de elementos em um mapa.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)
println("Tamanho do mapa: ${oMapa.size}")
println("Tamanho do mapa: ${oMapa.count()}")
```

## Métodos containsKey() e containsValue()

O método `containsKey()` verifica se o mapa contém uma chave específica, enquanto `containsValue()` verifica se o mapa contém um valor específico.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)

if (oMapa.containsKey("dois")) {
    println(true)
} else {
    println(false)
}

if (oMapa.containsValue(2)) {
    println(true)
} else {
    println(false)
}
```

## Método isEmpty()

O método `isEmpty()` retorna true se o mapa estiver vazio e false caso contrário.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)

if (oMapa.isEmpty()) {
    println(true)
} else {
    println(false)
}
```

## Método get()

O método `get()` pode ser usado para obter o valor correspondente a uma chave específica.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)

println("O valor para a chave 'dois': ${oMapa.get("dois")}")
println("O valor para a chave 'dois': ${oMapa["dois"]}")
```

## Adição de Mapas

Podemos usar o operador `+` para adicionar dois ou mais mapas em um único mapa. Isso adicionará o segundo mapa ao primeiro, descartando os elementos duplicados.

```kotlin
val primeiroMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)
val segundoMapa = mapOf("um" to 10, "quatro" to 4)
val resultadoMapa = primeiroMapa + segundoMapa

println(resultadoMapa)
```

## Subtração de Mapas

Podemos usar o operador `-` para subtrair um mapa de outro. Isso removerá todas as chaves presentes na lista do mapa e retornará o resultado.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)
val listaDeChaves = listOf("um", "quatro")
val resultadoMapa = oMapa - listaDeChaves

println(resultadoMapa)
```

## Remoção de Entradas do Mapa

Podemos usar o método `remove()` para remover um elemento de um mapa mutável, ou o operador `-=` para realizar a mesma operação.

```kotlin
val oMapa = mutableMapOf("um" to 1, "dois" to 2, "três" to 3, "quatro" to 4)
oMapa.remove("dois")
println(oMapa)

oMapa -= listOf("três")
println(oMapa)
```

## Ordenação de Elementos no Mapa

Podemos usar o método `toSortedMap()` para ordenar os elementos em ordem crescente ou `sortedDescending()` para ordenar em ordem decrescente. Também podemos criar um mapa ordenado com os pares chave/valor fornecidos usando o método `sortedMapOf()`.

```kotlin
val oMapa = mapOf("dois" to 2, "um" to 1, "quatro" to 4, "três" to 3,)
val resultadoMapa = oMapa.toSortedMap()

println(resultadoMapa)
```

## Filtragem de Elementos no Mapa

Podemos usar `filterKeys()` ou `filterValues()` para filtrar as entradas do mapa. O método `filter()` também pode ser utilizado para filtrar elementos que correspondem à chave e ao valor.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3, "quatro" to 4)
var resultadoMapa = oMapa.filterValues { it > 2 }
println(resultadoMapa)

resultadoMapa = oMapa.filterKeys { it == "dois" }
println(resultadoMapa)

resultadoMapa = oMapa.filter { it.key == "dois" || it.value == 4 }
println(resultadoMapa)
```

## Mapeamento de Elementos no Mapa

Podemos usar o método `map()` para mapear todos os elementos usando a função fornecida.

```kotlin
val oMapa = mapOf("um" to 1, "dois" to 2, "três" to 3)
val resultadoMapa = oMapa.map { (chave, valor) -> "Chave é $chave, Valor é $valor" }

println(resultadoMapa)
```