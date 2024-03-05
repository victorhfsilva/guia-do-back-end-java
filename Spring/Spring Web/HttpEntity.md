# HttpEntity

`HttpEntity` faz parte do Spring Framework e representa uma entidade de solicitação ou resposta HTTP. Pode ser usado para configurar e passar cabeçalhos HTTP e/ou o corpo da solicitação ou resposta.

## Criando um HttpEntity

### Criando um HttpEntity Simples

```java
HttpEntity<String> entity = new HttpEntity<>("conteúdo do corpo da solicitação");
```

### Criando um HttpEntity com Cabeçalhos

```java
HttpHeaders headers = new HttpHeaders();
headers.add("Nome-do-Cabeçalho", "Valor-do-Cabeçalho");

HttpEntity<String> entity = new HttpEntity<>("conteúdo do corpo da solicitação", headers);
```

## Usando o HttpEntity

### Fazendo uma Solicitação HTTP com o RestTemplate

```java
HttpEntity<String> entity = new HttpEntity<>("conteúdo do corpo da solicitação");

RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.exchange("https://api.exemplo.com/recurso", HttpMethod.GET, entity, String.class);
```
