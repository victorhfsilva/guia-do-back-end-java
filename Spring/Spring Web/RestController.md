# Spring Boot

## Rest Controller

Um Rest Controller é uma classe em um projeto Java que lida com solicitações HTTP e respostas em um aplicativo que segue o estilo de arquitetura REST (Representational State Transfer).

Em um Rest Controller, podemos definir métodos que correspondem a diferentes solicitações HTTP (como GET, POST, PUT, DELETE) e seus respectivos caminhos de URI. Quando um cliente faz uma solicitação HTTP para o URI correspondente ao método em um Rest Controller, o método é executado e retorna uma resposta.

As anotações mais comuns usadas em um Rest Controller são:

### @RestController:

Indica que a classe é um controlador REST que deve retornar dados no corpo da resposta HTTP, em vez de renderizar uma visualização.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    // métodos correspondentes a diferentes solicitações HTTP
}
```

### @RequestMapping: 

Define o URI e o método HTTP correspondente para o qual o método do controlador deve responder.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<Usuario> obterUsuarios() {
        // lógica para obter uma lista de usuários
    }
}
```

### @PathVariable:

Permite extrair um valor de uma variável em um URI.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario obterUsuarioPorId(@PathVariable("id") Long id) {
        // lógica para obter um usuário por ID
    }
}
```

### @RequestBody: 

Indica que o método espera um objeto no corpo da solicitação HTTP e que o Spring deve converter o corpo em um objeto Java correspondente.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        // lógica para criar um novo usuário
    }
}
```
**Converte um JSON para o tipo do objeto esperado como parâmetro no método.*

### @RequestParam:

Permite extrair um parâmetro da solicitação HTTP como uma variável Java.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<Usuario> obterUsuariosPorNome(@RequestParam("nome") String nome) {
        // lógica para obter uma lista de usuários por nome
    }
}
```

**Quando a solicitação é feita para `/exemplo/usuarios?nome=valor`, o Spring irá automaticamente associar o valor fornecido para nome à variável nome do método.*

### @GetMapping:

Mapeia solicitações HTTP GET para métodos específicos em um controlador. É uma abreviação conveniente para `@RequestMapping(method = RequestMethod.GET)`.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    
    @GetMapping("/usuarios")
    public List<Usuario> obterUsuarios() {
        // lógica para obter uma lista de usuários
    }
}
```

### @PostMapping:

Mapeia solicitações HTTP POST para métodos específicos em um controlador. É uma abreviação conveniente para `@RequestMapping(method = RequestMethod.POST)`.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    
    @PostMapping("/usuarios")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        // lógica para criar um novo usuário
    }
}
```

### @PutMapping:

Mapeia solicitações HTTP PUT para métodos específicos em um controlador. É uma abreviação conveniente para `@RequestMapping(method = RequestMethod.PUT)`.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    
    @PutMapping("/usuarios/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        // lógica para atualizar um usuário existente
    }
}
```

### @DeleteMapping:

Mapeia solicitações HTTP DELETE para métodos específicos em um controlador. É uma abreviação conveniente para `@RequestMapping(method = RequestMethod.DELETE)`.

```java
@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        // lógica para deletar um usuário existente
    }
}
```