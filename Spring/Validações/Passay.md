# **Validação de Senhas com Passay**

## Introdução

A validação de senhas é uma prática essencial para garantir a segurança em sistemas. O Passay é uma biblioteca Java que oferece recursos poderosos para validar e fortalecer políticas de senhas.

## Configuração Básica do Validador

```java
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
           new LengthRule(8, 30), 
           new CharacterRule(EnglishCharacterData.UpperCase, 1), 
           new CharacterRule(EnglishCharacterData.LowerCase, 1), 
           new CharacterRule(EnglishCharacterData.Digit, 1),
           new CharacterRule(EnglishCharacterData.Special, 1),
           new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),
           new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),
           new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()       
                .disableDefaultConstraintViolation();
        return false;
    }
}
```

## Anotação para Validar Senhas

```java
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Senha Inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
```

## Utilizando a Anotação em uma Entidade

```java
public class User {

    @ValidPassword
    private String password;

    // Outros atributos e métodos da entidade

}
```

## Mensagens de Erro Personalizadas

Personalize as mensagens de erro na anotação para fornecer feedback significativo aos usuários.

```java
@ValidPassword(message = "A senha deve ter pelo menos 8 caracteres, incluindo maiúsculas, minúsculas, números e caracteres especiais.")
private String password;
```

## Utilizando em Métodos de Serviço

Além de ser usado em entidades, você pode utilizar o validador em métodos de serviço.

```java
public class UserService {

    public boolean isValidPassword(String password) {
        return new PasswordConstraintValidator().isValid(password, null);
    }

    // Outros métodos de serviço

}
```

## Integrando com Frameworks de Validação

Integre o validador de senha em frameworks de validação, como o Hibernate Validator.

```java
@ValidPassword
@Column
private String password;
```

## Testando a Validação

Teste a validação de senha em unidades de teste usando bibliotecas como JUnit.

```java
@Test
void testValidPassword() {
    PasswordConstraintValidator validator = new PasswordConstraintValidator();
    assertTrue(validator.isValid("SecureP@ss123", null));
}

@Test
void testInvalidPassword() {
    PasswordConstraintValidator validator = new PasswordConstraintValidator();
    assertFalse(validator.isValid("weak", null));
}
```
