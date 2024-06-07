# Use uma imagem base do Ubuntu para construir a aplicação
FROM ubuntu:latest AS build

# Atualize os pacotes e instale o OpenJDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Defina o diretório de trabalho e copie o projeto
WORKDIR /app
COPY . .

# Construa a aplicação
RUN mvn clean install

# Use uma imagem base menor do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Exponha a porta 8080
EXPOSE 8080

# Copie o arquivo JAR da fase de construção para a nova imagem
COPY --from=build /app/target/ocean-care-0.0.1-SNAPSHOT.jar app.jar

# Defina o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]