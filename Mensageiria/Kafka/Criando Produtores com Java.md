# Criando Produtores com Java 


### Arquivo build.gradle

Adicione as dependências do Kafka no arquivo `build.gradle`:

```gradle
dependencies {
    implementation 'org.apache.kafka:kafka-clients:3.8.0'
}
```

### Classe GreetingProducer.java

Agora, crie a classe `GreetingProducer.java` na estrutura de diretórios especificada:

```java
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GreetingProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var producer = new KafkaProducer<String, String>(properties());
        var value = "Hello World";
        var record = new ProducerRecord<String, String>("GREETING", value, value);
        producer.send(record, (data, ex) -> {
            if(ex != null){
                ex.printStackTrace();
                return;
            }
            System.out.println("Topic: " + data.topic() + " - Partition: " + data.partition() + " - Offset: " + data.offset() + " - Timestamp: " + data.timestamp());
        }).get();

        producer.close();
    }

    private static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
```

## Explicação do Código

1. **Configurações do Produtor**:
   - `bootstrap.servers`: Endereço do servidor Kafka.
   - `key.serializer` e `value.serializer`: Classes que serão usadas para serializar as chaves e valores das mensagens.

2. **Criação do Produtor**:
   - A instância do produtor é criada usando as propriedades definidas.

3. **Envio de Mensagens**:
   - Uma mensagem com o valor "Hello World" é enviada para o tópico "GREETING".
   - A mensagem é enviada de forma assíncrona com um callback para tratar o resultado do envio.
   - Informações sobre o tópico, partição, offset e timestamp da mensagem são exibidas após o envio.

4. **Fechamento do Produtor**:
   - O método `producer.close()` é chamado para liberar os recursos do produtor após o envio das mensagens.
