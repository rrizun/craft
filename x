#!/bin/sh -ex
gradle bootJar
docker build -t craft .
docker run -p 5000:5000 craft
