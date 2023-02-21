FROM openjdk
VOLUME /tmp
EXPOSE 8080
ADD target/*.jar copilot-ocp-consumer-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /copilot-ocp-consumer-0.0.1-SNAPSHOT.jar" ]