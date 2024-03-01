# Collections

## Map

A interface `Map` é uma parte fundamental do framework Collections do Java e é usada para armazenar pares de chave-valor, onde cada chave é única e mapeada para um valor correspondente. Existem várias implementações da interface `Map`, como `HashMap`, `LinkedHashMap` e `TreeMap`. Vamos explorar a implementação `HashMap` e como ela pode ser usada.

## HashMap

O `HashMap` é uma implementação da interface `Map` que armazena pares de chave-valor em uma estrutura de dados de tabela de hash. Ele oferece alta eficiência para operações de busca, inserção e remoção, com complexidade média O(1) para essas operações.

### Inicialização de um HashMap

Você pode inicializar um `HashMap` de várias maneiras:

1. **Inicialização vazia:**
```java
Map<TipoDaChave, TipoDosValores> map = new HashMap<>();
```

2. **Inicialização com elementos pré-definidos:**
```java
Map<TipoDaChave, TipoDosValores> map = Map.of(chaveElemento, valorElemento, chaveElemento, valorElemento, ...);
```

3. **Inicialização com elementos e inicialização inline:**
```java
Map<TipoDaChave, TipoDosValores> map = new HashMap<>() {{
    put(chave, valor);
    put(chave, valor);
    // ...
}};
```

### Exemplo

```java
import java.util.HashMap;
import java.util.Map;

public class ExemploHashMap {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        // Adicionar pares chave-valor ao mapa
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // Acessar o valor associado a uma chave
        int valor = map.get("B"); // valor = 2

        // Verificar se o mapa contém uma chave específica
        boolean contemChave = map.containsKey("C"); // contemChave = true

        // Remover um par chave-valor pelo valor da chave
        map.remove("A");

        // Obter o tamanho do mapa
        int tamanho = map.size(); // tamanho = 2

        // Iterar sobre as chaves do mapa
        for (String chave : map.keySet()) {
            System.out.println("Chave: " + chave + ", Valor: " + map.get(chave));
        }
    }
}
```

O `HashMap` é uma excelente escolha quando você precisa armazenar dados com base em chaves únicas e quer alta eficiência para operações de inserção, busca e remoção. Lembre-se de que a ordem dos elementos em um `HashMap` não é garantida. Se você precisa de uma ordem específica ou ordem de inserção, considere usar o `LinkedHashMap`.


## LinkedHashMap

A interface `Map` no Java é usada para armazenar pares chave-valor, onde cada chave é única e mapeada para um valor correspondente. Vamos explorar a implementação `LinkedHashMap`, que é uma extensão do `HashMap` e preserva a ordem de inserção dos elementos.

### LinkedHashMap: Inicialização

Você pode inicializar um `LinkedHashMap` de várias maneiras:

1. **Inicialização vazia:**
```java
Map<TipoDaChave, TipoDosValores> linkedMap = new LinkedHashMap<>();
```

2. **Inicialização com elementos pré-definidos:**
```java
Map<TipoDaChave, TipoDosValores> linkedMap = Map.of(chaveElemento, valorElemento, chaveElemento, valorElemento, ...);
```

3. **Inicialização com elementos e inicialização inline:**
```java
Map<TipoDaChave, TipoDosValores> linkedMap = new LinkedHashMap<>() {{
    put(chave, valor);
    put(chave, valor);
    // ...
}};
```

### LinkedHashMap: Exemplo

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class ExemploLinkedHashMap {

    public static void main(String[] args) {
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        
        // Adicionar pares chave-valor ao mapa
        linkedMap.put("A", 1);
        linkedMap.put("B", 2);
        linkedMap.put("C", 3);

        // Acessar o valor associado a uma chave
        int valor = linkedMap.get("B"); // valor = 2

        // Verificar se o mapa contém uma chave específica
        boolean contemChave = linkedMap.containsKey("C"); // contemChave = true

        // Remover um par chave-valor pelo valor da chave
        linkedMap.remove("A");

        // Obter o tamanho do mapa
        int tamanho = linkedMap.size(); // tamanho = 2

        // Iterar sobre as chaves do mapa (ordem de inserção preservada)
        for (String chave : linkedMap.keySet()) {
            System.out.println("Chave: " + chave + ", Valor: " + linkedMap.get(chave));
        }
    }
}
```

O `LinkedHashMap` é uma ótima opção quando você precisa manter a ordem de inserção dos elementos. A implementação preserva a ordem em que os elementos foram adicionados ao mapa, tornando-o útil quando você precisa acessar os elementos na mesma ordem em que foram inseridos.

## TreeMap

A interface `Map` no Java é usada para armazenar pares chave-valor, onde cada chave é única e mapeada para um valor correspondente. Agora, vamos explorar a implementação `TreeMap`, que mantém os elementos em ordem natural ou personalizada das chaves.

### TreeMap: Inicialização

Você pode inicializar um `TreeMap` de várias maneiras:

1. **Inicialização vazia:**
```java
Map<TipoDaChave, TipoDosValores> treeMap = new TreeMap<>();
```

2. **Inicialização com elementos pré-definidos:**
```java
Map<TipoDaChave, TipoDosValores> treeMap = Map.of(chaveElemento, valorElemento, chaveElemento, valorElemento, ...);
```

3. **Inicialização com Comparator e elementos inline:**
```java
Map<TipoDaChave, TipoDosValores> treeMap = new TreeMap<>(comparator) {{
    put(chave, valor);
    put(chave, valor);
    // ...
}};
```

### TreeMap: Exemplo

```java
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ExemploTreeMap {

    public static void main(String[] args) {
        // Inicialização com ordem natural das chaves
        Map<String, Integer> treeMap = new TreeMap<>();

        // Adicionar pares chave-valor ao mapa
        treeMap.put("B", 2);
        treeMap.put("A", 1);
        treeMap.put("C", 3);

        // Acessar o valor associado a uma chave
        int valor = treeMap.get("B"); // valor = 2

        // Verificar se o mapa contém uma chave específica
        boolean contemChave = treeMap.containsKey("C"); // contemChave = true

        // Remover um par chave-valor pelo valor da chave
        treeMap.remove("A");

        // Obter o tamanho do mapa
        int tamanho = treeMap.size(); // tamanho = 2

        // Iterar sobre as chaves do mapa (ordem natural das chaves)
        for (String chave : treeMap.keySet()) {
            System.out.println("Chave: " + chave + ", Valor: " + treeMap.get(chave));
        }
    }
}
```

O `TreeMap` é uma ótima opção quando você precisa manter os elementos ordenados. Ele pode ser inicializado com um `Comparator` personalizado para controlar a ordem dos elementos, ou ele usa a ordem natural das chaves por padrão.