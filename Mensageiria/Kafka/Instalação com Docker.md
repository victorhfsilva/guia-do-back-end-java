# Instalação do Kafka usando Docker

## Instalação do Kafka usando Docker

O Docker simplifica muito o processo de instalação e configuração do Apache Kafka e do Zookeeper. Usando contêineres, é possível configurar rapidamente um ambiente de Kafka isolado e replicável, ideal para desenvolvimento e testes.

### Pré-requisitos

Antes de começar, certifique-se de ter o Docker instalado em sua máquina. Você pode baixar e instalar o Docker a partir do [site oficial](https://www.docker.com/products/docker-desktop).

### Instalação com Docker

#### Criar um arquivo `docker-compose.yml`

O `docker-compose.yml` é um arquivo de configuração que permite definir e gerenciar múltiplos contêineres Docker. Crie um novo arquivo chamado `docker-compose.yml` no diretório de sua escolha e adicione a seguinte configuração:

```yaml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    ports:
      - "9092:9092"
```

#### Iniciar os Contêineres

Com o arquivo `docker-compose.yml` criado, execute o comando a seguir no terminal para iniciar os contêineres do Kafka e Zookeeper:

```sh
docker-compose up -d
```

Esse comando faz o download das imagens necessárias, cria os contêineres e inicia os serviços em segundo plano (`-d` flag).

#### Verificar se os Contêineres estão em Execução

Para verificar se os contêineres estão em execução, utilize o comando:

```sh
docker-compose ps
```

Você deve ver uma saída semelhante a esta:

```plaintext
    Name                  Command               State             Ports
--------------------------------------------------------------------------------
kafka-docker_kafka_1       /etc/confluent/docker/run        Up             0.0.0.0:9092->9092/tcp
kafka-docker_zookeeper_1   /etc/confluent/docker/run        Up             0.0.0.0:2181->2181/tcp, 2888/tcp, 3888/tcp
```

### Configurações Adicionais

#### Configuração de Volumes

Para garantir que os dados do Kafka sejam persistidos mesmo após reiniciar os contêineres, você pode adicionar volumes ao arquivo `docker-compose.yml`:

```yaml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"
    volumes:
      - ./data/zookeeper:/var/lib/zookeeper

  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    ports:
      - "9092:9092"
    volumes:
      - ./data/kafka:/var/lib/kafka
```

#### Verificação de Logs

Para verificar os logs dos contêineres e diagnosticar problemas, use os seguintes comandos:

```sh
docker-compose logs zookeeper
docker-compose logs kafka
```

### Teste de Produção e Consumo de Mensagens

Após configurar e iniciar os contêineres, você pode testar a produção e consumo de mensagens utilizando os comandos do Kafka. Por exemplo, para criar um tópico, produzir e consumir mensagens, siga estes passos:

#### Criar um Tópico

```sh
docker-compose exec kafka kafka-topics --create --topic test --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

#### Produzir Mensagens

```sh
docker-compose exec kafka kafka-console-producer --topic test --bootstrap-server localhost:9092
```

Digite algumas mensagens e pressione Enter após cada uma.

#### Consumir Mensagens

```sh
docker-compose exec kafka kafka-console-consumer --topic test --bootstrap-server localhost:9092 --from-beginning
```

Você verá as mensagens que produziu anteriormente.

## Conclusão

A instalação do Apache Kafka usando Docker simplifica o processo de configuração e gerenciamento, permitindo que você rapidamente configure um ambiente de Kafka e Zookeeper. Com Docker Compose, você pode definir sua infraestrutura em um arquivo de configuração, garantindo portabilidade e facilidade de uso para ambientes de desenvolvimento e teste.