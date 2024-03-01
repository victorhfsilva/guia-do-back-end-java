# Collections
## Set

A interface `Set` no Java é usada para armazenar elementos únicos. Isso significa que não pode haver duplicatas em um conjunto. Vamos explorar três implementações populares da interface `Set`: `HashSet`, `LinkedHashSet` e `TreeSet`.

## HashSet

O `HashSet` é uma implementação da interface `Set` que armazena elementos sem repetição, mas não garante nenhuma ordem específica para os elementos.

### HashSet: Inicialização

Você pode inicializar um `HashSet` de várias maneiras:

1. **Inicialização vazia:**
```java
Set<Tipo> set = new HashSet<>();
```

2. **Inicialização com elementos pré-definidos:**
```java
Set<Tipo> set = new HashSet<>(Arrays.asList(elemento1, elemento2, elemento3, ...));
```

### HashSet: Exemplo

```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExemploHashSet {

    public static void main(String[] args) {
        // Inicialização
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 4, 5, 5));

        // Adicionar elementos ao conjunto
        set.add(6);
        set.add(7);
        set.add(8);

        // Verificar se o conjunto contém um elemento específico
        boolean contemElemento = set.contains(3); // contemElemento = true

        // Remover um elemento do conjunto
        boolean removido = set.remove(4); // removido = true

        // Obter o tamanho do conjunto
        int tamanho = set.size(); // tamanho = 5

        // Iterar sobre os elementos do conjunto (ordem não garantida)
        for (int elemento : set) {
            System.out.println("Elemento: " + elemento);
        }
    }
}
```

O `HashSet` é eficiente para verificações rápidas de pertencimento e inserções/remoções de elementos, mas não mantém nenhuma ordem específica para os elementos.

## LinkedHashSet

O `LinkedHashSet` é uma implementação da interface `Set` que mantém a ordem de inserção dos elementos. Portanto, a ordem em que os elementos são adicionados é a mesma em que são iterados.

### LinkedHashSet: Inicialização

Você pode inicializar um `LinkedHashSet` da mesma maneira que um `HashSet`:

```java
Set<Tipo> linkedSet = new LinkedHashSet<>();
```

## TreeSet

O `TreeSet` é uma implementação da interface `Set` que mantém os elementos em ordem natural ou personalizada. Portanto, os elementos em um `TreeSet` são sempre iterados em ordem.

### TreeSet: Inicialização

Você pode inicializar um `TreeSet` da mesma maneira que um `HashSet`:

```java
Set<Tipo> treeSet = new TreeSet<>();
```

Lembre-se de que, para usar um `TreeSet` com objetos personalizados, esses objetos devem ser comparáveis, ou você deve fornecer um `Comparator` ao construtor do `TreeSet`.