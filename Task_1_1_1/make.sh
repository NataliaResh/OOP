#!/bin/bash

javac -d ./build/classes/java/main ./src/main/java/*.java
javadoc -d ./docs -sourcepath ./src/main/java -subpackages heapsort
