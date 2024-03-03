# JUnit Assumptions

As "assumptions" no JUnit permitem que você defina condições que, quando não atendidas, resultarão na execução pulada do teste em vez de marcá-lo como uma falha. Isso é útil quando você tem testes que dependem de certas condições para serem executados corretamente. Se uma condição não for atendida, o teste será ignorado.

## `assumeTrue` e `assumeFalse`

- **`assumeTrue(boolean condition)`**: Se a condição fornecida for falsa, o teste é pulado.

- **`assumeFalse(boolean condition)`**: Se a condição fornecida for verdadeira, o teste é pulado.

### Exemplo:

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assumptions.*;

public class MyTest {

    @Test
    public void test1() {
        boolean isServerRunning = true; // substituir por código real para verificar se o servidor está em execução
        assumeTrue(isServerRunning, "Skipping test since server is not running");
        System.out.println("Running test1");
        // Se a condição for verdadeira, o teste será executado
    }

    @Test
    public void test2() {
        boolean isDatabaseAvailable = false; // substituir por código real para verificar se o banco de dados está disponível
        assumeFalse(isDatabaseAvailable, "Skipping test since database is available");
        System.out.println("Running test2");
        // Se a condição for falsa, o teste será executado
    }
}
```

## Usos das Assumptions

As "assumptions" são úteis quando você tem testes que dependem de recursos externos, configurações de ambiente ou condições específicas para serem executados corretamente. Se essas condições não forem atendidas, em vez de marcar o teste como falha, você pode pular a execução, economizando tempo e evitando falsos positivos.

Lembre-se de que as "assumptions" não devem ser usadas para verificar condições de negócios dentro dos testes. Se um teste for pulado devido a uma "assumption", isso deve significar que o teste não é apropriado para as condições atuais do ambiente ou configuração.

