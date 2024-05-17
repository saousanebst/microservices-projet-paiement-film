#Depuis quelle image Docker je pars
FROM openjdk:17-bullseye

#se positionner ds le repertoire /app
WORKDIR /app

ENV DATABASE_URL mysql://db:3306/filmbdd
ENV DATABASE_USERNAME root
ENV DATABASE_PASSWORD root

#copier le fichier "demo.txt" ds /app (on est déjà ds app dc on ne le met pas) 

COPY mvnw .
COPY .mvn/  .mvn/
COPY pom.xml .
COPY src/  src

EXPOSE 8082

#on ne peut pas modifier
ENTRYPOINT ["./mvnw", "spring-boot:run"]


