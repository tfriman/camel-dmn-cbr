#!/usr/bin/env sh

# echo '{"priceInCents": 10, "loyaltyLevel": 1}' | http :8080/cbr

curl -X POST -H "content-type: application/json" -d '{"priceInCents": 10, "loyaltyLevel": 2}' localhost:8080/cbr
