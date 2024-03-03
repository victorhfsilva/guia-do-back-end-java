# Maven: Gerenciamento de Build e Dependências

O Maven é uma ferramenta essencial para automatizar a construção, teste e gerenciamento de dependências de projetos Java. Com uma configuração baseada em XML, o Maven simplifica muitas tarefas de desenvolvimento, permitindo que os desenvolvedores se concentrem mais na lógica do projeto do que na configuração.

## Arquetipos do Maven

Os arquetipos do Maven são modelos de projetos que permitem criar rapidamente a estrutura básica de um projeto. Eles são úteis para evitar a criação manual de diretórios e arquivos iniciais.

### Gerando um Projeto a partir de um Arquétipo

Para gerar um novo projeto a partir de um arquétipo, você pode usar o seguinte comando:

```
mvn archetype:generate -DgroupId=<pacote> -DartifactId=<projeto> -Darchetype=<arquetipo> -DinteractiveMode=false
```

Exemplos:

Gerar um projeto rápido (quickstart):

```
mvn archetype:generate -DgroupId=edu.victor -DartifactId=quick-start-maven -Darchetype=maven-archetype-quickstart -DinteractiveMode=false
```

Gerar um projeto web (webapp):

```
mvn archetype:generate -DgroupId=edu.victor -DartifactId=webapp-maven -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4 
```

## Comandos Básicos do Maven

### Compilação

Para compilar o projeto:

```bash
mvn compile
```

### Testes

Para executar os testes do projeto:

```bash
mvn test
```

### Empacotamento

Para gerar um arquivo JAR do projeto:

```bash
mvn package
```

#### Execução de Arquivos JAR

Se a classe principal estiver especificada no manifesto do arquivo JAR:

```bash
java -jar <arquivo.jar>
```

Se não estiver, especifique a classe principal:

```bash
java -cp <arquivo.jar> <pacote>.<classe_principal>
```

### Limpeza do Diretório de Trabalho

Para remover os arquivos compilados:

```bash
mvn clean
```

### Instalação de Dependências

Para baixar e instalar as dependências do projeto:

```bash
mvn dependency:resolve
```

Este comando resolve as dependências do projeto e as instala no repositório local do Maven, permitindo que o projeto seja compilado corretamente.

Exemplo de declaração de dependência:

```xml
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

### Install

Compila o código-fonte do projeto, executa os testes, empacota o projeto e, em seguida, instala o artefato resultante (geralmente um arquivo JAR) no repositório local do Maven.

```bash
mvn install
```

O Maven é uma ferramenta essencial para simplificar a construção e a gestão de projetos Java. Com os comandos básicos, você pode facilmente compilar, testar e empacotar seu projeto, além de gerenciar suas dependências de maneira eficiente.