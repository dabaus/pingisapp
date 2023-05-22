FROM arm64v8/eclipse-temurin:20 AS base
EXPOSE 8080
FROM base as gradle-build
WORKDIR /repo
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew --version
COPY src/ ./src
RUN ./gradlew build

FROM base AS final
VOLUME /tmp
COPY --from=gradle-build ./repo/build/libs/pingpongapp * app.jar
ENTRYPOINT ["java","-jar","app.jar"]