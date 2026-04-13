#!/usr/bin/env sh

REPO_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
MAVEN_HOME="$REPO_DIR/.tools/apache-maven-3.9.9"

if [ ! -x "$MAVEN_HOME/bin/mvn" ]; then
  echo "Maven distribution not found under .tools/apache-maven-3.9.9"
  exit 1
fi

if [ -z "$JAVA_HOME" ] && [ -n "$USERPROFILE" ] && [ -x "$USERPROFILE/.jdks/openjdk-26/bin/java.exe" ]; then
  JAVA_HOME="$USERPROFILE/.jdks/openjdk-26"
  export JAVA_HOME
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
