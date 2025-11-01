# Parto da jdk21
FROM openjdk:21

# Non essendoci delle build predefinite con TomEE 10, ne prendiamo una noi online
RUN curl -L https://downloads.apache.org/tomee/tomee-10.1.2/apache-tomee-10.1.2-microprofile.tar.gz -o /tmp/tomee.tar.gz \
    && tar -xzf /tmp/tomee.tar.gz -C /usr/local/ \
    && mv /usr/local/apache-tomee-microprofile-10.1.2 /usr/local/tomee \
    && rm /tmp/tomee.tar.gz



# Si copia il war nel target (grazie a mvn clean package) all'interno del container della webapp
COPY target/UniClass-Sustainable.war /usr/local/tomee/webapps/UniClass-Sustainable.war

# Si copiano i context e system.properties (che aggiungono info sulla Risorsa DB e Proprietà sul sistema blacklist/whitelist EJB) nel container webapp
COPY .smarttomcat/UniClass/conf/context.xml /usr/local/tomee/conf/context.xml
COPY .smarttomcat/UniClass/conf/system.properties /usr/local/tomee/conf/system.properties

# Va sulla porta 8080
EXPOSE 8080

CMD ["/usr/local/tomee/bin/catalina.sh", "run"]
