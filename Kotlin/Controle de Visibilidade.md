# Controle de Visibilidade em Kotlin

## Introdução

O controle de visibilidade em Kotlin é essencial para restringir ou permitir o acesso a membros de uma classe, proporcionando encapsulamento e segurança ao código. Existem quatro níveis de visibilidade em Kotlin: `public`, `private`, `protected` e `internal`.

## `public`

### Definição

- **Visibilidade mais ampla:** Membros marcados como `public` são acessíveis de qualquer lugar, dentro ou fora do mesmo módulo.

### Exemplo

```kotlin
class Exemplo {
    public val dadoPublico: Int = 42

    public fun metodoPublico() {
        // Lógica do método
    }
}
```

## `private`

### Definição

- **Visibilidade mais restrita:** Membros marcados como `private` são acessíveis apenas dentro da classe que os contém.

### Exemplo

```kotlin
class Exemplo {
    private val dadoPrivado: String = "Segredo"

    private fun metodoPrivado() {
        // Lógica do método
    }
}
```

## `protected`

### Definição

- **Visibilidade intermediária:** Membros marcados como `protected` são acessíveis dentro da classe que os contém e suas subclasses.

### Exemplo

```kotlin
open class ClasseBase {
    protected val dadoProtegido: Double = 3.14
}

class ClasseDerivada : ClasseBase() {
    fun acessarDadoProtegido() {
        println("Dado protegido: $dadoProtegido")
    }
}
```

## `internal`

### Definição

- **Visibilidade de módulo:** Membros marcados como `internal` são acessíveis apenas dentro do mesmo módulo.

### Exemplo

```kotlin
// Módulo A
class ClasseA {
    internal val dadoInterno: Boolean = true
}

// Módulo B
class ClasseB {
    fun acessarDadoInterno(classeA: ClasseA) {
        println("Dado interno: ${classeA.dadoInterno}")
    }
}
```

## Considerações Finais

- A escolha do nível de visibilidade apropriado é crucial para garantir a segurança e manutenibilidade do código.
- `public` é o nível padrão se nenhum modificador de visibilidade for especificado.
- O controle de visibilidade promove o encapsulamento, permitindo um acesso controlado aos membros de uma classe.

Entender e aplicar os diferentes níveis de visibilidade em Kotlin é fundamental para criar código mais seguro, modular e compreensível.