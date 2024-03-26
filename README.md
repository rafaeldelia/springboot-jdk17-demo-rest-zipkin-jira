# /rafaeldelia -- Microservice integrating with Zipkin | Rate Limit | JIRA Software

### Integração com o Zipkin

### Rate Limit - org.aspectj.lang.annotation.Aspect

### 1. Tecnologias Utilizadas

   - Java 17
   - Rate Limit
   - Spring Boot 3.2.3
   - Lombok
   - Spring Actuator
   - SonarLint
   - Integração com JIRA
   
   -- OpenTelemetry
   - Spring Cloud Starter Zipkin
   - Micrometer Tracing Bridge OTel  -> Esta dependência é um "bridge" para integrar o Micrometer com o OpenTelemetry
   - Zipkin Exporter -> Adaptador que conecta o Micrometer ao OpenTelemetry
   - Spring AOP -> É uma dependência do Spring Boot que facilita o uso do AOP (Aspect-Oriented Programming) em aplicativos Spring Boot.
   
### 2. Configuração 

##### Instale

* [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/) ou outra IDE
* [Java JDK 17](https://openjdk.java.net/projects/jdk/17/)
* [Git](https://git-scm.com/downloads)
* [Postman Agent](https://www.postman.com/downloads/);
* [Sonar Lint](https://www.sonarsource.com/products/sonarlint/)

### 3. Build e Deploy 

#### 3.1 Ambiente de desenvolvimento (LocalHost)

##### Spring Boot 

   1. Abra o terminal e navegue até a pasta desejada
   2. Digite no terminal -> git clone <repo>
   3. Abra a IDE escolhida
   4. Importe o projeto
   5. Realize o build 
      1. Aperte o botão com o símbolo de um martelo
      - ou
      2. Digite no terminal mvn package -U
   6. Execute o projeto
      1. Aperte o botão com o símbolo de play
      - ou
      2. Digite no terminal mvn exec:java

##### SonarLint

   1. Adicione o plugin sonarLint na IDE
   2. Selecione a pasta ms-usuario e Digite Ctrl+Shift+S  
   3. Ou
   4. Abra algum arquivo   
   5. Vizualise os erros indicados pelo sonarLint
   6. Corrija-os

### 4. Executar o Teste

##### 4.1 - Docker Compose - Subindo o Zipkin

   1. Acesse a pasta \src\zipkin
   2. Abra o cmd e execute o comando: "docker compose up -d"
   3. Acesse a URL: "http://localhost:9411/zipkin"
   4. Se tiver no ar é porque está pronto para iniciar as chamadas no Postman.

##### 4.2 - Postman

   1. Abra o Postman Agent
   2. Acesse o postman por meio do link: https://web.postman.co/
   3. Crie um workplace
   4. Clique em "import"
   5. Selecione o arquivo "SpringBoot + ZIPKIN.postman_collection" localizado na pasta /src/postman

##### 4.3 - CURL

   1. curl --location 'http://localhost:8083/demo-zipkin/zipkin'
   
##### 4.4 - Zipkin
   
   1. Acesse a URL: "http://localhost:9411/zipkin"
   2. Aperte o botão RUN QUERY
   3. Veja o tracing da chamada até o microserviço 2   
   
### 5. Rate Limit

   1. Chame no Postman o endpoint que esteja com a anotação @RateLimited
   2. http://localhost:8086/demo-rest-template/v1/rest/hello
   3. Após a sexta chamada dentro de 1 minuto, o sistema vai bloquear a requisição
   4. No postman precisa adicionar o header a Key: "X-Client-ID" e passar o IP no value: "192.168.0.XX"
   
   