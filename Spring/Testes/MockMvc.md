# Testes de REST APIs com o MockMvc

Os testes unitários são fundamentais para garantir que os controllers em sua aplicação Spring funcionem corretamente, isolando-os de outras partes do sistema. Com o Spring Boot, você pode escrever testes unitários eficazes para seus controllers usando o `MockMvc`. 

## Configuração do Ambiente de Teste

Antes de começar a escrever os testes, configure o ambiente de teste. Você pode fazer isso anotando sua classe de teste com `@SpringBootTest` e definindo o ambiente de teste para carregar apenas o contexto do MVC usando `@AutoConfigureMockMvc`.

```java
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    // Escreva seus testes aqui
}
```

## Testando Requisições GET

Use o `MockMvc` para simular requisições GET ao controller. Você pode verificar o status da resposta e o conteúdo retornado.

```java
@Test
public void testGetUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.content().string("Usuário com ID 1"));
}
```

## Testando Requisições POST

Da mesma forma, você pode simular requisições POST e fornecer dados de entrada. Verifique o status da resposta e o comportamento do controller.

```java

@Test
public void testCreateUser() throws Exception {
    User user = new User();
    
    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(user)))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.content().string("Usuário criado com sucesso"));
}
```

## Testando Respostas JSON

Se o controller retorna JSON, você pode verificar o conteúdo JSON retornado.

```java
@Test
public void testGetUserAsJson() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alice"));
}
```

## Testando Redirecionamentos

Verifique se o controller redireciona corretamente.

```java
@Test
public void testRedirectToPage() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/redirect"))
           .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
           .andExpect(MockMvcResultMatchers.redirectedUrl("/targetPage"));
}
```
