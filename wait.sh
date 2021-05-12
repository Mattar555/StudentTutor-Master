#!/bin/sh

while ! nc -z student-tutor-db 3306 ; do
    echo "Waiting for MySQL Server"
    sleep 5
done

java -jar app.jar
