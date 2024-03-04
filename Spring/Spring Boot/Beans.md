# Spring Beans

## Introdução

No Spring Framework, um **bean** é um objeto gerenciado pelo contêiner Spring. Esses objetos são criados, configurados e gerenciados pelo Spring Container e podem representar qualquer classe Java.

## Declaração de Beans

### **1. Anotação `@Component`:**
   - A anotação `@Component` é usada para indicar que uma classe é um componente gerenciado pelo Spring.

   ```java
   @Component
   public class MyBean {
       // ...
   }
   ```

### **2. Anotação `@Service`, `@Repository`, `@Controller`:**
   - Estas anotações são especializações de `@Component` e são usadas para definir tipos específicos de **beans** (serviços, repositórios, controladores).

   ```java
   @Service
   public class MyService {
       // ...
   }
   ```

## Ciclo de Vida do Bean

### **1. Inicialização e Destruição:**
   - Use as anotações `@PostConstruct` e `@PreDestroy` para definir métodos de inicialização e destruição.

   ```java
   @Component
   public class MyBean {
       @PostConstruct
       public void init() {
           // Inicialização
       }

       @PreDestroy
       public void destroy() {
           // Destruição
       }
   }
   ```

## Escopo do Bean

### **1. Escopo Padrão:**
   - O escopo padrão é o singleton, o que significa que apenas uma instância do **bean** é criada e compartilhada.

   ```java
   @Component
   public class MySingletonBean {
       // ...
   }
   ```

### **2. Escopos Customizados:**
   - Você pode definir escopos personalizados usando a anotação `@Scope`.

   ```java
   @Component
   @Scope("prototype")
   public class MyPrototypeBean {
       // ...
   }
   ```

## @Bean

A anotação `@Bean` no Spring Framework é usada para marcar um método como produtor de um bean gerenciado pelo contêiner Spring. Isso significa que o método anotado com `@Bean` será responsável por criar e configurar um objeto, que será gerenciado e mantido pelo Spring Container.

Essa anotação é frequentemente usada em classes de configuração do Spring, onde você define como os beans devem ser criados e configurados. Aqui estão alguns casos de uso comuns da anotação `@Bean`:

1. **Definir Beans Personalizados:** Se você tem um objeto personalizado que deseja que o Spring Container gerencie, você pode criar um método anotado com `@Bean` para produzi-lo. Isso é útil quando não é possível usar outras anotações, como `@Component`, para marcar a classe.

   ```java
   @Configuration
   public class MyConfiguration {
       @Bean
       public MyBean myBean() {
           return new MyBean();
       }
   }
   ```

2. **Configuração de Terceiros:** Você pode usar `@Bean` para criar instâncias de classes de bibliotecas de terceiros e configurá-las para uso em sua aplicação.

   ```java
   @Configuration
   public class ThirdPartyConfig {
       @Bean
       public ThirdPartyLibraryClass thirdPartyBean() {
           ThirdPartyLibraryClass bean = new ThirdPartyLibraryClass();
           // Configuração personalizada
           return bean;
       }
   }
   ```

Em resumo, a anotação `@Bean` é uma parte essencial da configuração do Spring, permitindo que você defina e configure beans personalizados que serão gerenciados pelo contêiner Spring.