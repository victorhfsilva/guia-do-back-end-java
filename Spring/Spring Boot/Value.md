# Value

No Spring Boot, @Value é uma anotação usada para injetar propriedades ou valores externos em um bean do Spring. Os valores podem ser carregados de arquivos de propriedades, variáveis ​​de ambiente ou argumentos de linha de comando.

Aqui está um exemplo de uso de @Value para injetar propriedades em um bean do Spring:

```java
@Component
public class MyService {

    @Value("${my.property}")
    private String myProperty;

    public void doSomething() {
        System.out.println("O valor de my.property é: " + myProperty);
    }
}
```

No exemplo acima, o valor da propriedade my.property é injetado no campo myProperty do componente MyService.

O valor de my.property pode ser definido em um arquivo de propriedades externo (application.properties), por exemplo:

```
my.property=Olá, mundo!
```

A anotação @Value também pode ser usada para injetar valores de variáveis ​​de ambiente, por exemplo:

```java
@Value("${env.SOME_VAR}")
private String someVar;
```

Nesse caso, o valor da variável de ambiente SOME_VAR é injetado no campo someVar.