# STAGE 1: Build da Aplicação (usando o Maven)
FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
# Define o diretório de trabalho
WORKDIR /app
# Copia e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline
# Copia o código-fonte restante
COPY src ./src
# Empacota a aplicação em um JAR executável, skipando testes
RUN mvn package -DskipTests


# STAGE 2: Imagem de Produção (Runtime)
# Usa uma imagem Java Runtime Environment (JRE) para produção
FROM eclipse-temurin:21-jre-alpine
# Define o diretório de trabalho
WORKDIR /app
# Expõe a porta padrão do Spring Boot
EXPOSE 8080
# Cria um grupo chamado "spring" e um usuário "spring". Adiciona o usuario no grupo. Faz com que o CMD rode com as permissões desse user não-root 
RUN addgroup -S spring && adduser -S spring -G spring
USER spring
# Copia o JAR empacotado da fase de build para a fase de produção
COPY --from=build /app/target/consultas.jar /app/app.jar
# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]