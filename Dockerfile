FROM maven:3.9.5-eclipse-temurin-21 AS build
# 設置工作目錄
WORKDIR /app

# 複製整個項目結構
COPY . .

# 顯示項目結構以檢查文件
RUN ls -la && find . -name "pom.xml"

# 首先構建父模塊
WORKDIR /app/management-parent
RUN mvn clean install -N -DskipTests

# 構建 pojo 模塊
WORKDIR /app/management-pojo
RUN mvn clean install -DskipTests

# 構建 utils 模塊
WORKDIR /app/management-utils
RUN mvn clean install -DskipTests

# 最後構建 web-management 模塊
WORKDIR /app/web-management
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/web-management/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]