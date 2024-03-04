# Anotação Column

A anotação `@Column` é usada em entidades JPA para personalizar as colunas de banco de dados associadas a atributos de uma classe. Ela fornece diversos parâmetros para configurar o mapeamento da coluna. Abaixo está um cheatsheet dos parâmetros disponíveis em `@Column`:

```java
@Column(
    name = "nome_da_coluna",           // Nome da coluna no banco de dados (padrão: nome do atributo)
    unique = true,                     // Indica se os valores na coluna devem ser exclusivos (padrão: false)
    nullable = false,                  // Indica se a coluna pode conter valores nulos (padrão: true)
    insertable = true,                 // Indica se a coluna é inserível (padrão: true)
    updatable = true,                  // Indica se a coluna é atualizável (padrão: true)
    columnDefinition = "VARCHAR(50)",  // Define uma definição específica da coluna no banco de dados
    table = "tabela_personalizada"     // Nome da tabela personalizada para esta coluna
)
private String meuAtributo;
```

- `name`: Especifica o nome da coluna no banco de dados. Por padrão, ele é igual ao nome do atributo.
- `unique`: Indica se os valores na coluna devem ser exclusivos. O valor padrão é `false`.
- `nullable`: Indica se a coluna pode conter valores nulos. O valor padrão é `true`.
- `insertable`: Se definido como true, os valores da coluna serão incluídos na instrução SQL de inserção. Se definido como false, a coluna será excluída da instrução de inserção, impedindo que qualquer valor seja inserido naquela coluna durante uma operação de inserção.
- `updatable`: Se definido como true, a coluna será incluída na instrução SQL de atualização e poderá ser modificada. Se definido como false, a coluna será excluída da instrução de atualização, impedindo que seu valor seja alterado durante uma operação de atualização.
- `columnDefinition`: Permite especificar uma definição específica da coluna no banco de dados, como tipo de dados e tamanho. Isso é útil quando você deseja definir o tipo de coluna de maneira mais específica.
- `table`: Permite definir o nome da tabela personalizada para esta coluna se você estiver mapeando a entidade para uma tabela diferente no banco de dados.

Você pode usar esses parâmetros para personalizar o mapeamento de colunas de acordo com as necessidades específicas da sua aplicação. Certifique-se de que os tipos de dados e as configurações das colunas estejam alinhados com o esquema do banco de dados que você está usando.