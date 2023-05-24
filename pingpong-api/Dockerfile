FROM arm64v8/eclipse-temurin:20 AS base
EXPOSE 8080
FROM base as gradle-build
WORKDIR /repo
COPY settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew --version
COPY pingpong-api ./pingpong-api
RUN ./gradlew build

FROM base AS final
VOLUME /tmp
COPY --from=gradle-build ./repo/pingpong-api/build/libs/pingpong-api* app.jar
ENTRYPOINT ["java","-jar","app.jar"]