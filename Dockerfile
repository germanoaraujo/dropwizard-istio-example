FROM java:8
ADD target/getting-started-dropwizard.jar getting-started-dropwizard.jar
COPY local.yml /conf/local.yml
EXPOSE 8080
EXPOSE 8081
ENTRYPOINT exec java -jar getting-started-dropwizard.jar server /conf/local.yml