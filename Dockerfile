FROM java:8-jdk
WORKDIR "/app"
COPY run.sh .
COPY api/target/api-*.jar app.jar
COPY api/src/main/resources/stage/config.yaml config.yaml
COPY api/src/main/resources/naukrimilega-32e0e.json login_cred.json
CMD ["sh", "run.sh"]