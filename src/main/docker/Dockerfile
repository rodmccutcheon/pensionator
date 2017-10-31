FROM openjdk

VOLUME /tmp
ADD target/pensionator-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]