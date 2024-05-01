# Princípio de Segregação de Interface (ISP) do SOLID

O Princípio de Segregação de Interface (Interface Segregation Principle - ISP) é um dos cinco princípios fundamentais do SOLID, focado no design de interfaces em programação orientada a objetos. O ISP estabelece que uma classe não deve ser forçada a implementar interfaces que não utiliza. Isso evita o "inchaço" de interfaces e garante que as implementações não sejam obrigadas a depender de métodos que elas não usam, o que pode levar a implementações desnecessárias ou mesmo erradas.

## Compreendendo o ISP

O objetivo do ISP é reduzir o impacto das mudanças em sistemas de software, segregando interfaces extensas em interfaces menores e mais específicas. Isso permite que as classes implementem apenas os métodos que são realmente necessários para seu funcionamento, promovendo um design mais limpo e modular.

## Exemplos em Java

### Exemplo Violando o ISP

Considere uma interface `Machine` que inclui operações para dispositivos multifuncionais, como impressoras que também podem escanear e enviar fax.

```java
public interface Machine {
    void print(Document d);
    void scan(Document d);
    void fax(Document d);
}

public class MultiFunctionPrinter implements Machine {
    public void print(Document d) { /* implementação real */ }
    public void scan(Document d) { /* implementação real */ }
    public void fax(Document d) { /* implementação real */ }
}

public class SimplePrinter implements Machine {
    public void print(Document d) { /* implementação real */ }
    public void scan(Document d) {
        throw new UnsupportedOperationException("Scan not supported.");
    }
    public void fax(Document d) {
        throw new UnsupportedOperationException("Fax not supported.");
    }
}
```

Neste exemplo, `SimplePrinter` é forçado a implementar `scan` e `fax`, métodos que não são relevantes para suas funcionalidades, violando o ISP.

### Refatorando para Cumprir o ISP

Para aderir ao ISP, podemos dividir a interface `Machine` em várias interfaces menores, cada uma representando uma funcionalidade específica.

```java
public interface Printer {
    void print(Document d);
}

public interface Scanner {
    void scan(Document d);
}

public interface Fax {
    void fax(Document d);
}

public class MultiFunctionPrinter implements Printer, Scanner, Fax {
    public void print(Document d) { /* implementação real */ }
    public void scan(Document d) { /* implementação real */ }
    public void fax(Document d) { /* implementação real */ }
}

public class SimplePrinter implements Printer {
    public void print(Document d) { /* implementação real */ }
}
```

### Benefícios da Refatoração

1. **Flexibilidade**: Cada dispositivo pode implementar apenas as interfaces que correspondem às suas capacidades.
2. **Manutenibilidade**: Alterações em uma interface específica têm um impacto limitado apenas às classes que implementam essa interface.
3. **Reusabilidade**: Interfaces pequenas e focadas são mais fáceis de reutilizar em diferentes partes do sistema.

### Considerações Adicionais

Ao aplicar o ISP, é importante manter o equilíbrio entre a segregação de interfaces e o excesso de granularidade. Muitas interfaces pequenas podem tornar o sistema complexo e difícil de entender, especialmente se muitas delas são frequentemente usadas juntas.

## Conclusão

O Princípio de Segregação de Interface é essencial para criar sistemas flexíveis e desacoplados. Seguindo o ISP, os desenvolvedores podem reduzir a dependência entre os componentes do sistema, tornando o código mais limpo, mais fácil de manter e menos suscetível a erros devido a alterações inesperadas. Ao projetar interfaces, considere cuidadosamente as responsabilidades que elas devem abranger para evitar forçar classes a implementar métodos desnecessários ou não desejados.