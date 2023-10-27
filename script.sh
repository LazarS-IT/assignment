#!/bin/bash

# Build WAR file using gradle
./gradlew clean build

SOURCE="./build/libs/assignment-1.0.0.war"
DEST="./assignment.war"

# Check if build was successful.
if [ $? -eq 0 ]; then
  echo "WAR file build successfully."
  cp "$SOURCE" "$DEST"
  echo "Press any button to continue"
  read user_input
else
  echo "WAR file build failed."
  read user_input
fi