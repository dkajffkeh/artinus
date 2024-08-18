#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 기동 포트번호를 힙력 해주세요 ex) 18000"
  exit 1
fi

PORT=$1

echo "---Build user-app---"

./gradlew clean build

java -jar -Dserver.port="$PORT" ./build/libs/user-app-0.0.1.jar

