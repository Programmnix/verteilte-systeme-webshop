FROM tomcat:8.0

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ROOT /usr/local/tomcat/webapps/ROOT

COPY libs/* $CATALINA_HOME/lib/

EXPOSE 8080

CMD ["catalina.sh", "run"]
