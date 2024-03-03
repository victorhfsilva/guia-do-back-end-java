# Condicionais If Else em Kotlin

## **Introdução**

As expressões condicionais `if...else` em Kotlin são fundamentais para controlar o fluxo de execução do programa. Elas permitem que você tome decisões com base em condições específicas. 

### **Sintaxe Básica**

A sintaxe básica da expressão condicional `if...else` é a seguinte:

```kotlin
if (condicao) {
   // bloco de código A a ser executado se a condição for verdadeira
} else {
   // bloco de código B a ser executado se a condição for falsa
}
```

## **Expressão `if...else` como uma Expressão**

Em Kotlin, a expressão `if...else` também pode ser usada como uma expressão que retorna um valor, o qual pode ser atribuído a uma variável.

### **Sintaxe para Expressão**

```kotlin
val resultado = if (condicao) {
   // valor a ser retornado se a condição for verdadeira
} else {
   // valor a ser retornado se a condição for falsa
}
```

#### **Exemplo Simples**

```kotlin
val idade: Int = 10

val resultado = if (idade > 18) {
    "Adulto"
} else {
    "Menor"
}
println(resultado)
```

## **Expressões Lambda e `if...else`**

Você pode incorporar expressões lambda em suas condicionais `if...else`, proporcionando maior flexibilidade na execução do código.

### **Exemplo de Uso de Expressões Lambda**

```kotlin
val resultado = if (idade > 18) {
    { println("Condição verdadeira") }
    "Adulto"
} else {
    { println("Condição falsa") }
    "Menor"
}
println("O valor do resultado: $resultado")
```

## **`if...else if` em Cascata**

Você pode encadear várias condições usando `else if` para especificar novas condições se a primeira for falsa.

### **Sintaxe para `if...else if`**

```kotlin
if (condicao1) {
  // bloco de código A a ser executado se a condição1 for verdadeira
} else if (condicao2) {
  // bloco de código B a ser executado se a condição2 for verdadeira
} else {
  // bloco de código C a ser executado se a condição1 e a condição2 forem falsas
}
```

#### **Exemplo Prático**

```kotlin
val resultado = if (idade > 19) {
    "Adulto"
} else if (idade > 12 && idade < 20) {
    "Adolescente"
} else {
    "Menor"
}
println("O valor do resultado: $resultado")
```

## **Expressões Condicionais Aninhadas**

Kotlin permite colocar uma expressão `if` dentro de outra, criando assim expressões condicionais aninhadas.

### **Sintaxe para Aninhamento**

```kotlin
if (condicao1) {
   // bloco de código A a ser executado se a condição1 for verdadeira
   if (condicao2) {
      // bloco de código B a ser executado se a condição2 for verdadeira
   } else {
      // bloco de código C a ser executado se a condição2 for falsa
   }
} else {
  // bloco de código D a ser executado se a condição1 for falsa
}
```

#### **Exemplo de Aninhamento**

```kotlin
val resultado = if (idade > 12) {
   if (idade > 12 && idade < 20) {
       "Adolescente"
   } else {
       "Adulto"
   }
} else {
    "Menor"
}
println("O valor do resultado: $resultado")
```

