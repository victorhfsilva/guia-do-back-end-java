# Tratamento de Exceções em Kotlin

## Introdução

O tratamento de exceções é uma parte crucial da programação que lida com situações imprevistas ou erros durante a execução de um programa. Em Kotlin, o tratamento de exceções é feito usando blocos `try`, `catch`, `finally` e a hierarquia de exceções.

## Hierarquia de Exceções

As exceções em Kotlin fazem parte da hierarquia de classes, sendo a classe base `Throwable`. Existem duas principais categorias de exceções: `Error` (erros graves) e `Exception` (situações excepcionais).

```kotlin
try {
    // código que pode gerar uma exceção
} catch (e: Exception) {
    // tratamento específico para exceção
} finally {
    // bloco opcional, sempre é executado, mesmo se houver exceção ou não
}
```

## Bloco `try` e `catch`

O bloco `try` é usado para envolver o código que pode gerar exceções. Se uma exceção ocorrer, o controle é transferido para o bloco `catch` correspondente.

```kotlin
try {
    // código que pode gerar uma exceção
} catch (e: SomeException) {
    // tratamento específico para SomeException
} catch (e: AnotherException) {
    // tratamento específico para AnotherException
} finally {
    // bloco opcional, sempre é executado, mesmo se houver exceção ou não
}
```

## Lançando Exceções

Você pode lançar exceções explicitamente usando a palavra-chave `throw`. Isso é útil para sinalizar erros em seu próprio código.

```kotlin
fun dividir(a: Int, b: Int): Int {
    if (b == 0) {
        throw ArithmeticException("Divisão por zero não permitida")
    }
    return a / b
}
```

## Uso de `finally`

O bloco `finally` é usado para garantir que determinado código seja executado, independentemente de uma exceção ser lançada ou não.

```kotlin
var recurso: Recurso? = null
try {
    recurso = Recurso()
    // código que utiliza o recurso
} finally {
    recurso?.fechar()
}
```

## Tratamento Condicional

Você pode tratar exceções de maneira condicional usando a expressão `try` como uma expressão que retorna um valor.

```kotlin
val resultado = try {
    operacaoPotencialmenteFalha()
} catch (e: ExcecaoPersonalizada) {
    // tratamento da exceção
    valorPadrao
}
```

Ao usar a expressão `try` como uma expressão, você pode manipular o resultado do bloco `try` ou o resultado do bloco `catch`.

