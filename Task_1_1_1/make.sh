#!/bin/bash

javac -d ./build/classes/main/java/heapsort ./src/main/java/heapsort/*.java
javadoc -d ./docs -sourcepath ./src/main/java -subpackages heapsort
java ./build/classes/main/java/heapsort/Main
