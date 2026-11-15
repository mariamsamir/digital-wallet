FROM eclipse-temurin:17-jre

WORKDIR /


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} digital-wallet.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "digital-wallet.jar"]
