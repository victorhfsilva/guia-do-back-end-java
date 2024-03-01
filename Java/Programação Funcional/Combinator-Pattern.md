# Combinator Pattern em Java

## Introdução

O padrão de design **Combinator** é uma técnica usada para criar validações complexas e encadeadas em um estilo funcional, mantendo o código limpo e legível. Ele envolve a combinação de várias funções pequenas para formar uma validação completa.

## Benefícios

- Separação de preocupações: Cada função lida com uma validação específica.
- Composição: Funções podem ser combinadas em uma sequência para criar validações complexas.
- Reutilização: As funções individuais podem ser reutilizadas em diferentes contextos.

## Exemplo

```java
import java.time.LocalDate;
import java.util.function.Function;

// Funções para validações individuais
Function<String, Boolean> isNotNullOrEmpty = s -> s != null && !s.trim().isEmpty();
Function<String, Boolean> isEmailValid = email -> email.contains("@");
Function<LocalDate, Boolean> isAdult = dob -> LocalDate.now().minusYears(18).isAfter(dob);

// Funções de combinação
Function<String, Boolean> isEmailValidAndNotNull = isNotNullOrEmpty.and(isEmailValid);
Function<String, Boolean> isAdultEmailValid = isEmailValidAndNotNull.and(isAdultEmailValid);

// Uso das funções de combinação
String email = "example@email.com";
LocalDate birthDate = LocalDate.of(1990, 5, 10);

boolean isValid = isAdultEmailValid.apply(email) && isAdult.apply(birthDate);
```

## Considerações Finais

O padrão Combinator permite criar validações complexas ao dividir o processo em funções menores e depois combiná-las para formar uma validação completa. Isso resulta em um código mais limpo, modular e reutilizável. O padrão pode ser especialmente útil em cenários onde a validação envolve múltiplas etapas e regras complexas.