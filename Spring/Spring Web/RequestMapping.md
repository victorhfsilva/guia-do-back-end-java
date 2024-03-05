# RequestMapping

**1. Mapeamento de URL Básico:**

```java
@RequestMapping("/rota")
public String handlerMethod() {
    // Lógica do controlador
}
```

- Este código faz com que o método `handlerMethod()` seja acionado sempre que uma solicitação GET for feita para a URL `/rota`.

**2. Métodos HTTP Específicos:**

```java
@RequestMapping(value = "/rota", method = RequestMethod.POST)
public String handlerMethod() {
    // Lógica do controlador para lidar com solicitações POST
}
```

- Define que o método `handlerMethod()` será executado somente quando uma solicitação POST for feita para a URL `/rota`.

**3. Múltiplas URLs:**

```java
@RequestMapping(value = {"/rota1", "/rota2"})
public String handlerMethod() {
    // Mapeia múltiplas URLs para o mesmo método
}
```

- Este trecho permite que o método `handlerMethod()` seja acionado tanto para `/rota1` quanto para `/rota2`.

**4. Parâmetros de Solicitação:**

```java
@RequestMapping(value = "/rota", params = "param=valor")
public String handlerMethod() {
    // Mapeia a rota somente se o parâmetro "param" for igual a "valor"
}
```

- Esse código garante que o método `handlerMethod()` seja executado apenas se a solicitação incluir o parâmetro `param` com o valor `valor`.

**5. Cabeçalhos de Solicitação:**

```java
@RequestMapping(value = "/rota", headers = "Content-Type=application/json")
public String handlerMethod() {
    // Mapeia a rota apenas se o cabeçalho "Content-Type" for "application/json"
}
```

- Este trecho indica que o método `handlerMethod()` será executado somente se a solicitação HTTP incluir um cabeçalho "Content-Type" com o valor "application/json".

**6. Consumo de Mídia:**

```java
@RequestMapping(value = "/rota", consumes = "application/json")
public String handlerMethod() {
    // Mapeia a rota com base no tipo de mídia da solicitação
}
```

- Especifica que o método `handlerMethod()` será executado apenas se a solicitação HTTP tiver um cabeçalho "Content-Type" com o valor "application/json".

**7. Produção de Mídia:**

```java
@RequestMapping(value = "/rota", produces = "application/json")
public String handlerMethod() {
    // Mapeia a rota com base no tipo de mídia da resposta
}
```

- Indica o tipo de mídia (media type) que este método produzirá como resposta. Neste caso, o método produzirá uma resposta no formato JSON. Isso significa que o cliente que faz a solicitação espera receber uma resposta com um cabeçalho "Content-Type" com o valor "application/json".

**8. Expressões Ant:**

```java
@RequestMapping(value = "/rota/**")
public String handlerMethod() {
    // Mapeia qualquer URL que comece com "/rota/"
}
```

- Define que o método `handlerMethod()` será executado para qualquer URL que comece com `/rota/`.

**9. Mapeamento por Controlador de Classe:**

```java
@Controller
@RequestMapping("/rota")
public class MeuControlador {
    // Métodos dentro desta classe herdam o mapeamento "/rota"
}
```

- Este código define que todos os métodos dentro da classe `MeuControlador` serão mapeados para URLs que começam com `/rota`.
