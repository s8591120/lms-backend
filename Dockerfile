FROM maven:3.9.5-eclipse-temurin-21 AS build
# 設置工作目錄
WORKDIR /app
# 複製整個項目
COPY . .
# 切換到包含 pom.xml 的目錄
WORKDIR /app/web-management
# 構建項目
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
# 從構建階段複製 jar 文件
COPY --from=build /app/web-management/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]