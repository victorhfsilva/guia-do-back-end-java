# Herança em Kotlin

## Introdução à Herança

Herança pode ser definida como o processo no qual uma classe adquire os membros (métodos e propriedades) de outra classe. Com o uso da herança, a informação é organizada de forma hierárquica.

- Uma classe que herda os membros de outra classe é conhecida como subclasse (classe derivada ou classe filha).
- A classe cujos membros estão sendo herdados é conhecida como superclasse (classe base ou classe pai).

Herança é uma das características-chave da programação orientada a objetos, que permite ao usuário criar uma nova classe a partir de uma classe existente. Na herança, podemos herdar todas as características da classe base e ainda ter características adicionais próprias.

Todas as classes em Kotlin têm uma superclasse comum chamada `Any`, que é a superclasse padrão para uma classe sem supertipos declarados:

```kotlin
class Exemplo // Herda implicitamente de Any
```

A superclasse `Any` em Kotlin possui três métodos: `equals()`, `hashCode()` e `toString()`. Assim, esses métodos são definidos para todas as classes em Kotlin.

## Declarando Herança

Em Kotlin, tudo é por padrão `final`, portanto, usamos a palavra-chave `open` na frente da declaração da classe para torná-la herdável por outras classes. Kotlin usa o operador ":" para herdar uma classe.

**Exemplo:**
Considere o seguinte exemplo de herança:

```kotlin
open class ABC {
   fun pensar() {
      println("Ei!! Estou pensando")
   }
}
class BCD: ABC() // Herança acontece usando o construtor padrão
```

### Sobrescrevendo Métodos

Ao sobrescrever o método `pensar()` na classe filha, precisamos considerar o seguinte exemplo em que criamos duas classes e sobrescrevemos um de seus métodos na classe filha.

```kotlin
open class ABC {
   open fun pensar() {
      println("Ei!! Estou pensando")
   }
}
class BCD: ABC() { 
    // Herança acontece usando o construtor padrão 
   override fun pensar() {
      println("Eu sou da classe filha")
   }
}
```

### Restrições na Sobrescrita

Um membro marcado com a palavra-chave `override` é ele próprio aberto (`open`), portanto, pode ser sobrescrito em subclasses. Se desejar proibir a re-sobrescrita, você deve torná-lo final da seguinte maneira:

```kotlin
class BCD: ABC() {
   final override fun pensar() {
      println("Eu sou da classe filha")
   }
}
```

### Sobrescrevendo Propriedades

O mecanismo de sobrescrita funciona em propriedades da mesma forma que funciona em métodos. As propriedades declaradas em uma superclasse que são então redeclaradas em uma classe derivada devem ser prefixadas com a palavra-chave `override` e devem ter um tipo compatível.

```kotlin
open class ABC {
   open val contador: Int = 0
   
   open fun pensar() {
      println("Ei!! Estou pensando")
   }
}
class BCD: ABC() {
   override val contador: Int
   
   init {
      contador = 100
   }

   override fun pensar() {
      println("Eu sou da classe filha")
   }
   
   fun exibirContador() {
      println("O valor do contador é $contador")
   }
}
```

### Utilizando Construtor Primário na Sobrescrita

Podemos usar a palavra-chave `override` como parte da declaração de propriedade em um construtor primário. O exemplo a seguir utiliza um construtor primário para substituir a propriedade `contador`, que terá um valor padrão de 400 caso não seja passado nenhum valor para o construtor:

```kotlin
open class ABC {
   open val contador: Int = 0
   
   open fun pensar() {
      println("Ei!! Estou pensando")
   }
}
class BCD(override val contador: Int = 400): ABC() {

   override fun pensar() {
      println("Eu sou da classe filha")
   }
   
   fun exibirContador() {
      println("O valor do contador é $contador")
   }
}
```

## Ordem de Inicialização da Classe Derivada

Quando criamos um objeto de uma classe derivada, a inicialização do construtor começa pela classe base. Isso significa que, antes de mais nada, as propriedades da classe base serão inicializadas; após isso, qualquer construtor de classe derivada será chamado, e o mesmo se aplica a quaisquer classes derivadas subsequentes.

**Exemplo:**
```kotlin
open class Base {
   init {
      println("Estou na classe Base")
   }
}
open class Filha: Base() {
   init {
      println("Estou na classe Filha")
   }
}
class Neta: Filha() {
   init {
      println("Estou na classe Neta")
   }
}
```

Ao executar o programa acima, ele gerará a seguinte saída:

```
Estou na classe Base
Estou na classe Filha
Estou na classe Neta
```

## Acessando Membros da Superclasse

O código em uma classe derivada pode chamar diretamente as funções e propriedades de sua superclasse usando a palavra-chave `super`:

```kotlin
open class Base() {
   open val nome:String
   init {
      nome = "Base"
   }
   open fun exibirNome() {
      println("Estou na " +  this.nome)
   }
}
class Filha(): Base() {
   override fun exibirNome() {
      super.exibirNome()
      println("Estou na " + super.nome)
   }
}
```

Ao executar o programa acima, ele gerará a seguinte saída:

```
Estou na Base
Estou na Base
```

## Regras de Sobrescrita

Se uma classe filha herda implementações múltiplas do mesmo membro de suas superclasses imediatas, ela deve sobrescrever esse membro e fornecer sua própria implementação.

Isso é diferente de uma classe filha que herda membros de um único pai; neste caso, não é obrigatório para a classe filha fornecer a implementação de todos os membros abertos.

**Exemplo:**

```kotlin
open class Retangulo {
    open fun desenhar() { /* ... */ }
}

interface Poligono {
    fun desenhar() { /* ... */ } // membros de interface são 'open' por padrão
}

class Quadrado() : Retangulo(), Poligono {
    // O compilador exige que desenhar() seja sobrescrito:
    override fun desenhar() {
        super<Retangulo>.desenhar() // chamada para Retangulo.desenhar()
    }
}
```

É permitido herdar tanto de Retangulo quanto de Poligono, mas ambos têm implementações de seu método desenhar(), então você precisa sobrescrever desenhar() em Quadrado e fornecer uma implementação separada para eliminar a ambiguidade.