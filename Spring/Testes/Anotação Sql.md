# **Anotação `@Sql`**

A anotação `@Sql` é usada para executar scripts SQL antes ou depois da execução de um método de teste.

- `@Sql`: Esta anotação pode ser usada em métodos de teste para definir scripts SQL a serem executados antes ou depois do método de teste. Por exemplo:

    ```java
    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void meuTeste() {
        // Lógica do teste
    }
    ```

    Neste exemplo, os scripts `schema.sql` e `data.sql` serão executados antes da execução do método de teste.

### **Anotação `@SqlGroup`**

A anotação `@SqlGroup` é utilizada para agrupar várias anotações `@Sql` em uma única anotação, permitindo uma organização mais clara dos scripts SQL a serem executados.

- `@SqlGroup`: Esta anotação pode ser usada em métodos de teste para agrupar várias anotações `@Sql`. Por exemplo:

    ```java
    @Test
    @SqlGroup({
        @Sql("/cleanup.sql"),
        @Sql("/schema.sql"),
        @Sql("/data.sql")
    })
    public void meuTeste() {
        // Lógica do teste
    }
    ```

    Neste exemplo, os scripts serão executados na ordem em que são definidos dentro da anotação `@SqlGroup`. Primeiro, o script `cleanup.sql` será executado para limpar o banco de dados. Em seguida, o script `schema.sql` será executado para criar a estrutura do banco de dados, seguido pelo script `data.sql` para inserir dados de teste.

### **Utilizando Placeholders**

Você também pode usar placeholders nos scripts SQL para parametrizar os scripts e torná-los mais flexíveis. Por exemplo:

```java
@Test
@SqlGroup({
    @Sql("/cleanup.sql"),
    @Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public void meuTeste() {
    // Lógica do teste
}
```

Neste exemplo, `BEFORE_TEST_METHOD` indica que o script será executado antes do método de teste, enquanto `AFTER_TEST_METHOD` indica que será executado após o método de teste.
