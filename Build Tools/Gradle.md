# Gradle: Construção de Projetos Java

O Gradle é uma poderosa ferramenta de automação de construção que permite criar, testar e empacotar projetos de software de forma eficiente. Ele oferece uma sintaxe concisa e flexível baseada em DSL (Linguagem de Domínio Específico) para definir tarefas, dependências e configurações de projetos.

## Gradle Wrapper (Gradlew)

O Gradle Wrapper é uma maneira de garantir que todos os membros da equipe estejam usando a mesma versão do Gradle para a construção do projeto, evitando problemas de compatibilidade. Ele é distribuído junto com o projeto e permite executar comandos Gradle sem precisar ter o Gradle instalado globalmente.

### Instalação

Para inicializar o Gradle Wrapper no seu projeto:

1. Navegue até a raiz do projeto no terminal.
2. Execute o seguinte comando para gerar os arquivos do Gradle Wrapper:

```
gradle wrapper
```

3. O Gradle Wrapper gerará os arquivos necessários, incluindo o script `gradlew` (ou `gradlew.bat` no Windows) e a pasta `gradle/wrapper` contendo os binários do Gradle.

### Uso

Para executar tarefas do Gradle usando o Gradle Wrapper, basta prefixar o comando com `./gradlew` (ou `.\gradlew.bat` no Windows):

```
./gradlew <tarefa>
```

## Arquivo build.gradle

O arquivo `build.gradle` é o coração do projeto Gradle. Ele contém a definição de tarefas, plugins, dependências e configurações do projeto.

### Exemplo de Configuração Básica

```groovy
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.slf4j:slf4j-api:1.7.32'
    testImplementation 'junit:junit:5.8.0'
}

compileJava.options.encoding = 'UTF-8'
```

Neste exemplo:
- O plugin Java é aplicado ao projeto.
- O repositório Maven Central é configurado para buscar dependências.
- Duas dependências são declaradas, uma para a implementação e outra para testes.
- A codificação UTF-8 é configurada para a compilação Java.

### Campos mais comuns

1. `plugins`: Este bloco permite que você defina os plugins Gradle que serão aplicados ao seu projeto. Os plugins adicionam funcionalidades específicas, como suporte para Java, Kotlin, aplicativos Android, etc.

   Exemplo:
   ```groovy
   plugins {
       id 'java'
   }
   ```

2. `repositories`: Define onde o Gradle procurará por dependências. Pode apontar para repositórios Maven Central, JCenter, repositórios locais ou repositórios personalizados.

   Exemplo:
   ```groovy
   repositories {
       mavenCentral()
   }
   ```

3. `dependencies`: Lista as dependências do seu projeto, incluindo bibliotecas e frameworks que seu projeto precisa para ser compilado e executado.

   Exemplo:
   ```groovy
   dependencies {
       implementation 'com.example:my-library:1.0.0'
       testImplementation 'junit:junit:4.12'
   }
   ```

4. `sourceCompatibility` e `targetCompatibility`: Define a versão do Java que o projeto deve ser compatível. Isso afeta a compilação do código-fonte.

   Exemplo:
   ```groovy
   sourceCompatibility = 1.8
   targetCompatibility = 1.8
   ```

## Tarefas do Gradle

O Gradle oferece uma ampla variedade de tarefas que podem ser usadas para diferentes fins durante o desenvolvimento e o ciclo de vida do projeto. Aqui estão algumas tarefas adicionais que você pode encontrar úteis:

### Limpeza do Projeto

Para limpar os artefatos de construção e diretórios temporários:

```bash
./gradlew clean
```

### Compilação e Construção do Projeto

Compila o código-fonte do projeto, executa os testes, empacota o projeto e gera os artefatos resultantes, como JARs, WARs ou outros formatos, conforme configurado no arquivo build.gradle.

```bash
./gradlew build
```

### Execução de Aplicativos

Para executar um aplicativo Java após a compilação:

```bash
./gradlew run
```

### Geração de Documentação

Para gerar documentação JavaDoc para o projeto:

```bash
./gradlew javadoc
```

### Análise Estática de Código

Para executar ferramentas de análise estática de código, como Checkstyle, PMD ou FindBugs:

```bash
./gradlew check
```

### Empacotamento de Artefatos

Para empacotar o projeto em um arquivo JAR executável:

```bash
./gradlew jar
```

### Execução de Testes

Para executar todos os testes:

```bash
./gradlew test
```

Para executar testes específicos usando o nome do método:

```bash
./gradlew test --tests <nome_do_metodo>
```

### Monitoramento de Atualizações de Dependências

Para verificar se há atualizações de dependências:

```
./gradlew dependencyUpdates
```

## Inicialização de Projeto

Para criar um novo projeto Gradle:

1. Navegue até a pasta onde deseja criar o projeto.
2. Execute o seguinte comando para criar uma estrutura básica:

```bash
gradle init
```

3. Siga as instruções interativas para escolher o tipo de projeto (aplicação, biblioteca, etc.) e a linguagem (Java, Kotlin, etc.).

O Gradle criará os arquivos e pastas necessários para o projeto, incluindo o `build.gradle`.

O Gradle é uma ferramenta poderosa que facilita a construção, teste e empacotamento de projetos Java. Com sua sintaxe flexível e a capacidade de automatizar tarefas comuns, o Gradle se tornou uma escolha popular entre os desenvolvedores para gerenciar projetos de software.


