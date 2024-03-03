# Kotlin

## Also

A função de escopo `also` em Kotlin é uma ferramenta poderosa para realizar ações em um objeto enquanto ainda retorna o próprio objeto. 

## Introdução

A função `also` é útil quando queremos realizar operações em um objeto sem modificar seu estado interno. Ela simplifica o código ao permitir a execução de ações adicionais no objeto e ainda retornar o objeto original.

## Sintaxe Básica

A estrutura fundamental da função `also` é a seguinte:

```kotlin
val resultado = objeto.also {
    // Bloco de código a ser executado no contexto do objeto
    operacao1()
    operacao2()
    // ...
}
```

## Utilizando para Inicialização e Configuração

O `also` é frequentemente empregado para inicializar objetos e configurar suas propriedades de maneira concisa.

```kotlin
data class Pessoa(var nome: String, var idade: Int)

val pessoa = Pessoa("João", 25).also {
    // Configuração de propriedades
    it.idade += 5
}
```

## Encadeando Operações

Facilitando o encadeamento de operações, o `also` melhora a legibilidade do código ao agrupar várias ações em um único bloco.

```kotlin
val resultado = objeto.also {
    operacao1()
    operacao2()
    // ...
}.calcularResultadoFinal()
```

## Executando e Logando

O `also` é especialmente útil para realizar operações e, ao mesmo tempo, logar informações úteis para fins de depuração.

```kotlin
val resultado = calcularOperacaoComplexa().also {
    println("Resultado intermediário: $it")
}
```

## Conclusão

A função de escopo `also` em Kotlin é uma ferramenta valiosa para realizar operações adicionais em objetos sem modificar seu estado interno. Seja para inicializar objetos, encadear operações, logar informações ou realizar outras tarefas enquanto se mantém a cadeia de chamadas fluida, o `also` é um recurso versátil na caixa de ferramentas do desenvolvedor Kotlin.