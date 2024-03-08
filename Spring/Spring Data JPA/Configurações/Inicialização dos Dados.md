# Inicialização de Esquema e Dados com Spring Boot

Quando você está configurando um aplicativo Spring Boot com acesso a banco de dados, é comum também criar e inicializar o esquema do banco de dados e inserir dados iniciais. O Spring Boot fornece uma maneira conveniente de fazer isso usando propriedades específicas no arquivo `application.properties` (ou `application.yml`). É importante também considerar a configuração do DDL (Data Definition Language) para controlar a criação e atualização do esquema do banco de dados. 

**1. Esquema (Schema) e Dados Iniciais:**

Para criar o esquema do banco de dados e adicionar dados iniciais, você pode usar as seguintes configurações no seu arquivo `application.properties`:

```properties
spring.sql.init.schema-locations=classpath*:db/initDB.sql
spring.sql.init.data-locations=classpath*:db/insertAddresses.sql,classpath*:db/insertStudents.sql
```

- `spring.sql.init.schema-locations` especifica a localização do arquivo SQL que cria o esquema do banco de dados.
- `spring.sql.init.data-locations` especifica a localização dos arquivos SQL que contêm dados iniciais.

**2. DDL "none":**

É importante observar que você deve configurar a propriedade `spring.jpa.hibernate.ddl-auto` com o valor "none" para evitar que o Hibernate tente criar ou atualizar o esquema do banco de dados. A criação e atualização do esquema devem ser tratadas pelos arquivos SQL especificados nas propriedades `schema-locations` e não pelo Hibernate. 

Exemplo de configuração DDL "none":

```properties
spring.jpa.hibernate.ddl-auto=none
```

Dessa forma, você garante que o esquema e os dados são criados e inseridos somente a partir dos arquivos SQL definidos nas propriedades `schema-locations` e `data-locations`, e o Hibernate não interferirá na criação do esquema.

**3. Inicialização tardia do data source:**

A configuração spring.jpa.defer-datasource-initialization=true no Spring Boot indica que a inicialização do datasource (fonte de dados) será adiada até que o Hibernate esteja pronto para usá-lo.

```
spring.jpa.defer-datasource-initialization=true
```

Quando essa configuração é habilitada, o Spring Boot adia a inicialização do datasource até o Hibernate estar totalmente configurado e pronto para usá-lo. Isso pode ser útil em cenários em que a configuração do Hibernate depende de outras configurações ou beans do Spring que precisam ser totalmente inicializados primeiro.

**4. Habilitar a inicialização por script:**

Para habilitar a inicialização do banco de dados por scripts basta utilizar:

```
spring.sql.init.mode=always
```

Esta opção é habilitada por padrão em bancos de dados em memória como o H2.