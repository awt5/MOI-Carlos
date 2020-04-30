FROM carlosmc23/ubuntu-java-python:1.0


RUN mkdir source
COPY build/libs/MOI-*.jar /source/MOI-APP.jar
WORKDIR /source

ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=docker","-jar","MOI-APP.jar"]