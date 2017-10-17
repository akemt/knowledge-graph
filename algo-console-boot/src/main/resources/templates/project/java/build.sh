#!/bin/bash

JARS="ivy dist lib"

# Build jars
ant stage $@ && \

# Assemble beyondalgo.zip
zip -1 -FS -r beyondalgo.zip $JARS

EXIT_CODE=$?

# Clean up
ant clean

exit $EXIT_CODE
