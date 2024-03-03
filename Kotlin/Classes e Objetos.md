# Classes e Objetos em Kotlin

## Introdução

Kotlin suporta tanto programação funcional quanto programação orientada a objetos (OOP). Além das características funcionais, como funções, funções de alta ordem e lambdas, Kotlin oferece recursos de Programação Orientada a Objetos, como abstração, encapsulamento, herança, etc. 

## Classes em Kotlin

### Definição de Classes

Uma classe é um modelo para objetos que define um template a ser usado para criar os objetos desejados. Em Kotlin, uma classe é definida usando a palavra-chave `class`. A sintaxe básica de uma classe é a seguinte:

```kotlin
class NomeDaClasse { // Cabeçalho da Classe

   //
   // Variáveis ou membros de dados
   // Funções membros ou Métodos
   //
   ...
   ...
}
```

Por padrão, as classes em Kotlin são públicas, e podemos controlar a visibilidade dos membros da classe usando diferentes modificadores.

### Exemplo de Classe em Kotlin

```kotlin
class MinhaClasse {
   // Propriedade
   private var nome: String = "Kotlin"

   // Função
   fun imprimeNome() {
      print("Uma ótima linguagem de programação - $nome")
   }
}
```

### Criação de Objeto da Classe

Os objetos são criados a partir da classe Kotlin e compartilham as propriedades e comportamentos comuns definidos pela classe. A sintaxe para declarar um objeto de uma classe é:

```kotlin
val nomeDoObjeto = NomeDaClasse()
```

Podemos acessar as propriedades e métodos de uma classe usando o operador `.` (ponto), conforme mostrado abaixo:

```kotlin
val nomeDoObjeto = NomeDaClasse()

nomeDoObjeto.propriedade = <Valor>
nomeDoObjeto.nomeDaFuncao()
```

### Exemplo de Uso de Classe em Kotlin

```kotlin
class MinhaClasse {
   // Propriedade (membro de dados)
   private var nome: String = "Kotlin"

   // Função membro
   fun imprimeNome() {
      print("Uma ótima linguagem de programação - $nome")
   }
}

fun main(args: Array<String>) {
   val obj = MinhaClasse() // Cria objeto obj da classe MinhaClasse
   obj.imprimeNome() // Chama uma função membro usando o objeto
}
```

## Classes Aninhadas em Kotlin

### Definição de Classe Aninhada

Quando uma classe é criada dentro de outra classe, ela é chamada de classe aninhada. A classe aninhada é, por padrão, estática, portanto, pode ser acessada sem criar nenhum objeto dessa classe, mas usando o operador `.` (ponto). Ao mesmo tempo, não podemos acessar membros da classe externa dentro de uma classe aninhada.

A sintaxe básica para criar uma classe aninhada é:

```kotlin
class ClasseExterna {
   // Membros da Classe Externa
   class ClasseAninhada {
      // Membros da Classe Aninhada
   }
}
```

### Exemplo de Classe Aninhada em Kotlin

```kotlin
fun main(args: Array<String>) {
   val obj = ClasseExterna.ClasseAninhada()

   print(obj.foo())
}

class ClasseExterna {
   class ClasseAninhada {
      fun foo() = "Bem-vindo ao TutorialsPoint.com"
   }
}
```

## Classes Internas em Kotlin

Em Kotlin, quando uma classe aninhada é marcada com a palavra-chave `inner`, ela é denominada como classe interna. Essa classificação especial permite que a classe interna acesse membros de dados específicos da classe externa. Embora não seja possível criar diretamente um objeto da classe interna, é possível instanciá-la usando um objeto da classe externa.

### Sintaxe Básica de uma Classe Interna

A criação de uma classe interna em Kotlin envolve a utilização da palavra-chave `inner` no momento da declaração da classe. Vejamos a estrutura básica:

```kotlin
class ClasseExterna {
   // Membros da Classe Externa
   inner class ClasseInterna {
      // Membros da Classe Interna
   }
}
```

### Exemplo Prático de uma Classe Interna em Kotlin

Vamos ilustrar o conceito de classe interna com um exemplo prático:

```kotlin
fun main(args: Array<String>) {
   val obj = Externa().Interna()

   print(obj.foo())
}

class Externa {
   private val mensagemDeBoasVindas: String = "Bem-vindo"
   inner class Interna {
      fun foo() = mensagemDeBoasVindas
   }
}
```

## Classe Anônima em Kotlin


Em Kotlin, as classes anônimas são uma ferramenta valiosa que simplifica a implementação de interfaces. A essência por trás desse conceito é criar um objeto de uma interface usando referência de objeto durante a execução, sendo denominado como classe anônima.

### Exemplo Prático de Classe Anônima em Kotlin

Vamos explorar um exemplo prático para compreender melhor o conceito de classe anônima em Kotlin:

```kotlin
fun main(args: Array<String>) {
   var programador: Humano = object : Humano { 
        // Classe anônima
        override fun pensar() { 
            // sobrescrevendo o método pensar
            print("Este é um exemplo de Classe Anônima ")
        }
   }
   programador.pensar()
}

interface Humano {
   fun pensar()
}
```

Neste exemplo, temos uma interface chamada `Humano` com um método `pensar()`. Utilizando uma classe anônima, criamos um objeto `programador` que implementa a interface `Humano` e sobrescreve o método `pensar()`. Ao chamar `programador.pensar()`.


## Kotlin Type Aliases

Type Aliases em Kotlin significa dar um nome alternativo a um tipo existente. Type alias fornece uma maneira mais limpa de escrever um código mais legível.

Considere uma função a seguir que retorna informações do usuário, como nome, sobrenome e idade:

```kotlin
fun informacoesDoUsuario(): Triple<String, String, Int>{
   return Triple("Zara","Ali",21)
}
```

Agora podemos criar um tipo alias para

 o `Triple` dado:

```kotlin
typealias Usuario = Triple<String, String, Int>
```

Finalmente, a função acima pode ser escrita da seguinte forma, o que parece mais limpo:

```kotlin
fun informacoesDoUsuario(): Usuario{
   return Triple("Zara","Ali",21)
}
```