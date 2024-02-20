#!/bin/bash

# Compile Java source files and create JAR file
javac -d build/classes src/main/java/*.java
jar cfe build/YourProject.jar YourMainClass -C build/classes .



