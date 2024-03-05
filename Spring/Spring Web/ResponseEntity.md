# ResponseEntity

`ResponseEntity` é uma classe do Spring Framework que representa uma resposta HTTP. Ele fornece informações sobre o corpo da resposta, cabeçalhos e o código de status da resposta. É frequentemente usado ao construir controladores de API REST no Spring.

## Criando um ResponseEntity

### Criando um ResponseEntity Simples

```java
ResponseEntity<String> response = new ResponseEntity<>("Corpo da resposta", HttpStatus.OK);
```

### Criando um ResponseEntity com Cabeçalhos

```java
HttpHeaders headers = new HttpHeaders();
headers.add("Nome-do-Cabeçalho", "Valor-do-Cabeçalho");

ResponseEntity<String> response = new ResponseEntity<>("Corpo da resposta", headers, HttpStatus.OK);
```

## Acessando Informações do ResponseEntity

### Obtendo o Corpo da Resposta

```java
String corpoDaResposta = response.getBody();
```

### Obtendo os Cabeçalhos da Resposta

```java
HttpHeaders cabeçalhosDaResposta = response.getHeaders();
```

### Obtendo o Código de Status

```java
HttpStatus códigoDeStatus = response.getStatusCode();
```

## Respostas com Diferentes Códigos de Status

`ResponseEntity` permite criar respostas com diferentes códigos de status HTTP. Os códigos de status mais comuns são:

- `HttpStatus.OK`: 200 OK
- `HttpStatus.CREATED`: 201 Created
- `HttpStatus.NO_CONTENT`: 204 No Content
- `HttpStatus.BAD_REQUEST`: 400 Bad Request
- `HttpStatus.NOT_FOUND`: 404 Not Found
- `HttpStatus.INTERNAL_SERVER_ERROR`: 500 Internal Server Error

## Exemplo

```java
@RestController
public class MeuControlador {

    @GetMapping("/exemplo")
    public ResponseEntity<String> exemploDeResposta() {
        String corpoDaResposta = "Exemplo de corpo de resposta";
        
        HttpHeaders cabeçalhos = new HttpHeaders();
        cabeçalhos.add("Meu-Cabeçalho", "Valor");

        return new ResponseEntity<>(corpoDaResposta, cabeçalhos, HttpStatus.OK);
    }
}
```
