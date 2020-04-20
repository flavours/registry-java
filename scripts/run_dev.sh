#!/usr/bin/env bash

set -o errexit
set -o nounset

./gradlew --stop --quiet
./gradlew --continuous --no-daemon --quiet build &
./gradlew --no-daemon bootRun
