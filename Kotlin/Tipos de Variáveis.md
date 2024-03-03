# Tipos de Variáveis em Kotlin

## **Números em Kotlin**
Os números em Kotlin são divididos em inteiros e de ponto flutuante.

```kotlin
val inteiro: Int = 42
val flutuante: Double = 3.14
```

### **Tipos de Inteiros**
- `Byte`: 8 bits, -128 a 127
- `Short`: 16 bits, -32768 a 32767
- `Int`: 32 bits, -2.147.483.648 a 2.147.483.647
- `Long`: 64 bits, -9.223.372.036.854.775.808 a 9.223.372.036.854.775.807

### **Tipos de Ponto Flutuante**
- `Float`: 32 bits, 1.40129846432481707e-45 a 3.40282346638528860e+38
- `Double`: 64 bits, 4.94065645841246544e-324 a 1.79769313486231570e+308

## **Caracteres em Kotlin**
O tipo `Char` é usado para armazenar um único caractere.

```kotlin
val letra: Char = 'A'
```

## **Strings em Kotlin**
O tipo `String` armazena sequências de caracteres e pode ser representado como strings de escape ou strings brutas.

```kotlin
val texto: String = "Olá, Kotlin!"
val textoBruto: String = """Este é um
    texto bruto em Kotlin."""
```

## **Booleanos em Kotlin**
Variáveis booleanas podem ter valores `true` ou `false`.

```kotlin
val condicao: Boolean = true
```

## **Arrays em Kotlin**
Arrays armazenam dados homogêneos e são declarados usando a função `arrayOf`.

```kotlin
val numeros: IntArray = intArrayOf(1, 2, 3, 4, 5)
```

## **Conversão de Tipos em Kotlin**
A conversão de tipos é o processo de transformar o valor de um tipo de dado em outro. Kotlin fornece funções específicas para essa finalidade.

```kotlin
val x: Int = 100
val y: Long = x.toLong()
```
