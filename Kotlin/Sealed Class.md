# Sealed Classes em Kotlin

As sealed classes em Kotlin são uma forma de representar hierarquias de classes restritas, onde todas as subclasses são conhecidas antecipadamente. Elas oferecem segurança e extensibilidade para trabalhar com tipos de dados específicos.

## Declaração

Para criar uma sealed class, utilize a palavra-chave `sealed`:

```kotlin
sealed class Resultado {
    class Sucesso(val mensagem: String) : Resultado()
    class Erro(val motivo: String) : Resultado()
}
```

## Uso de Subclasses

Sealed classes geralmente contêm subclasses como casos específicos. No exemplo acima, `Sucesso` e `Erro` são subclasses de `Resultado`.

## Benefícios

- **Exaustividade:** Ao utilizar uma sealed class em uma estrutura `when`, o Kotlin verifica automaticamente se todos os casos são tratados, proporcionando segurança na análise.

```kotlin
fun processarResultado(resultado: Resultado): String {
    return when (resultado) {
        is Resultado.Sucesso -> "Sucesso: ${resultado.mensagem}"
        is Resultado.Erro -> "Erro: ${resultado.motivo}"
    }
}
```

## Funcionalidades Comuns de Data Classes

Uma sealed class pode incluir funcionalidades comuns, como funções `copy`, `toString()`, `hashCode()` e `equals()`. Estas funções devem ser definidas nas subclasses.

## Destructuring

Assim como em data classes, a destructuring é suportada em sealed classes, permitindo extrair os componentes facilmente:

```kotlin
val resultado: Resultado = Resultado.Erro("Conexão perdida")
val (mensagem) = resultado as Resultado.Erro
println("Mensagem de Erro: $mensagem")
```

## Exemplo

```kotlin
// Definição de uma sealed class chamada FormaGeometrica
sealed class FormaGeometrica {

    // Subclasse que representa um círculo
    data class Circulo(val raio: Double) : FormaGeometrica()

    // Subclasse que representa um quadrado
    data class Quadrado(val lado: Double) : FormaGeometrica()

    // Subclasse que representa um retângulo
    data class Retangulo(val largura: Double, val altura: Double) : FormaGeometrica()

    // Subclasse que representa um triângulo
    data class Triangulo(val base: Double, val altura: Double) : FormaGeometrica()
}

// Função que processa diferentes formas geométricas e retorna informações sobre cada uma
fun processarForma(forma: FormaGeometrica): String {
    return when (forma) {
        is FormaGeometrica.Circulo -> "Círculo com raio ${forma.raio}"
        is FormaGeometrica.Quadrado -> "Quadrado com lado ${forma.lado}"
        is FormaGeometrica.Retangulo -> "Retângulo com largura ${forma.largura} e altura ${forma.altura}"
        is FormaGeometrica.Triangulo -> "Triângulo com base ${forma.base} e altura ${forma.altura}"
    }
}

fun main() {
    // Exemplos de diferentes formas geométricas
    val circulo = FormaGeometrica.Circulo(5.0)
    val quadrado = FormaGeometrica.Quadrado(4.0)
    val retangulo = FormaGeometrica.Retangulo(6.0, 3.0)
    val triangulo = FormaGeometrica.Triangulo(5.0, 8.0)

    // Processar e imprimir informações sobre cada forma
    println(processarForma(circulo))
    println(processarForma(quadrado))
    println(processarForma(retangulo))
    println(processarForma(triangulo))
}
```

## Considerações

As sealed classes são ideais quando se deseja representar um conjunto fixo de tipos relacionados, garantindo que todos os casos sejam considerados e tratados adequadamente.