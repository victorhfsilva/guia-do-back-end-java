# **Espionando Objetos com o Mockito**

O Mockito permite espiar objetos reais para verificar chamadas a métodos e acessar o comportamento real dos mesmos. Aqui está um guia rápido sobre como espionar objetos usando o Mockito:


### **Crie um Espião**

Para espionar um objeto real, use o método `spy()` do Mockito:

```java
SeuObjetoReal objetoReal = new SeuObjetoReal();
SeuObjetoReal objetoEspiao = Mockito.spy(objetoReal);
```


### **Anotações (Opcional)**

Se você estiver usando testes JUnit, pode usar as anotações `@Spy` para criar objetos espiões e `@InjectMocks` para injetá-los em classes de teste:

```java
@Spy
SeuObjetoReal objetoEspiao;

@InjectMocks
ClasseDeTeste classeDeTeste;
```


### **Acesse o Comportamento Real**

Você pode acessar o comportamento real do objeto espião, desde que não seja configurado de forma diferente. Por exemplo, chamando métodos reais:

```java
// Suponhamos que o método real faça algo útil
String resultadoReal = objetoEspiao.metodoReal();

// Você pode usar o resultado real em seus testes
```


### **Configuração Personalizada (Opcional)**

Se você desejar substituir o comportamento de métodos específicos do objeto espião, pode fazê-lo usando o `when...thenReturn` do Mockito:

```java
when(objetoEspiao.metodoEspecifico()).thenReturn(valorPersonalizado);

// Agora, quando você chamar o método, ele retornará o valor personalizado
```


### **Verificando Chamadas a Métodos**

Você pode verificar se métodos reais ou configurados foram chamados no objeto espião:

```java
// Verifica se o método real foi chamado
verify(objetoEspiao).metodoReal();

// Verifica se o método com configuração personalizada foi chamado
verify(objetoEspiao).metodoEspecifico();
```


### **Matchers de Argumentos (Opcional)**

Você pode usar matchers de argumentos para verificar chamadas a métodos com argumentos específicos:

```java
when(objetoEspiao.metodoComArgumentos(eq("valor"), anyInt())).thenReturn(valorPersonalizado);

// Verifica se o método foi chamado com argumentos específicos
verify(objetoEspiao).metodoComArgumentos("valor", 42);
```


### **Reiniciando o Objeto Espião (Opcional)**

Se você desejar redefinir as configurações no objeto espião, pode usar o `reset`:

```java
reset(objetoEspiao);
```


### **Conclusão**

Espiar objetos com o Mockito é uma técnica útil para testar o comportamento de objetos reais e verificar chamadas a métodos. Lembre-se de que o uso excessivo de espionagem pode tornar seus testes complexos, portanto, use-a com moderação e concentre-se nos cenários de teste mais relevantes.