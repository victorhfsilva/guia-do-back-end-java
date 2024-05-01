# Princípio de Substituição de Liskov (LSP) do SOLID

O Princípio de Substituição de Liskov (Liskov Substitution Principle - LSP), formulado por Barbara Liskov em 1987, é um dos cinco princípios do SOLID que orientam o design orientado a objetos. O LSP estabelece que objetos de uma classe base devem ser substituíveis por objetos de classes derivadas sem afetar a integridade da aplicação. Este princípio é fundamental para garantir que uma classe derivada mantenha a comportamento esperado, promovendo o reuso de código e a integridade do sistema.

## Compreendendo o LSP

O LSP foca em garantir que uma subclasse possa assumir o lugar de sua superclasse sem erros ou comportamentos inesperados. Isso significa que a subclasse deve:
- Cumprir os contratos e as especificações da superclasse.
- Não alterar os comportamentos esperados da superclasse.

## Exemplos em Java

### Exemplo Violando o LSP

Imagine um sistema de gestão para formas geométricas, com um método que define que todas as formas devem poder calcular sua área. Um desenvolvedor decide adicionar um novo tipo de forma que não se encaixa bem nessa definição.

```java
public class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
```

Neste exemplo, `Square` viola o LSP porque redefine o comportamento das propriedades `setWidth` e `setHeight` de `Rectangle`. Quando um `Square` é usado no lugar de um `Rectangle`, isso pode levar a resultados inesperados, pois o comportamento de `Square` não é compatível com a superclasse.

### Refatorando para Cumprir o LSP

Para aderir ao LSP, é aconselhável criar uma interface comum que ambas as classes podem implementar de maneira que não comprometam o princípio de substituição.

```java
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}

public class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}
```

### Benefícios da Refatoração

1. **Manutenibilidade**: As alterações na superclasse ou subclasses são menos propensas a introduzir bugs em componentes que dependem dessas classes.
2. **Reusabilidade**: Classes que seguem o LSP são mais fáceis de reutilizar porque seu comportamento é previsível e consistente.
3. **Interoperabilidade**: Substituição sem problemas promove maior flexibilidade no uso de componentes.

### Aplicando o LSP em Outros Contextos

O LSP pode ser aplicado em qualquer hierarquia de classe onde a substituição de uma superclasse por uma subclasse deve não alterar o funcionamento do programa. É crucial para o design de APIs robustas, bibliotecas, e frameworks.

## Conclusão

O Princípio de Substituição de Liskov é essencial para garantir que as extensões de uma classe mantenham o comportamento esperado e a compatibilidade com os clientes da superclasse. Seguir o LSP aumenta a robustez e a confiabilidade do software, facilitando o desenvolvimento e a manutenção de sistemas complexos.