# Loops While e Do-While em Kotlin

## Introdução
Em Kotlin, loops são estruturas de controle que permitem repetir um bloco de código várias vezes. O loop `while` e o loop `do-while` são dois tipos de loops que oferecem diferentes abordagens para a execução repetida de código.

## Loop While
O loop `while` em Kotlin executa um bloco de código repetidamente enquanto uma condição específica permanecer verdadeira.

### Sintaxe
```kotlin
while (condicao) {
    // Código a ser executado enquanto a condição for verdadeira
}
```

### Exemplo
```kotlin
var contador = 0

while (contador < 5) {
    println("Contador: $contador")
    contador++
}
```

## Loop Do-While
O loop `do-while` executa um bloco de código pelo menos uma vez e continua a repetição enquanto uma condição específica permanecer verdadeira.

### Sintaxe
```kotlin
do {
    // Código a ser executado pelo menos uma vez
} while (condicao)
```

### Exemplo
```kotlin
var tentativas = 0

do {
    println("Tentativa: $tentativas")
    tentativas++
} while (tentativas < 3)
```

## Diferenças entre While e Do-While
A principal diferença entre os loops `while` e `do-while` é que o bloco de código dentro do loop `do-while` é executado pelo menos uma vez, independentemente da condição.

## Melhores Práticas
- Certifique-se de que a condição do loop eventualmente se torne falsa para evitar loops infinitos.
- Evite modificar a variável de controle de loop dentro do bloco de código de forma que a condição nunca seja falsa.

