FROM maven:3-openjdk-8-slim AS maven

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean package


FROM openjdk:8-jdk-alpine
COPY --from=maven /app/target/*.jar app.jar

#ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES="127.0.0.1:11800"
#ENV SW_AGENT_NAME="customer-server"


ENTRYPOINT [ "java","-jar", "app.jar"]
EXPOSE 9200
