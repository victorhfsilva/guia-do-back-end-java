# Injeção de Dependências

## Referenciando Beans

### **1. Injeção por Nome:**
   - Use a anotação `@Resource` para injetar um **bean** por seu nome.

```java
@Repository("myRepository") // Definindo o nome do bean como "myRepository"
public class MyRepositoryImpl implements MyRepository {

    @Override
    public void save(String data) {
        System.out.println("Saving data: " + data);
    }

    @Override
    public String find() {
        return "Retrieving data";
    }
}
```

```java
@Service
public class MyService {
    @Resource(name = "myRepository")
    private MyRepository repository;
}
```

### **2. Usando `@Autowired`:**
   - Você pode usar a anotação `@Autowired` para injetar **beans** por tipo.

   ```java
   @Controller
   public class MyController {
       @Autowired
       private MyService service;
   }
   ```
## Tipos de Injeção

### **1. Injeção de Construtor:**
   - Use a anotação `@Autowired` no construtor para realizar a injeção de dependências.
   - Utilize a injeção de construtor quando quiser garantir que todas as dependências obrigatórias sejam fornecidas durante a criação do objeto. Essa abordagem promove a imutabilidade dos objetos e torna as dependências explícitas no momento da construção.

   ```java
   @Service
   public class MyService {
       private final MyRepository repository;

       @Autowired
       public MyService(MyRepository repository) {
           this.repository = repository;
       }
   }
   ```

### **2. Injeção por Método:**
   - Use a anotação `@Autowired` em métodos para realizar a injeção de dependências.
    - Utilize a injeção por método quando houver várias dependências opcionais ou quando precisar injetar dependências após a criação do objeto. Essa abordagem oferece mais flexibilidade do que a injeção de construtor, pois permite que você defina métodos específicos para configurar as dependências conforme necessário.

   ```java
   @Controller
   public class MyController {
       private MyService service;

       @Autowired
       public void setService(MyService service) {
           this.service = service;
       }
   }
   ```

### **3. Injeção por Campo:**

- Use a anotação `@Autowired` diretamente em campos.
- Utilize a injeção por campo quando desejar simplicidade e concisão, especialmente em classes que possuem apenas uma ou duas dependências. No entanto, essa abordagem pode tornar as dependências menos explícitas e dificultar a escrita de testes unitários.

   ```java
   @Repository
   public class MyRepository {
       @Autowired
       private JdbcTemplate jdbcTemplate;
   }
   ```

## **Qualificadores:**

Quando temos várias implementações de um mesmo tipo de bean no Spring, precisamos usar a anotação `@Qualifier` para especificar qual implementação queremos injetar em nossos componentes.

#### **Exemplo:**

Suponha que temos duas implementações da interface `MyRepository`: `PrimaryRepository` e `SecondaryRepository`. Para injetá-las em um serviço chamado `MyService`, devemos usar `@Qualifier` para indicar qual versão queremos utilizar.

```java
public interface MyRepository {
    void save(String data);
    String find();
}
```

```java
@Repository
@Qualifier("primaryRepository") // Qualificador para diferenciar essa implementação
public class PrimaryRepository implements MyRepository {

    @Override
    public void save(String data) {
        System.out.println("Saving data to primary database: " + data);
    }

    @Override
    public String find() {
        return "Data retrieved from primary database";
    }
}
```

```java
@Repository
@Qualifier("secondaryRepository") // Qualificador para diferenciar essa implementação
public class SecondaryRepository implements MyRepository {

    @Override
    public void save(String data) {
        System.out.println("Saving data to secondary database: " + data);
    }

    @Override
    public String find() {
        return "Data retrieved from secondary database";
    }
}

```

```java
@Service
public class MyService {
    private final MyRepository primaryRepository;
    private final MyRepository secondaryRepository;

    @Autowired
    public MyService(@Qualifier("primaryRepository") MyRepository primaryRepository, 
                     @Qualifier("secondaryRepository") MyRepository secondaryRepository) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;
    }
}
```

Neste exemplo, usamos `@Qualifier("primaryRepository")` e `@Qualifier("secondaryRepository")` para diferenciar entre as implementações PrimaryRepository e SecondaryRepository, respectivamente. Isso garante que o Spring injete a implementação correta em cada campo do MyService.

## Primary

A anotação `@Primary` é usada para indicar que um bean é o candidato preferido quando existem várias opções para injeção.

```java
@Service
@Primary
public class MyPrimaryService implements MyService {
    // ...
}
```
