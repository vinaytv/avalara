FROM openjdk:11-jre-slim
RUN mkdir -p /app
COPY target/*.jar  /app/app.jar
RUN groupadd -g 10001 assessment && useradd -g assessment -u 10001 assessment
RUN chown -R assessment:assessment /app && chown -R assessment:assessment /var

RUN apt-get update && apt-get install curl unzip -y && mkdir \
USER assessment
EXPOSE 8080
ENTRYPOINT ["/usr/local/openjdk-11/bin/java"]
CMD ["-jar", "/app/app.jar"]
