## Consultas Personalizadas no Spring Data MongoDB

### Introdução

O Spring Data MongoDB permite a criação de consultas personalizadas através de diversas anotações e utilizando a classe `MongoTemplate` e `Criteria`. Isso fornece flexibilidade adicional ao lidar com consultas complexas que não podem ser facilmente definidas usando métodos de consulta derivada.

### Anotação @Query

A anotação `@Query` permite definir consultas personalizadas usando a linguagem de consulta do MongoDB diretamente na interface do repositório.

#### Sintaxe Básica

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    @Query("{ 'nome' : ?0 }")
    List<Usuario> buscarPorNome(String nome);
    
    @Query("{ 'idade' : { $gt: ?0 } }")
    List<Usuario> buscarPorIdadeMaiorQue(int idade);
}
```

### Anotação @Aggregation

A anotação `@Aggregation` é usada para definir pipelines de agregação personalizados. Agregações são úteis para cálculos, transformações e outras operações complexas.

#### Sintaxe Básica

```java
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Aggregation("{ $group: { _id: '$idade', total: { $sum: 1 } } }")
    List<IdadeAggregationResult> contarUsuariosPorIdade();
}

class IdadeAggregationResult {
    private int _id;
    private int total;

    // Getters e Setters
}
```

### Utilização de MongoTemplate e Criteria

O `MongoTemplate` fornece uma API de nível inferior para realizar operações de banco de dados, permitindo maior flexibilidade e controle sobre as consultas. `Criteria` é usada em conjunto com `MongoTemplate` para definir consultas complexas de forma programática.

#### Exemplo de MongoTemplate e Criteria

```java
@Service
public class UsuarioService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Usuario> buscarPorNomeComTemplate(String nome) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(nome));
        return mongoTemplate.find(query, Usuario.class);
    }

    public List<Usuario> buscarPorIdadeMaiorQueComTemplate(int idade) {
        Query query = new Query();
        query.addCriteria(Criteria.where("idade").gt(idade));
        return mongoTemplate.find(query, Usuario.class);
    }
}
```

### Utilização de MongoTemplate com Aggregation

O MongoTemplate também suporta a criação de pipelines de agregação complexos, que são úteis para operações como agrupamento, transformação de dados e cálculos agregados.

#### Exemplo de Agregação com MongoTemplate

```java
@Service
public class UsuarioService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Usuario> buscarPorNomeComTemplate(String nome) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(nome));
        return mongoTemplate.find(query, Usuario.class);
    }

    public List<Usuario> buscarPorIdadeMaiorQueComTemplate(int idade) {
        Query query = new Query();
        query.addCriteria(Criteria.where("idade").gt(idade));
        return mongoTemplate.find(query, Usuario.class);
    }

    public List<IdadeAggregationResult> contarUsuariosPorIdade() {
        Aggregation agg = Aggregation.newAggregation(
            Aggregation.group("idade").count().as("total")
        );
        
        AggregationResults<IdadeAggregationResult> results = 
            mongoTemplate.aggregate(agg, "usuarios", IdadeAggregationResult.class);
        
        return results.getMappedResults();
    }
}

class IdadeAggregationResult {
    private int _id;
    private int total;

    // Getters e Setters
}
```