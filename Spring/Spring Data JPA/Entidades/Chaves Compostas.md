# Chaves Compostas com o EmbeddedId

Embora o uso de uma chave primária simples seja comum em entidades JPA, às vezes você precisa representar chaves compostas para um relacionamento complexo entre tabelas. O `@EmbeddedId` é uma anotação que permite criar chaves primárias compostas em entidades no Java Persistence API (JPA).

### 1. Crie a Classe Embeddable

Primeiro, crie uma classe que representa a chave primária composta. Essa classe deve ser anotada com `@Embeddable`. Ela conterá os atributos que compõem a chave composta.

```java
@Embeddable
public class CompositeKey implements Serializable {
    private Long firstKey;
    private String secondKey;

    // Construtor, getters e setters
}
```

### 2. Crie a Classe de Entidade

Em sua classe de entidade, declare um campo do tipo da classe `CompositeKey` criada anteriormente. Anote esse campo com `@EmbeddedId`. Além disso, forneça métodos getters e setters para esse campo.

```java
@Entity
public class MyEntity {
    @EmbeddedId
    private CompositeKey compositeKey;

    // Outros atributos da entidade
    // Construtor, getters e setters
}
```

### 3. Configure a Chave Primária Composta

No `@EmbeddedId`, você pode configurar os atributos que fazem parte da chave composta usando anotações como `@AttributeOverrides` e `@AttributeOverride` se os nomes dos campos não coincidirem entre a classe de entidade e a classe incorporada.

```java
@EmbeddedId
@AttributeOverrides({
    @AttributeOverride(name = "firstKey", column = @Column(name = "first_key")),
    @AttributeOverride(name = "secondKey", column = @Column(name = "second_key"))
})
private CompositeKey compositeKey;
```

### 4. Use a Chave Primária Composta

Agora você pode usar essa chave primária composta nas operações de leitura e gravação no banco de dados. Você pode criar instâncias de `MyEntity` com uma chave composta e consultá-las.

```java
// Criar uma instância de MyEntity com uma chave composta
CompositeKey key = new CompositeKey();
key.setFirstKey(1L);
key.setSecondKey("ABC");

MyEntity entity = new MyEntity();
entity.setCompositeKey(key);

// Persistir a entidade no banco de dados
entityManager.persist(entity);

// Consultar a entidade pelo objeto da chave composta
MyEntity foundEntity = entityManager.find(MyEntity.class, key);
```

Com isso, você configurou e usou com sucesso uma chave primária composta em sua entidade JPA. Isso é útil quando você precisa representar relacionamentos complexos entre tabelas com várias colunas na chave primária. Certifique-se de que as classes estejam corretamente anotadas e configuradas de acordo com seus requisitos específicos.