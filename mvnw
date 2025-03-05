#!/bin/sh
MAVEN_OPTS="-Xmx1024m -XX:MaxPermSize=256m"
export MAVEN_OPTS

# Execute Maven with the provided arguments
exec ./mvnw "$@"