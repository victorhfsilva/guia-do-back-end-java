# **Singleton em Java**

O Singleton é um padrão de projeto criacional que garante que uma classe tenha apenas uma instância e forneça um ponto global de acesso a essa instância. Ele é útil quando precisamos controlar o acesso a recursos compartilhados, como bancos de dados, conexões de rede ou caches de memória.

![Singleton](./images/singleton.png)

### Implementação Básica em Java

Aqui está uma implementação básica do Singleton em Java:

```java
public class Singleton {
    // O campo estático que armazena a única instância da classe Singleton.
    private static Singleton instance;

    // O construtor privado impede a criação de instâncias da classe fora dela mesma.
    private Singleton() {
        // Inicialização do Singleton.
    }

    // O método estático que fornece a instância única da classe Singleton.
    public static Singleton getInstance() {
        // Se a instância ainda não foi criada, cria-a.
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Métodos adicionais da classe Singleton.
}
```

### Inicialização Estática

```java
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

### Threads e Synchronized

A implementação básica do Singleton pode enfrentar problemas de concorrência em um ambiente multithread. Para evitar isso, podemos usar a palavra-chave `synchronized` no método `getInstance()` para garantir que apenas uma thread possa acessá-lo de cada vez:

```java
public static synchronized Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}
```

### Lazy Initialization

A abordagem de inicialização preguiçosa (lazy initialization) adia a criação da instância Singleton até o momento em que ela é necessária pela primeira vez. Isso pode economizar recursos se a instância Singleton não for necessária em todas as execuções do programa:

### Enum Singleton (Recomendado)

Uma maneira mais segura e fácil de implementar o Singleton em Java é usando enum, pois é garantido que uma instância de enumeração será instanciada apenas uma vez em um programa Java, independentemente de quantas threads estejam tentando criar a instância. Aqui está como implementar um Singleton usando enum:

```java
public enum Singleton {
    INSTANCE;

    // Métodos da classe Singleton.
}
```

### Conclusão

O Singleton é um padrão de projeto útil para garantir que uma classe tenha apenas uma instância em um programa Java. Ele pode ser implementado de várias maneiras, mas o uso de enum para Singleton é recomendado devido à sua segurança e simplicidade. No entanto, escolha a abordagem que melhor se adapte às necessidades específicas do seu projeto.
