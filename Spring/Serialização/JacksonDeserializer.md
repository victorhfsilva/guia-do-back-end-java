# Jackson Deserializer

**O que é um Jackson Deserializer?**

- Um Jackson Deserializer é uma classe personalizada que define como um formato serializado, como JSON, deve ser convertido de volta em um objeto Java durante o processo de desserialização.

**Quando Usar um Jackson Deserializer:**

- Use um Deserializer personalizado quando você precisar controlar a desserialização de um formato serializado para objetos Java. Isso é útil quando você tem requisitos específicos para converter JSON, XML ou outros formatos em objetos personalizados.

### **Exemplo de Jackson Deserializer Simples:**

```java
public class MyObjectDeserializer extends JsonDeserializer<MyObject> {
    @Override
    public MyObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String customProperty = jsonParser.getValueAsString("customProperty");
        // Extraia mais campos personalizados e crie um objeto MyObject
        return new MyObject(customProperty);
    }
}
```

### **Registrar um Deserializer Personalizado:**

```java
SimpleModule module = new SimpleModule();
module.addDeserializer(MyObject.class, new MyObjectDeserializer());

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(module);

// Agora, o ObjectMapper usará o Deserializer personalizado para converter JSON em objetos MyObject.
```
