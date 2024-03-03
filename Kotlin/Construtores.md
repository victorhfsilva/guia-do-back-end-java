# Construtores em Kotlin

## Introdução aos Construtores

Em Kotlin, um construtor é uma função especial em uma classe que é invocada quando um objeto é instanciado. Sempre que um objeto é criado, o construtor definido é chamado automaticamente, sendo utilizado para inicializar as propriedades da classe.

Cada classe Kotlin precisa ter um construtor, e se não o definirmos, o compilador gera um construtor padrão. Existem dois tipos de construtores em Kotlin: o Construtor Primário e Construtores Secundários.

## Construtor Primário

O construtor primário pode ser declarado no nível do cabeçalho da classe, como mostrado no exemplo a seguir:

```kotlin
class Pessoa constructor(val nome: String, val idade: Int) {
   // corpo da classe
}
```

A palavra-chave `constructor` pode ser omitida se não houver anotações ou modificadores de acesso especificados, como `public`, `private` ou `protected`.

```kotlin
class Pessoa(val nome: String, val idade: Int) {
   // corpo da classe
}
```

### Bloco Inicializador

O construtor primário não pode conter nenhum código. O código de inicialização pode ser colocado em blocos inicializadores, prefixados com a palavra-chave `init`. Pode haver mais de um bloco `init`, e durante a inicialização de uma instância, os blocos `init` são executados na mesma ordem em que aparecem no corpo da classe, intercalados com os inicializadores de propriedades.

Vejamos um exemplo com o uso de um bloco inicializador:

```kotlin
class Pessoa(val _nome: String, val _idade: Int) {
   // Variáveis membros
   var nome: String
   var idade: Int

   // Bloco Inicializador
   init {
      this.nome = _nome
      this.idade = _idade
      println("Nome = $nome")
      println("Idade = $idade")
   }
}
```

### Valores Padrão

O Kotlin permite inicializar os parâmetros do construtor com valores padrão. Vejamos um exemplo prático:

```kotlin
class Pessoa(val _nome: String, val _idade: Int = 20) {
   // Variáveis membros
   var nome: String
   var idade: Int

   // Bloco Inicializador
   init {
      this.nome = _nome
      this.idade = _idade
      println("Nome = $nome")
      println("Idade = $idade")
   }
}

fun main(args: Array<String>) {
   val zara = Pessoa("Zara")
   val nuha = Pessoa("Nuha", 11)
}
```

## Construtor Secundário em Kotlin

Como mencionado anteriormente, Kotlin permite criar um ou mais construtores secundários para a classe. Este construtor secundário é criado usando a palavra-chave `constructor`. Ele é necessário sempre que você deseja criar mais de um construtor em Kotlin ou quando deseja incluir mais lógica no construtor primário, mas não pode fazer isso porque o construtor primário pode ser chamado por alguma outra classe.

### Exemplo de Construtor Secundário

Aqui está um exemplo onde criamos um construtor secundário para implementar o exemplo anterior:

```kotlin
class Pessoa {
   // Variáveis membros
   var nome: String
   var idade: Int

   // Bloco Inicializador
   init {
      println("Bloco Inicializador")
   }

   // Construtor Secundário
   constructor(_nome: String, _idade: Int) {
      this.nome = _nome
      this.idade = _idade
      println("Nome = $nome")
      println("Idade = $idade")
   }
}

fun main(args: Array<String>) {
   val zara = Pessoa("Zara", 20)
}
```

### Restrições dos Construtores Secundários

Os construtores secundários não permitem o uso de `val` ou `var` com os parâmetros do construtor secundário. 

### Exemplo com Dois Construtores

Agora, vejamos um exemplo com dois construtores secundários:

```kotlin
class Pessoa {
   // Variáveis membros
   var nome: String
   var idade: Int
   var salario: Double

   // Primeiro Construtor Secundário
   constructor(_nome: String, _idade: Int) {
      this.nome = _nome
      this.idade = _idade
      this.salario = 0.00
      println("Nome = $nome")
      println("Idade = $idade")
   }

   // Segundo Construtor Secundário
   constructor(_nome: String, _idade: Int, _salario: Double) {
      this.nome = _nome
      this.idade = _idade
      this.salario = _salario
      println("Nome = $nome")
      println("Idade = $idade")
      println("Salário = $salario")
   }
}

fun main(args: Array<String>) {
   val nuha = Pessoa("Nuha", 12)
   val zara = Pessoa("Zara", 20, 2000.00)
}
```

