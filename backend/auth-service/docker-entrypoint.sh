#!/bin/sh
set -e

# If JWT_SECRET is set in the environment (e.g. EB / ECS), prefer it.
if [ -n "${JWT_SECRET}" ]; then
  :
elif [ -f /run/secrets/jwt_secret ]; then
  # read file and strip CR/LF and surrounding whitespace to avoid illegal base64 characters
  export JWT_SECRET="$(tr -d '\r\n' < /run/secrets/jwt_secret | sed 's/^\s*//;s/\s*$//')"
fi

exec java -jar /auth-service.jar
