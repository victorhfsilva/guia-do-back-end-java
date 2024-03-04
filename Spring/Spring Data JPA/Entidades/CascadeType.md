# CascadeType

A anotação `CascadeType` é usada em mapeamento de entidades JPA (Java Persistence API) para definir como as operações de persistência devem ser propagadas de uma entidade pai para suas entidades filhas associadas. Ela permite especificar se as operações de persistência, como inserção, atualização e exclusão, devem ser refletidas nas entidades associadas. Aqui está um cheatsheet para entender o uso da anotação `CascadeType`:

### CascadeType na anotação @OneToOne

- `CascadeType.ALL`: Todas as operações de persistência (inserção, atualização, exclusão) na entidade pai são propagadas para a entidade filha.
- `CascadeType.PERSIST`: Apenas a operação de persistência (inserção) na entidade pai é propagada para a entidade filha.
- `CascadeType.MERGE`: Apenas a operação de mesclagem (atualização) na entidade pai é propagada para a entidade filha.
- `CascadeType.REMOVE`: Apenas a operação de remoção (exclusão) na entidade pai é propagada para a entidade filha.

Exemplo:

```java
@OneToOne(cascade = CascadeType.ALL)
private Address address;
```

```java
@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
private Address address;
```

### CascadeType na anotação @OneToMany e @ManyToMany

- `CascadeType.ALL`: Todas as operações de persistência (inserção, atualização, exclusão) na entidade pai são propagadas para as entidades filhas associadas.
- `CascadeType.PERSIST`: Apenas a operação de persistência (inserção) na entidade pai é propagada para as entidades filhas associadas.
- `CascadeType.MERGE`: Apenas a operação de mesclagem (atualização) na entidade pai é propagada para as entidades filhas associadas.
- `CascadeType.REMOVE`: Apenas a operação de remoção (exclusão) na entidade pai é propagada para as entidades filhas associadas.
- `CascadeType.DETACH`: A entidade filha será desanexada (removida do contexto de persistência) quando a entidade pai for desanexada.
- `CascadeType.REFRESH`: A entidade filha será recarregada (atualizada) quando a entidade pai for recarregada.

Exemplo:

```java
@OneToMany(cascade = CascadeType.ALL)
private List<OrderDetail> orderDetails;
```

### CascadeType na anotação @ManyToOne e @OneToOne (relações inversas)

- `CascadeType.ALL`: Todas as operações de persistência (inserção, atualização, exclusão) na entidade filha são propagadas para a entidade pai (relação inversa).
- `CascadeType.PERSIST`: Apenas a operação de persistência (inserção) na entidade filha é propagada para a entidade pai.
- `CascadeType.MERGE`: Apenas a operação de mesclagem (atualização) na entidade filha é propagada para a entidade pai.
- `CascadeType.REMOVE`: Apenas a operação de remoção (exclusão) na entidade filha é propagada para a entidade pai.

Exemplo:

```java
@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
private Address address;
```

Lembre-se de escolher os tipos de cascata apropriados com base nos requisitos de seu aplicativo. O uso incorreto de cascata pode levar a problemas de integridade de dados, como a exclusão acidental de entidades associadas. Portanto, é essencial entender as implicações de cada tipo de cascata ao usá-lo em suas entidades JPA.