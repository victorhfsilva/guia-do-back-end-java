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

Lembre-se de que a configuração do banco de dados e a inicialização do esquema e dados podem variar dependendo do banco de dados que você está usando, e as localizações dos arquivos SQL devem ser ajustadas de acordo com a estrutura do seu projeto. Certifique-se de configurar essas propriedades de acordo com suas necessidades específicas.