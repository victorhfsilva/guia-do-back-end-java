# Configuração do Kafka usando Spring for Apache Kafka

## Introdução

Spring for Apache Kafka é um projeto que facilita a integração do Apache Kafka com aplicações Spring, simplificando a configuração e o uso do Kafka. 

### Configuração do Kafka para Producers

Crie uma classe de configuração para definir os beans necessários para o Kafka Producer.

```java
public class Message {
    private String message;

    // Getters e setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
```

```java
package com.example;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
```

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;

public class JacksonSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper;

    public JacksonSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public byte[] serialize(String s, T t) {
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing object", e);
        }
    }
}
```

Lembre-se de adicionar as dependências do jackson no build.gradle:

```java
dependencies {
    // demais dependências...
    
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2'
}
```

### Configuração do Kafka para Consumers

Crie uma classe de configuração para definir os beans necessários para o Kafka Consumer.

```java
package com.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, Message> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonDeserializer.class));
        configProps.put("value.deserializer.type", Message.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
```

```java
public class JacksonDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper;
    private Class<T> tClass;

    public JacksonDeserializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        tClass = (Class<T>) configs.get("value.deserializer.type");
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            return objectMapper.readValue(data, tClass);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON", e);
        }
    }
}
```

Lembre-se de adicionar as dependências do jackson no build.gradle:

```java
dependencies {
    // demais dependências...
    
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2'
}
```


### Produtor Kafka (KafkaProducer.java)

Crie uma classe para enviar mensagens para o Kafka.

```java
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "test_topic";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
```

### Consumidor Kafka (KafkaConsumer.java)

Crie uma classe para consumir mensagens do Kafka.

```java
package com.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test_topic", groupId = "my-group-id")
    public void listen(Message message) {
        System.out.println("Received message: " + message.getMessage());
    }
}
```

### Exemplo de Envio de Mensagem

No seu aplicativo Spring Boot, você pode enviar uma mensagem usando o `KafkaProducer`.

```java
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaExampleApplication implements CommandLineRunner {

    @Autowired
    private KafkaProducer kafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Message message = new Message();
        message.setMessage("Hello, Kafka!");
        kafkaProducer.sendMessage(message);
    }
}
```