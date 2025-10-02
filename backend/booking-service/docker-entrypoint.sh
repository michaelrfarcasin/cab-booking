#!/bin/sh
set -e

# Prefer environment variable if present (EB/ECS). Otherwise read secret mounted at /run/secrets/jwt_secret
if [ -n "${JWT_SECRET}" ]; then
  :
elif [ -f /run/secrets/jwt_secret ]; then
  # read file and strip CR/LF and surrounding whitespace to avoid illegal base64 characters
  export JWT_SECRET="$(tr -d '\r\n' < /run/secrets/jwt_secret | sed 's/^\s*//;s/\s*$//')"
fi

exec java -jar /booking-service.jar
