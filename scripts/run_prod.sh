#!/usr/bin/env bash

# Launch script for the packaged Java app.

set -o errexit
set -o nounset

java -jar app.jar
