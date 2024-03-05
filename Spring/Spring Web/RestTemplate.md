# Consumindo uma API com RestTemplate

**1. Instanciando o RestTemplate:**

- Crie uma instância de `RestTemplate` (você pode injetá-lo como um bean no Spring se estiver usando Spring Boot):

   ```java
   RestTemplate restTemplate = new RestTemplate();
   ```

**2. Realizando Requisições GET:**

- Use o método `getForObject()` para fazer uma solicitação GET e receber uma resposta:

   ```java
   String apiUrl = "https://api.example.com/data";
   T response = restTemplate.getForObject(apiUrl, T.class);
   ```

**3. Realizando Requisições POST:**

- Use o método `postForObject()` para fazer uma solicitação POST com dados e receber uma resposta:

   ```java
   String apiUrl = "https://api.example.com/resource";
   T requestObject = ...; // crie o objeto que deseja enviar
   T response = restTemplate.postForObject(apiUrl, requestObject, T.class);
   ```

**4. Enviando Parâmetros:**

- Você pode adicionar parâmetros à sua solicitação GET usando o `UriComponentsBuilder`:

   ```java
   String apiUrl = "https://api.example.com/resource";
   UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
       .queryParam("param1", value1)
       .queryParam("param2", value2);
   String finalUrl = builder.toUriString();
   T response = restTemplate.getForObject(finalUrl, T.class);
   ```

**5. Trabalhando com Headers:**

- Você pode definir cabeçalhos HTTP em suas solicitações, se necessário:

   ```java
   HttpHeaders headers = new HttpHeaders();
   headers.set("Authorization", "Bearer yourToken");
   HttpEntity<String> entity = new HttpEntity<>(headers);
   ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, T.class);
   ```

   ```java
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer yourToken");
    T requestbody = new T();
    HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);
    ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, T.class);
   ```
 
**6. Tratando Respostas:**

- O `RestTemplate` converte automaticamente a resposta em um objeto Java (por exemplo, usando `ResponseEntity<T>`).
- Certifique-se de que o tipo correspondente seja especificado corretamente para a conversão automática.

**7. Tratando Exceções:**

- Trate exceções, como `HttpClientErrorException` para erros 4xx e `HttpServerErrorException` para erros 5xx, ao fazer solicitações REST. Essas exceções fornecem informações detalhadas sobre o erro.

**8. Usando Spring Boot:**

- Se você estiver usando o Spring Boot, o `RestTemplate` já estará disponível como um bean gerenciado. Basta injetá-lo em seus serviços.

```java
@Autowired
private RestTemplate restTemplate;
```
