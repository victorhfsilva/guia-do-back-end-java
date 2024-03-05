# **Matchers no Mockito**

Os matchers no Mockito são usados para verificar argumentos de métodos em testes de unidade, permitindo flexibilidade na verificação de chamadas de métodos. 


### **Matchers Simples**

- `any(Class)` - Aceita qualquer argumento do tipo especificado.
- `eq(valor)` - Aceita um argumento que seja igual ao valor especificado.
- `isNull()` - Aceita um argumento nulo.
- `notNull()` - Aceita um argumento que não seja nulo.

```java
// Exemplos de uso de matchers simples
verify(objetoMock).metodo(any(String.class));
verify(objetoMock).metodo(eq(42));
verify(objetoMock).metodo(isNull());
verify(objetoMock).metodo(notNull());
```


### **Matchers Combinados**

- `and(matcher1, matcher2)` - Combina dois matchers para criar um que aceite se ambos forem verdadeiros.
- `or(matcher1, matcher2)` - Combina dois matchers para criar um que aceite se pelo menos um deles for verdadeiro.

```java
verify(objetoMock).metodo(or(eq("string"), eq("outra_string")));
```


### **Matchers Genéricos (Generics Matchers)**

Você pode usar matchers genéricos para aceitar qualquer argumento. Por exemplo, `any()` aceita qualquer argumento, e `anyString()` aceita qualquer string.

```java
// Exemplos de uso de matchers genéricos
verify(objetoMock).metodo(any());
verify(objetoMock).metodo(anyString());
```

### **Matchers em Mocks**

Ao criar mocks, você pode usar matchers para especificar comportamentos de métodos com base nos argumentos. Por exemplo:

```java
when(objetoMock.metodo(eq("argumento_esperado"))).thenReturn(resultadoEsperado);
```

### **Utilizando Matchers com `doNothing()`**

```java
doNothing().when(objetoMock).metodo(anyInt());
```