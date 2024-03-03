# Controle de Fluxo em Kotlin: Break e Continue

## Break em Loops

O comando `break` em Kotlin é utilizado para sair de um loop assim que uma determinada condição é atendida. Este loop pode ser do tipo for, while ou do...while.

### Exemplo
```kotlin
var contador = 0

while (contador < 5) {
    println("Contador: $contador")
    contador++
    if (contador == 3) {
        break
    }
}
```

## Break com Rótulo (Label)

Em Kotlin, um rótulo é uma forma de identificador seguido pelo símbolo @, por exemplo, `rotulo@` ou `externo@`. O comando `break` com rótulo é usado para encerrar um loop específico. Isso é feito usando a expressão `break@NomeDoRotulo`.

### Exemplo
```kotlin
externo@ for (i in 1..3) {
    interno@ for (j in 1..3) {
        println("i = $i e j = $j")
        if (i == 2) {
            break@externo
        }
    }
}
```

## Continue em Loops

O comando `continue` em Kotlin interrompe a iteração atual do loop (ignora a parte após o comando `continue` até o final do loop) e continua com a próxima iteração.

### Exemplo
```kotlin
var i = 0

while (i++ < 6) {
    if (i == 3) {
        continue
    }
    println(i)
}
```

## Continue com Rótulo (Label)

O comando `continue` com rótulo é utilizado para pular parte de um loop específico. Isso é feito usando a expressão `continue@NomeDoRotulo`.

### Exemplo
```kotlin
externo@ for (i in 1..3) {
    interno@ for (j in 1..3) {
        if (i == 2) {
            continue@externo
        }
        println("i = $i e j = $j")
    }
}
```