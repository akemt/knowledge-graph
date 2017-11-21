#!/bin/bash

JARS="ivy dist lib"

# Build jars
ant stage $@ && \

# Assemble algmarket.zip
zip -1 -FS -r algmarket.zip $JARS

EXIT_CODE=$?

# Clean up
ant clean

exit $EXIT_CODE
