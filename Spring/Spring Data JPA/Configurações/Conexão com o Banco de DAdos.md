# Configurando a Conexão com o Banco de Dados

Configurar bancos de dados em um aplicativo Spring Boot através do arquivo `application.properties` (ou `application.yml`) é uma tarefa comum. 

**1. H2 Database:**

```properties
# DataSource configuration for H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```

**2. MySQL:**

```properties
# DataSource configuration for MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=password
```

**3. PostgreSQL:**

```properties
# DataSource configuration for PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=password
```



**4. Microsoft SQL Server (MS SQL):**

```properties
# DataSource configuration for MS SQL Server
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=mydb
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.datasource.username=sa
spring.datasource.password=password
```

**5. Oracle Database:**

```properties
# DataSource configuration for Oracle
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.driverClassName=oracle.jdbc.OracleDriver
spring.datasource.username=myuser
spring.datasource.password=mypassword
```
**6. Configurando o banco de dados usando variáveis de ambiente:**

- O que vem depois dos `:` são os valores padrão caso a variável de ambiente não exista.

```
spring.datasource.url=${DATABASE_URL:jdbc:postgresql://localhost:5432/mydb}
spring.datasource.username=${DATABASE_USERNAME:postgres}
spring.datasource.password=${DATABASE_PASSWORD:password}
```

Lembre-se de substituir as informações específicas, como URLs, nomes de bancos de dados, credenciais e drivers, pelas configurações correspondentes ao seu ambiente e banco de dados.

Com essas configurações no `application.properties`, o Spring Boot se conectará automaticamente ao banco de dados especificado. Certifique-se de ter as dependências apropriadas no seu arquivo `pom.xml` (ou `build.gradle`) para o banco de dados que você está usando.

## Proprieadades Adicionais

Além das configurações básicas de conexão com bancos de dados no `application.properties`, você pode precisar definir propriedades adicionais, dependendo dos requisitos do seu aplicativo. Abaixo estão algumas propriedades adicionais pertinentes a bancos de dados:

**1. DDL Auto (Data Definition Language Auto):**
   - Controla a criação e atualização do esquema do banco de dados.
   - Valores possíveis: `none`, `update`, `create`, `create-drop`.
   - Exemplo:
     ```properties
     spring.jpa.hibernate.ddl-auto=update
     ```

**2. Especificação de Dialeto (Dialect):**
   - Define o dialeto do SQL a ser usado.
   - Exemplo para PostgreSQL:
     ```properties
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     ```

**3. Pool de Conexões (Connection Pooling):**
   - Configura o número máximo de conexões no pool.
   - Exemplo:
     ```properties
     spring.datasource.hikari.maximum-pool-size=10
     ```

**4. Nome do Esquema (Schema Name):**
   - Define o nome do esquema (schema) do banco de dados.
   - Exemplo:
     ```properties
     spring.datasource.schema=public
     ```

**5. Inicialização de Dados (Data Initialization):**
   - Controla como os dados iniciais devem ser carregados no banco de dados.
   - Valores possíveis: `always`, `never`, `embedded`, `classpath`, `file`.
   - Exemplo:
     ```properties
     spring.datasource.data=classpath:data.sql
     ```

**6. Configuração de Cache de Consulta (Query Cache Configuration):**
   - Controla o cache de consultas.
   - Exemplo:
     ```properties
     spring.jpa.properties.hibernate.cache.use_second_level_cache=true
     ```

**7. Chave de Encriptação (Encryption Key):**
   - Define uma chave para criptografar senhas ou dados sensíveis.
   - Exemplo:
     ```properties
     spring.datasource.password=ENC(mySecretPassword)
     ```

**8. Nível de Isolamento (Isolation Level):**
   - Controla o nível de isolamento de transações.
   - Exemplo:
     ```properties
     spring.datasource.hikari.transaction-isolation=TRANSACTION_READ_COMMITTED
     ```

**9. Propriedades de Desempenho (Performance Properties):**
   - Você pode configurar várias propriedades de desempenho específicas do seu banco de dados, como tamanho de lote, otimizações de desempenho, etc.
   - Exemplo:
     ```properties
     spring.jpa.properties.hibernate.jdbc.batch_size=20
     ```

**10. Informações de Logging:**

```
logging.level.org.springframework=<DEBUG|INFO>
logging.level.org.springframework.data=<DEBUG|INFO>
logging.level.org.hibernate.SQL=<DEBUG|INFO>
```

Lembre-se de que as propriedades adicionais que você precisa configurar podem variar de acordo com o banco de dados que está usando e os requisitos do seu aplicativo. Certifique-se de consultar a documentação do seu banco de dados e do Spring Boot para obter mais informações sobre as configurações específicas disponíveis.