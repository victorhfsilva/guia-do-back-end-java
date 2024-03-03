# Padrão de Projeto Command

O padrão Command é um padrão de design comportamental que encapsula uma solicitação como um objeto, permitindo parametrizar clientes com operações, fazer fila ou registrar solicitações e suportar operações que podem ser desfeitas. Ele é útil quando é necessário desacoplar o objeto que faz a solicitação do objeto que a executa, permitindo que você parametrize clientes com diferentes solicitações, filtre ou registre solicitações e implemente operações que podem ser desfeitas.

### Vantagens

- Desacoplamento: o padrão Command desacopla o objeto que faz a solicitação do objeto que a executa, tornando o código mais flexível e extensível.
- Parametrização: permite parametrizar clientes com diferentes solicitações, facilitando a reutilização de código.
- Filas e Registro: facilita a implementação de filas de solicitações e registro de operações, permitindo desfazer e refazer ações.

### Quando usar o padrão Command?

- Quando você deseja desacoplar o objeto que faz a solicitação do objeto que a executa.
- Quando você precisa parametrizar clientes com diferentes solicitações e filas de solicitações.
- Quando você quer implementar operações que podem ser desfeitas e refazidas de forma eficiente.


### Recebedor

- Contém a lógica real para executar a solicitação.
- Pode ser qualquer classe que implementa a funcionalidade desejada.

**Exemplo**: Implementação da classe Editor que executa operações de edição de texto.

```java
// Editor
class Editor {
    String text;

    public String getSelection() {
        // Retorna o texto selecionado.
        return "";
    }

    public void deleteSelection() {
        // Exclui o texto selecionado.
    }

    public void replaceSelection(String text) {
        // Insere o conteúdo na seleção atual.
    }
}
```

### Command

- Define uma interface para executar uma solicitação.
- Geralmente inclui métodos para executar e desfazer a solicitação.

**Exemplo**: Interface para comandos que executam solicitações.

```java
abstract class Command {
    protected Application app;
    protected Editor editor;
    protected String backup;

    public Command(Application app, Editor editor) {
        this.app = app;
        this.editor = editor;
    }

    // Faz um backup do estado do editor.
    public void saveBackup() {
        backup = editor.text;
    }

    // Restaura o estado do editor.
    public void undo() {
        editor.text = backup;
    }

    // O método de execução é declarado como abstrato para forçar
    // todos os comandos concretos a fornecerem suas próprias
    // implementações. O método deve retornar true ou false
    // dependendo se o comando altera ou não o estado do editor.
    public abstract boolean execute();
}
```

### Comandos Concretos

- Implementa a interface Command para realizar uma solicitação específica.
- Contém referências para os objetos que executam a operação.

**Exemplo**: Implementação de comandos que executam operações específicas.

```java
class CopyCommand extends Command {
    // O comando de cópia não é salvo no histórico, pois não
    // altera o estado do editor.
    public boolean execute() {
        app.clipboard = editor.getSelection();
        return false;
    }
}

class CutCommand extends Command {
    // O comando de corte altera o estado do editor, portanto
    // deve ser salvo no histórico e será salvo enquanto o método
    // retornar true.
    public boolean execute() {
        saveBackup();
        app.clipboard = editor.getSelection();
        editor.deleteSelection();
        return true;
    }
}

class PasteCommand extends Command {
    public boolean execute() {
        saveBackup();
        editor.replaceSelection(app.clipboard);
        return true;
    }
}

// A operação de desfazer também é um comando.
class UndoCommand extends Command {
    public boolean execute() {
        app.undo();
        return false;
    }
}
```

### Invocador

- Invoca o comando para executar uma solicitação.
- Pode manter uma lista de comandos executados para suportar operações de desfazer/refazer.

**Exemplo**: Implementação da classe Application que invoca comandos para executar solicitações.

```java
// A classe de aplicativo configura as relações entre objetos. Ela
// age como um remetente: quando algo precisa ser feito, ela cria
// um objeto de comando e o executa.
class Application {
    String clipboard;
    List<Editor> editors;
    Editor activeEditor;
    CommandHistory history;

    // O código que atribui comandos a objetos de interface do
    // usuário pode parecer assim.
    public void createUI() {
        // ...
        Runnable copy = () -> executeCommand(new CopyCommand(this, activeEditor));
        copyButton.setCommand(copy);
        shortcuts.onKeyPress("Ctrl+C", copy);

        Runnable cut = () -> executeCommand(new CutCommand(this, activeEditor));
        cutButton.setCommand(cut);
        shortcuts.onKeyPress("Ctrl+X", cut);

        Runnable paste = () -> executeCommand(new PasteCommand(this, activeEditor));
        pasteButton.setCommand(paste);
        shortcuts.onKeyPress("Ctrl+V", paste);

        Runnable undo = () -> executeCommand(new UndoCommand(this, activeEditor));
        undoButton.setCommand(undo);
        shortcuts.onKeyPress("Ctrl+Z", undo);
    }

    // Executa um comando e verifica se ele deve ser adicionado ao
    // histórico.
    public void executeCommand(Command command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    // Pega o comando mais recente do histórico e executa seu
    // método undo. Observe que não conhecemos a classe desse
    // comando. Mas não precisamos, pois o comando sabe como
    // desfazer sua própria ação.
    public void undo() {
        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        Editor editor = new Editor();

        // Cria e executa comandos para executar solicitações.
        Command copyCommand = new CopyCommand(app, editor);
        app.executeCommand(copyCommand);

        Command pasteCommand = new PasteCommand(app, editor);
        app.executeCommand(pasteCommand);

        // Desfaz a última operação.
        app.undo();
    }
}
```

### Saída Esperada

```
// A operação de cópia e colagem é executada.
// A última operação é desfeita com sucesso.
```

Neste exemplo, o padrão Command é utilizado para encapsular solicitações como objetos. A classe `Application` age como um invocador, criando e executando comandos para executar operações no objeto `Editor`. Os comandos encapsulam as operações específicas a serem realizadas, como cópia e colagem de texto. O padrão Command desacopla o cliente que invoca as solicitações do objeto que as executa, permitindo flexibilidade e suporte para operações de desfazer/refazer.

### Exemplo

```java
// A classe de comando base define a interface comum para todos os
// comandos concretos.
abstract class Command {
    protected Application app;
    protected Editor editor;
    protected String backup;

    public Command(Application app, Editor editor) {
        this.app = app;
        this.editor = editor;
    }

    // Faz um backup do estado do editor.
    public void saveBackup() {
        backup = editor.text;
    }

    // Restaura o estado do editor.
    public void undo() {
        editor.text = backup;
    }

    // O método de execução é declarado como abstrato para forçar
    // todos os comandos concretos a fornecerem suas próprias
    // implementações. O método deve retornar true ou false
    // dependendo se o comando altera ou não o estado do editor.
    public abstract boolean execute();
}

// Os comandos concretos vão aqui.
class CopyCommand extends Command {
    // O comando de cópia não é salvo no histórico, pois não
    // altera o estado do editor.
    public boolean execute() {
        app.clipboard = editor.getSelection();
        return false;
    }
}

class CutCommand extends Command {
    // O comando de corte altera o estado do editor, portanto
    // deve ser salvo no histórico e será salvo enquanto o método
    // retornar true.
    public boolean execute() {
        saveBackup();
        app.clipboard = editor.getSelection();
        editor.deleteSelection();
        return true;
    }
}

class PasteCommand extends Command {
    public boolean execute() {
        saveBackup();
        editor.replaceSelection(app.clipboard);
        return true;
    }
}

// A operação de desfazer também é um comando.
class UndoCommand extends Command {
    public boolean execute() {
        app.undo();
        return false;
    }
}

// O histórico global de comandos é apenas uma pilha.
class CommandHistory {
    private List<Command> history;

    // Último a entrar...
    public void push(Command c) {
        // Adiciona o comando ao final da lista de histórico.
    }

    // ...primeiro a sair.
    public Command pop() {
        // Obtém o comando mais recente do histórico.
        return null; // Retorna null temporariamente.
    }
}

// A classe do editor tem operações reais de edição de texto. Ela
// desempenha o papel de receptor: todos os comandos acabam
// delegando a execução para os métodos do editor.
class Editor {
    String text;

    public String getSelection() {
        // Retorna o texto selecionado.
        return "";
    }

    public void deleteSelection() {
        // Exclui o texto selecionado.
    }

    public void replaceSelection(String text) {
        // Insere o conteúdo da área de transferência na posição atual.
    }
}

// A classe de aplicativo configura as relações entre objetos. Ela
// age como um remetente: quando algo precisa ser feito, ela cria
// um objeto de comando e o executa.
class Application {
    String clipboard;
    List<Editor> editors;
    Editor activeEditor;
    CommandHistory history;

    // O código que atribui comandos a objetos de interface do
    // usuário pode parecer assim.
    public void createUI() {
        // ...
        Runnable copy = () -> executeCommand(new CopyCommand(this, activeEditor));
        copyButton.setCommand(copy);
        shortcuts.onKeyPress("Ctrl+C", copy);

        Runnable cut = () -> executeCommand(new CutCommand(this, activeEditor));
        cutButton.setCommand(cut);
        shortcuts.onKeyPress("Ctrl+X", cut);

        Runnable paste = () -> executeCommand(new PasteCommand(this, activeEditor));
        pasteButton.setCommand(paste);
        shortcuts.onKeyPress("Ctrl+V", paste);

        Runnable undo = () -> executeCommand(new UndoCommand(this, activeEditor));
        undoButton.setCommand(undo);
        shortcuts.onKeyPress("Ctrl+Z", undo);
    }

    // Executa um comando e verifica se ele deve ser adicionado ao
    // histórico.
    public void executeCommand(Command command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    // Pega o comando mais recente do histórico e executa seu
    // método undo. Observe que não conhecemos a classe desse
    // comando. Mas não precisamos, pois o comando sabe como
    // desfazer sua própria ação.
    public void undo() {
        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}
```