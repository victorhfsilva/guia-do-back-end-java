# Programação Funcional

## Reference Method

O *Reference Method* é uma característica do Java que permite referenciar um método de forma funcional. Em vez de escrever uma expressão lambda para executar um método específico, você pode simplesmente fazer referência ao método pelo seu nome.

A sintaxe para usar *Reference Method* é: `classe::método`.

Por exemplo, suponha que você tenha um array de nomes que você deseja ordenar usando um método de comparação personalizado:

```java
import java.util.Arrays;

public class ExemploReferenceMethod {
   public static void main(String[] args) {
      
      String[] nomes = {"Alice", "Bob", "Charlie", "David"};
      
      // Ordena os nomes usando um método de referência
      Arrays.sort(nomes, ExemploReferenceMethod::compararNomes);
      
      // Imprime os nomes ordenados
      for (String nome : nomes) {
         System.out.println(nome);
      }
   }
   
   // Um método estático para comparar dois nomes
   public static int compararNomes(String nome1, String nome2) {
      return nome1.compareTo(nome2);
   }
}
```

Nesse exemplo, a linha `Arrays.sort(nomes, ExemploReferenceMethod::compararNomes);` usa um *Reference Method* para fazer referência ao método `compararNomes` definido na classe `ExemploReferenceMethod`. Isso simplifica o código e torna mais claro qual método está sendo utilizado para a comparação.