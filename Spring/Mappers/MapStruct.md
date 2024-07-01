# MapStruct

## Introdução
MapStruct é uma biblioteca Java que automatiza a geração de mapeamentos de objetos. Ele converte automaticamente entre tipos de objetos de maneira eficiente, gerando código de mapeamento em tempo de compilação. Isso ajuda a reduzir o código boilerplate e erros durante o mapeamento de objetos.

## Configuração do Projeto

### Adicionando Dependências
Adicione as dependências do MapStruct e do processador de anotações no seu arquivo `pom.xml` (para projetos Maven) ou `build.gradle` (para projetos Gradle).

**Gradle:**

```groovy
plugins {
    id 'java'
}

dependencies {
	implementation group: 'org.mapstruct', name: 'mapstruct', version: '1.5.5.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.4.2.Final'
}
```

## Criando Mapeamentos com MapStruct

### Definindo os Objetos
Primeiro, defina as classes que você deseja mapear. Vamos usar um exemplo simples com uma entidade `User` e um DTO `UserDTO`.

```java
public class User {
    private Long id;
    private String name;
    private String email;

    // Getters e Setters
}

public class UserDTO {
    private Long id;
    private String name;

    // Getters e Setters
}
```

### Criando o Mapper
Crie uma interface para definir o mapeamento. Use a anotação `@Mapper` do MapStruct para indicar que esta interface é um mapper.

```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
```

### Usando o Mapper
Você pode agora usar o mapper para converter entre `User` e `UserDTO`.

```java
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserDTO save(UserDTO userDTO){
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        
        return userMapper.userToUserDTO(savedUser);
    }
}
```

## Mapeamentos Personalizados

### Mapeamentos de Campos Diferentes

Se os nomes dos campos forem diferentes entre as classes, use a anotação `@Mapping`.

```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "email", target = "emailAddress")
    UserDTO userToUserDTO(User user);
}
```

### Ignorando atributos durante o mapeamento

Se você desejar ignorar qualquer atributo durante a conversão então será necessário referenciar qual o atributo do destino que será desconsiderado no código gerado.

```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    @Mapping(target = "zipCode", ignore = true)
    ClienteDestino toClienteDestino (ClienteOrigem clienteOrigem);
}
```


### Mapeamentos com Lógica Personalizada
Você pode adicionar métodos personalizados para lógica de mapeamento mais complexa.

```java
public class UserBodyImperialValuesDTO {
    private int inch;
    private int pound;
    // constructor, getters, and setters
}
```

```java
public class UserBodyValues {
    private double kilogram;
    private double centimeter;
    // constructor, getters, and setters
}
```

```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserBodyValuesMapper {  
    @Mapping(source = "inch", target = "centimeter", qualifiedByName = "inchToCentimeter")
    public UserBodyValues userBodyValuesMapper(UserBodyImperialValuesDTO dto);
    
    @Named("inchToCentimeter") 
    public static double inchToCentimeter(int inch) { 
        return inch * 2.54; 
    }
}
```

### Trabalhando com Coleções
MapStruct também pode mapear coleções de objetos.

```java
import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> usersToUserDTOs(List<User> users);
}
```
