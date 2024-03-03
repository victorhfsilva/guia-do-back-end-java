# Classes Abstratas e Interfaces em Kotlin

## Introdução

Classes abstratas e interfaces são recursos importantes em Kotlin que permitem a criação de estruturas hierárquicas e contratos para implementação. Ambas são fundamentais na programação orientada a objetos.

## Classes Abstratas

### Definição

Uma classe abstrata é uma classe que não pode ser instanciada diretamente. Ela é marcada com a palavra-chave `abstract`. Pode conter métodos abstratos (sem implementação) e métodos concretos (com implementação).

### Sintaxe

```kotlin
abstract class Veiculo {
    abstract fun acelerar() // Método abstrato sem implementação
    fun desligar() {
        println("Veículo desligado")
    }
}
```

### Utilização

- Uma classe abstrata pode ser herdada por outras classes.
- As classes filhas devem fornecer implementação para todos os métodos abstratos da classe mãe.

```kotlin
class Carro : Veiculo() {
    override fun acelerar() {
        println("Carro acelerando")
    }
}
```

## Interfaces

### Definição

Uma interface é semelhante a uma classe abstrata, mas não pode conter estados (campos com valores). Ela define contratos para implementação, ou seja, quais métodos devem ser implementados por suas classes.

### Sintaxe

```kotlin
interface Conduzivel {
    fun acelerar() // Método da interface sem implementação
    fun frear() {
        println("Veículo freando")
    }
}
```

### Utilização

- Uma classe pode implementar várias interfaces.
- Todos os métodos de uma interface devem ser implementados pela classe que a utiliza.

```kotlin
class Moto : Conduzivel {
    override fun acelerar() {
        println("Moto acelerando")
    }
}
```

## Classes Abstratas vs. Interfaces

### Similaridades

- Ambas podem ter métodos abstratos, que devem ser implementados por classes filhas ou classes que implementam a interface.
- Ambas podem ter métodos concretos (com implementação).

### Diferenças

#### Herança

- Classes abstratas: Uma classe abstrata pode herdar de uma única classe (abstrata ou concreta).
- Interfaces: Uma classe pode implementar várias interfaces.

#### Construtores

- Classes abstratas: Podem ter construtores.
- Interfaces: Não podem ter construtores.

#### Campos

- Classes abstratas: Podem ter campos (variáveis de instância).
- Interfaces: Não podem ter campos com valores.

### Quando Usar

- Classes abstratas: Quando há uma relação "é um" forte entre a classe mãe e suas subclasses, e há uma implementação parcial compartilhada.
- Interfaces: Quando há uma relação "tem um" ou quando várias classes precisam compartilhar um contrato comum, independentemente da hierarquia de herança.

## Considerações Finais

- Use classes abstratas quando desejar fornecer uma implementação base para suas subclasses.
- Use interfaces para definir contratos que podem ser implementados por diferentes classes.
- Você pode combinar o uso de classes abstratas e interfaces em uma hierarquia de classes complexa.

Ao entender as diferenças e semelhanças entre classes abstratas e interfaces, você pode tomar decisões informadas ao projetar e organizar suas classes em Kotlin.