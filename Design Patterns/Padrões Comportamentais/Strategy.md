# Padrão de Projeto Strategy

O padrão Strategy é um padrão de design comportamental que permite definir uma família de algoritmos, encapsulá-los e torná-los intercambiáveis. Ele permite que o algoritmo varie independentemente dos clientes que o utilizam. O Strategy promove a flexibilidade ao definir uma série de algoritmos, encapsulá-los em classes separadas e permitir que o cliente escolha o algoritmo desejado durante o tempo de execução.

### Vantagens

- **Flexibilidade**: O Strategy permite que o algoritmo seja alterado dinamicamente durante o tempo de execução, sem alterar a classe cliente.
- **Desacoplamento**: O padrão promove o desacoplamento entre a lógica do algoritmo e a classe cliente, facilitando a manutenção e a extensão do código.
- **Reutilização de código**: Os algoritmos podem ser reutilizados em diferentes contextos, promovendo a modularidade e a reutilização de código.

### Quando usar o padrão Strategy?

- Quando há necessidade de definir uma família de algoritmos relacionados e encapsulá-los em classes separadas.
- Quando diferentes variantes de um algoritmo precisam ser utilizadas e alternadas dinamicamente durante o tempo de execução.
- Quando é necessário evitar a poluição de uma classe com múltiplas condicionais relacionadas a diferentes algoritmos.

### Componentes do Padrão

- **Contexto (Context)**: Define a interface para interagir com a estratégia. Mantém uma referência para uma instância de uma estratégia concreta e encaminha as solicitações do cliente para a estratégia atual.
  
- **Estratégia (Strategy)**: Define a interface comum para todos os algoritmos suportados. Permite que as classes concretas forneçam implementações específicas para esses algoritmos.
  
- **Estratégia Concreta (Concrete Strategy)**: Implementa a interface de Estratégia para fornecer uma implementação específica de um algoritmo.

![Strategy](./images/strategy.png)

### Exemplo de Implementação

Suponha que temos uma classe `PaymentProcessor` que processa pagamentos de diferentes formas: cartão de crédito, PayPal e transferência bancária. Utilizando o padrão Strategy, podemos encapsular os diferentes algoritmos de processamento de pagamento em classes separadas.

### Exemplo de Código

```java
// Estratégia
interface PaymentStrategy {
    void processPayment(double amount);
}

// Estratégia Concreta: Pagamento com Cartão de Crédito
class CreditCardPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        // Lógica de processamento de pagamento com cartão de crédito
        System.out.println("Pagamento de " + amount + " realizado com cartão de crédito.");
    }
}

// Estratégia Concreta: Pagamento com PayPal
class PayPalPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        // Lógica de processamento de pagamento com PayPal
        System.out.println("Pagamento de " + amount + " realizado com PayPal.");
    }
}

// Estratégia Concreta: Pagamento por Transferência Bancária
class BankTransferPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        // Lógica de processamento de pagamento por transferência bancária
        System.out.println("Pagamento de " + amount + " realizado por transferência bancária.");
    }
}

// Contexto
class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.processPayment(amount);
        } else {
            System.out.println("Selecione um método de pagamento válido.");
        }
    }
}

// Cliente
public class Main {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // Define e executa a estratégia de pagamento com cartão de crédito
        paymentProcessor.setPaymentStrategy(new CreditCardPayment());
        paymentProcessor.processPayment(100.00);

        // Define e executa a estratégia de pagamento com PayPal
        paymentProcessor.setPaymentStrategy(new PayPalPayment());
        paymentProcessor.processPayment(50.00);

        // Define e executa a estratégia de pagamento por transferência bancária
        paymentProcessor.setPaymentStrategy(new BankTransferPayment());
        paymentProcessor.processPayment(200.00);
    }
}
```

### Saída Esperada

```
Pagamento de 100.0 realizado com cartão de crédito.
Pagamento de 50.0 realizado com PayPal.
Pagamento de 200.0 realizado por transferência bancária.
```

Neste exemplo, o padrão Strategy é utilizado para encapsular diferentes algoritmos de processamento de pagamento em classes separadas. O `PaymentProcessor` define uma interface para processar pagamentos e mantém uma referência para uma instância de uma estratégia concreta. Durante a execução, diferentes estratégias de pagamento são definidas dinamicamente e executadas, permitindo a flexibilidade na escolha do método de pagamento. Isso promove o desacoplamento, facilita a manutenção e a extensão do código, além de permitir a reutilização de diferentes algoritmos em diferentes contextos.