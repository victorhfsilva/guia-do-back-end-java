# Padrão de Projeto Flyweight

O padrão Flyweight é um padrão de design estrutural que visa minimizar o uso de memória ou recursos computacionais compartilhando o máximo possível de dados com objetos semelhantes. Ele é útil quando você precisa criar um grande número de objetos semelhantes que possuem partes compartilhadas e podem ser agrupados em objetos leves.

### Vantagens

- Reduz a quantidade de memória ou recursos computacionais necessários, especialmente ao lidar com um grande número de objetos.
- Melhora o desempenho e a eficiência, eliminando a redundância de dados.
- Facilita o compartilhamento de estado entre objetos semelhantes, economizando recursos.

### Quando usar o padrão Flyweight?

- Quando você precisa criar um grande número de objetos semelhantes.
- Quando os objetos podem ser compartilhados entre vários contextos e suas variações podem ser armazenadas extrinsecamente.
- Quando você deseja economizar memória ou recursos computacionais compartilhando dados comuns entre objetos.

### Flyweight

- Representa objetos leves que podem ser compartilhados entre vários contextos.
- Armazena dados intrínsecos (invariantes) que são compartilhados entre objetos.
- Define operações que podem receber dados extrínsecos (variantes) como parâmetros.

**Exemplo**: Interface para objetos Flyweight que representam caracteres.

```java
// Flyweight
interface Flyweight {
    void render(String font, int size);
}
```

### ConcreteFlyweight

- Implementa a interface Flyweight.
- Armazena dados intrínsecos que são compartilhados entre objetos.
- Pode ser compartilhado entre vários contextos.

**Exemplo**: Implementação de caracteres que compartilham os mesmos dados intrínsecos.

```java
// ConcreteFlyweight
class CharacterFlyweight implements Flyweight {
    private char character;

    public CharacterFlyweight(char character) {
        this.character = character;
    }

    public void render(String font, int size) {
        System.out.println("Character: " + character + ", Font: " + font + ", Size: " + size);
    }
}
```

### FlyweightFactory

- Gerencia objetos Flyweight e garante que objetos compartilhados sejam reutilizados.
- Mantém um pool de objetos Flyweight e fornece métodos para recuperar ou criar novos objetos Flyweight.

**Exemplo**: Fábrica para criar e gerenciar objetos Flyweight.

```java
// FlyweightFactory
class CharacterFactory {
    private Map<Character, Flyweight> characters = new HashMap<>();

    public Flyweight getCharacter(char character) {
        if (!characters.containsKey(character)) {
            characters.put(character, new Character(character));
        }
        return characters.get(character);
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        // Recupera ou cria objetos Flyweight para caracteres comuns.
        Flyweight characterA = factory.getCharacter('A');
        Flyweight characterB = factory.getCharacter('B');
        Flyweight characterC = factory.getCharacter('C');

        // Usa os objetos Flyweight.
        characterA.render("Arial", 12);
        characterB.render("Times New Roman", 14);
        characterC.render("Calibri", 10);
    }
}
```

### Saída Esperada

```
Character: A, Font: Arial, Size: 12
Character: B, Font: Times New Roman, Size: 14
Character: C, Font: Calibri, Size: 10
```

Neste exemplo, o padrão Flyweight é utilizado para criar e compartilhar objetos Flyweight representando caracteres. A fábrica `CharacterFactory` garante que objetos Flyweight sejam reutilizados, minimizando a criação desnecessária de objetos e economizando recursos de memória. Cada objeto Flyweight representa um caractere e compartilha os mesmos dados intrínsecos entre vários contextos. Isso resulta em uma redução significativa na quantidade de memória ou recursos computacionais necessários para representar um grande número de caracteres.

### Exemplo 2

```java
// A classe flyweight contém uma parte do estado de uma árvore. Esses campos armazenam valores que são únicos para cada árvore específica. Por exemplo, você não encontrará aqui as coordenadas da árvore. Mas a textura e as cores compartilhadas entre muitas árvores estão aqui. Como esses dados geralmente são GRANDES, você desperdiçaria muita memória mantendo-os em cada objeto de árvore. Em vez disso, podemos extrair textura, cor e outros dados repetitivos em um objeto separado no qual muitos objetos de árvore individuais podem fazer referência.
class TipoArvore {
    private String nome;
    private String cor;
    private String textura;

    public TipoArvore(String nome, String cor, String textura) {
        this.nome = nome;
        this.cor = cor;
        this.textura = textura;
    }

    public void desenhar(String canvas, int x, int y) {
        // 1. Criar um bitmap de um determinado tipo, cor e textura.
        // 2. Desenhar o bitmap no canvas nas coordenadas X e Y.
    }
}

// A fábrica flyweight decide se deve reutilizar um flyweight existente ou criar um novo objeto.
class FabricaArvore {
    private static Map<String, TipoArvore> tiposArvore = new HashMap<>();

    public static TipoArvore getTipoArvore(String nome, String cor, String textura) {
        TipoArvore tipo = tiposArvore.get(nome + cor + textura);
        if (tipo == null) {
            tipo = new TipoArvore(nome, cor, textura);
            tiposArvore.put(nome + cor + textura, tipo);
        }
        return tipo;
    }
}

// O objeto contextual contém a parte extrínseca do estado da árvore. Um aplicativo pode criar bilhões desses, pois eles são bastante pequenos: apenas duas coordenadas inteiras e um campo de referência.
class Arvore {
    private int x, y;
    private TipoArvore tipo;

    public Arvore(int x, int y, TipoArvore tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public void desenhar(String canvas) {
        tipo.desenhar(canvas, x, y);
    }
}

// As classes Árvore e Floresta são os clientes do flyweight. Você pode fundi-los se não planeja desenvolver ainda mais a classe Árvore.
class Floresta {
    private List<Arvore> arvores = new ArrayList<>();

    public void plantarArvore(int x, int y, String nome, String cor, String textura) {
        TipoArvore tipo = FabricaArvore.getTipoArvore(nome, cor, textura);
        Arvore arvore = new Arvore(x, y, tipo);
        arvores.add(arvore);
    }

    public void desenhar(String canvas) {
        for (Arvore arvore : arvores) {
            arvore.desenhar(canvas);
        }
    }
}
```