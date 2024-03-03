# Data Classes em Kotlin

As data classes em Kotlin são uma maneira concisa de criar classes que são usadas principalmente para armazenar dados. Elas vêm com várias funcionalidades úteis automaticamente.

## Declaração

Para declarar uma data class, utilize a palavra-chave `data`:

```kotlin
data class Pessoa(val nome: String, val idade: Int)
```

## Função `copy`

A função `copy` é uma característica essencial de data classes, permitindo criar cópias modificadas de objetos existentes:

```kotlin
val pessoa1 = Pessoa("Alice", 25)
val pessoa2 = pessoa1.copy(idade = 26)
```

## Funções `toString()`, `hashCode()`, e `equals()`

As data classes geram automaticamente implementações para as funções `toString()`, `hashCode()`, e `equals()`. Isso proporciona uma representação legível, comparação de conteúdo e geração de códigos hash eficientes:

```kotlin
val pessoa1 = Pessoa("Alice", 25)
val pessoa2 = Pessoa("Alice", 25)

println(pessoa1.toString()) // Saída: Pessoa(nome=Alice, idade=25)
println(pessoa1 == pessoa2)   // Saída: true
```

## Destructuring

A destructuring é suportada em data classes, o que permite atribuir rapidamente seus componentes a variáveis:

```kotlin
val pessoa = Pessoa("Bob", 30)
val (nome, idade) = pessoa

println("Nome: $nome, Idade: $idade")
```

Ao utilizar data classes em Kotlin, é possível criar e manipular objetos de forma concisa, aproveitando as funcionalidades automáticas fornecidas por elas.