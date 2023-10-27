#!/bin/bash

# Build WAR file using gradle
./gradlew clean build

# Check if build was successful.
if [ $? -eq 0 ]; then
  echo "WAR file build successfully."

  docker build -t assignemntapp .
  docker run -p 8080:8080 assignemntapp

  echo "Press any button to continue"
  read user_input
else
  echo "WAR file build failed."
  read user_input
fi