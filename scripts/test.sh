#!/bin/bash

# Set the classpath for JUnit and your compiled classes
CLASSPATH="lib/junit-platform-console-standalone-1.8.2.jar:build/classes"

# Set the package and class name for your JUnit tests
TEST_CLASS="com.example.YourTestClass"

# Run unit tests using JUnit
java -cp "$CLASSPATH" org.junit.platform.console.ConsoleLauncher --scan-classpath --select-class "$TEST_CLASS"
