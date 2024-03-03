# Funções em Kotlin

## **Introdução**

Funções em Kotlin são blocos de código que podem ser chamados com um nome específico. Elas podem ter parâmetros, um tipo de retorno e podem ser aninhadas dentro de classes ou outras funções. Este cheatsheet abordará alguns aspectos fundamentais das funções em Kotlin.

### **Definição Básica**

```kotlin
fun nomeDaFuncao(parametro1: Tipo, parametro2: Tipo): TipoDeRetorno {
    // Corpo da função
    // Código a ser executado
    return valorDeRetorno
}
```

## **Unit Returning Functions**

### **Funções sem Retorno Explícito**

Se uma função não retorna nenhum valor explicitamente, seu tipo de retorno é `Unit`. A palavra-chave `Unit` pode ser omitida ao declarar a função.

```kotlin
fun cumprimentar(nome: String) {
    println("Olá, $nome!")
}
```

### **Declaração Simples**

```kotlin
fun imprimirMensagem(): Unit {
    println("Esta função não retorna nenhum valor.")
}
```

## **Higher-Order Functions**

### **Funções de Alta Ordem**

Funções de alta ordem são aquelas que recebem outras funções como parâmetros ou retornam funções. Elas são uma parte fundamental da programação funcional em Kotlin.

### **Exemplo**

```kotlin
fun main(args: Array<String>) {
   
   val result = calculate(4, 5, ::sum) 
   println( result )
   
}
fun sum(a: Int, b: Int) = a + b 

fun calculate(a: Int, b: Int, operation:(Int, Int) -> Int): Int {
    return operation(a, b)                                       
}
```


## **Lambda Expressions**

### **Expressões Lambda**

As expressões lambda são blocos de código concisos que podem ser usados em lugares onde funções são esperadas. Elas são especialmente úteis ao trabalhar com funções de alta ordem.

### **Sintaxe Básica**

```kotlin
val somar: (Int, Int) -> Int = { a, b -> a + b }
val resultado = somar(3, 4)  // Resultado é 7
```

### **Com Inferência de Tipo**

```kotlin
val dobrar: (Int) -> Int = { it * 2 }
val resultadoDobro = dobrar(5)  // Resultado é 10
```

### **Expressões Lambda como Parâmetros**

```kotlin
fun main(args: Array<String>) {
   
   val result = calculate(4, 5, (a, b) -> a + b) 
   println( result )
   
}

fun calculate(a: Int, b: Int, operation:(Int, Int) -> Int): Int {
    return operation(a, b)                                       
}
```