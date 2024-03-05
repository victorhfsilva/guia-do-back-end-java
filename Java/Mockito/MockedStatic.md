# **MockedStatic no Mockito**

O `MockedStatic` é uma funcionalidade que permite criar mocks estáticos para métodos estáticos em classes. Isso é útil quando você deseja simular chamadas de métodos estáticos durante os testes. Aqui está um cheatsheet sobre como usar o `MockedStatic` no Mockito:

```java
// Importe classes necessárias
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

// 1. Criar um MockedStatic para uma classe com métodos estáticos
try (MockedStatic<ClasseEstatica> mockedStatic = mockStatic(ClasseEstatica.class)) {

    // 2. Definir o comportamento esperado para métodos estáticos
    when(ClasseEstatica.metodoEstatico()).thenReturn(ValorEsperado);

    // 3. Execute o código que chama métodos estáticos
    ClasseChamadora.chamarMetodoEstatico();

    // 4. Verifique as chamadas de métodos estáticos
    verify(ClasseEstatica).metodoEstatico();
}

// 5. Fora do bloco 'try', o MockedStatic é automaticamente fechado

```

### Exemplo:

```java
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class MinhaClasse {
    public static String metodoEstatico() {
        return "Método Estático Original";
    }
}

public class MinhaClasseChamadora {
    public String chamarMetodoEstatico() {
        return MinhaClasse.metodoEstatico();
    }
}

public class ExemploDeTeste {
    public void testeUsandoMockedStatic() {
        try (MockedStatic<MinhaClasse> mockedStatic = mockStatic(MinhaClasse.class)) {
            // 2. Defina o comportamento esperado para o método estático
            when(MinhaClasse.metodoEstatico()).thenReturn("Mocked Value");

            // 3. Execute o código que chama o método estático
            MinhaClasseChamadora chamadora = new MinhaClasseChamadora();
            String resultado = chamadora.chamarMetodoEstatico();

            // 4. Verifique as chamadas do método estático
            verify(MinhaClasse).metodoEstatico();

            // 5. Verifique o resultado
            assertEquals("Mocked Value", resultado);
        }

        // 6. Fora do bloco 'try', o MockedStatic é automaticamente fechado
    }
}
```
