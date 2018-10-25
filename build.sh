#!/usr/bin/env bash
mvn clean package -U -Dmaven.test.skip=true
docker build -t hankzhangorion/bootu-product:latest .
docker push hankzhangorion/bootu-product:latest