FROM arm64v8/eclipse-temurin:20
EXPOSE 8080
VOLUME /tmp
COPY build/libs/pingpongapp* app.jar
ENTRYPOINT ["java","-jar","/app.jar"]