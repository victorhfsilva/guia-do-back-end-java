# **Hibernate Validator**

## Introdução

O Hibernate Validator é a implementação de referência da API de Validação de Beans. Ele fornece um framework robusto e extensível para validação de dados declarativa e programática. Este guia rápido destaca os conceitos-chave e práticas para usar o Hibernate Validator de forma eficaz.

## Uso Básico

### Anotando o Modelo

Para começar a usar o Hibernate Validator, você pode simplesmente anotar os campos do seu modelo com as restrições desejadas. Por exemplo:

```java
public class Usuario {

    @NotNull
    @Size(min = 3, max = 20)
    private String nomeDeUsuario;

    @Email
    private String email;

    // Outros campos e métodos
}
```

## Restrições Personalizadas

### Criando uma Restrição

Você também pode criar suas próprias restrições personalizadas. Primeiro, você define a anotação da restrição:

```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorUsuarioUnico.class)
public @interface UsuarioUnico {

    String message() default "Nome de usuário deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

### Implementando o Validador

Em seguida, você implementa o validador para essa restrição:

```java
public class ValidadorUsuarioUnico implements ConstraintValidator<UsuarioUnico, String> {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public boolean isValid(String nomeDeUsuario, ConstraintValidatorContext context) {
        return !repositorioUsuario.existsByNomeDeUsuario(nomeDeUsuario);
    }
}
```

### Usando a Restrição Personalizada

Depois, você pode usar essa restrição personalizada em seus modelos:

```java
public class Usuario {

    @UsuarioUnico
    private String nomeDeUsuario;

    // Outros campos e métodos
}
```

## Validação Cascateada

Você pode validar objetos aninhados em uma hierarquia de objetos usando a validação cascata:

```java
public class Endereco {

    @NotNull
    private String cidade;

    @NotNull
    private String cep;

    // Outros campos e métodos
}

public class Usuario {

    @Valid
    private Endereco endereco;

    // Outros campos e métodos
}
```

## Personalização de Mensagens de Erro

Você pode personalizar as mensagens de erro das restrições diretamente nas anotações:

```java
public class Usuario {

    @Size(min = 5, max = 20, message = "O comprimento do nome de usuário deve estar entre {min} e {max}")
    private String nomeDeUsuario;

    // Outros campos e métodos
}
```

## Tratamento de Exceções

Para lidar com exceções de validação globalmente, você pode criar um manipulador de exceções:

```java
@ControllerAdvice
public class ManipuladorExcecaoGlobal {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> manipularExcecoesDeValidacao(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body("A validação falhou: " + ex.getMessage());
    }
}
```

## Testes

Por fim, você pode escrever casos de teste para garantir que suas validações funcionem conforme o esperado:

```java
class TesteValidadorUsuario {

    private Validator validador = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void validarUsuarioValido() {
        Usuario usuario = new Usuario();
        usuario.setNomeDeUsuario("joao_doe");

        Set<ConstraintViolation<Usuario>> violacoes = validador.validate(usuario);

        assertTrue(violacoes.isEmpty());
    }

    // Adicione mais casos de teste para cenários inválidos
}
```
