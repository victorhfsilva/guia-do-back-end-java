# Variáveis em Kotlin

## **Declaração de Variáveis**
Em Kotlin, as variáveis podem ser mutáveis (alteráveis) ou imutáveis (constantes).

### Variáveis Mutáveis
Variáveis mutáveis são declaradas usando `var` e podem ter seus valores modificados após a inicialização.

```kotlin
var contador = 0
contador = 1  // Válido, pois a variável é mutável
```

### Variáveis Imutáveis
Variáveis imutáveis são declaradas usando `val` e não podem ser reatribuídas após a inicialização.

```kotlin
val pi = 3.14
// pi = 3.1415  // Inválido, pois a variável é imutável
```

## **Null Safety**
Kotlin incorpora null safety para evitar erros de NullPointerException. Variáveis não podem ser nulas a menos que explicitamente declaradas como tal.

```kotlin
var nullSafeName: String = "Kotlin"  // Não pode ser nulo
var name: String? = null  // Válido
```

## **Tipos de Variáveis**
Kotlin é uma linguagem fortemente tipada, mas oferece inferência de tipo. Os tipos incluem:

- **Inteiro:** `Int`
  
  ```kotlin
  var idade: Int = 25
  ```

- **Decimal:** `Double` ou `Float`
  
  ```kotlin
  var altura: Double = 1.75
  ```

- **Caractere:** `Char`
  
  ```kotlin
  var inicial: Char = 'K'
  ```

- **Booleano:** `Boolean`
  
  ```kotlin
  var ehKotlinDivertido: Boolean = true
  ```

- **Texto:** `String`
  
  ```kotlin
  var mensagem: String = "Olá, Kotlin!"
  ```

## **Regras de Nomenclatura**
- Use nomes descritivos: `idade`, `nomeCompleto`.
- Siga a convenção camelCase: `nomeDaVariavel`, `idadeDoUsuario`.
- Evite caracteres especiais, exceto `_`.
- Evite palavras reservadas e acrônimos ambíguos.

```kotlin
var numeroDeAlunos = 42
var usuarioAtivo = true
var nomeCompletoDoUsuario = "John Doe"
```