# Padrão de Projeto Adapter

O padrão de projeto Adapter é um dos padrões estruturais mais utilizados na engenharia de software. Ele permite que interfaces incompatíveis cooperem entre si. O Adapter atua como uma camada intermediária entre dois componentes, permitindo que eles trabalhem juntos sem alterar seu código fonte original.

### Problema

Imagine que você tenha um componente existente que espera uma interface específica, mas você deseja usar outro componente que possui uma interface diferente. Sem o Adapter, você seria forçado a modificar o código existente para acomodar a nova interface, o que pode não ser possível ou prático em muitos casos.

### Solução

O padrão Adapter resolve esse problema criando uma classe intermediária entre o componente existente e o novo componente. Esta classe intermediária, chamada Adapter, implementa a interface esperada pelo componente existente e encapsula o novo componente, traduzindo suas operações para operações compatíveis com a interface esperada.

### Estrutura

O padrão Adapter é composto por três componentes principais:

1. **Cliente**: É o componente que interage com a interface alvo (Target).

2. **Target**: É a interface desejada pelo Cliente. É a interface que o Cliente espera usar.

3. **Adapter**: É a classe intermediária que conecta o Cliente ao Adaptee. Implementa a interface Target e encapsula o Adaptee.

4. **Adaptee**: É a interface existente, a ser adaptada para a interface Target. É o componente com a interface incompatível que precisa ser integrado ao sistema.

#### Exemplo

Vamos considerar um exemplo de um sistema de reprodução de mídia que possui um reprodutor de música que espera arquivos MP3, mas recebemos um novo reprodutor de música que reproduz arquivos em formato FLAC. Vamos usar o padrão Adapter para integrar o novo reprodutor de música ao sistema existente.

```java
// Target Interface (Interface Alvo)
interface MediaPlayer {
    void play(String fileName);
}

// Adaptee (Componente Existente)
class AdvancedMediaPlayer {
    void playFLAC(String fileName) {
        System.out.println("Playing FLAC file: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer player;

    MediaAdapter() {
        player = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String fileName) {
        if (fileName.endsWith(".flac")) {
            player.playFLAC(fileName);
        } else {
            System.out.println("Unsupported format");
        }
    }
}

// Client
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String fileName) {
        if (fileName.endsWith(".mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (fileName.endsWith(".flac")) {
            mediaAdapter = new MediaAdapter();
            mediaAdapter.play(fileName);
        } else {
            System.out.println("Unsupported format");
        }
    }
}

// Exemplo de Uso
public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();

        player.play("song.mp3");
        player.play("song.flac");
        player.play("song.wav"); // Formato não suportado
    }
}
```

Neste exemplo, `MediaPlayer` é a interface Target que nosso Cliente espera usar. `AdvancedMediaPlayer` é o Adaptee, o componente existente com interface incompatível. O `MediaAdapter` atua como o Adapter que traduz as chamadas da interface `MediaPlayer` para a interface `AdvancedMediaPlayer`.

#### Conclusão

O padrão Adapter é uma ferramenta poderosa para integrar componentes com interfaces incompatíveis. Ele permite que você reutilize componentes existentes em novos sistemas, sem precisar modificá-los. É útil em situações onde você precisa interagir com sistemas legados, bibliotecas de terceiros ou quando deseja reutilizar código existente em novos contextos.