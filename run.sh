#!/bin/bash


JAVA_OPTS="-XX:+UnlockCommercialFeatures -XX:+FlightRecorder"

java -server -jar /Users/santanu/IdeaProjects/data-manager/api/target/*.jar server /Users/santanu/IdeaProjects/data-manager/api/src/main/resources/stage/config.yaml 2>&1
