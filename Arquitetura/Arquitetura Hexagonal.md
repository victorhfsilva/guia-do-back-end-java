# Arquitetura Hexagonal

A Arquitetura Hexagonal, também conhecida como Ports and Adapters, é uma metodologia de design de software que promove o isolamento da lógica de negócios (o domínio da aplicação) de influências externas, como bancos de dados, interfaces de usuário ou serviços externos. Proposta por Alistair Cockburn, essa arquitetura facilita a criação de sistemas flexíveis e facilmente testáveis.

## Componentes da Arquitetura Hexagonal

A arquitetura é dividida em várias partes, enfocando a separação entre a lógica interna do software e o mundo exterior:

1. **Domínio**: Contém a lógica e os dados de negócios centrais da aplicação.
2. **Ports**: São interfaces que definem os pontos de entrada ou saída do sistema. Existem dois tipos de ports:
   - **Primary (ou Driving)**: Interfaces através das quais o mundo externo pode acessar o domínio (como interfaces de usuário ou API REST).
   - **Secondary (ou Driven)**: Interfaces que o domínio usa para acessar recursos externos (como bancos de dados ou serviços externos).
3. **Adapters**: Implementações concretas dos ports que adaptam a comunicação entre o domínio e os agentes externos. Cada adapter é específico para uma fonte externa ou tipo de interface de usuário.

![Hexagonal Architecture](./imagens/hexagonal_architecture.png)

## Exemplo de Distribuição de Pacotes

A estrutura de pacotes em um projeto Java que utiliza a Arquitetura Hexagonal poderia ser organizada da seguinte forma:

### Estrutura de Diretórios

```
src/
└── main/
    ├── java/
    │   └── com/
    │       └── minhaempresa/
    │           └── minhaaplicacao/
    │               ├── core/
    │               │   ├── domain/
    │               │   │   ├── models/
    │               │   │   │   └── User.java
    │               │   │   ├── ports/
    │               │   │   │   ├── UserRepository.java
    │               │   │   │   └── UserService.java
    │               │   │   └── services/
    │               │   │       └── UserServiceImpl.java
    │               │   └── application/
    │               │       ├── ports/
    │               │       │   └── UserWeb.java
    │               │       └── services/
    │               │           └── UserWebImpl.java
    │               ├── infrastructure/
    │               │   ├── web/
    │               │   │   ├── UserController.java
    │               │   │   └── UserDTO.java
    │               │   ├── persistence/
    │               │   │   ├── UserRepositoryImpl.java
    │               │   │   └── DatabaseConfig.java
    │               └── configuration/
    │                   └── ApplicationConfig.java
    ├── resources/
    │   └── application.properties
    └── webapp/
        └── WEB-INF/
            └── web.xml
```

### Descrição dos Componentes

#### Core (Domínio)
- **`models/User.java`**: Entidade que representa o usuário.
- **`ports/UserRepository.java` e `UserService.java`**: Interfaces do port para operações relacionadas a usuários.
- **`services/UserServiceImpl.java`**: Implementação da lógica de negócios.

#### Application (Adapters de Entrada)
- **`ports/UserWeb.java`**: Interface do port de entrada para operações web.
- **`services/UserWebImpl.java`**: Implementação concreta das operações da web.

#### Infrastructure (Adapters de Saída)
- **`web/UserController.java`**: Controlador que lida com as requisições HTTP.
- **`persistence/UserRepositoryImpl.java`**: Implementação do repositório que lida com a persistência dos dados.

#### Configuration
- **`ApplicationConfig.java`**: Configurações centrais da aplicação.

## Benefícios da Arquitetura Hexagonal

1. **Isolamento da Lógica de Negócios**: O domínio da aplicação é mantido puro e isolado de fatores externos.
2. **Facilidade de Teste**: Facilidade de testar a lógica de negócios sem a necessidade de interações com elementos externos, como banco de dados ou web.
3. **Flexibilidade de Implementação**: Adaptadores permitem a troca de componentes de infraestrutura sem impactar o

 núcleo da aplicação.
4. **Fácil Adaptação a Mudanças**: A aplicação pode responder mais facilmente às mudanças de requisitos ou tecnologias externas.

Ao seguir a Arquitetura Hexagonal, os desenvolvedores podem criar sistemas mais robustos, testáveis e flexíveis, que são capazes de suportar crescimento e mudança sem comprometer a integridade do core business.