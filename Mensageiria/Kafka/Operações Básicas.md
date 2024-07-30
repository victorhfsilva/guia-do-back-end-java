# Operações Básicas

O Apache Kafka é uma plataforma poderosa para o processamento de streams de dados em tempo real. Compreender as operações básicas do Kafka é essencial para qualquer desenvolvedor ou administrador que deseja utilizar essa tecnologia. Esta seção aborda as operações fundamentais, incluindo a criação de tópicos, produção de mensagens, consumo de mensagens e monitoramento de clusters, tanto em uma instalação tradicional quanto usando Docker.

### Criação de Tópicos

Tópicos são uma das principais unidades de organização no Kafka. Para criar um tópico, você pode utilizar a ferramenta de linha de comando `kafka-topics.sh`.

#### Passos para Criar um Tópico:

##### Usando Instalação Tradicional:

1. **Navegue até o diretório do Kafka**:
   ```sh
   cd /path/to/kafka
   ```

2. **Execute o comando para criar um tópico**:
   ```sh
   bin/kafka-topics.sh --create --topic meu-topico --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
   ```

   - `--topic`: Nome do tópico.
   - `--bootstrap-server`: Endereço do servidor Kafka.
   - `--partitions`: Número de partições para o tópico.
   - `--replication-factor`: Fator de replicação para o tópico.

##### Usando Docker:

1. **Execute o comando para criar um tópico**:
   ```sh
   docker-compose exec kafka kafka-topics --create --topic meu-topico --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
   ```

#### Verificar Tópicos Criados:

Para listar todos os tópicos existentes:

##### Usando Instalação Tradicional:

```sh
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

##### Usando Docker:

```sh
docker-compose exec kafka kafka-topics --list --bootstrap-server localhost:9092
```

### Exclusão de Tópicos

Excluir tópicos no Kafka é uma operação que remove completamente o tópico e todos os seus dados do cluster. Esta operação pode ser útil para liberar espaço ou remover dados desnecessários.

#### Passos para Excluir um Tópico:

##### Usando Instalação Tradicional:

1. **Navegue até o diretório do Kafka**:
   ```sh
   cd /path/to/kafka
   ```

2. **Execute o comando para excluir um tópico**:
   ```sh
   bin/kafka-topics.sh --delete --topic meu-topico --bootstrap-server localhost:9092
   ```

##### Usando Docker:

1. **Execute o comando para excluir um tópico**:
   ```sh
   docker-compose exec kafka kafka-topics --delete --topic meu-topico --bootstrap-server localhost:9092
   ```

#### Verificar Exclusão do Tópico:

Para garantir que o tópico foi excluído, liste os tópicos existentes novamente:

##### Usando Instalação Tradicional:

```sh
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

##### Usando Docker:

```sh
docker-compose exec kafka kafka-topics --list --bootstrap-server localhost:9092
```

### Produção de Mensagens

Produzir mensagens no Kafka envolve enviar dados para um tópico específico. Isso pode ser feito usando a ferramenta de linha de comando `kafka-console-producer.sh`.

#### Passos para Produzir Mensagens:

##### Usando Instalação Tradicional:

1. **Navegue até o diretório do Kafka**:
   ```sh
   cd /path/to/kafka
   ```

2. **Execute o comando para iniciar o produtor**:
   ```sh
   bin/kafka-console-producer.sh --topic meu-topico --bootstrap-server localhost:9092
   ```

3. **Digite suas mensagens**:
   Após executar o comando, você pode começar a digitar mensagens que serão enviadas para o tópico especificado. Cada linha que você digitar será uma mensagem separada.

##### Usando Docker:

1. **Execute o comando para iniciar o produtor**:
   ```sh
   docker-compose exec kafka kafka-console-producer --topic meu-topico --bootstrap-server localhost:9092
   ```

2. **Digite suas mensagens**:
   Após executar o comando, você pode começar a digitar mensagens que serão enviadas para o tópico especificado. Cada linha que você digitar será uma mensagem separada.

### Consumo de Mensagens

Para consumir mensagens de um tópico no Kafka, você pode utilizar a ferramenta de linha de comando `kafka-console-consumer.sh`.

#### Passos para Consumir Mensagens:

##### Usando Instalação Tradicional:

1. **Navegue até o diretório do Kafka**:
   ```sh
   cd /path/to/kafka
   ```

2. **Execute o comando para iniciar o consumidor**:
   ```sh
   bin/kafka-console-consumer.sh --topic meu-topico --bootstrap-server localhost:9092 --from-beginning
   ```

   - `--from-beginning`: Indica que o consumidor deve ler todas as mensagens desde o início do tópico.

##### Usando Docker:

1. **Execute o comando para iniciar o consumidor**:
   ```sh
   docker-compose exec kafka kafka-console-consumer --topic meu-topico --bootstrap-server localhost:9092 --from-beginning
   ```








