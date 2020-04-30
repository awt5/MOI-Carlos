FROM carlosmc23/ubuntu-java-python:1.0

WORKDIR /source
COPY build/libs/MOI-*.jar /source/MOI-APP.jar

ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=docker","-jar","MOI-APP.jar"]