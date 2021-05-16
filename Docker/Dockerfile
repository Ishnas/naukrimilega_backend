FROM java:8-jdk
WORKDIR "/app"
COPY run.sh .
COPY api-*.jar app.jar
COPY config.yaml config.yaml
COPY naukrimilega-32e0e.json login_cred.json
CMD ["sh", "run.sh"]