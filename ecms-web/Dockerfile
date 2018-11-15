FROM java:8
VOLUME /tmp
COPY /target/ecms-web-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c "touch /app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

# docker run -d -p 18080:8080 --name docker-ecms ecms/app:1.0