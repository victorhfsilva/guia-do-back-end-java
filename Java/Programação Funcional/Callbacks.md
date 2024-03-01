# Callbacks em Java

## Introdução

Callbacks são uma técnica em programação onde uma função é passada como argumento para outra função. A função passada como callback será executada em um momento específico ou quando um evento ocorrer. Em Java, callbacks são frequentemente usados para implementar ações assíncronas, tratamento de eventos e extensibilidade.

## Exemplo Básico

```java
public class CallbackExample {

    // Definindo a interface de callback
    interface Callback {
        void execute();
    }

    // Função que recebe um callback como argumento e o executa
    static void performAction(Callback callback) {
        System.out.println("Performing some action...");
        callback.execute();
    }

    public static void main(String[] args) {
        // Criando um objeto de callback usando expressão lambda
        Callback callback = () -> System.out.println("Callback executed!");

        // Chamando a função performAction com o callback
        performAction(callback);
    }
}
```

## Callbacks em Programação Assíncrona

```java
public class AsyncCallbackExample {

    interface Callback {
        void onComplete(String result);
    }

    static void asyncOperation(Callback callback) {
        new Thread(() -> {
            // Simulating some time-consuming operation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            callback.onComplete("Async operation completed");
        }).start();
    }

    public static void main(String[] args) {
        asyncOperation(result -> System.out.println(result));
        System.out.println("Async operation started...");
    }
}
```

## Considerações Finais

Callbacks são uma maneira flexível de passar comportamento para funções em Java. Eles são frequentemente usados para implementar tratamento de eventos, programação assíncrona e extensibilidade em bibliotecas. No entanto, o uso excessivo de callbacks pode levar a código complicado e difícil de manter, então é importante usá-los com moderação e considerar outras abordagens, como interfaces funcionais e lambdas.