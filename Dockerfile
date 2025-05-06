# Sử dụng image OpenJDK 23 để chạy ứng dụng
FROM openjdk:23-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file JAR từ máy chủ vào container
COPY target/Server_LofiMusic.jar app.jar

# Mở port 8080
EXPOSE 8085

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
