# Getters e Setters em Kotlin

## Introdução

Em Kotlin, os getters e setters são mecanismos que permitem acessar e modificar os valores das propriedades de uma classe. Ao contrário de algumas outras linguagens, em Kotlin, você não precisa criar explicitamente getters e setters, a menos que precise de uma lógica personalizada.

## Propriedades

Em Kotlin, as propriedades são declaradas usando as palavras-chave `val` (para propriedades somente leitura) e `var` (para propriedades mutáveis). O compilador automaticamente gera getters e setters padrão para essas propriedades.

```kotlin
class Pessoa {
    var nome: String = ""
    val idade: Int = 0
}
```

No exemplo acima, `nome` é uma propriedade mutável e `idade` é uma propriedade somente leitura. O compilador gera automaticamente getters e setters para `nome` e um getter para `idade`.

## Getters Padrão

Para propriedades declaradas com `val` (imutáveis), apenas o getter é gerado automaticamente.

```kotlin
val total: Double = 100.0
```

O getter padrão para a propriedade `total` é automaticamente gerado.

## Setters Padrão

Para propriedades declaradas com `var` (mutáveis), tanto o getter quanto o setter são gerados automaticamente.

```kotlin
var contador: Int = 0
```

O getter e o setter padrão para a propriedade `contador` são automaticamente gerados.

## Acesso Personalizado

Se precisar de lógica personalizada ao acessar ou modificar uma propriedade, você pode definir seus próprios getters e setters.

```kotlin
var preco: Double = 0.0
    get() = field * 1.1  // Getter personalizado
    set(value) {
        if (value > 0) {
            field = value  // Setter personalizado
        }
    }
```

No exemplo acima, um getter personalizado é usado para calcular um preço com um acréscimo de 10%. O setter personalizado verifica se o novo valor é positivo antes de atribuí-lo à propriedade `preco`.

## Campos de Suporte

Os getters e setters em Kotlin podem acessar um campo de suporte especial chamado `field`. Este campo contém o valor real da propriedade.

```kotlin
var quantidade: Int = 0
    set(value) {
        if (value >= 0) {
            field = value
        }
    }
```

O uso de `field` permite que você se refira ao valor real da propriedade, evitando assim recursão infinita no caso de você referenciar a própria propriedade dentro do getter ou setter.

Lembre-se de que, em muitos casos, os getters e setters padrão gerados automaticamente pelo Kotlin são suficientes e não é necessário fornecer uma implementação personalizada. Apenas adicione lógica personalizada quando necessário.