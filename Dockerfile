FROM ubuntu
RUN apt-get update; \
    apt-get install -y curl; \
    apt-get install -y nano; \
    apt-get install -y openjdk-11-jdk; \
    apt-get install -y python3;

COPY build/libs/MOI-1.0-SNAPSHOT.jar /source
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-jar","MOI-1.0-SNAPSHOT.jar"]

