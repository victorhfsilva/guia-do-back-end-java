# Keys e Partitions no Kafka

## Partitions no Kafka

### O que são Partitions?

Partitions (partições) são unidades de paralelismo no Kafka. Cada tópico é dividido em múltiplas partições, e cada partição é uma sequência ordenada e imutável de mensagens que é constantemente adicionada.

### Uso de Partitions

1. **Paralelismo**: Permite que múltiplos consumidores leiam de diferentes partições em paralelo, aumentando o throughput do sistema.
2. **Distribuição de Carga**: Mensagens são distribuídas entre várias partições, o que ajuda a equilibrar a carga de trabalho entre os brokers do Kafka.

### Configuração de Partitions

Ao criar um tópico, você pode especificar o número de partições. Mais partições aumentam o paralelismo, mas também podem aumentar a complexidade do sistema.

#### Criar um Tópico com Partições

##### Usando Instalação Tradicional:

```sh
bin/kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

##### Usando Docker:

```sh
docker-compose exec kafka kafka-topics --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

## Keys no Kafka

### O que são Keys?

Keys (chaves) são usadas no Kafka para garantir a ordem das mensagens dentro de uma partição específica. Uma key é um identificador que pode ser associado a uma mensagem para determinar a partição para onde essa mensagem será enviada.

### Uso de Keys

1. **Garantia de Ordem**: Mensagens com a mesma key serão sempre enviadas para a mesma partição, garantindo que a ordem das mensagens seja mantida dentro dessa partição.
2. **Particionamento Personalizado**: As keys permitem que os produtores enviem mensagens para partições específicas, o que pode ser útil para distribuir carga de trabalho ou agrupar mensagens relacionadas.

### Exemplo de Uso de Keys

Vamos considerar um exemplo onde queremos enviar mensagens para um tópico específico usando uma key.

```java
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KeyedProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String, String> producer = new KafkaProducer<>(properties);

        String key = "user123";
        String value = "User data";
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", key, value);

        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Sent message with key: " + key + " to partition: " + metadata.partition());
            } else {
                exception.printStackTrace();
            }
        });

        producer.close();
    }
}
```

Neste exemplo, a mensagem com a key `user123` será sempre enviada para a mesma partição do tópico `my-topic`, garantindo a ordem das mensagens.

