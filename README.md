# Chat Client-Server com Criptografia RSA

![Java](https://img.shields.io/badge/Java-11%2B-blue?style=for-the-badge&logo=java)

## 📜 Descrição do Projeto

Este projeto implementa uma aplicação de chat em Java que opera em uma arquitetura Cliente-Servidor. O principal objetivo é estabelecer uma comunicação segura entre o cliente e o servidor, onde todas as mensagens trocadas são criptografadas utilizando o algoritmo RSA (Rivest-Shamir-Adleman).

A comunicação é bidirecional (Cliente ↔ Servidor) e utiliza Sockets TCP para garantir uma transmissão de dados confiável e ordenada. A camada de criptografia foi desenvolvida do zero, com base em parâmetros de chaves pré-definidos, para criptografar e descriptografar cada caractere das mensagens.

## ✨ Funcionalidades Principais

-   **Comunicação Bidirecional**: O cliente e o servidor podem enviar e receber mensagens em tempo real.
-   **Criptografia de Mensagens**: Todas as mensagens são criptografadas antes do envio e descriptografadas no recebimento, garantindo a confidencialidade.
-   **Arquitetura Modular**: O projeto é dividido em pacotes lógicos para criptografia (`crypto`) e rede (`net`), facilitando a manutenção e o entendimento.
-   **Conexão TCP/IP**: Utiliza `java.net.Socket` e `java.net.ServerSocket` para uma conexão estável e confiável.

## ⚙️ Tecnologias Utilizadas

-   **Linguagem**: Java 21
-   **IDE**: IntelliJ
-   **Rede**: Java Sockets (TCP/IP)
-   **Criptografia**: `java.math.BigInteger` para os cálculos do algoritmo RSA.

## 🔑 Parâmetros de Criptografia RSA

As chaves criptográficas utilizadas nesta aplicação são fixas (hardcoded) e baseadas nos seguintes parâmetros:

| Componente | Símbolo | Valor | Descrição |
| :--- | :---: | :---: | :--- |
| Primo P | `p` | 13 | Primeiro número primo secreto. |
| Primo Q | `q` | 29 | Segundo número primo secreto. |
| Módulo | `n` | 377 | Calculado como `p * q`. Parte da chave pública e privada. |
| Função Totiente | `ϕ(n)` | 336 | Calculado como `(p-1) * (q-1)`. |
| Expoente Público | `e` | 5 | Parte da chave pública. Usado para **criptografar**. |
| Expoente Privado | `d` | 269 | Parte da chave privada. Usado para **descriptografar**. |

-   **Chave Pública**: (e, n) = (5, 377)
-   **Chave Privada**: (d, n) = (269, 377)

## 🚀 Como Executar

Para compilar e executar o projeto, você precisará ter o **JDK (Java Development Kit)** instalado em sua máquina.

### Pré-requisitos

-   JDK 11 ou superior.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    cd seu-repositorio
    ```

2.  **Compile os arquivos `.java`:**
    Abra um terminal na pasta raiz do projeto e execute o seguinte comando:
    ```bash
    javac -d . src/br/com/rsa/crypto/RSA.java src/br/com/rsa/net/Server.java src/br/com/rsa/net/Client.java
    ```
    Este comando compila todos os arquivos e organiza os `.class` na estrutura de pacotes correta.

3.  **Inicie o Servidor:**
    Abra um **primeiro terminal** e execute o comando abaixo. O servidor ficará aguardando uma conexão.
    ```bash
    java br.com.rsa.net.Server
    ```

4.  **Inicie o Cliente:**
    Abra um **segundo terminal** e execute o seguinte comando para conectar ao servidor:
    ```bash
    java br.com.rsa.net.Client
    ```

5.  **Comunique-se!**
    Agora você pode digitar mensagens no terminal do cliente. As mensagens serão criptografadas, enviadas ao servidor, descriptografadas e exibidas. Em seguida, o servidor solicitará uma resposta, que seguirá o mesmo fluxo seguro de volta para o cliente. Digite `sair` para encerrar a conexão.

## 🔄 Fluxo da Aplicação

A execução do programa segue 4 etapas principais, que são exibidas nos terminais:

1.  **Etapa 1: Conexão**
    -   O Servidor inicia e aguarda na porta `12345`.
    -   O Cliente se conecta ao endereço e porta do servidor, estabelecendo um Socket TCP.

2.  **Etapa 2: Troca de Chaves (Simulada)**
    -   Como as chaves são pré-definidas, esta etapa consiste em uma confirmação de que ambos os lados estão usando os mesmos parâmetros públicos `(E, N)`.

3.  **Etapa 3: Comunicação de Dados**
    -   O Cliente solicita uma mensagem ao usuário.
    -   A mensagem é criptografada caractere por caractere usando a chave pública.
    -   O texto cifrado é enviado ao Servidor.
    -   O Servidor recebe o texto cifrado e o descriptografa usando a chave privada.
    -   O processo se repete na direção oposta para a resposta do Servidor.

4.  **Etapa 4: Desconexão**
    -   A sessão é encerrada quando o cliente ou o servidor envia a mensagem "sair".
    -   Os Sockets e os fluxos de dados são fechados de forma segura.
