# Padrão de Projeto Template Method

O padrão Template Method é um padrão de design comportamental que define a estrutura de um algoritmo em uma classe base, permitindo que subclasses forneçam implementações específicas de partes desse algoritmo. Ele permite que o algoritmo seja redefinido pelas subclasses sem alterar sua estrutura geral. O Template Method é útil quando você deseja definir um esqueleto de algoritmo com etapas comuns, mas permitindo que as subclasses forneçam implementações específicas para algumas etapas.

### Vantagens

- **Reutilização de código**: O Template Method permite que partes do algoritmo sejam reutilizadas pelas subclasses, evitando duplicação de código.
- **Padronização**: Ele define uma estrutura comum para algoritmos, garantindo consistência na implementação em diferentes subclasses.
- **Extensibilidade**: As subclasses podem estender ou substituir partes específicas do algoritmo, oferecendo flexibilidade para adaptação a diferentes contextos.

### Quando usar o padrão Template Method?

- Quando você tem um algoritmo com uma estrutura fixa, mas com algumas etapas que podem variar entre as subclasses.
- Quando você deseja evitar a duplicação de código em várias subclasses que compartilham partes comuns de um algoritmo.
- Quando você deseja permitir que as subclasses forneçam implementações específicas para partes do algoritmo, enquanto mantém a estrutura geral inalterada.

### Componentes do Padrão

- **Classe Abstrata (Abstract Class)**: Define o esqueleto do algoritmo em um método comum chamado "Template Method". Ele pode conter métodos concretos, abstratos ou hooks que as subclasses podem estender ou substituir.
  
- **Método Template (Template Method)**: Define a estrutura geral do algoritmo, chamando os métodos específicos do algoritmo em uma ordem predefinida. Pode conter etapas comuns e chamadas para métodos abstratos ou hooks.

- **Métodos Concretos (Concrete Methods)**: Implementações padrão de partes do algoritmo que são comuns a todas as subclasses. Podem ser chamados pelo método Template Method ou pelos métodos abstratos.

### Exemplo de Implementação

Vamos supor que temos um algoritmo para preparar diferentes tipos de bebidas quentes, como café e chá. Usaremos o padrão Template Method para definir o esqueleto do algoritmo de preparação, enquanto permitimos que as subclasses forneçam implementações específicas para algumas etapas.

### Exemplo de Código

```java
// Classe Abstrata: Bebida Quente
abstract class HotDrink {
    // Método Template: Preparação da bebida quente
    public final void prepareDrink() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Métodos abstratos que devem ser implementados pelas subclasses
    protected abstract void brew();
    protected abstract void addCondiments();

    // Métodos concretos comuns a todas as bebidas quentes
    private void boilWater() {
        System.out.println("Fervendo a água");
    }

    private void pourInCup() {
        System.out.println("Despejando a bebida na xícara");
    }
}

// Subclasse: Café
class Coffee extends HotDrink {
    // Implementação específica para a preparação do café
    protected void brew() {
        System.out.println("Fazendo café");
    }

    protected void addCondiments() {
        System.out.println("Adicionando açúcar e leite");
    }
}

// Subclasse: Chá
class Tea extends HotDrink {
    // Implementação específica para a preparação do chá
    protected void brew() {
        System.out.println("Preparando chá");
    }

    protected void addCondiments() {
        System.out.println("Adicionando limão");
    }
}

// Cliente
public class Main {
    public static void main(String[] args) {
        HotDrink coffee = new Coffee();
        HotDrink tea = new Tea();

        // Preparando café
        System.out.println("Preparando café:");
        coffee.prepareDrink();

        System.out.println();

        // Preparando chá
        System.out.println("Preparando chá:");
        tea.prepareDrink();
    }
}
```

### Saída Esperada

```
Preparando café:
Fervendo a água
Fazendo café
Despejando a bebida na xícara
Adicionando açúcar e leite

Preparando chá:
Fervendo a água
Preparando chá
Despejando a bebida na xícara
Adicionando limão
```

Neste exemplo, o padrão Template Method é utilizado para definir um esqueleto comum para a preparação de diferentes bebidas quentes. A classe abstrata `HotDrink` define o método Template `prepareDrink`, que chama etapas comuns de preparação de bebidas quentes, como ferver água, despejar na xícara, entre outras. As subclasses `Coffee` e `Tea` fornecem implementações específicas para as etapas de fazer café e chá, respectivamente. O método Template `prepareDrink` permanece inalterado, enquanto as implementações específicas são fornecidas pelas subclasses. Isso promove a reutilização de código e a flexibilidade na definição de diferentes tipos de bebidas quentes.