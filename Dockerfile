# Use uma imagem base do Tomcat
FROM tomcat:9.0

# Copie o arquivo WAR gerado para o diretório de webapps do Tomcat
COPY ./target/myapp.war /usr/local/tomcat/webapps/

# Expõe a porta 8080 para a aplicação
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
