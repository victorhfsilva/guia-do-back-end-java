# Padrão de Projeto Iterator

O padrão Iterator é um padrão de design comportamental que fornece uma maneira de acessar os elementos de um objeto agregado sequencialmente sem expor sua representação subjacente. Ele permite percorrer os elementos de uma coleção sem expor sua estrutura interna, facilitando a iteração sobre diferentes tipos de coleções de maneira uniforme.

### Vantagens

- Encapsulamento: o padrão Iterator encapsula a iteração sobre uma coleção, ocultando a estrutura interna da coleção e fornecendo uma interface consistente para iteração.
- Flexibilidade: permite iteração sobre diferentes tipos de coleções usando uma interface comum, tornando o código mais flexível e reutilizável.
- Separação de preocupações: separa a lógica de iteração da lógica de negócios, facilitando a manutenção e o teste do código.

### Quando usar o padrão Iterator?

- Quando você precisa percorrer os elementos de uma coleção sem expor sua estrutura interna.
- Quando você quer fornecer uma interface consistente para iteração sobre diferentes tipos de coleções.
- Quando você deseja separar a lógica de iteração da lógica de negócios.


![Iterator](./images/iterator.png)

### Iterator

- Define uma interface para acessar e percorrer os elementos de uma coleção.
- Geralmente inclui métodos para verificar se há mais elementos, obter o próximo elemento e reiniciar a iteração.

**Exemplo**: Interface para iteradores que percorrem uma coleção.

```java
// Iterator
interface Iterator<T> {
    boolean hasNext();
    T next();
}
```

### Iterador Concreto

- Implementa a interface Iterator para percorrer os elementos de uma coleção específica.
- Mantém o estado da iteração e implementa métodos para acessar os elementos da coleção.

**Exemplo**: Implementação de um iterador que percorre os elementos de uma lista.

```java
// Iterador Concreto
class ListIterator<T> implements Iterator<T> {
    private List<T> list;
    private int position = 0;

    public ListIterator(List<T> list) {
        this.list = list;
    }

    public boolean hasNext() {
        return position < list.size();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(position++);
    }
}
```

### Agregado

- Define uma interface para criar um objeto iterador que percorre seus elementos.
- Pode ser uma interface ou uma classe abstrata que define métodos para criar um iterador.

**Exemplo**: Interface para agregados que podem ser percorridos por um iterador.

```java
// Agregado
interface Aggregator<T> {
    Iterator<T> createIterator();
}
```

### Coleção Concreta

- Implementa a interface Aggregator para criar um iterador que percorre seus elementos.
- Fornece métodos para adicionar e remover elementos da coleção.

**Exemplo**: Implementação de uma lista que pode ser percorrida por um iterador.

```java
// Coleção Concreta
class ListCollection<T> implements Aggregator<T> {
    private List<T> list = new ArrayList<>();

    public void add(T item) {
        list.add(item);
    }

    public Iterator<T> createIterator() {
        return new ListIterator<>(list);
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria uma lista de números.
        ListCollection<Integer> numbers = new ListCollection<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        // Cria um iterador para percorrer a lista de números.
        Iterator<Integer> iterator = numbers.createIterator();

        // Percorre e imprime os elementos da lista.
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

### Saída Esperada

```
1
2
3
```

Neste exemplo, o padrão Iterator é utilizado para percorrer os elementos de uma lista de números sem expor sua estrutura interna. A classe `ListCollection` implementa a interface `Aggregator`, que permite criar um iterador para percorrer os elementos da lista. O iterador `ListIterator` implementa a interface `Iterator`, fornecendo métodos para acessar e percorrer os elementos da lista de forma sequencial. Isso permite iterar sobre diferentes tipos de coleções usando uma interface comum, tornando o código mais flexível e reutilizável.