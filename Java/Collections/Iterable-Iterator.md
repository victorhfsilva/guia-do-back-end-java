# Implementando a Interface `Iterable`

1. **Implementação da Interface `Iterable`**: Para tornar uma classe iterável, ela deve implementar a interface `Iterable<T>`, onde `T` é o tipo dos elementos que você deseja iterar.

   ```java
   public class MinhaClasse<T> implements Iterable<T> {
       // Implemente a interface Iterable
   }
   ```

2. **Método `iterator()`**: Você deve fornecer uma implementação do método `iterator()` que retorna uma instância de um objeto que implementa a interface `Iterator<T>`.

   ```java
   @Override
   public Iterator<T> iterator() {
       // Retorne uma instância do seu Iterator personalizado
   }
   ```

## Escrevendo um `Iterator` Personalizado

1. **Implementando a Interface `Iterator`**: Um `Iterator` personalizado deve implementar a interface `Iterator<T>`, onde `T` é o tipo dos elementos a serem iterados.

   ```java
   public class MeuIterator<T> implements Iterator<T> {
       // Implemente a interface Iterator
   }
   ```

2. **Método `hasNext()`**: Você deve fornecer uma implementação para o método `hasNext()`, que retorna `true` se houver mais elementos a serem iterados e `false` caso contrário.

   ```java
   @Override
   public boolean hasNext() {
       // Implemente a lógica para verificar se há mais elementos
   }
   ```

3. **Método `next()`**: Implemente o método `next()`, que retorna o próximo elemento na iteração e avança o cursor.

   ```java
   @Override
   public T next() {
       // Implemente a lógica para retornar o próximo elemento
   }
   ```

4. **Método `remove()` (opcional)**: O método `remove()` permite remover um elemento durante a iteração. Não é obrigatório implementá-lo, e na maioria dos casos, você pode simplesmente lançar uma exceção `UnsupportedOperationException`.

   ```java
   @Override
   public void remove() {
       throw new UnsupportedOperationException("Remoção não suportada");
   }
   ```

## Exemplo Completo

Aqui está um exemplo completo de uma classe que implementa a interface `Iterable` e um `Iterator` personalizado:

```java
import java.util.Iterator;

public class MinhaClasse<T> implements Iterable<T> {
    private T[] elementos;

    public MinhaClasse(T[] elementos) {
        this.elementos = elementos;
    }

    @Override
    public Iterator<T> iterator() {
        return new MeuIterator();
    }

    private class MeuIterator implements Iterator<T> {
        private int indiceAtual = 0;

        @Override
        public boolean hasNext() {
            return indiceAtual < elementos.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return elementos[indiceAtual++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remoção não suportada");
        }
    }
}
```

Agora você pode criar uma instância de `MinhaClasse` e iterar por ela usando um loop `for-each` ou qualquer outra construção de iteração suportada pelo Java.