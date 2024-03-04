# Datas e Horas

A anotação `@Temporal` em JPA (Java Persistence API) é usada para mapear um atributo que representa uma data ou hora em um banco de dados relacional. Ela trabalha em conjunto com a enumeração `TemporalType` para especificar o tipo de mapeamento de data/hora que deve ser aplicado a um campo.

### Usando a Anotação `@Temporal`

Para mapear um atributo de data ou hora em uma entidade JPA, siga estas etapas:

1. **Importe as classes necessárias**:

   Certifique-se de importar as classes `@Temporal` e `TemporalType` em sua classe de entidade.

   ```java
   import javax.persistence.Temporal;
   import javax.persistence.TemporalType;
   ```

2. **Aplique a anotação `@Temporal` ao atributo**:

   Aplique a anotação `@Temporal` ao atributo que representa a data ou hora.

   ```java
   @Temporal(TemporalType.DATE)
   private Date dataDeNascimento;
   ```

3. **Especifique o tipo de `TemporalType` desejado**:

   Escolha um valor adequado para `TemporalType` com base no tipo de dado da sua coluna no banco de dados:

   - `TemporalType.DATE`: Mapeia o atributo como uma data (sem informações de hora).
   - `TemporalType.TIME`: Mapeia o atributo como um horário (sem informações de data).
   - `TemporalType.TIMESTAMP`: Mapeia o atributo como uma data e hora (incluindo informações de data e hora).

### Exemplo de Uso

Aqui está um exemplo de uso da anotação `@Temporal` com diferentes tipos de `TemporalType`:

```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Pessoa {
    @Id
    private Long id;

    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataDeNascimento;

    @Temporal(TemporalType.TIME)
    private Date horaDoRegistro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraDaUltimaAtualizacao;
    
    // Outros atributos, construtores e métodos
}
```

Neste exemplo, os atributos `dataDeNascimento`, `horaDoRegistro` e `dataHoraDaUltimaAtualizacao` são mapeados com diferentes tipos de `TemporalType` para representar datas, horas e data e hora, respectivamente.

A anotação `@Temporal` é uma ferramenta poderosa para mapear datas e horas em entidades JPA, permitindo que você escolha o tipo de mapeamento apropriado com base nas necessidades do seu aplicativo. Certifique-se de que os tipos de dados no banco de dados correspondam aos tipos `TemporalType` que você escolheu.