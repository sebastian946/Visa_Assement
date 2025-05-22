FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .
RUN mvn clean test && \
    echo "=== Archivos en /app/target ===" && \
    ls -la /app/target/

FROM alpine:latest

WORKDIR /target

COPY --from=builder /app/target/cucumber-reports.html .

CMD ["sh", "-c", "echo '=== Contenido de /target ==='; \
     ls -la /target; \
     echo '=== Verificando existencia del reporte ==='; \
     if [ -f '/target/cucumber-reports.html' ]; then \
       echo 'Reporte copiado correctamente'; \
     else \
       echo 'Error: Reporte no encontrado'; \
     fi; \
     tail -f /dev/null"]