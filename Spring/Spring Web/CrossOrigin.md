# CrossOrigin no Spring

Uma solicitação de origem cruzada, ou "Cross-Origin Request" em inglês, ocorre quando um cliente (geralmente um navegador da web) faz uma requisição para buscar recursos de um domínio (origem) que é diferente da origem do próprio cliente. As origens, nesse contexto, se referem aos protocolos, domínios e portas envolvidos nas solicitações.

Para ilustrar, aqui estão alguns exemplos de origens diferentes:

- Origem 1: `http://meusite.com`
- Origem 2: `http://outrosite.com`
- Origem 3: `https://outror.com`

Se um cliente em `http://meusite.com` tentar fazer uma solicitação para buscar recursos de `http://outrosite.com` ou `https://outror.com`, essas solicitações são consideradas solicitações de origem cruzada.

As solicitações de origem cruzada são regulamentadas por políticas de segurança do navegador, como a Política de Mesma Origem (Same-Origin Policy), que é uma medida de segurança importante para evitar potenciais ameaças à segurança dos navegadores. A política padrão não permite que um site faça solicitações a partir de um domínio diferente por padrão.

No entanto, em muitos cenários da web, como o consumo de APIs de terceiros, é necessário permitir solicitações de origem cruzada. Para fazer isso, o servidor que hospeda a API pode configurar as chamadas políticas de Cross-Origin Resource Sharing (CORS), que permitem que os navegadores acessem os recursos da API a partir de domínios diferentes, seguindo regras específicas.

Em resumo, uma solicitação de origem cruzada ocorre quando um cliente faz uma requisição para um servidor que está em uma origem diferente da origem do próprio cliente. Isso requer configuração especial, geralmente em forma de políticas CORS, para que a solicitação seja permitida.

A anotação `@CrossOrigin` é usada no Spring Framework para configurar a política de controle de acesso a recursos da web, permitindo ou restringindo solicitações cruzadas (cross-origin requests) de diferentes origens. Ela é comumente usada para habilitar requisições de domínios específicos em uma API RESTful.

## Habilitar o `@CrossOrigin`

Para habilitar o `@CrossOrigin` em um controlador ou método específico, você pode fazer o seguinte:

```java
@CrossOrigin
@RestController
@RequestMapping("/api/exemplo")
public class ExemploController {
    // Métodos do controlador aqui
}
```

O exemplo acima permite solicitações de qualquer origem. Você também pode personalizar as configurações do `@CrossOrigin` para restringir as origens e definir outras políticas.

## Configuração Avançada

A anotação `@CrossOrigin` oferece várias opções de configuração para controlar o comportamento das solicitações cruzadas. Alguns dos atributos comuns incluem:

- `origins`: Uma lista de origens permitidas. Por padrão, permite todas as origens (`"*"`). Exemplo: `@CrossOrigin(origins = "http://dominio-permitido.com")`.

- `methods`: Métodos HTTP permitidos (GET, POST, PUT, DELETE, etc.). Exemplo: `@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})`.

- `allowedHeaders`: Cabeçalhos HTTP permitidos na solicitação.

- `exposedHeaders`: Cabeçalhos HTTP expostos pela resposta.

- `allowCredentials`: Define se as credenciais (como cookies) podem ser incluídas nas solicitações. O padrão é `false`.

- `maxAge`: Define o tempo máximo em segundos que a política de CORS deve ser armazenada em cache pelo navegador.

## Exemplo Completo

Aqui está um exemplo completo de como usar a anotação `@CrossOrigin` em um controlador:

```java
@CrossOrigin(origins = "http://dominio-permitido.com", methods = {RequestMethod.GET, RequestMethod.POST},
        allowedHeaders = {"Authorization", "Content-Type"}, exposedHeaders = "X-Custom-Header", allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/exemplo")
public class ExemploController {

    @GetMapping("/recurso")
    public ResponseEntity<String> getRecurso() {
        // Lógica do controlador
        return ResponseEntity.ok("Recurso obtido com sucesso.");
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarRecurso(@RequestBody String corpo) {
        // Lógica do controlador
        return ResponseEntity.ok("Recurso enviado com sucesso.");
    }
}
```

Neste exemplo, o `@CrossOrigin` permite solicitações somente da origem `http://dominio-permitido.com` usando os métodos GET e POST. Ele também especifica cabeçalhos permitidos, cabeçalhos expostos, permite credenciais e define um tempo de cache de 1 hora. Certifique-se de ajustar as configurações de acordo com suas necessidades de segurança e requisitos de aplicação.