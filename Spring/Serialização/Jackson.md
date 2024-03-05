# Jackson

### **O que é o Jackson?**

- Jackson é uma biblioteca de código aberto em Java para processar formatos de dados, como JSON, XML, CSV e outros. É amplamente usado para mapear objetos Java para formatos serializados e vice-versa.

### **Configuração do Jackson:**

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.0</version>
</dependency>
```

### **Exemplo de Uso Básico:**

```java
// Convertendo um objeto Java para JSON
ObjectMapper objectMapper = new ObjectMapper();
String json = objectMapper.writeValueAsString(objetoJava);

// Convertendo JSON para um objeto Java
ObjetoJava objeto = objectMapper.readValue(json, ObjetoJava.class);
```

### **Mapeando Nomes de Propriedades:**

```java
public class ObjetoJava {
    @JsonProperty("nomeCompleto")
    private String nome;
    // ...
}
```

### **Ignorando Propriedades:**

```java
public class ObjetoJava {
    @JsonIgnore
    private String propriedadeParaIgnorar;
    // ...
}
```

### **Personalizando o Formato de Data:**

```java
public class ObjetoJava {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date data;
    // ...
}
```

### **Personalização com Serializers e Deserializers:**

```java
public class CustomSerializer extends JsonSerializer<MeuObjeto> {
    @Override
    public void serialize(MeuObjeto obj, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // Lógica personalizada para serialização
    }
}

public class CustomDeserializer extends JsonDeserializer<MeuObjeto> {
    @Override
    public MeuObjeto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // Lógica personalizada para desserialização
    }
}
```
