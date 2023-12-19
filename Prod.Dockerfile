# Simple build script from spring
# with gradle build profile specified
# and env variables forwarded from docker-compose args
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app

COPY . /workspace/app

# TODO: Remove redundant stuff (other profiles, is src is in the image?, and so on)

RUN --mount=type=cache,target=/root/.gradle ./gradlew -PbuildProfile=prod clean build # gradle build with prod profile
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*-SNAPSHOT.jar)

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD
ENV SPRING_DATASOURCE_URL $SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_USERNAME $SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD $SPRING_DATASOURCE_PASSWORD

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
# running with prod profile
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "-Dspring.profiles.active=prod", "com.example.demo.InfoApplication"]
