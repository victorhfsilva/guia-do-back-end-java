# Configuração de Segurança do Servidor

Configurar a segurança do Eureka Server é fundamental para proteger as informações sobre o registro e a descoberta de serviços em um ambiente de microserviços. Vou guiá-lo através das etapas para configurar a proteção de acesso ao Eureka.

### Habilitar a Autenticação Básica no Eureka Server

A autenticação básica é uma maneira simples, porém eficaz, de controlar o acesso ao servidor Eureka. Vamos configurar o Spring Security para adicionar autenticação básica.

#### Adicionar Dependências

Primeiro, você precisa adicionar a dependência do Spring Security ao seu projeto. Para projetos Maven, adicione o seguinte no seu `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Para projetos Gradle, adicione isso no seu `build.gradle`:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-security'
```

#### Configurar o Spring Security

Após adicionar a dependência, crie uma classe de configuração do Spring Security. Aqui está um exemplo básico de configuração:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
```

Os usuários podem ser definidos em memória ou em um banco de dados. Neste exemplo definimos o usuário padrão no application.properties:

```
spring.security.user.name=admin
spring.security.user.password=Senha@123
```

Para acessar o servidor nos clientes basta definir o usuário e a senha codificados na URL do servidor:

```
eureka.client.serviceUrl.defaultZone=http://admin:Senha%40123@localhost:8081/eureka/
```