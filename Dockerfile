# build environment
FROM openjdk:11
#create app volume
VOLUME /tmp

# install app dependencies
COPY target/*.jar app.jar

# assign app port
EXPOSE 8080
# run the web service on container startup
ENTRYPOINT ["java","-jar", "app.jar"]


