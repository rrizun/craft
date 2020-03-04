#!/bin/sh -ex
./gradlew bootJar
docker build -t craft .
docker run -p 5000:5000 craft
