# Configuração do Eureka Server

Configurar um servidor Eureka com Gradle em vez de Maven e utilizando o arquivo `application.properties` para configurações é uma excelente abordagem para muitos desenvolvedores Java.


### Configuração do Servidor Eureka

Abra o arquivo `build.gradle` e garanta que as dependências estejam corretamente configuradas:

```gradle

dependencies {
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

Configure o arquivo `application.properties` com as propriedades necessárias para o servidor Eureka:

```
# Define a porta em que o servidor Eureka irá rodar.
server.port=8761

# Indica que este servidor não precisa se registrar em outro servidor Eureka como um cliente.
eureka.client.registerWithEureka=false

# Indica que este servidor não tentará buscar o registro de serviços de outro servidor Eureka.
eureka.client.fetchRegistry=false

# Desativa a preservação automática do Eureka Server. Isso impede que o Eureka entre em modo de proteção
# quando a conectividade de rede é instável e não está recebendo tantos heartbeats dos clientes.
eureka.server.enableSelfPreservation=false

# Define a URL base para onde o servidor Eureka enviará os registros e heartbeats.
# É crucial em ambientes com múltiplos servidores Eureka para sincronização de registro.
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

Essas configurações são típicas para um servidor Eureka que está operando como o registro central em um ambiente onde ele é o único ponto de registro e descoberta de serviços, sem a necessidade de se comunicar com outros servidores Eureka. A configuração enableSelfPreservation=false é útil durante o desenvolvimento ou em testes para evitar comportamentos indesejados devido à falta de conectividade ou atrasos nos heartbeats, mas deve ser usada com cautela em ambientes de produção.

No contexto de múltiplos servidores Eureka, a defaultZone configura a URL principal que os clientes Eureka e outros servidores Eureka usarão para se comunicar com este servidor. Essa configuração é especialmente útil para configurar um sistema de failover ou para balanceamento de carga entre servidores Eureka. No exemplo acima, a URL está apontando para localhost, mas em um ambiente de produção, você substituiria localhost pelo endereço IP ou nome do domínio do servidor Eureka correspondente.

### Habilitar o Servidor Eureka

No arquivo principal da aplicação Java, adicione a anotação `@EnableEurekaServer`:

```java
@SpringBootApplication
@EnableEurekaServer
public class ServidorEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServidorEurekaApplication.class, args);
    }
}
```

### Considerações Finais

Este servidor Eureka configurado é adequado para desenvolvimento e teste. Para ambientes de produção, você deve considerar configurações de segurança, alta disponibilidade e monitoramento avançado.