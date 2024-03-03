# Iteradores Personalizados em Kotlin

## **Introdução**

Iteradores personalizados em Kotlin são uma ferramenta poderosa para criar estruturas de dados ou sequências adaptadas a necessidades específicas. 

## **Conceitos Básicos de Iteradores**

Os iteradores personalizados implementam a interface `Iterator`, com métodos `next()` e `hasNext()`. Aqui está um exemplo prático:

```kotlin
class LivrosIterator(private val livros: List<Livro>) : Iterator<Livro> {
    private var indiceAtual = 0

    override fun hasNext(): Boolean {
        return indiceAtual < livros.size
    }

    override fun next(): Livro {
        return livros[indiceAtual++]
    }
}
```

## **Criando uma Classe Iterável**

Para tornar uma classe iterável, ela precisa implementar `Iterable` e fornecer a função `iterator()`. Vamos usar uma classe de biblioteca como exemplo:

```kotlin
class Biblioteca(val livros: List<Livro>) : Iterable<Livro> {
    override fun iterator(): Iterator<Livro> {
        return LivrosIterator(livros)
    }
}
```

## **Utilizando Iteradores Personalizados**

Ao usar iteradores personalizados, podemos percorrer uma biblioteca de livros de maneira personalizada:

```kotlin
val biblioteca = Biblioteca(listaDeLivros)

for (livro in biblioteca) {
    // Lógica personalizada para cada livro na biblioteca
    println(livro.titulo)
}
```

## **Iteradores com Estado**

Iteradores podem manter um estado interno, útil para processamento controlado. Vamos considerar um exemplo de filtro:

```kotlin
class FiltroIterator(private val fonte: Iterator<Livro>, val autorDesejado: String) : Iterator<Livro> {
    override fun hasNext(): Boolean {
        return fonte.hasNext()
    }

    override fun next(): Livro {
        var proximoLivro = fonte.next()
        while (proximoLivro.autor != autorDesejado && fonte.hasNext()) {
            proximoLivro = fonte.next()
        }
        return proximoLivro
    }
}
```

## **Usando Sequências e Iteradores Personalizados**

Sequências em Kotlin podem ser combinadas com iteradores personalizados para criar fluxos de dados eficientes. Suponhamos uma sequência de livros:

```kotlin
val sequenciaLivros = sequence {
    yield(Livro("Título 1", "Autor A"))
    yield(Livro("Título 2", "Autor B"))
    // ... lógica de geração
}
```

## **Iteradores Infinitos**

Iteradores personalizados podem representar sequências infinitas. Um exemplo seria uma série de números primos:

```kotlin
class PrimosIterator : Iterator<Int> {
    private var atual = 2

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): Int {
        val primoAtual = atual
        atual = encontrarProximoPrimo(atual + 1)
        return primoAtual
    }

    private fun encontrarProximoPrimo(numero: Int): Int {
        var candidato = numero
        while (!ehPrimo(candidato)) {
            candidato++
        }
        return candidato
    }

    private fun ehPrimo(numero: Int): Boolean {
        if (numero < 2) return false
        for (i in 2 until numero) {
            if (numero % i == 0) {
                return false
            }
        }
        return true
    }
}
```