# Comentários em Kotlin

## **Comentários Simples**
Comentários simples são utilizados para incluir informações no código que não são executadas pelo compilador. Eles começam com `//`.

```kotlin
// Este é um comentário simples em Kotlin
val numero = 42  // Comentário na mesma linha
```

## **Comentários Multilinha**
Comentários multilinha são úteis para inserir explicações mais extensas. Eles são delimitados por `/*` e `*/`.

```kotlin
/*
   Este é um comentário
   multilinha em Kotlin.
*/
val resultado = 3 + 4
```

## **Documentação (KDoc)**
Kotlin suporta o KDoc, um formato de documentação similar ao JavaDoc. Os comentários KDoc começam com `/**` e podem incluir tags para documentar parâmetros, retornos, etc.

```kotlin
/**
 * Esta função soma dois números.
 * @param a Primeiro número a ser somado.
 * @param b Segundo número a ser somado.
 * @return Resultado da soma.
 */
fun somar(a: Int, b: Int): Int {
    return a + b
}
```



## **Anotações de Código em Kotlin**

Anotações de código são mensagens especiais inseridas nos comentários para fornecer informações adicionais ou instruções ao desenvolvedor, ferramentas de construção ou ao ambiente de desenvolvimento. 

### **Anotação `TODO`**
A anotação `TODO` é usada para sinalizar áreas do código que precisam ser implementadas ou aprimoradas. É uma maneira rápida de identificar tarefas pendentes.

```kotlin
// TODO: Implementar validação de entrada
fun processarDados(dados: String) {
    // Lógica atual
}
```

## **Anotação `FIXME`**
Semelhante ao `TODO`, a anotação `FIXME` é usada para destacar código problemático que precisa ser corrigido.

```kotlin
// FIXME: Corrigir bug na lógica de ordenação
fun ordenarLista(lista: List<Int>): List<Int> {
    // Implementação atual
}
```

## **Anotação `BUG`**
A anotação `BUG` é usada para relatar a presença de um bug no código. Ajuda a chamar a atenção para problemas conhecidos.

```kotlin
// BUG: Esta função produz resultados incorretos para valores negativos
fun calcularResultado(valor: Int): Int {
    // Implementação atual
}
```

## **Anotação `OPTIMIZE`**
A anotação `OPTIMIZE` destaca oportunidades de otimização no código. Pode ser usada para indicar áreas que podem ser melhoradas em termos de desempenho.

```kotlin
// OPTIMIZE: Refatorar esta parte do código para melhorar a eficiência
fun otimizarAlgoritmo() {
    // Implementação atual
}
```

## **Anotação `REVIEW`**
A anotação `REVIEW` é usada para solicitar uma revisão de código. Pode ser útil quando uma parte específica do código precisa ser examinada por outros desenvolvedores.

```kotlin
// REVIEW: Verificar se esta lógica de tratamento de erros está correta
fun tratarErros() {
    // Implementação atual
}
```

## **Anotação `DEPRECATED`**
A anotação `DEPRECATED` marca elementos (como funções ou classes) que estão obsoletos e serão removidos em versões futuras. Ajuda a alertar os desenvolvedores sobre mudanças planejadas.

```kotlin
@Deprecated("Esta função será removida na próxima versão. Use aNovaFuncao() em vez disso.")
fun funcaoAntiga() {
    // Implementação atual
}
```

Essas anotações proporcionam uma maneira eficaz de comunicar informações importantes no código-fonte, facilitando a colaboração e manutenção do código ao longo do tempo. Escolha a anotação apropriada com base na natureza da mensagem que deseja transmitir.