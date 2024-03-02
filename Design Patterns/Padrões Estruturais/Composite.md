# Padrão de Projeto Composite

O padrão Composite é um padrão estrutural que permite compor objetos em estruturas de árvore para representar hierarquias parte-todo. Ele permite que os clientes tratem objetos individuais e composições de objetos de maneira uniforme, tornando a composição de objetos aninhados mais simples.

### Vantagens
- Simplifica a manipulação de objetos complexos e suas composições.
- Torna o código mais genérico e flexível, pois permite que os clientes tratem objetos individuais e composições de forma uniforme.

### Quando usar o padrão Composite?
- Quando você tem uma estrutura hierárquica que pode ser representada como uma árvore.
- Quando você deseja que os clientes tratem objetos individuais e composições de objetos de maneira uniforme.
- Quando você quer que as operações recursivas sejam aplicadas de forma transparente para objetos primitivos e compostos.

### Componente
- Define a interface para todos os objetos na composição.
- Pode ser uma interface ou uma classe abstrata que implementa operações comuns para objetos primitivos e compostos.
- Declara operações comuns a objetos primitivos e compostos, como `add`, `remove`, `getChild`, `operation`, etc.

**Exemplo**: Define a interface comum para arquivos e diretórios. Pode ser uma classe abstrata que possui operações comuns como `display`, `add`, `remove`, etc.

```java
// Componente
interface FileSystemComponent {
    void display();
}
```

### Folha (Leaf)
- Implementa o comportamento para objetos primitivos na composição.
- Não tem filhos.
- Geralmente são as classes finais na hierarquia.

**Exemplo**: Representa um arquivo. Implementa operações específicas para arquivos, como `display`.
```java
// Folha
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("File: " + name);
    }
}
```

### Composite
- Representa objetos que têm filhos.
- Implementa operações definidas na interface do componente para manipular filhos.
- Pode ter filhos que são componentes individuais ou outros compostos.
- Implementa operações específicas para composições, como `add`, `remove`, `getChild`, etc.

**Exemplo**: Representa um diretório. Possui uma lista de filhos (arquivos e subdiretórios). Implementa operações para adicionar, remover e listar filhos. Além disso, implementa operações como `display` que percorrem recursivamente a árvore de diretórios para exibir o conteúdo.

```java
// Composite
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children;

    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : children) {
            component.display();
        }
    }
}
```

### Uso do Composite

```java
public class Main {
    public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        Directory dir1 = new Directory("Folder 1");
        dir1.add(file1);
        dir1.add(file2);

        File file3 = new File("file3.txt");
        Directory dir2 = new Directory("Folder 2");
        dir2.add(file3);
        dir2.add(dir1);

        dir2.display();
    }
}
```

Neste exemplo, `File` e `Directory` são componentes. `File` é uma folha e `Directory` é um composite. O código principal demonstra como criar uma hierarquia de diretórios e arquivos e exibir sua estrutura usando o padrão Composite.