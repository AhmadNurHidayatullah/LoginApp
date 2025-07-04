
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM eclipse-temurin:21-jdk-alpine
RUN mkdir -p /opt/pdi
copy target/loginapp-0.0.1-SNAPSHOT.jar /opt/pdi/ahmad.jar
EXPOSE 8080
CMD ["java","-jar","/opt/pdi/ahmad.jar"]