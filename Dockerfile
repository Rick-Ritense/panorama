FROM eclipse-temurin:21-alpine

ADD /build/libs /

#you need the crazy-looking filename, e.g., the extra /./, to trick Java into accepting your filename. If you just use /dev/urandom, Java decides you didn't really mean it and replaces what you wrote with /dev/random. Craziness!
ENTRYPOINT ["java","-Xms2048M","-Xmx2048M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]