# Princípio da Responsabilidade Única (SRP) do SOLID

O Princípio da Responsabilidade Única (SRP) é um dos cinco princípios do SOLID, essenciais para o design de software orientado a objetos. O SRP afirma que "uma classe deve ter apenas uma razão para mudar", o que significa que ela deve ter apenas uma responsabilidade ou funcionalidade.

## Entendendo o SRP

O SRP é sobre limitar o impacto das mudanças, simplificando classes ao dividir funcionalidades complexas em partes menores e mais gerenciáveis. Isso não só torna o código mais limpo e fácil de manter, mas também facilita o teste e a reutilização.

## Exemplo Prático sem SRP

Considere uma classe Java que gerencia detalhes de usuários e também cuida de sua persistência no banco de dados.

```java
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void saveUser() {
        System.out.println("Salvar usuário no banco de dados");
        // Código para salvar o usuário no banco de dados
    }

    // Getters e setters
}
```

**Problema:** A classe `User` está gerenciando não apenas os dados do usuário, mas também sua persistência no banco de dados. Isso viola o SRP porque a classe tem mais de uma razão para mudar: mudanças nos dados do usuário ou mudanças na lógica de persistência.

## Refatorando com SRP

Para aderir ao SRP, podemos dividir essa classe em duas: uma para manter os dados do usuário e outra para gerenciar a persistência.

### Classe de Domínio do Usuário

```java
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters e setters
}
```

### Classe de Persistência do Usuário

```java
public class UserRepository {
    public void saveUser(User user) {
        System.out.println("Salvar usuário no banco de dados");
        // Código para salvar o usuário no banco de dados
    }
}
```

**Benefícios:** Com essa separação, a classe `User` agora se concentra exclusivamente nos dados do usuário, enquanto `UserRepository` lida com toda a lógica de persistência. Isso torna ambos os componentes mais fáceis de gerenciar e testar.

## Aplicando SRP em um Contexto Maior

O SRP também se aplica a métodos dentro de classes. Cada método deve ter uma única responsabilidade. Por exemplo, se um método `processUserData()` em uma classe `DataProcessor` estiver realizando validação de dados e também enviando e-mails, isso violaria o SRP.

### Método Refatorado

```java
public class DataProcessor {
    public boolean validateUserData(User user) {
        // Validar dados do usuário
        return true;
    }

    public void sendEmailNotification(String message) {
        // Lógica para enviar e-mail
    }
}
```

**Melhor Prática:** Separar diferentes funcionalidades em métodos distintos facilita a manutenção e a compreensão do código, além de permitir a reutilização de métodos em diferentes contextos sem alterações.

## Conclusão

Aplicar o Princípio da Responsabilidade Única ajuda a criar um design de software mais limpo e modular. Ao garantir que classes e métodos tenham uma única responsabilidade, você minimiza a complexidade, facilita o teste e melhora a manutenção do código. Seguir o SRP é um passo crucial para o desenvolvimento de software robusto e escalável.