#!/bin/sh
# Wrapper script. The real gradle-wrapper.jar is generated once by: gradle wrapper
# On GitHub Actions gradle is installed by setup-gradle action, so ./gradlew is not needed there.
DIR="$(cd "$(dirname "$0")" && pwd)"
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo "Gradle is not installed. Install Gradle 8.10+ or run 'gradle wrapper' once to generate the wrapper jar."
exit 1
