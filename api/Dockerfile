# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copia os arquivos de build
COPY pom.xml . 
COPY src ./src

COPY mvnw .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Cria um usuário sem privilégios
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Usa esse usuário para rodar a aplicação
USER appuser

# Define variáveis de ambiente 
ENV SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle-db:1521:XE
ENV SPRING_DATASOURCE_USERNAME=system
ENV SPRING_DATASOURCE_PASSWORD=oracle123

# Copia o jar construído do estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
