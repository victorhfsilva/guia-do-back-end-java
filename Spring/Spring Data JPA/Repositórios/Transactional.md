# Transactional

A anotação `@Transactional` é uma anotação chave em Java, amplamente utilizada em frameworks como o Spring, para controlar transações em aplicativos baseados em Java. Ela permite que você defina de maneira declarativa as transações nos métodos de sua aplicação.

### Uso Básico:
```java
@Transactional
public void myTransactionalMethod() {
    // Seu código aqui
}
```

### Propriedades Principais

#### Propagation (Propagação)

A propagação de transações em Spring define como as transações se comportam quando estão aninhadas ou quando um método chamado já está dentro de uma transação existente. Aqui estão algumas das opções comuns de propagação:

- **REQUIRED**: O método chamado participará de uma transação existente se já houver uma, ou criará uma nova transação se não houver.
- **REQUIRES_NEW**: Sempre cria uma nova transação, suspendendo a transação atual se houver uma.
- **NESTED**: Semelhante ao `REQUIRES_NEW`, mas a nova transação é aninhada dentro da transação existente, se houver. Se a transação pai for revertida, a transação aninhada também será.

#### Isolation (Isolamento)

O isolamento de transações determina como uma transação é protegida de interferências de outras transações concorrentes. Aqui estão alguns níveis comuns de isolamento:

- **READ_COMMITTED**: Uma transação só pode ver os dados que foram confirmados por outras transações. Isso evita que a transação veja dados não confirmados (sujeitos a alterações).
- **SERIALIZABLE**: Garante o mais alto nível de isolamento, onde as transações são executadas como se fossem em série, uma após a outra.

#### Outras Propriedades

- **readOnly**: Indica se a transação é apenas de leitura. Isso pode otimizar o desempenho e permitir que o banco de dados realize otimizações.
- **timeout**: Define um tempo limite para a transação, após o qual ela será revertida automaticamente.
- **rollbackFor** / **noRollbackFor**: Especifica quais exceções devem (ou não) acionar o rollback da transação.

#### Exemplo de Uso com Propriedades:

```java
@Transactional(propagation = Propagation.REQUIRES_NEW, 
               isolation = Isolation.SERIALIZABLE, 
               readOnly = false, 
               timeout = 60, 
               rollbackFor = {CustomException.class}, 
               noRollbackFor = {AnotherException.class})
public void myMethodWithTransactionalProperties() {
    // Seu código aqui
}
```

Neste exemplo, o método `myMethodWithTransactionalProperties` será executado em uma nova transação (`REQUIRES_NEW`) com isolamento serializável (`SERIALIZABLE`), e terá um tempo limite de 60 segundos. Se uma `CustomException` for lançada, a transação será revertida, mas se uma `AnotherException` for lançada, a transação não será revertida.

### Anotação em Nível de Classe:

Você também pode aplicar a anotação `@Transactional` em nível de classe, para que todos os métodos da classe herdem as configurações transacionais. Isso é útil quando a maioria dos métodos da classe segue as mesmas configurações de transação.

#### Exemplo de Uso em Nível de Classe:
```java
@Transactional
@Service
public class MyService {
    // Métodos da classe MyService
}
```

### Habilitar Transações no Spring Boot:
No Spring Boot, as transações são habilitadas por padrão. Se você estiver usando o Spring Boot e estiver configurando sua aplicação corretamente, a anotação `@Transactional` funcionará automaticamente.

#### Como Funciona:
- Quando um método anotado com `@Transactional` é chamado, o Spring gerencia automaticamente a abertura e o fechamento da transação.
- Se a transação for bem-sucedida, as mudanças no banco de dados são confirmadas no final do método.
- Se uma exceção for lançada, a transação é revertida (rollback) e as mudanças não são persistidas no banco de dados.

Lembre-se de que a anotação `@Transactional` é uma ferramenta poderosa para controlar transações em aplicativos Spring e Spring Boot. Certifique-se de aplicá-la adequadamente para garantir a integridade dos dados e o correto funcionamento de seus métodos transacionais.