# Testes de Integração de REST APIs com Spring Boot

Os testes de integração são fundamentais para garantir que sua API REST funcione corretamente em um ambiente semelhante ao de produção. Com o Spring Boot, você pode escrever testes de integração eficazes usando o `TestRestTemplate`. Vamos explorar como configurar e escrever testes de integração para sua API REST com exemplos práticos.

## Configuração do Ambiente de Teste

Antes de começar a escrever os testes, configure o ambiente de teste. Você pode fazer isso anotando sua classe de teste com `@SpringBootTest` e definindo o ambiente da web para `WebEnvironment.RANDOM_PORT` para iniciar o servidor com uma porta aleatória, evitando conflitos em ambientes de teste.

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // Métodos de teste serão adicionados aqui
}
```

## Testando Requisições GET

Você pode usar o `TestRestTemplate` para realizar chamadas GET em sua API REST. Verifique o status da resposta e o conteúdo retornado.

```java
@Test
public void testGetUser() {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/users/1", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Usuário com ID 1", response.getBody());
}
```

## Testando Requisições POST

Da mesma forma, você pode simular requisições POST e fornecer dados de entrada. Verifique o status da resposta e o conteúdo retornado.

```java
@Test
public void testCreateUser() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    String requestBody = "{\"name\": \"Alice\"}";
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/api/users", requestEntity, String.class);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Usuário criado com sucesso", response.getBody());
}
```

## Testando Respostas JSON

Se sua API REST retornar JSON, você pode verificar o conteúdo JSON retornado.

```java
@Test
public void testGetUserAsJson() {
    ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:" + port + "/api/users/1", User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User user = response.getBody();
    assertEquals(1L, user.getId());
    assertEquals("Alice", user.getName());
}
```

## Testando Cabeçalhos e Parâmetros

Você pode testar cabeçalhos e parâmetros em suas requisições.

```java
@Test
public void testRequestWithHeadersAndParams() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer your-access-token");

    // Adicione os cabeçalhos na solicitação
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/resource", HttpMethod.GET, requestEntity, String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Response Content", response.getBody());
}
```

Com esses exemplos, você pode começar a escrever testes de integração robustos para sua API REST no Spring Boot, garantindo que ela funcione conforme o esperado em um ambiente semelhante ao de produção.