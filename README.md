# RabbitMQ Queue Testing
First time using queue (RabbitMQ)

# Queue RabbitMQ

Este projeto implementa um sistema de enfileiramento usando RabbitMQ, com o objetivo de processar mensagens de diferentes fontes de forma assíncrona. A aplicação permite enviar e receber mensagens entre várias filas, utilizando a tecnologia de mensageria para garantir que as mensagens sejam processadas de maneira eficiente.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada para a implementação da aplicação.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java, facilitando a configuração e o desenvolvimento da aplicação.
- **RabbitMQ**: Sistema de mensageria utilizado para o enfileiramento das mensagens. As filas são gerenciadas pelo RabbitMQ para garantir a entrega e o processamento adequado das mensagens.
- **Spring AMQP**: Biblioteca do Spring para integração com o RabbitMQ.

## Como Funciona

O sistema é composto por várias filas, onde cada fila representa um ponto de enfileiramento para mensagens de diferentes sistemas. As mensagens são processadas assíncronamente por consumidores que escutam as filas e processam as mensagens conforme necessário. As mensagens são enviadas para uma fila principal chamada `main_queue`, a partir de diferentes filas como `system_GO_queue` e `system_AC_queue`.

## Estrutura do Projeto

O projeto é estruturado da seguinte forma:

- **Services**:
  - **SystemGOService**: Escuta a fila `system_GO_queue` e envia mensagens para a `main_queue`.
  - **SystemACService**: Escuta a fila `system_AC_queue` e envia mensagens para a `main_queue`.
  - **EnfileiramentoService**: Enfileira as mensagens recebidas para a fila principal, `main_queue`.
  - **MainProcessingService**: Processa as mensagens da fila `main_queue`.

- **RabbitMQ**:
  - **Exchange**: Utilizado para roteamento das mensagens entre as filas.
  - **Binding**: Define como as filas estão associadas ao exchange.

## Como Rodar o Projeto

### Pré-requisitos

1. **RabbitMQ** deve estar instalado e em execução. Se você ainda não tem o RabbitMQ, siga as instruções de instalação [aqui](https://www.rabbitmq.com/download.html).

2. **Java 17** ou superior deve estar instalado no seu sistema. Você pode verificar se o Java está instalado usando o comando:
   ```bash
   java -version
3. **Maven** deve estar instalado para gerenciar dependências e executar o projeto. Você pode verificar se o Maven está instalado com o comando:
   ```bash
   mvn -version

### Executando Projeto
1. Clone este repositório:
   ```bash
   git clone https://github.com/NMaksed/queue_rabbitmq.git
   cd queue_rabbitmq
2. Execute o comando abaixo para rodar o projeto com o Maven:
   ```bash
   mvn spring-boot:run

### Enviando Mensagens
Após a aplicação estar em funcionamento, você pode enviar mensagens para as filas system_GO_queue ou system_AC_queue e elas serão processadas e redirecionadas para a fila principal main_queue.

Monitorando as Filas no RabbitMQ
Você pode acessar o painel de administração do RabbitMQ pelo endereço http://localhost:15672. O login padrão é guest e a senha também é guest. Lá, você pode visualizar o estado das filas, exchanges e bindings.

### Fluxo do Sistema

1. SystemGOService e SystemACService escutam as filas system_GO_queue e system_AC_queue, respectivamente.
2. Quando uma mensagem chega em uma dessas filas, o serviço correspondente a processa e a envia para a fila main_queue.
3. EnfileiramentoService lida com o roteamento das mensagens para a main_queue através do exchange.
4. MainProcessingService recebe as mensagens da main_queue e as processa conforme necessário.

