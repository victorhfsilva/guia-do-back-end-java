# Criando Consumidores com Java

### Arquivo build.gradle

Adicione as dependências do Kafka e as configurações de compilação no arquivo `build.gradle`:

```gradle
dependencies {
    implementation 'org.apache.kafka:kafka-clients:3.8.0'
}
```

### Classe GreetingConsumer.java

Agora, crie a classe `GreetingConsumer.java` na estrutura de diretórios especificada:

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

## Explicação do Código

1. **Configurações do Consumidor**:
   - `bootstrap.servers`: Endereço do servidor Kafka.
   - `key.deserializer` e `value.deserializer`: Classes que serão usadas para desserializar as chaves e valores das mensagens.
   - `group.id`: Identificador do grupo de consumidores.

2. **Criação do Consumidor**:
   - A instância do consumidor é criada usando as propriedades definidas.

3. **Assinatura em Tópico**:
   - O consumidor se inscreve no tópico "GREETING" usando `consumer.subscribe`.

4. **Consumo de Mensagens**:
   - O método `consumer.poll` é chamado com timeout de 1s em um loop para buscar mensagens do tópico.
   - As mensagens recebidas são processadas e os detalhes são logados usando `logger`.
