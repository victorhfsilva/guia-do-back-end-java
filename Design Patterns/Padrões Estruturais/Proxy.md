# Padrão de Projeto Proxy

O padrão Proxy é um padrão de design estrutural que permite controlar o acesso a um objeto por meio de um objeto proxy. O proxy atua como intermediário entre o cliente e o objeto real, fornecendo um objeto de substituição ou representante para controlar o acesso ao objeto real. Ele é útil para adicionar uma camada de controle ou funcionalidades adicionais, como verificação de autorização, cache, registro, etc., sem modificar o objeto real.

### Vantagens

- Controle de acesso: o proxy pode controlar e monitorar o acesso ao objeto real, permitindo implementar lógica adicional, como verificação de autorização.
- Encapsulamento: o cliente não precisa saber sobre a existência do proxy, pois ele age como um substituto transparente para o objeto real.
- Melhoria de desempenho: o proxy pode implementar técnicas de otimização, como cache, para melhorar o desempenho do acesso ao objeto real.

### Quando usar o padrão Proxy?

- Controle de acesso: quando você precisa controlar o acesso a um objeto real, como verificar permissões de usuário.
- Encapsulamento: quando você precisa ocultar a complexidade de acesso ou lógica adicional do cliente.
- Otimização de desempenho: quando você precisa implementar cache ou outras técnicas de otimização para melhorar o desempenho.

### Interface

**Exemplo**: Interface para o objeto real e seu proxy.

```java
// Proxy
interface Subject {
    void request();
}
```

### Objeto Real

- Define o objeto real que o proxy representa.
- Implementa a mesma interface que o proxy.
- Executa a funcionalidade real quando chamado pelo proxy.

**Exemplo**: Implementação do objeto real que o proxy representa.

```java
// Objeto Real
class RealSubject implements Subject {
    public void request() {
        // Implementa a funcionalidade real.
    }
}
```

### Proxy

- Mantém uma referência ao objeto real.
- Implementa a mesma interface que o objeto real, permitindo que o cliente interaja com o proxy da mesma maneira que interagiria com o objeto real.
- Controla o acesso ao objeto real, adicionando funcionalidades adicionais, como verificação de autorização, cache, etc.

**Exemplo**: Implementação do proxy que controla o acesso ao objeto real.

```java
// Proxy Concreto
class Proxy implements Subject {
    private RealSubject realSubject;

    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        // Execute ações adicionais antes ou depois de chamar o objeto real.
        realSubject.request();
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        // Cria um proxy para o objeto real.
        Subject proxy = new Proxy();

        // Chama o método request através do proxy.
        proxy.request();
    }
}
```

### Saída Esperada

```
Executando a solicitação real.
```

Neste exemplo, o padrão Proxy é utilizado para controlar o acesso a um objeto real através de um proxy. O cliente interage com o proxy da mesma maneira que interagiria com o objeto real, sem precisar saber sobre a existência do proxy. O proxy controla o acesso ao objeto real e pode adicionar funcionalidades adicionais, como verificação de autorização ou otimização de desempenho, sem modificar o objeto real. Isso ajuda a manter o código do cliente limpo e desacoplado da lógica específica de acesso ao objeto real.