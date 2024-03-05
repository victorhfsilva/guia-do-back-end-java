# ControllerAdvice

O `@ControllerAdvice` é uma anotação do Spring Framework que permite criar classes em Java que centralizam o tratamento de exceções em toda a aplicação. Essas classes atuam como conselheiros (advisors) para os controladores, interceptando exceções lançadas e tratando-as de acordo com as regras definidas. O uso do `@ControllerAdvice` é particularmente útil quando você deseja aplicar o mesmo tratamento de exceção a vários controladores.

## **Exemplo de Uso Básico:**

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno: " + ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Recurso não encontrado: " + ex.getMessage());
    }
}
```

## **Principais Conceitos e Anotações Relacionadas:**

- `@ControllerAdvice`: Anote uma classe com `@ControllerAdvice` para definir um controlador global de exceções.

- `@ExceptionHandler`: Use a anotação `@ExceptionHandler` em métodos de classe anotados com `@ControllerAdvice` para tratar exceções específicas. Esses métodos devem retornar uma resposta apropriada, como um `ResponseEntity`.


## **Benefícios do @ControllerAdvice:**

- Tratamento centralizado de exceções: O `@ControllerAdvice` permite centralizar o tratamento de exceções em um local específico da aplicação.

- Reutilização de tratamento: Você pode reutilizar os tratamentos de exceção definidos em várias partes da aplicação.

- Código limpo: Evita a repetição de código de tratamento de exceções em vários controladores.

- Personalização: Permite personalizar o tratamento de exceções com base em regras de negócios específicas.

## **Quando Usar @ControllerAdvice:**

- Use `@ControllerAdvice` quando você desejar centralizar o tratamento de exceções em toda a aplicação.

- Use-o quando quiser aplicar tratamento de exceção a vários controladores em vez de repeti-lo em cada controlador.

- É útil ao lidar com exceções comuns que podem ser tratadas de maneira uniforme em toda a aplicação.

## Conselhos Aplicados Globalmente

A classe anotada com `@ControllerAdvice` é aplicada globalmente a todos os controladores na aplicação.

## Ordem de Execução

Os manipuladores de exceção são executados na ordem em que são definidos na classe `@ControllerAdvice`.

## Observações

- Os manipuladores de exceção podem retornar diferentes tipos de respostas, como `ResponseEntity`, `ModelAndView` ou `String`.
- Personalize a lógica de tratamento de exceções com base nos requisitos da sua aplicação.
- Os manipuladores de exceção podem ter parâmetros adicionais, como `WebRequest`, `Locale`, etc., se necessário.


## **Exemplos:**

### Definindo um Objeto contendo uma descrição do erro

```java
public class ErrorMessage {
    private String description;
    private int status;
    private String exceptionMessage;
    private LocalDateTime timestamp;
    
    public ErrorMessage(String description, int status, String exceptionMessage) {
        this.description = description;
        this.status = status;
        this.exceptionMessage = exceptionMessage;
        this.timestamp = LocalDateTime.now();
    }
}
```

### Lidando com Exceções

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorMessage> handleException(Exception ex) {
    ErrorMessage errorMessage = new ErrorMessage("Erro Interno do Servidor", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
}
```

### Lidando com Múltiplas Exceções

```java
@ExceptionHandler({CustomException1.class, CustomException2.class})
public ResponseEntity<ErrorMessage> handleCustomExceptions(Exception ex) {
    ErrorMessage errorMessage = new ErrorMessage("Ocorreu uma Exceção Personalizada", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
}
```

### Lidando com Erros de Validação

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();

    // Construir resposta de erro
    ErrorMessage errorMessage = new ErrorMessage("Falha na Validação", HttpStatus.BAD_REQUEST.value(), "Erros de validação na requisição");
    // Adicionar erros de campo à mensagem de erro, se necessário

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
}
```