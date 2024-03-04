# Criando Repositórios Personalizados com o EntityManager

Criar um repositório usando o `EntityManager` no Java Persistence API (JPA) é útil quando você precisa executar consultas personalizadas ou operações de banco de dados que não são facilmente suportadas pelos métodos padrão dos repositórios do Spring Data JPA. 

### Crie a Interface do Repositório Personalizado

Comece criando uma interface personalizada que estenda a interface `JpaRepository` ou `CrudRepository` do Spring Data JPA.
```java
public interface CustomRepository<T, ID> extends JpaRepository<T, ID> {
    
    // Adicione métodos personalizados aqui
    
}
```

### Implemente a Interface do Repositório Personalizado

Crie uma classe que implemente a interface do repositório personalizado e injete o `EntityManager` para usá-lo na execução de consultas personalizadas. Você também pode usar a anotação `@Repository` para indicar que a classe é um componente gerenciado pelo Spring.

```java
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomRepositoryImpl<T, ID> implements CustomRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Implemente métodos personalizados aqui, usando o EntityManager
}
```

### Crie Métodos Personalizados com o `EntityManager`

Dentro da classe do repositório personalizado, você pode criar métodos para executar consultas personalizadas usando o `EntityManager`. Use a linguagem de consulta JPQL (Java Persistence Query Language) ou critérios do `CriteriaBuilder` para definir suas consultas.


```java
public class CustomRepositoryImpl<T, ID> implements CustomRepository<T, ID> {

    // ...

    @Override
    public List<T> customQuery() {
        String jpql = "SELECT t FROM " + getDomainClass().getName() + " t WHERE ...";
        Query query = entityManager.createQuery(jpql);
        // Configure parâmetros da consulta, se necessário
        return query.getResultList();
    }
    
    // Implemente outros métodos personalizados aqui
}
```

### Use o Repositório Personalizado

Agora você pode injetar o repositório personalizado em seus serviços e controladores e usar os métodos personalizados para executar consultas personalizadas.

```java
@Service
public class MyService {

    private final CustomRepository<MyEntity, Long> customRepository;

    @Autowired
    public MyService(CustomRepository<MyEntity, Long> customRepository) {
        this.customRepository = customRepository;
    }

    public List<MyEntity> customQuery() {
        return customRepository.customQuery();
    }
}
```

### Operações Básicas do `EntityManager`:

1. **Injeção do `EntityManager`**:

   Para usar o `EntityManager`, você deve injetá-lo em seus serviços ou classes de repositório. Você pode fazer isso usando a anotação `@PersistenceContext`.

   ```java
   @PersistenceContext
   private EntityManager entityManager;
   ```

   Exemplo: 

   ```java
    @Repository
    @Transactional
    public class CustomRepositoryImpl<T, ID> implements CustomRepository<T, ID> {

        @PersistenceContext
        private final EntityManager entityManager;

        public CustomRepositoryImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public MyEntity save(MyEntity entity) {
            entityManager.persist(entity);
            return entity;
        }
    }
    ```

2. **Persistência de uma Entidade**:

   Use o método `persist` para salvar uma nova entidade no banco de dados. A entidade deve estar no estado "transitório" (ainda não gerenciada).

   ```java
   MyEntity entity = new MyEntity();
   entityManager.persist(entity);
   ```

3. **Atualização de uma Entidade**:

   Use o método `merge` para atualizar uma entidade já gerenciada pelo `EntityManager`. Isso é necessário quando você deseja atualizar uma entidade que não está no contexto de persistência.

   ```java
   MyEntity entity = entityManager.find(MyEntity.class, id);
   entity.setNome("Novo Nome");
   entityManager.merge(entity);
   ```

4. **Remoção de uma Entidade**:

   Use o método `remove` para excluir uma entidade do banco de dados.

   ```java
   MyEntity entity = entityManager.find(MyEntity.class, id);
   entityManager.remove(entity);
   ```

5. **Consultar uma Entidade por Identificador (Chave Primária)**:

   Use o método `find` para recuperar uma entidade com base em sua chave primária.

   ```java
   MyEntity entity = entityManager.find(MyEntity.class, id);
   ```

   `entity` será `null` se a entidade com a chave primária especificada não for encontrada.

6. **Consultar com JPQL (Java Persistence Query Language)**:

   Use consultas JPQL para recuperar entidades com base em critérios específicos. As consultas JPQL são escritas em uma sintaxe semelhante ao SQL e permitem flexibilidade nas consultas.

   ```java
   TypedQuery<MyEntity> query = entityManager.createQuery(
       "SELECT e FROM MyEntity e WHERE e.nome = :nome", MyEntity.class);
   query.setParameter("nome", "Nome Específico");
   List<MyEntity> resultList = query.getResultList();
   ```


