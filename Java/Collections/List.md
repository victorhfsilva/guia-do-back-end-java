# Collections

## List

A interface `List` é parte do framework Collections do Java e representa uma coleção ordenada de elementos, permitindo a inclusão de elementos duplicados e garantindo a ordem de inserção. Existem diferentes implementações da interface `List`, como `ArrayList`, `LinkedList`, entre outras. Vamos explorar um pouco mais sobre o `ArrayList` e como ele pode ser usado.

### ArrayList

O `ArrayList` é uma implementação da interface `List` que utiliza um array dinâmico para armazenar elementos. Ele oferece alta eficiência para operações de acesso, mas pode ser menos eficiente para inserções e remoções frequentes, devido à necessidade de redimensionar o array interno.

### Inicialização de um ArrayList

Você pode inicializar um `ArrayList` de diferentes maneiras:

1. **Inicialização vazia:**
```java
List<Tipo> lista = new ArrayList<>();
```

2. **Especificando um tipo concreto:**
```java
ArrayList<Tipo> lista = new ArrayList<>();
```

3. **Inicialização com elementos:**
```java
List<Tipo> lista = new ArrayList<>(Arrays.asList(elementos));
```

### Exemplo

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExemploArrayList {

    public static void main(String[] args) {
        List<Double> lista = new ArrayList<>(Arrays.asList(1.2, 3.4, 5.6, 7.8));

        // Adicionar um novo elemento à lista
        lista.add(9.0);

        // Acessar um elemento pelo índice
        double elemento = lista.get(2); // elemento = 5.6

        // Remover um elemento pelo valor
        lista.remove(3.4);

        // Verificar se a lista contém um valor específico
        boolean contemValor = lista.contains(5.6); // contemValor = true

        // Obter o tamanho da lista
        int tamanho = lista.size(); // tamanho = 4

        // Iterar sobre os elementos da lista
        for (Double valor : lista) {
            System.out.println(valor);
        }
    }
}
```

O `ArrayList` é uma escolha adequada quando você precisa de uma lista ordenada e não planeja fazer muitas inserções ou remoções frequentes. Se o seu foco for em inserções/remoções frequentes, a classe `LinkedList` pode ser mais apropriada devido à sua estrutura de dados baseada em nós encadeados.

### LinkedList

A `LinkedList` é uma implementação da interface `List` que utiliza uma estrutura de dados de lista duplamente encadeada para armazenar elementos. Ela é mais eficiente para inserções e remoções frequentes, mas pode ser menos eficiente para operações de acesso.

### Inicialização de uma LinkedList

Você pode inicializar uma `LinkedList` de diferentes maneiras:

1. **Inicialização vazia:**
```java
List<Tipo> lista = new LinkedList<>();
```

2. **Especificando um tipo concreto:**
```java
LinkedList<Tipo> lista = new LinkedList<>();
```

3. **Inicialização com elementos:**
```java
List<Tipo> lista = new LinkedList<>(Arrays.asList(elementos));
```

### Exemplo

```java
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

public class ExemploLinkedList {

    public static void main(String[] args) {
        List<String> lista = new LinkedList<>(Arrays.asList("A", "B", "C"));

        // Adicionar um novo elemento à lista
        lista.add("D");

        // Acessar um elemento pelo índice
        String elemento = lista.get(2); // elemento = "C"

        // Remover um elemento pelo valor
        lista.remove("B");

        // Verificar se a lista contém um valor específico
        boolean contemValor = lista.contains("A"); // contemValor = true

        // Obter o tamanho da lista
        int tamanho = lista.size(); // tamanho = 3

        // Iterar sobre os elementos da lista
        for (String valor : lista) {
            System.out.println(valor);
        }
    }
}
```

A `LinkedList` é uma escolha adequada quando você precisa fazer muitas inserções e remoções frequentes, já que sua estrutura de lista duplamente encadeada facilita essas operações. No entanto, se você planeja acessar os elementos frequentemente por índice, a classe `ArrayList` pode ser mais eficiente.