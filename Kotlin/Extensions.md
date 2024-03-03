# Extensions em Kotlin

## Introdução

As extensions em Kotlin fornecem a capacidade de estender uma classe com novas funcionalidades sem a necessidade de implementar o conceito de herança por meio de uma classe ou usar padrões de design, como o Decorator. Essas extensions basicamente adicionam funcionalidades a uma classe existente sem estendê-la.

## Extension Function

### Definição

- As extension functions permitem adicionar métodos a uma classe sem modificar a própria classe.

### Sintaxe

```kotlin
fun <nome_da_classe>.<nome_do_metodo>(){
   // Corpo da função
}
```

### Exemplo

```kotlin
// Definindo uma classe Livro com um título inicial padrão
class Livro {
   var titulo: String = "Sem Título"
	
   // Método para exibir o título do livro
   fun exibirTitulo() {
      println(titulo)
   }		
}

// Criando uma função de extensão para a classe Livro
fun Livro.combinarInformacoes(outroLivro: Livro): String {
   // Criando um novo Livro para armazenar as informações combinadas
   val livroCombinado = Livro()
   
   // Combinando as informações do livro atual com as do outro livro
   livroCombinado.titulo = "$titulo & ${outroLivro.titulo}"
   
   // Retornando as informações combinadas
   return livroCombinado.titulo
}

// Exemplo de uso das classes e da função de extensão
fun main() {
   // Criando dois Livros com títulos diferentes
   val livro1 = Livro()
   livro1.titulo = "Aventuras na Floresta"
   
   val livro2 = Livro()
   livro2.titulo = "Mistérios da Cidade"
   
   // Combinando as informações dos dois Livros usando a função de extensão
   val informacoesCombinadas = livro1.combinarInformacoes(livro2)
   
   // Exibindo as informações combinadas
   println("Informações Combinadas: $informacoesCombinadas")
}

```

## Extended Library Classes

- Kotlin permite estender classes da biblioteca padrão, bem como classes definidas pelo usuário.
- Exemplo: Adicionar uma função `countVowels` à classe `String` para contar o número de vogais.

```kotlin
fun String.countVowels(): Int {
   var vowels = 0
   for (i in 0 until this.length) {
      val ch = this[i]
      if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
         ++vowels
      }
   }
   return vowels
}
```

## Companion Object Extensions

- Kotlin oferece um mecanismo para implementar funcionalidades estáticas semelhantes ao Java usando o `companion object`.
- O `companion object` pode ser usado para criar objetos de uma classe dentro de um método de fábrica.

```kotlin
// Definindo a classe Usuario com um companion object
class Usuario(val nome: String) {
    // Método de instância para exibir o nome do usuário
    fun exibirNome() {
        println("Nome do Usuário: $nome")
    }

    // Companion object dentro da classe Usuario
    companion object {
        // Método estático para exibir uma mensagem de boas-vindas
        fun mostrarMensagemDeBoasVindas(): String {
            return "Bem-vindo ao sistema em Kotlin!"
        }
    }
}

fun main() {
    // Criando uma instância da classe Usuario
    val usuario = Usuario("João")
    
    // Exibindo o nome do usuário usando o método de instância
    usuario.exibirNome()
    
    // Chamando o método estático do companion object para exibir a mensagem de boas-vindas
    val mensagemBoasVindas = Usuario.mostrarMensagemDeBoasVindas()
    println(mensagemBoasVindas)
}
```

## Extension Properties

- As extension properties em Kotlin são definidas de maneira semelhante às extension functions.
- Quando usamos extension properties em Kotlin, não há um campo de suporte eficiente como nas propriedades convencionais da classe. Além disso, não podemos definir inicializadores para extension properties, o que significa que o valor é calculado a cada acesso, potencialmente afetando o desempenho.

```kotlin
class Temperature(var celsius: Float)

var Temperature.fahrenheit: Float
    get() = (celsius * 9 / 5) + 32
    set(value) {
        celsius = (value - 32) * 5 / 9
    }
```
