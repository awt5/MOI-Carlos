FROM ubuntu:18.04
RUN apt-get update; \
    apt-get install -y openjdk-11-jdk; \
    apt-get install -y python3;

RUN mkdir source
COPY build/libs/MOI-1.0-SNAPSHOT.jar /source
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-jar","MOI-1.0-SNAPSHOT.jar"]

