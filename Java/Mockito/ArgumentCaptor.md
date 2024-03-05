# **Captor no Mockito**

O Mockito é uma biblioteca amplamente usada para testes de unidades em Java, e o uso de captors é uma técnica útil para verificar argumentos passados para métodos mockados. Um captor captura os argumentos passados ​​a um método mockado para que você possa realizar verificações específicas nesses argumentos.

```java
// 1. Criação do Captor
ArgumentCaptor<TipoDoArgumento> argumentCaptor = ArgumentCaptor.forClass(TipoDoArgumento.class);

// 2. Execute o código que usa o captor
seuObjetoMock.metodoQueVoceEstaTestando(argumentCaptor.capture());

// 3. Realize verificações nos argumentos capturados
TipoDoArgumento argumentoCapturado = argumentCaptor.getValue();

TipoDoArgumento expectedArgumentoCapturado = new TipoDoArgumento(...);

// Você pode usar assert ou assertThat para verificar o argumento capturado
assertEquals(expectedArgumentoCapturado, argumentoCapturado);
```

Outra forma de criar o Captor é atráves da anotação `@Captor`:

```java
@Captor
ArgumentCaptor<TipoDoArgumento> argumentCaptor;
```

### Exemplo:

```java
// 1. Criação do captor
ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

// 2. Execute o código que usa o captor
List<String> mockedList = mock(List.class);
mockedList.add("Exemplo");

// 3. Capture o argumento e faça as verificações
verify(mockedList).add(argumentCaptor.capture());

String argumentoCapturado = argumentCaptor.getValue();
assertEquals("Exemplo", argumentoCapturado);
```
