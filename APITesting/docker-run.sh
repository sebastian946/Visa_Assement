#!/bin/bash

mvn clean test

docker rm -f cucumber-report 2>/dev/null || true

docker build -t cucumber-report .

docker run -d -p 5050:80 --name cucumber-report cucumber-report

echo ""
echo "Report available: http://localhost:5050"
echo ""