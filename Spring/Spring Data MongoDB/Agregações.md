## Aggregation com MongoTemplate no Spring Data MongoDB

### Introdução

O Spring Data MongoDB fornece uma poderosa funcionalidade de agregação através da classe `MongoTemplate`. A agregação permite realizar operações complexas como agrupamentos, filtragens, transformações e cálculos em coleções MongoDB, similar às funções de agregação em bancos de dados relacionais.

### Configuração Inicial

Antes de começarmos a usar agregações com `MongoTemplate`, certifique-se de que o `MongoTemplate` está configurado no seu projeto.

```java
@Service
public class AggregationService {

    @Autowired
    private MongoTemplate mongoTemplate;
}
```

### Definindo uma Agregação

As agregações são definidas usando a classe `Aggregation` e são compostas por várias etapas (`stages`) que são executadas sequencialmente. Algumas das etapas mais comuns são:

- `$match`: Filtra documentos.
- `$group`: Agrupa documentos por um campo específico e aplica operações de agregação.
- `$project`: Transforma os documentos.
- `$sort`: Ordena os documentos.
- `$limit`: Limita o número de documentos.

#### Exemplo Básico de Agregação

Vamos criar um exemplo onde agrupamos documentos da coleção `usuarios` pela idade e contamos quantos usuários existem em cada grupo de idade.

```java
@Service
public class AggregationService {

    @Autowired
    private MongoTemplate mongoTemplate;

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

### Etapas de Agregação Comuns

#### $match

O estágio `$match` filtra os documentos com base em critérios específicos.

```java
Aggregation agg = Aggregation.newAggregation(
    Aggregation.match(Criteria.where("idade").gt(18))
);
```

#### $group

O estágio `$group` agrupa documentos por um campo específico e aplica operações de agregação, como `count`, `sum`, `avg`, etc.

```java
Aggregation agg = Aggregation.newAggregation(
    Aggregation.group("idade").count().as("total")
);
```

#### $project

O estágio `$project` permite transformar a forma dos documentos.

```java
Aggregation agg = Aggregation.newAggregation(
    Aggregation.project("nome", "idade")
);
```

#### $sort

O estágio `$sort` ordena os documentos.

```java
Aggregation agg = Aggregation.newAggregation(
    Aggregation.sort(Sort.Direction.ASC, "idade")
);
```

#### $limit

O estágio `$limit` limita o número de documentos retornados.

```java
Aggregation agg = Aggregation.newAggregation(
    Aggregation.limit(5)
);
```

### Exemplo Completo de Agregação

Vamos combinar várias etapas de agregação para realizar uma consulta mais complexa. Neste exemplo, queremos encontrar a média de idade dos usuários que têm mais de 18 anos e agrupar os resultados por cidade, ordenando-os pela média de idade.

```java
@Service
public class AggregationService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<CidadeIdadeMediaResult> calcularIdadeMediaPorCidade() {
        Aggregation agg = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("idade").gt(18)),
            Aggregation.group("cidade").avg("idade").as("mediaIdade"),
            Aggregation.sort(Sort.Direction.ASC, "mediaIdade")
        );
        AggregationResults<CidadeIdadeMediaResult> results =
            mongoTemplate.aggregate(agg, "usuarios", CidadeIdadeMediaResult.class);
        return results.getMappedResults();
    }
}

class CidadeIdadeMediaResult {
    private String cidade;
    private double mediaIdade;

    // Getters e Setters
}
```