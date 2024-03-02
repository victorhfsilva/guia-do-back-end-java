# Padrão de Projeto Facade

O padrão Facade é um padrão de design estrutural que fornece uma interface unificada para um conjunto de interfaces em um subsistema. Ele encapsula um conjunto de interfaces complexas em uma interface de nível mais alto, facilitando o uso e ocultando a complexidade do subsistema para os clientes.

### Vantagens

- Fornece uma interface simplificada para interagir com um subsistema complexo.
- Abstrai detalhes de implementação e oculta a complexidade do subsistema.
- Promove o princípio de design de menor conhecimento, mantendo os clientes desacoplados das classes do subsistema.

### Quando usar o padrão Facade?

- Quando você precisa fornecer uma interface simples para um subsistema complexo.
- Quando você deseja ocultar a complexidade e detalhes de implementação do subsistema dos clientes.
- Quando você quer desacoplar os clientes do subsistema, reduzindo o acoplamento entre componentes.

### Subsistema

- Consiste em várias classes que implementam a funcionalidade específica do subsistema.
- Pode ser complexo e composto por várias classes inter-relacionadas.

**Exemplo**: Classes que compõem o subsistema de um sistema de compras online.

```java
class Inventory {
    public boolean isProductAvailable(String productId, int quantity) { ... }
    public double getProductPrice(String productId) { ... }
    public void reduceStock(String productId, int quantity) { ... }
}

class PaymentGateway {
    public boolean makePayment(String paymentMethod, double amount) { ... }
}

class ShippingService {
    public void shipProduct(String productId, int quantity, String shippingAddress) { ... }
}
```

### Componente Facade

- Fornece uma interface unificada para um subsistema complexo.
- Encapsula a lógica de interação com os componentes individuais do subsistema.
- Pode simplificar operações complexas do subsistema em operações mais simples para os clientes.

**Exemplo**: Facade para um sistema de compras online.

```java
// Facade
class OnlineShoppingFacade {
    private Inventory inventory;
    private PaymentGateway paymentGateway;
    private ShippingService shippingService;

    public OnlineShoppingFacade() {
        inventory = new Inventory();
        paymentGateway = new PaymentGateway();
        shippingService = new ShippingService();
    }

    public void purchaseProduct(String productId, int quantity, String paymentMethod, String shippingAddress) {
        if (inventory.isProductAvailable(productId, quantity)) {
            boolean paymentStatus = paymentGateway.makePayment(paymentMethod, quantity * inventory.getProductPrice(productId));
            if (paymentStatus) {
                shippingService.shipProduct(productId, quantity, shippingAddress);
                inventory.reduceStock(productId, quantity);
                System.out.println("Purchase successful!");
            } else {
                System.out.println("Payment failed!");
            }
        } else {
            System.out.println("Product not available in required quantity!");
        }
    }
}
```

### Exemplo de Uso

```java
public class Main {
    public static void main(String[] args) {
        OnlineShoppingFacade facade = new OnlineShoppingFacade();
        facade.purchaseProduct("ABC123", 2, "CreditCard", "123 Street, City");
    }
}
```

### Saída Esperada

```
Purchase successful!
```

Neste exemplo, o padrão Facade é usado para simplificar a interação com um subsistema complexo de compras online. O `OnlineShoppingFacade` fornece uma interface unificada para realizar uma compra, encapsulando a lógica de interação com o estoque, o gateway de pagamento e o serviço de envio. Isso permite que os clientes realizem uma compra sem precisar conhecer os detalhes internos de como a compra é processada.