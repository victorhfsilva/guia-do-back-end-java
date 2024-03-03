# Sintaxe Básica do Kotlin

## **Ponto de Entrada do Programa Kotlin**
O ponto de entrada de um aplicativo Kotlin é a função `main()`. Uma função é um bloco de código projetado para realizar uma tarefa específica.

```kotlin
fun main() {
   var string: String  = "Hello, World!"
   println("$string")
}
```

## **Ponto de Entrada com Parâmetros**
A função `main()` aceita um número variável de argumentos do tipo String.

```kotlin
fun main(args: Array<String>){
    println("Hello, world!")
}
```

## **print() vs println()**
- `print()` imprime seu argumento na saída padrão.
- `println()` também imprime seu argumento, mas adiciona uma quebra de linha na saída.

```kotlin
fun main(args: Array<String>){
    println("Hello,")
    println(" world!")

    print("Hello,")
    print(" world!")
}
```

## **Ponto e Vírgula (;) em Kotlin**
Em Kotlin, não é necessário usar ponto e vírgula para terminar uma declaração, ao contrário de muitas outras linguagens de programação.

```kotlin
fun main() {
    println("Sem ponto e vírgula")
    
    println("Com ponto e vírgula");
}
```

## 6. **Pacotes em Kotlin**
O código Kotlin é geralmente definido em pacotes, embora a especificação de pacotes seja opcional. Se nenhum pacote for especificado em um arquivo fonte, seu conteúdo vai para o pacote padrão.

```kotlin
package edu.exemplo

fun main() {
    println("Hello, World!")
}
```
