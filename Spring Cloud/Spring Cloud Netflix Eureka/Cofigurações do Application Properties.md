# Configurações do Application Properties

Configurar adequadamente o arquivo `application.properties` ou `application.yml` é crucial para gerenciar um serviço dentro do ecossistema Spring Cloud, especialmente ao utilizar o Netflix Eureka para descoberta de serviços. Este guia fornecerá uma visão detalhada sobre como configurar esses arquivos para otimizar a integração com o Eureka.

### Configuração do Servidor Eureka

Se você está configurando um **servidor Eureka**, as seguintes propriedades são essenciais:

```properties
# Porta em que o servidor Eureka será acessível
server.port=8761

# Indicar que o servidor não deve se registrar em si mesmo
eureka.client.registerWithEureka=false

# Indicar que o servidor não precisa buscar registros de outros servidores
eureka.client.fetchRegistry=false

# Desabilitar a preservação automática para evitar o modo de proteção em ambientes instáveis
eureka.server.enableSelfPreservation=false

# Endereço padrão que aponta para o próprio servidor (utilizado em configurações de cluster)
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

### Configuração do Cliente Eureka

Para uma aplicação que atua como **cliente Eureka**, você configurará propriedades para permitir que ela se registre com o servidor Eureka:

```properties
# Nome da aplicação, usado pelo Eureka para identificação
spring.application.name=nome-da-aplicacao

# Porta em que o cliente será acessível (pode ser dinâmica)
server.port=8080

# URL do servidor Eureka onde o cliente se registra
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/

# Habilitar o registro do cliente no servidor Eureka
eureka.client.registerWithEureka=true

# Habilitar a busca de registros para descobrir outros serviços
eureka.client.fetchRegistry=true

# Usar o endereço IP ao invés do nome do host ao se registrar
eureka.instance.preferIpAddress=true
```

### Configurações Avançadas

Além das configurações básicas, existem várias outras opções que podem ser úteis dependendo do seu ambiente e requisitos:

```properties
# Definir um período em segundos entre as renovações de registro do cliente ao servidor
eureka.instance.leaseRenewalIntervalInSeconds=10

# Definir a duração em segundos que o servidor espera sem receber uma renovação antes de remover o serviço do registro
eureka.instance.leaseExpirationDurationInSeconds=30

# Ativar/desativar a encriptação de tráfego entre clientes e servidor
eureka.server.enableSecurePort=true
eureka.instance.securePort=8443

# Definir metadados adicionais para a instância, como zona ou região em ambientes mais complexos
eureka.instance.metadataMap.zone=primary
eureka.instance.metadataMap.region=east

# Definir a estratégia de balanceamento de carga para o cliente
eureka.client.loadbalancer.cacheUpdateIntervalSeconds=15
```

### Considerações Finais

Ao ajustar seu `application.properties`, sempre leve em consideração o ambiente em que a aplicação está sendo executada: desenvolvimento, teste ou produção. Em ambientes de produção, considere utilizar `application-profile.properties` para definir configurações específicas para cada ambiente (por exemplo, `application-prod.properties` para produção), o que pode ser ativado usando perfis Spring (`spring.profiles.active=prod`).

Com estas informações, você deve ser capaz de configurar de forma eficaz suas aplicações que utilizam o Spring Cloud Netflix Eureka para descoberta de serviços.