# Consumer Groups no Kafka

## O que são Consumer Groups?

Consumer Groups (grupos de consumidores) são conjuntos de consumidores que trabalham juntos para consumir mensagens de um ou mais tópicos de forma cooperativa. Cada grupo de consumidores tem um identificador de grupo único (`group.id`). Dentro de um grupo, cada partição de um tópico é consumida por apenas um consumidor, garantindo que mensagens de uma mesma partição não sejam processadas por múltiplos consumidores simultaneamente.

### Benefícios dos Consumer Groups

1. **Escalabilidade**: Permite que várias instâncias de consumidores processem mensagens em paralelo, distribuindo a carga de trabalho.
2. **Tolerância a Falhas**: Se um consumidor em um grupo falhar, o Kafka redistribui automaticamente as partições atribuídas a ele entre os outros consumidores do grupo, garantindo a continuidade do processamento.
3. **Balanceamento de Carga**: As mensagens são balanceadas entre os consumidores disponíveis no grupo, otimizando a utilização dos recursos.

## Configuração de um Consumer Group

Para configurar um consumidor como parte de um Consumer Group, você precisa definir o identificador do grupo (`group.id`) nas propriedades de configuração do consumidor.

### Exemplo de Configuração de um Consumer Group

```java
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class GreetingConsumer {

    public static void main(String[] args) {
        var consumer = new KafkaConsumer<String, String>(properties());
        while(true) {
            consumer.subscribe(Collections.singletonList("GREETING"));
            var records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                System.out.println(records.count() + " records found.");
                for (var record: records){
                    System.out.println("Topic: " + record.topic() + " - Key: " + record.key() + " - Value: " + record.value());
                }
            }
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GreetingConsumer.class.getSimpleName());
        return properties;
    }
}
```

### Explicação do Código

1. **Configurações do Consumidor**:
   - `bootstrap.servers`: Endereço do servidor Kafka.
   - `key.deserializer` e `value.deserializer`: Classes que serão usadas para desserializar as chaves e valores das mensagens.
   - `group.id`: Identificador do grupo de consumidores.

2. **Criação do Consumidor**:
   - A instância do consumidor é criada usando as propriedades definidas.

3. **Assinatura em Tópico**:
   - O consumidor se inscreve no tópico "GREETING" usando `consumer.subscribe`.

4. **Consumo de Mensagens**:
   - O método `consumer.poll` é chamado em um loop para buscar mensagens do tópico.
   - As mensagens recebidas são processadas e os detalhes são logados usando `logger`.

## Rebalanceamento de Partições

Quando novos consumidores se juntam a um grupo ou quando consumidores existentes saem, o Kafka redistribui as partições entre os consumidores disponíveis. Esse processo é conhecido como rebalanceamento. O rebalanceamento garante que todas as partições sejam atendidas, mesmo que a composição do grupo de consumidores mude.

### Exemplo de Rebalanceamento

Se um grupo de consumidores consiste inicialmente em duas instâncias de consumidores consumindo de um tópico com quatro partições, cada consumidor pode ser responsável por duas partições. Se um terceiro consumidor se juntar ao grupo, o Kafka redistribui as partições entre os três consumidores, possivelmente atribuindo uma partição a cada consumidor e a quarta partição ao terceiro consumidor.
