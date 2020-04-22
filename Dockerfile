FROM ubuntu-java-python:1.0

RUN mkdir source
COPY build/libs/MOI-1.0-SNAPSHOT.jar /source
WORKDIR /source
ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=docker","-jar","MOI-1.0-SNAPSHOT.jar"]
