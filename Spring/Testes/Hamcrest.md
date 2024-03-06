# Hamcrest

### **Introdução**

O Hamcrest é uma biblioteca de correspondência (matchers) que permite escrever afirmações mais expressivas e legíveis em testes unitários. Com o Hamcrest, é possível criar afirmações personalizadas que facilitam a escrita e a compreensão dos testes.

### **Importando o Hamcrest:**

Para usar o Hamcrest em seus testes, você precisa adicionar a dependência correta ao seu projeto. Se estiver usando Maven, adicione a seguinte dependência ao seu arquivo `pom.xml`:

```xml
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest</artifactId>
    <version>2.2</version>
    <scope>test</scope>
</dependency>
```

Certifique-se de ajustar a versão conforme necessário. Se estiver usando o Gradle, adicione esta dependência ao seu arquivo `build.gradle`:

```gradle
testImplementation 'org.hamcrest:hamcrest:2.2'
```

Após adicionar a dependência, você pode importar os matchers do Hamcrest em seus testes usando:

```java
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
```

### **Matchers Básicos do Hamcrest**

Os Matchers básicos do Hamcrest são utilizados para comparar valores simples, como strings, números e objetos.

- **equalTo:** Verifica se dois valores são iguais.
  ```java
  assertThat(valor, equalTo(valorEsperado));
  ```

- **is:** Alias para `equalTo`.
  ```java
  assertThat(valor, is(valorEsperado));
  ```

- **not:** Nega um Matcher.
  ```java
  assertThat(valor, not(equalTo(valorEsperado)));
  ```

### **Matchers de Coleção do Hamcrest**

Os Matchers de coleção do Hamcrest são utilizados para verificar elementos em coleções, como listas, conjuntos e mapas.

- **hasItem:** Verifica se a coleção contém um elemento específico.
  ```java
  assertThat(lista, hasItem(elemento));
  ```

- **hasItems:** Verifica se a coleção contém uma lista de elementos.
  ```java
  assertThat(lista, hasItems(elemento1, elemento2));
  ```

- **containsInAnyOrder:** Verifica se a coleção contém exatamente os elementos fornecidos, independentemente da ordem.
  ```java
  assertThat(lista, containsInAnyOrder(elemento1, elemento2));
  ```

### **Matchers de Texto do Hamcrest**

Os Matchers de texto do Hamcrest são utilizados para verificar strings.

- **startsWith:** Verifica se uma string começa com determinado texto.
  ```java
  assertThat(texto, startsWith("prefixo"));
  ```

- **endsWith:** Verifica se uma string termina com determinado texto.
  ```java
  assertThat(texto, endsWith("sufixo"));
  ```

- **containsString:** Verifica se uma string contém determinado texto.
  ```java
  assertThat(texto, containsString("texto"));
  ```

### **Matchers Customizados do Hamcrest**

O Hamcrest permite criar Matchers customizados para expressar condições específicas nos testes.

- **Criando um Matcher customizado:**
  ```java
  public class MeuMatcher extends TypeSafeMatcher<Tipo> {
      @Override
      protected boolean matchesSafely(Tipo item) {
          // Lógica para verificar se o item atende à condição
      }

      @Override
      public void describeTo(Description description) {
          description.appendText("descrição do Matcher");
      }

      // Método estático para usar o Matcher
      public static Matcher<Tipo> meuMatcher() {
          return new MeuMatcher();
      }
  }
  ```

- **Usando um Matcher customizado:**
 
  ```java
  assertThat(valor, meuMatcher());
  ```


### **Principais Matchers do Hamcrest**

| Matcher                            | Descrição                                                   |
|----------------------------------- |-------------------------------------------------------------|
| `equalTo(expected)`                | Verifica se o valor é igual ao valor esperado.              |
| `is(instance)`                    | Verifica se o objeto é uma instância de uma determinada classe. |
| `nullValue()`                     | Verifica se o valor é nulo.                                 |
| `notNullValue()`                  | Verifica se o valor não é nulo.                             |
| `instanceOf(type)`                | Verifica se o valor é uma instância do tipo especificado.   |
| `hasProperty(propertyName)`      | Verifica se o objeto tem uma propriedade específica.        |
| `hasSize(size)`                   | Verifica se a coleção tem um determinado tamanho.           |
| `hasItem(expected)`               | Verifica se a coleção contém um determinado item.           |
| `hasItems(expected1, expected2)` | Verifica se a coleção contém todos os itens especificados.  |
| `containsInAnyOrder(...)`         | Verifica se a coleção contém os itens em qualquer ordem.   |
| `greaterThan(value)`              | Verifica se o valor é maior que um valor especificado.      |
| `lessThan(value)`                 | Verifica se o valor é menor que um valor especificado.      |
| `allOf(matcher1, matcher2)`      | Verifica se todas as condições são verdadeiras.             |
| `anyOf(matcher1, matcher2)`      | Verifica se pelo menos uma das condições é verdadeira.      |


### Utilizando o Hamcrest com o MockMvc

Para utilizar os matchers do Hamcrest dentro do método `andExpect` do MockMvc, você pode combinar os matchers do Hamcrest com os métodos de asserção do MockMvc. Aqui está um exemplo de como você pode fazer isso:

```java
mockMvc.perform(get("/api/usuarios/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.nome", equalTo("Alice")))
        .andExpect(jsonPath("$.idade", greaterThan(18)));
```

Neste exemplo:
- O método `status().isOk()` verifica se o status da resposta é 200 (OK).
- O método `jsonPath("$.id", is(1))` verifica se o campo "id" da resposta é igual a 1.
- O método `jsonPath("$.nome", equalTo("Alice"))` verifica se o campo "nome" da resposta é igual a "Alice".
- O método `jsonPath("$.idade", greaterThan(18))` verifica se o campo "idade" da resposta é maior que 18.

Dessa forma, você pode utilizar os matchers do Hamcrest em conjunto com o `jsonPath` do MockMvc para realizar asserções mais detalhadas nos resultados das suas requisições HTTP em testes. Certifique-se de importar corretamente os matchers do Hamcrest e os métodos do MockMvc para usar essas funcionalidades.